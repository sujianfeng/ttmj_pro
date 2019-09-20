package com.len.kindle.controller.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.len.kindle.bean.ChargeInfoBean;
import com.len.kindle.bean.PapPayNotifyModel;
import com.len.kindle.bean.ResultBean;
import com.len.kindle.bean.VivoPraPayResultBean;
import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.OrderStatus;
import com.len.kindle.config.enums.PayType;
import com.len.kindle.config.enums.Sdk;
import com.len.kindle.entity.SdkOrder;
import com.len.kindle.service.SdkOrderService;
import com.len.kindle.util.CloudUtil;
import com.len.kindle.util.FantasticUtil;
import com.len.kindle.util.HttpClientHelper;
import com.len.kindle.util.MD5Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.len.kindle.config.Constant.TRADE_SUCCESS;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/sdk/order")
public class SdkOrderController {

    @Value("${url.pay.result}")
    private String url_pay_result;

    @Value("${sdk.vivo.CpID}")
    private String vivo_cpid;
    @Value("${sdk.vivo.Appkey}")
    private String vivo_key;
    @Value("${sdk.vivo.AppID}")
    private String vivo_appid;
    @Value("${url.sdk.notify.vivo}")
    private String vivo_notify_url;
    @Value("${sdk.vivo.prepay}")
    private String vivo_prepay_url;


    @Autowired
    private SdkOrderService sdkOrderService;

    @RequestMapping("/notify")
    @ResponseBody
    public String notify(HttpServletRequest request, HttpServletResponse response) {
        log.info("收到的数据" + request.getQueryString());
        String body = CloudUtil.getRequestString(request);
        log.info("收到的body:" + body);

        SdkOrder sdkOrder = SdkOrder.builder()
                .sdkOrderId(request.getParameter("notifyId"))
                .orderId(request.getParameter("partnerOrder"))
                .productName(request.getParameter("productName"))
                .productDesc(request.getParameter("productDesc"))
                .price(Integer.parseInt(request.getParameter("price")))
                .count(Integer.parseInt(request.getParameter("count")))
                .attach(request.getParameter("attach"))
                .sign(request.getParameter("sign"))
                .orderStatus(OrderStatus.SUCCESS.getStatus())
                .orderTime(new Date())
                .sdkId(Sdk.OPPO.getId())
                .createTime(new Date())
                .payType(PayType.UNKNOWN.getPayType())
                .build();

        log.info(sdkOrder.toString());

        saveAndSendOrder(sdkOrder);


        return buildResultMsg(Constant.RESULT_OK, "");
    }

