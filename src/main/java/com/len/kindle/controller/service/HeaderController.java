package com.len.kindle.controller.service;

import com.len.kindle.service.HeaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
public class HeaderController {

    @Autowired
    private HeaderService headerInfoService;

    @PostMapping("/service/fm/avatar/{dir}/{userid}.jpg")
    public void upLoadHeader(@PathVariable String dir, @PathVariable String userid, HttpServletRequest request, HttpServletResponse response) {
        log.info("dir=" + dir);
        log.info("userid=" + userid);

        Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String parameterName = (String) e.nextElement();
            log.info(parameterName + ": " + request.getHeader(parameterName));
        }

        headerInfoService.uploadFile(request, dir, userid);


    }

    @GetMapping("/service/avatar/{dir}/{userid}.jpg")
    public void downloadHeader(@PathVariable String dir, @PathVariable String userid, HttpServletRequest request, HttpServletResponse response) {
        log.info("dir=" + dir);
        log.info("userid=" + userid);

        Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String parameterName = (String) e.nextElement();
            log.info(parameterName + ": " + request.getHeader(parameterName));
        }
        headerInfoService.downloadFile(request, response, dir, userid);
    }


}
