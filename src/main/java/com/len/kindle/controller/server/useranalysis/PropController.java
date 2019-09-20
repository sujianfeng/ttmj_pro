package com.len.kindle.controller.server.useranalysis;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.PropertyRecord;
import com.len.kindle.service.useranalysis.UserAnalysisService;
import com.len.kindle.util.CloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/ua/prop")
public class PropController {

    @Autowired
    private UserAnalysisService userAnalysisService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

        //初始化查询条件
        condition = condition == null ? new String[]{sdf1.format(new Date()), sdf2.format(new Date()), "", ""} : condition;
        CloudUtil.logCondition(condition);

        Page<PropertyRecord> propertyRecordPage = userAnalysisService.getPropertyInfo(condition, pageable);

        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, propertyRecordPage);

        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_UA_PROP.getPage();
    }
}
