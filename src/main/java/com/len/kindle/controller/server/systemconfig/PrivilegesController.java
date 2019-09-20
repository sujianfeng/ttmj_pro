package com.len.kindle.controller.server.systemconfig;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.systemconfig.SysCfgService;
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

/**
 * @author sujianfeng
 * @date 2018/11/3 12:39 PM
 */
@Slf4j
@Controller
@RequestMapping("/server/sc/privileges")
public class PrivilegesController {

    @Autowired
    private SysCfgService sysCfgService;


    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        ////初始化查询条件
        //condition = condition == null ? new String[8] : condition;
        //CloudUtil.logCondition(condition);
        //
        //Page<GameRecord> gameRecordPage = gameRecordService.findAll(condition, pageable);
        //
        ////页面数据存储
        //request.setAttribute(Constant.PAGE_KEY, gameRecordPage);
        request.setAttribute(Constant.CONDITION_KEY, condition);

        return PageEnum.PAGE_SC_PRIVILEGES.getPage();
    }
}
