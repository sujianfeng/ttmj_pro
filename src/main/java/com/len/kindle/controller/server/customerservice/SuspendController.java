package com.len.kindle.controller.server.customerservice;

import com.len.kindle.bean.UserBehaviorBean;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.customerservice.CustomerService;
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
 * @date 2018/11/3 11:17 AM
 */
@Slf4j
@Controller
@RequestMapping("/server/cs/suspend")
public class SuspendController {
    @Value("${url.restrict.user.behavior}")
    private String url_restrict_user_behavior;
    @Autowired
    private CustomerService customerService;


    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response) {

        return PageEnum.PAGE_CS_SUSPEND.getPage();
    }

    @RequestMapping("/fenhao")
    @ResponseBody
    public String fenhao(@RequestParam(name = "userId") Integer userId) {

        UserBehaviorBean userBehaviorBean = UserBehaviorBean.builder().userId(userId).silent(0).suspend(1).build();
        boolean result = CloudUtil.updateUserrBehavior(url_restrict_user_behavior, userBehaviorBean);
        return CloudUtil.buildAjaxOperationJson(result, result ? "更新成功" : "更新失败");
    }

    @RequestMapping("/jiefen")
    @ResponseBody
    public String jiefen(@RequestParam(name = "userId") Integer userId) {

        UserBehaviorBean userBehaviorBean = UserBehaviorBean.builder().userId(userId).silent(0).suspend(-1).build();
        boolean result = CloudUtil.updateUserrBehavior(url_restrict_user_behavior, userBehaviorBean);
        return CloudUtil.buildAjaxOperationJson(result, result ? "更新成功" : "更新失败");
    }
}
