package com.len.kindle.controller.server.productreport;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.productreport.ProductReportService;
import com.len.kindle.util.CloudUtil;
import com.len.kindle.vo.OutputVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author sujianfeng
 * @date 2018/11/3 11:17 AM
 */
@Slf4j
@Controller
@RequestMapping("/server/pr/output")
public class OutputController {

    @Autowired
    private ProductReportService productReportService;


    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

        //初始化查询条件
        condition = condition == null ? new String[]{sdf1.format(new Date()), sdf2.format(new Date()), Constant.ALL} : condition;
        CloudUtil.logCondition(condition);
        List<OutputVo> chanchuVoList = productReportService.getChanchuVoList(condition);

        //页面数据存储
        request.setAttribute(Constant.DATA_SUMMARY_LIST_KEY, chanchuVoList);
        request.setAttribute(Constant.CONDITION_KEY, condition);

        return PageEnum.PAGE_PR_OUTPUT.getPage();
    }

}
