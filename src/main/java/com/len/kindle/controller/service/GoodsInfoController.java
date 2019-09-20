package com.len.kindle.controller.service;

import com.len.kindle.service.GoodsInfoService;
import com.len.kindle.util.CloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
public class GoodsInfoController {

    @Autowired
    private GoodsInfoService goodsInfoService;


    @GetMapping("/service/etres/img/{goodsId}.png")
    public void downloadHeader(@PathVariable Integer goodsId, HttpServletRequest request, HttpServletResponse response) {
        log.info("goodsId=" + goodsId);


        goodsInfoService.downloadFile(request, response, goodsId);
    }

    @GetMapping("/service/etres/notice/{filename}")
    public void downloadNotice(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) {
        //下发通知信息
        String fileUrl = "/data/notice/" + filename;

        try {
            CloudUtil.downloadFile(fileUrl, filename, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/service/etres/noticeHD/{filename}")
    public void downloadNoticeHD(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) {
        //下发通知信息
        String fileUrl = "/data/noticeHD/" + filename;

        try {
            CloudUtil.downloadFile(fileUrl, filename, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
