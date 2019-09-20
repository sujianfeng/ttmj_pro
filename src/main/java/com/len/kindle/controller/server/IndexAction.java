package com.len.kindle.controller.server;

import com.len.kindle.config.enums.PageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author len
 * @date 2016/12/20
 */
@Slf4j
@Controller
public class IndexAction {

    @RequestMapping("/")
    public String index() {
        log.info("跳转到登录页");
        return PageEnum.PAGE_LOGIN.getPage();
    }
}