    @RequestMapping("/vivo_prepay")
    @ResponseBody
    public String vivo_prepay(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "orderTime") String orderTime,
                              @RequestParam(name = "orderAmount") String orderAmount,
                              @RequestParam(name = "orderTitle") String orderTitle,
                              @RequestParam(name = "orderDesc") String orderDesc,
                              @RequestParam(name = "extInfo") String extInfo) {
        log.info("收到的数据" + request.getQueryString());
        String body = CloudUtil.getRequestString(request);
        log.info("收到的body:" + body);

        String cpOrderNumber = CloudUtil.getOrderId();

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("version", Constant.SDK_VIVO_VERSION);
        paramMap.put("cpId", vivo_cpid);
        paramMap.put("appId", vivo_appid);
        paramMap.put("cpOrderNumber", cpOrderNumber);
        paramMap.put("notifyUrl", vivo_notify_url);
        paramMap.put("orderTime", orderTime);
        paramMap.put("orderAmount", orderAmount);
        paramMap.put("orderTitle", orderTitle);
        paramMap.put("orderDesc", orderDesc);
        paramMap.put("extInfo", cpOrderNumber);

        String signature = CloudUtil.signParam(paramMap, MD5Tool.md5Encryption(vivo_key));
        paramMap.put("signMethod", Constant.SDK_VIVO_SIGNMETHOD);
        paramMap.put("signature", signature);

        List<NameValuePair> nameValuePairList = CloudUtil.map2NameValuePairList(paramMap);
        try {
            String vivoPrePayResult = HttpClientHelper.doPost(vivo_prepay_url, nameValuePairList);
            ObjectMapper mapper = new ObjectMapper();
            VivoPraPayResultBean vivoPraPayResultBean = mapper.readValue(vivoPrePayResult, VivoPraPayResultBean.class);
            vivoPraPayResultBean.setCpOrderNumer(cpOrderNumber);

            SdkOrder sdkOrder = SdkOrder.builder()
                    .attach(extInfo).sdkOrderId(vivoPraPayResultBean.getOrderNumber()).orderStatus(OrderStatus.CREATE.getStatus()).orderId(cpOrderNumber)
                    .price(Integer.parseInt(orderAmount)).productDesc(orderDesc).productId(vivo_appid).createTime(new Date())
                    .productName(orderTitle).payType(PayType.UNKNOWN.getPayType()).sdkId(Sdk.VIVO.getId()).build();

            sdkOrderService.saveSdkOrder(sdkOrder);

            return mapper.writeValueAsString(vivoPraPayResultBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/vivo_notify")
    @ResponseBody
    public String vivo_notify(HttpServletRequest request, HttpServletResponse response) {
        log.info("收到的数据" + request.getQueryString());
        String body = CloudUtil.getRequestString(request);
        log.info("收到的body:" + body);

        String respCode = request.getParameter("respCode");
        if (StringUtils.isNotBlank(respCode) && "200".equals(respCode)) {

            SdkOrder sdkOrder = sdkOrderService.getSdkOrderByCpOrderId(request.getParameter("cpOrderNumber"));

            if (sdkOrder != null) {

                if (sdkOrder.getOrderStatus() == OrderStatus.SUCCESS.getStatus()) {
                    return Constant.RESULT_SUCCESS.toLowerCase();
                }

                try {
                    sdkOrder.setSdkOrderId(request.getParameter("orderNumber"));
                    sdkOrder.setOrderStatus("0000".equals(request.getParameter("tradeStatus")) ? 1 : 2);
                    sdkOrder.setOrderTime(FantasticUtil.TL_SDF_2_S.get().parse(request.getParameter("payTime")));

                    sdkOrder = sdkOrderService.saveSdkOrder(sdkOrder);
                    if (sdkOrder.getOrderStatus() == OrderStatus.SUCCESS.getStatus()) {
                        sendPayResult(sdkOrder);
                    }
                    return Constant.RESULT_SUCCESS.toLowerCase();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return Constant.RESULT_FAIL.toLowerCase();
    }

    @RequestMapping("/yyb")
    @ResponseBody
    public String yyb(HttpServletRequest request, HttpServletResponse response) {
        log.info("收到的数据" + request.getQueryString());
        String body = CloudUtil.getRequestString(request);
        log.info("收到的body:" + body);

        return "{\"ret\":0,\"msg\":\"OK\"}";
    }


    @RequestMapping("/jy_notify")
    @ResponseBody
    public String jy_notify(HttpServletRequest request, HttpServletResponse response) {
        log.info("收到的数据" + request.getQueryString());
        String body = CloudUtil.getRequestString(request);
        JSONObject bodyJson = new JSONObject(body);

        JSONObject dataJson = bodyJson.getJSONObject("data");


        try {
            SdkOrder sdkOrder = SdkOrder.builder()
                    .sdkOrderId(dataJson.getString("tradeId"))
                    .orderId(dataJson.getString("orderId"))
                    .productId(dataJson.getString("gameId"))
                    .price((int) (100 * Double.parseDouble(dataJson.getString("amount"))))
                    .attach(dataJson.getString("attachInfo"))
                    .sign(bodyJson.getString("sign"))
                    .sdkId(Sdk.JIUYOU.getId())
                    .orderStatus("S".equals(dataJson.getString("orderStatus")) ? OrderStatus.SUCCESS.getStatus() : OrderStatus.FAIL.getStatus())
                    .orderMsg(dataJson.getString("failedDesc"))
                    .orderTime(FantasticUtil.TL_SDF_2_S.get().parse(dataJson.getString("tradeTime")))
                    .createTime(new Date())
                    .build();

            switch (dataJson.getString("payType")) {
                case "999":
                    sdkOrder.setPayType(PayType.UC.getPayType());
                    break;
                case "305":
                    sdkOrder.setPayType(PayType.CMCC.getPayType());
                    break;
                case "306":
                    sdkOrder.setPayType(PayType.UNICOM.getPayType());
                    break;
                case "307":
                    sdkOrder.setPayType(PayType.TELECOM.getPayType());
                    break;
                default:
                    sdkOrder.setPayType(PayType.UNKNOWN.getPayType());
                    break;

            }
            saveAndSendOrder(sdkOrder);


            return Constant.RESULT_SUCCESS;


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return Constant.RESULT_SUCCESS;
    }

    @RequestMapping("/huawei_notify")
    @ResponseBody
    public ResultBean huawei_notify(HttpServletRequest request, HttpServletResponse response) {

        log.info("收到的数据" + request.getQueryString());
        String body = CloudUtil.getRequestString(request);
        log.info("收到的数据" + body);

        SdkOrder sdkOrder = SdkOrder.builder().attach(request.getParameter("extReserved")).createTime(new Date())
                .count(1).orderId(request.getParameter("requestId"))
                .orderStatus(request.getParameter("result").equals("0") ? OrderStatus.SUCCESS.getStatus() : OrderStatus.FAIL.getStatus())
                .productName(request.getParameter("productName")).price((int) (100 * Double.valueOf(request.getParameter("amount"))))
                .sdkId(Sdk.HUAWEI.getId()).sdkOrderId(request.getParameter("orderId"))
                .payType(PayType.UNKNOWN.getPayType()).build();

        saveAndSendOrder(sdkOrder);


        return ResultBean.builder().result(0).build();
    }

    @RequestMapping("/xiaomi_notify")
    @ResponseBody
    public ResultBean xiaomi_notify(HttpServletRequest request, HttpServletResponse response) {

        log.info("收到的数据" + request.getQueryString());
        String body = CloudUtil.getRequestString(request);
        log.info("收到的数据" + body);

        SdkOrder sdkOrder = SdkOrder.builder().attach(request.getParameter("cpUserInfo")).createTime(new Date())
                .count(1).orderId(request.getParameter("cpOrderId"))
                .orderStatus(request.getParameter("orderStatus").equals("TRADE_SUCCESS") ? OrderStatus.SUCCESS.getStatus() : OrderStatus.FAIL.getStatus())
                .productName(request.getParameter("productName")).price(Integer.valueOf(request.getParameter("payFee")))
                .sdkId(Sdk.XIAOMI.getId()).sdkOrderId(request.getParameter("orderId")).productId(request.getParameter("appId"))
                .payType(PayType.UNKNOWN.getPayType()).build();

        saveAndSendOrder(sdkOrder);


        return ResultBean.builder().errcode(200).build();
    }

    @RequestMapping("/weixin_notify")
    @ResponseBody
    public String weixin_notify(HttpServletRequest request, HttpServletResponse response) {
        log.info("接收到微信数据同步");
        String notifyString = CloudUtil.getRequestString(request);
        log.info("微信数据：" + notifyString);
        try {
            JAXBContext context = JAXBContext.newInstance(PapPayNotifyModel.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PapPayNotifyModel papPayNotifyModel = (PapPayNotifyModel) unmarshaller.unmarshal(new StringReader(notifyString));
            if (papPayNotifyModel.getReturn_code().equals(Constant.RESULT_SUCCESS)
                    && papPayNotifyModel.getResult_code().equals(Constant.RESULT_SUCCESS)) {
                SdkOrder sdkOrder = SdkOrder.builder().attach(papPayNotifyModel.getAttach()).createTime(new Date())
                        .count(1).orderId(papPayNotifyModel.getOut_trade_no())
                        .orderStatus(OrderStatus.SUCCESS.getStatus())
                        .productName("").price(papPayNotifyModel.getTotal_fee())
                        .sdkId(Sdk.WEIXIN.getId()).sdkOrderId(papPayNotifyModel.getTransaction_id())
                        .productId(papPayNotifyModel.getAppid())
                        .payType(PayType.WECHAT.getPayType()).build();

                saveAndSendOrder(sdkOrder);
            }
        } catch (Exception e) {
            log.info("出现异常:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }


    @RequestMapping("/ali_notify")
    @ResponseBody
    public ResultBean ali_notify(HttpServletRequest request, HttpServletResponse response) {
        log.info("接收到支付宝数据同步");
        String notifyString = request.getQueryString();
        log.info("支付宝数据：" + notifyString);

        CloudUtil.getReqParams(request);

        String trade_status = request.getParameter("trade_status");
        if (TRADE_SUCCESS.equals(trade_status)) {
            SdkOrder sdkOrder = SdkOrder.builder().attach(request.getParameter("passback_params")).createTime(new Date())
                    .count(1).orderId(request.getParameter("out_trade_no"))
                    .orderStatus(OrderStatus.SUCCESS.getStatus())
                    .productName(request.getParameter("subject")).price((int) (Double.parseDouble(request.getParameter("total_amount")) * 100))
                    .sdkId(Sdk.ALIPAY.getId()).sdkOrderId(request.getParameter("trade_no"))
                    .productId(request.getParameter("app_id"))
                    .payType(PayType.ALI.getPayType()).build();

            saveAndSendOrder(sdkOrder);
        }


        return ResultBean.builder().errcode(200).build();
    }


    private void saveAndSendOrder(SdkOrder sdkOrder) {
        SdkOrder sdkOrderDb = sdkOrderService.getSdkOrder(sdkOrder.getSdkOrderId());
        if (sdkOrderDb == null) {
            sdkOrderService.saveSdkOrder(sdkOrder);
            log.info(sdkOrder.toString());
            if (sdkOrder.getOrderStatus() == 1) {
                sendPayResult(sdkOrder);
            }
        } else {
            log.info("order exist");
        }
    }


    private String buildResultMsg(String status, String desc) {
        return "result=" + status + "&resultMsg=" + desc;
    }

    /**
     * 发送数据到游戏服务器
     *
     * @param sdkOrder
     */
    private void sendPayResult(SdkOrder sdkOrder) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ChargeInfoBean chargeInfoBean = mapper.readValue(sdkOrder.getAttach(), ChargeInfoBean.class);
            chargeInfoBean.setOrderId(sdkOrder.getOrderId());
            chargeInfoBean.setChargeType(sdkOrder.getPayType());

            String body = mapper.writeValueAsString(chargeInfoBean);
            log.info(sdkOrder.getOrderId() + "发送到游戏服务器的数据:" + body);
            String result = HttpClientHelper.doPost(url_pay_result, null, Constant.UTF_8, body);
            log.info(sdkOrder.getOrderId() + "游戏服务器响应的数据" + result);
            if (StringUtils.isNotBlank(result)) {
                ResultBean resultBean = mapper.readValue(result, ResultBean.class);
                if (resultBean.getStatus() == 1) {
                    log.info(sdkOrder.getOrderId() + "数据发送成功");
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.info(sdkOrder.getOrderId() + "数据发送异常:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.info(sdkOrder.getOrderId() + "数据发送异常:" + e.getMessage());
        }


    }
}
