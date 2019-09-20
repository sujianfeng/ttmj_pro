package com.len.kindle.controller.server.tableinfo;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.LoginRecord;
import com.len.kindle.service.tableinfo.LoginRecordService;
import com.len.kindle.util.CloudUtil;
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

/**
 * @author sujianfeng
 */
@Controller
@RequestMapping("/server/loginrecord")
public class LoginRecordController {
    @Autowired
    private LoginRecordService loginRecordService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        condition = condition == null ? new String[11] : condition;
        CloudUtil.logCondition(condition);

        Page<LoginRecord> loginRecordPage = loginRecordService.findAll(condition, pageable);

        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, loginRecordPage);
        request.setAttribute(Constant.CONDITION_KEY, condition);

        return PageEnum.PAGE_TABLEINFO_LOGINRECORDLIST.getPage();
    }
}
