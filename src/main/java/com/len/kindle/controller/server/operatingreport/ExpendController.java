package com.len.kindle.controller.server.operatingreport;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.operatingreport.ExpendService;
import com.len.kindle.util.CloudUtil;
import com.len.kindle.vo.ExpendVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/or/expend")
public class ExpendController {

    @Autowired
    private ExpendService expendService;

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
        condition = condition == null ? new String[]{sdf1.format(new Date()), sdf2.format(new Date()), ""} : condition;
        CloudUtil.logCondition(condition);

        //Page<PropertyExpend> expendPage = expendService.findAll(condition, pageable);
        //List<PropertyExpend> propertyExpendList = null;
        //try {
        //    propertyExpendList = expendService.getCurExpendList();
        //} catch (ParseException e) {
        //    e.printStackTrace();
        //}
        //
        ////页面数据存储
        //request.setAttribute(Constant.DATA_SUMMARY_LIST_KEY, propertyExpendList);
        ////页面数据存储
        //request.setAttribute(Constant.PAGE_KEY, expendPage);

        List<ExpendVo> list = expendService.getExpendList(condition);
        request.setAttribute(Constant.DATA_SUMMARY_LIST_KEY, list);
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_OR_EXPEND.getPage();
    }
}
