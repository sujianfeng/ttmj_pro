package com.len.kindle.controller.server.operatingreport;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.operatingreport.RealTimePumpingService;
import com.len.kindle.util.CloudUtil;
import com.len.kindle.vo.PumpingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/or/realtimepumping")
public class RealTimePumpingController {

    @Autowired
    private RealTimePumpingService realTimePumpingService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       String[] condition) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

        //初始化查询条件
        condition = condition == null ? new String[]{sdf1.format(new Date()), sdf2.format(new Date())} : condition;
        CloudUtil.logCondition(condition);
        List<PumpingVo> pumpingVoList = realTimePumpingService.getPumpingVoList(condition);

        //页面数据存储
        request.setAttribute(Constant.DATA_SUMMARY_LIST_KEY, pumpingVoList);
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_OR_REALTIMEPUMPING.getPage();
    }
}
