package com.len.kindle.service;

import com.len.kindle.entity.GoodsInfo;
import com.len.kindle.repo.GoodsInfoRepo;
import com.len.kindle.util.CloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author sujianfeng
 */
@Slf4j
@Service
public class GoodsInfoService {


    @Autowired
    private GoodsInfoRepo goodsInfoRepo;

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
        //检查头像用户ID是否存在头像
        GoodsInfo goodsInfo = goodsInfoRepo.findGoodsInfoByGoodsId(goodsId);
        if (goodsInfo == null) {
            return;
        }
        String modifyTime = goodsInfo.getModifyTime();
        String uploadImgTime = request.getHeader("If-Modified-Since");
        log.info("客户端上传的If-Modified-Since=" + uploadImgTime);
        String fileUrl = goodsInfo.getPicPath();
        File file = new File(fileUrl);
        long fileModifyTime = file.lastModified() / 1000;
        log.info("服务端文件大小fileModifyTime=" + fileModifyTime);

        if (StringUtils.isBlank(modifyTime) || !modifyTime.equals(String.valueOf(fileModifyTime))) {
            goodsInfo.setModifyTime(String.valueOf(fileModifyTime));
            goodsInfoRepo.saveAndFlush(goodsInfo);
        }

        if (StringUtils.isBlank(uploadImgTime) || !uploadImgTime.equals(String.valueOf(fileModifyTime))) {
            //下发头像信息
            String filename = goodsId + ".png";
            if (StringUtils.isNotBlank(fileUrl)) {
                try {
                    CloudUtil.downloadFile(fileUrl, filename, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
