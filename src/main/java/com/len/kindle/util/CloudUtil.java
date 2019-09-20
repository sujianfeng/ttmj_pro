package com.len.kindle.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.len.kindle.bean.ResultBean;
import com.len.kindle.bean.UserBehaviorBean;
import com.len.kindle.bean.UserInfoBean;
import com.len.kindle.config.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sujianfeng
 */
@Slf4j
public class CloudUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

    /**
     * 获取http请求中的数据流
     *
     * @param request
     * @return 数据流
     */
    public static String getRequestString(HttpServletRequest request) {
        String dataString = null;
        try {
            StringBuffer requestString = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(request.getInputStream(), "UTF-8"));
            String tmpString = "";
            while ((tmpString = reader.readLine()) != null) {
                requestString.append(tmpString);
            }
            reader.close();
            dataString = requestString.toString().trim();
        } catch (IOException e) {
            log.error("exception:" + e.getMessage());
            e.printStackTrace();
        }
        return dataString;
    }

    /**
     * 判断请求是否超时
     *
     * @param timestamp 请求时间戳
     * @return false:未超时 true:超时
     */
    public static boolean isOverTime(String timestamp) {
        try {
            long requesTimestamp = Long.parseLong(timestamp);
            log.info("request_time:"
                    + new Date(requesTimestamp).toString());
            long currentTimestamp = System.currentTimeMillis();
            log.info("current_time:"
                    + new Date(currentTimestamp).toString());
            long diffTimestamp = 5 * 60 * 1000;

            long timediff = (currentTimestamp - requesTimestamp) > 0 ? (currentTimestamp - requesTimestamp)
                    : (requesTimestamp - currentTimestamp);

            log.info("timediff:" + timediff);
            if (timediff > diffTimestamp) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("exception:" + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 按照字典升序拼接连接字符串
     *
     * @param params 需要拼接的字符串map
     * @return 拼接后的字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {

            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {
                // 拼接时，不包括最后一个&字符
                prestr.append(key + "=" + value);
            } else {
                prestr.append(key + "=" + value + "&");
            }
        }
        return prestr.toString();
    }

    /**
     * 校验同方参数加密
     *
     * @param map
     * @param key
     * @return
     */
    public static String signParam(Map<String, String> map, String key) {
        log.info("签名key:" + key);
        String linkString = createLinkString(map);
        String source = linkString + "&" + key;
        // String source = linkString + "&app_secret=" + key;
        log.info("加密前字符串:" + source);
        String signString = MD5Tool.md5Encryption(source);
        log.info("加密后字符串:" + signString);
        return signString;
    }

    /**
     * 创建订单号
     *
     * @return 基于当前日期的订单号
     */
    public static String getOrderId() {
        StringBuffer result = new StringBuffer();
        result.append(sdf.format(new Date()));
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            result.append(r.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 获取几个月后的日期
     *
     * @param date     初始日期
     * @param duration 月份间隔
     * @return 几个月后的日期
     */
    public static Date getMonthLater(Date date, int duration) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, duration);
        // 日期加duration个月
        Date laterDate = rightNow.getTime();
        return laterDate;
    }

    /**
     * 获取客户端IP
     *
     * @param request
     * @return 客户端IP
     */
    public static String getClientIp(HttpServletRequest request) {
        String internerIp = request.getRemoteAddr();
        if (StringUtils.isBlank(internerIp)) {
            internerIp = request.getRemoteHost();
        }
        return internerIp;
    }

    /**
     * 输出json到客户端
     *
     * @param response
     * @param outputjson
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void writeJsonResult(HttpServletResponse response,
                                       JSONObject outputjson) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                response.getOutputStream(), "utf-8"));
        out.print(outputjson);
        out.flush();
        out.close();
    }

    /**
     * 输出字符串到客户端
     *
     * @param response
     * @param result
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void writeStringResult(HttpServletResponse response,
                                         String result) throws IOException {
        log.info("写入到客户端的数据：" + result);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                response.getOutputStream(), "utf-8"));
        out.print(result);
        out.flush();
        out.close();
    }


    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return 1;
        }
        String[] versionArray1 = version1.split("\\.");
        // 注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        // 取最小长度值
        int diff = 0;
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                // 先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            // 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 检查参数是否完整
     *
     * @param params
     * @return
     */
    public static boolean checkParams(String... params) {

        for (String param : params) {
            if (StringUtils.isBlank(param)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回保存信息
     *
     * @param bool
     * @param msg
     */
    public static String buildAjaxOperationJson(boolean bool, String msg) {
        JSONObject resultJson = new JSONObject();
        if (bool) {
            resultJson.put("isCorrect", 1);
            resultJson.put("msg", msg);
        } else {
            resultJson.put("isCorrect", 2);
            resultJson.put("msg", msg);
        }
        return resultJson.toString();
    }

    /**
     * 获取运营商名称
     *
     * @param oid
     * @return
     */
    public static String getOName(int oid) {
        String oname = "移动";
        switch (oid) {
            case 1:
                oname = "移动";
                break;
            case 2:
                oname = "联通";
                break;
            case 3:
                oname = "电信";
                break;
            case 4:
                oname = "支付宝";
                break;
            case 5:
                oname = "微信";
                break;
            case 6:
                oname = "宽带";
                break;
            default:
        }
        return oname;
    }

    /**
     * 记录查询条件
     *
     * @param condition
     */
    public static void logCondition(String[] condition) {
        StringBuffer conditionBuff = new StringBuffer();
        for (String con : condition) {
            conditionBuff.append(con + ",");
        }
        log.info("查询条件:" + conditionBuff);
    }


    /**
     * 创建免校验ssl连接
     *
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        // 信任所有
                        @Override
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) {
                            return true;
                        }

                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * 输入错误信息
     *
     * @param resultMsg
     * @return
     */
    public static String buildErrorResponse(String resultMsg) {
        try {
            JSONObject json = new JSONObject();
            json.put("returnCode", 0);
            json.put("resultCode", 0);
            json.put("resultMsg", resultMsg);
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件上传
     *
     * @param request
     * @param file
     * @return
     */
    public static boolean uploadFile(HttpServletRequest request, String file) {
        try {
            InputStream in = request.getInputStream();
            OutputStream out = new FileOutputStream(file);
            //创建一个缓冲区
            byte[] buffer = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while ((len = in.read(buffer)) > 0) {
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录当中
                out.write(buffer, 0, len);
            }
            //关闭输入流
            in.close();
            //关闭输出流
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传失败");
            return false;
        }
        log.error("文件上传成功");
        return true;
    }

    /**
     * 文件下载
     *
     * @param fileUrl
     * @param filename
     * @param response
     * @throws IOException
     */
    public static void downloadFile(String fileUrl, String filename, HttpServletResponse response) throws IOException {
        /* 读取文件 */
        File file = new File(fileUrl);
        /* 如果文件存在 */
        if (file.exists()) {
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename='" + filename + "'");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            if (fileLength != 0) {
                InputStream inStream = new FileInputStream(file);
                byte[] buf = new byte[4096];
                ServletOutputStream servletOS = response.getOutputStream();
                int readLength;
                while (((readLength = inStream.read(buf)) != -1)) {
                    servletOS.write(buf, 0, readLength);
                }
                servletOS.flush();
                if (servletOS != null) {
                    servletOS.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            }
        }
    }

    /**
     * 更新用户信息
     *
     * @param userInfoBean
     * @return
     */
    public static boolean updateUserInfo(String url, UserInfoBean userInfoBean) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String userInfoString = mapper.writeValueAsString(userInfoBean);
            String result = HttpClientHelper.doPost(url, null, Constant.UTF_8, userInfoString);
            if (StringUtils.isNotBlank(result)) {
                ResultBean resultBean = mapper.readValue(result, ResultBean.class);
                if (resultBean.getStatus() == 0) {
                    return true;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUserrBehavior(String url, UserBehaviorBean userBehaviorBean) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String userBehaviorString = mapper.writeValueAsString(userBehaviorBean);
            String result = HttpClientHelper.doPost(url, null, Constant.UTF_8, userBehaviorString);
            if (StringUtils.isNotBlank(result)) {
                ResultBean resultBean = mapper.readValue(result, ResultBean.class);
                if (resultBean.getStatus() == 0) {
                    return true;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * map转参数列表
     *
     * @param paramMap
     * @return
     */
    public static List<NameValuePair> map2NameValuePairList(Map<String, String> paramMap) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (!paramMap.isEmpty()) {
            for (String key : paramMap.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            return nameValuePairs;
        }
        return null;
    }

    /**
     * @param request
     * @return 本接口Content-Type是：application/x-www-form-urlencoded，对所有参数，会自动进行编码，接收端收到消息也会自动根据Content-Type进行解码。
     * 同时，接口中参数在发送端并没有进行单独的URLEncode (sign和extReserved、sysReserved参数除外)，所以，在接收端根据Content-Type解码后，即为原始的参数信息。
     * 但是HttpServletRequest的getParameter()方法会对指定参数执行隐含的URLDecoder.decode(),所以，相应参数中如果包含比如"%"，就会发生错误。
     * 因此，我们建议通过如下方法获取原始参数信息。
     * <p>
     * 注：使用如下方法必须在原始ServletRequest未被处理的情况下进行，否则无法获取到信息。比如，在Struts情况，由于struts层已经对参数进行若干处理，
     * http中InputStream中其实已经没有信息，因此，本方法不适用。要获取原始信息，必须在原始的，未经处理的ServletRequest中进行。
     */
    public static Map<String, Object> getValue(HttpServletRequest request) {

        String line = null;
        StringBuffer sb = new StringBuffer();
        try {
            request.setCharacterEncoding("UTF-8");

            InputStream stream = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
            log.info("The original data is : " + sb.toString());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        String str = sb.toString();
        Map<String, Object> valueMap = new HashMap<String, Object>();
        if (null == str || "".equals(str)) {
            return valueMap;
        }

        String[] valueKey = str.split("&");
        for (String temp : valueKey) {
            String[] single = temp.split("=");
            valueMap.put(single[0], single[1]);
        }
        log.info("The parameters in map are : " + valueMap);

        //接口中，如下参数sign和extReserved是URLEncode的，所以需要decode，其他参数直接是原始信息发送，不需要decode
        try {
            String sign = (String) valueMap.get("sign");
            String extReserved = (String) valueMap.get("extReserved");
            String sysReserved = (String) valueMap.get("sysReserved");

            if (null != sign) {
                sign = URLDecoder.decode(sign, "utf-8");
                valueMap.put("sign", sign);
            }
            if (null != extReserved) {
                extReserved = URLDecoder.decode(extReserved, "utf-8");
                valueMap.put("extReserved", extReserved);
            }

            if (null != sysReserved) {
                sysReserved = URLDecoder.decode(sysReserved, "utf-8");
                valueMap.put("sysReserved", sysReserved);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return valueMap;
    }

    public static Map<String, String> getReqParams(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<String, String>();
        Enumeration<String> rnames = request.getParameterNames();
        for (Enumeration<String> e = rnames; e.hasMoreElements(); ) {
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            log.info("paramMap:" + thisName + "=" + thisValue);
            paramMap.put(thisName, thisValue);
        }
        return paramMap;
    }

}
