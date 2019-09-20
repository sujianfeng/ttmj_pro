package com.len.kindle.controller.server.useranalysis;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.useranalysis.UserAnalysisService;
import com.len.kindle.util.CloudUtil;
import com.len.kindle.vo.GameVo;
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
 */
@Slf4j
@Controller
@RequestMapping("/server/ua/playcards")
public class PlayCardsController {
    @Autowired
    private UserAnalysisService userAnalysisService;


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
        List<GameVo> gameVoList = userAnalysisService.getGameVoList(condition);

        //页面数据存储
        request.setAttribute(Constant.DATA_SUMMARY_LIST_KEY, gameVoList);
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_UA_PLAYCARDS.getPage();
    }
}
