package com.len.kindle.controller.server;

import com.len.kindle.config.enums.PageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author len
 * @date 2016/12/15
 */
@Slf4j
@Controller
@RequestMapping("/server")
public class RedirectController {

    @RequestMapping("/{url}.html")
    public String redirectUrl(@PathVariable("url") String url) {
        log.info("redirect to :" + url);
        if (url.equals(PageEnum.PAGE_LOGIN.getPage())) {

        }
        return url;
    }
}
