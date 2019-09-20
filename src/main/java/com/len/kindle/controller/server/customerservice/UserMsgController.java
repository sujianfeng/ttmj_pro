package com.len.kindle.controller.server.customerservice;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.ServiceMessage;
import com.len.kindle.service.customerservice.CustomerService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sujianfeng
 * @date 2018/11/3 11:16 AM
 */
@Slf4j
@Controller
@RequestMapping("/server/cs/usermsg")
public class UserMsgController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

        condition = condition == null ? new String[]{sdf1.format(new Date()), sdf2.format(new Date()), "", ""} : condition;
        CloudUtil.logCondition(condition);

        Page<ServiceMessage> userMessagePage = customerService.findAllUserMsg(condition, pageable);

        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, userMessagePage);
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_CS_USERMESSAGE.getPage();
    }

    @RequestMapping("/toRead")
    public String toRead(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") int id, String[] condition) {

        customerService.readUserMsg(id);

        return this.list(request, response, 0, Integer.parseInt(Constant.PAGE_SIZE), condition);
    }

    @RequestMapping("/saveUserMsg")
    @ResponseBody
    public String saveUserMsg(@RequestParam(name = "userId") int userId, @RequestParam(name = "userMsg") String userMsg) {
        log.info("userId:{}", userId);
        log.info("userMsg:{}", userMsg);

        boolean result = customerService.saveUserMsg(userId, userMsg);
        return CloudUtil.buildAjaxOperationJson(result, result ? "发送成功" : "发送失败");
    }

}
