package com.len.kindle.service;

import com.len.kindle.entity.HeaderInfo;
import com.len.kindle.repo.HeaderInfoRepo;
import com.len.kindle.util.CloudUtil;
import com.len.kindle.util.HttpClientHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sujianfeng
 */
@Slf4j
@Service
public class HeaderService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    private HeaderInfoRepo headerInfoRepo;

    public void uploadFile(HttpServletRequest request, String dir, String userid) {

        String file = uploadDir + userid + "_" + System.currentTimeMillis() + ".jpg";
        String uploadImgSize = request.getHeader("x-fl");
        String uploadImgTime = request.getHeader("x-lm");


        boolean isUpload = CloudUtil.uploadFile(request, file);

        if (isUpload) {
            //检查头像用户ID是否存在头像
            HeaderInfo headerInfo = headerInfoRepo.findByUserid(userid);
            if (headerInfo == null) {
                //第一次上传
                headerInfo = new HeaderInfo();
                headerInfo.setUserid(userid);
                headerInfo.setDir(dir);
            }

            if (StringUtils.isNotBlank(headerInfo.getUploadImg())) {
                //如果存在未审核的头像，需要删除之前上传的
                File oldFile = new File(headerInfo.getUploadImg());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            headerInfo.setUploadImg(file);
            headerInfo.setUploadImgSize(uploadImgSize);
            headerInfo.setUploadImgTime(uploadImgTime);
            headerInfo.setStatus(0);
            //前期自动审核通过
            headerInfo.setCurImg(file);
            headerInfo.setCurImgSize(uploadImgSize);
            headerInfo.setCurImgTime(uploadImgTime);


            headerInfoRepo.saveAndFlush(headerInfo);
        }
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String dir, String userid) {
        //检查头像用户ID是否存在头像
        HeaderInfo headerInfo = headerInfoRepo.findByUserid(userid);
        if (headerInfo == null) {
            return;
        }
        String curImgTime = headerInfo.getCurImgTime();
        String uploadImgTime = request.getHeader("If-Modified-Since");
        if (StringUtils.isBlank(uploadImgTime) || !curImgTime.equals(uploadImgTime)) {
            //下发头像信息
            String filename = userid + ".jpg";
            String fileUrl = headerInfo.getCurImg();
            if (StringUtils.isNotBlank(fileUrl)) {

                sendLog(userid, CloudUtil.getClientIp(request), fileUrl);

                try {
                    CloudUtil.downloadFile(fileUrl, filename, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Async
    public void sendLog(String user, String ip, String url) {

        String path = "http://120.24.53.66:9092/logSystem/service/logData";

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("user", user));
        list.add(new BasicNameValuePair("ip", ip));
        list.add(new BasicNameValuePair("url", url));

        try {
            HttpClientHelper.doPost(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
