package com.len.kindle.controller.server.backtransaction;

import com.len.kindle.bean.UserInfoBean;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.backtransaction.BackTransactionService;
import com.len.kindle.util.CloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sujianfeng
 * @date 2018/11/3 11:15 AM
 */
@Slf4j
@Controller
@RequestMapping("/server/bt/gold")
public class GoldController {

    @Value("${url.userinfo.update}")
    private String url_userinfo_update;
    @Autowired
    private BackTransactionService backTransactionService;


    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response) {

        return PageEnum.PAGE_BT_GOLD.getPage();
    }

    @RequestMapping("/saveGold")
    @ResponseBody
    public String saveGold(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "gold") Integer gold) {
        log.info("userID:{}", userId);
        log.info("gold:{}", gold);
        UserInfoBean userInfoBean = UserInfoBean.builder().userId(userId).gem(0).et(0).gold(gold).ts(0).zd(0).build();
        boolean result = CloudUtil.updateUserInfo(url_userinfo_update, userInfoBean);
        return CloudUtil.buildAjaxOperationJson(result, result ? "更新成功" : "更新失败");
    }

    @RequestMapping("/saveProp")
    @ResponseBody
    public String saveProp(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "ts", defaultValue = "0") Integer ts, @RequestParam(name = "zd", defaultValue = "0") Integer zd) {
        log.info("userID:{}", userId);
        log.info("ts:{}", ts);
        log.info("zd:{}", zd);
        UserInfoBean userInfoBean = UserInfoBean.builder().userId(userId).gem(0).et(0).gold(0).ts(ts).zd(zd).build();
        boolean result = CloudUtil.updateUserInfo(url_userinfo_update, userInfoBean);
        return CloudUtil.buildAjaxOperationJson(result, result ? "更新成功" : "更新失败");
    }
}
