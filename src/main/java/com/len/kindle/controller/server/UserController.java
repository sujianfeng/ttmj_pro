package com.len.kindle.controller.server;


import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.User;
import com.len.kindle.service.UserService;
import com.len.kindle.util.CloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, String code) {

        log.info("登录用户：" + username);
        log.info("登录密码：" + password);
        log.info("登录验证码：" + code);

        if (!CloudUtil.checkParams(username, password, code)) {
            return PageEnum.PAGE_LOGIN.getPage();
        }


        if (!code.equals(request.getSession().getAttribute(Constant.CODE_KEY))) {
            request.setAttribute("errMsg", "验证码错误");
            return PageEnum.PAGE_LOGIN.getPage();

        }

        User user = userService.login(username, password);
        if (user == null) {
            request.setAttribute("errMsg", "用户名或密码错误");
            return PageEnum.PAGE_LOGIN.getPage();
        }
        request.getSession().setAttribute(Constant.USER_KEY, user);
        return PageEnum.PAGE_INDEX.getPage();

    }
}
