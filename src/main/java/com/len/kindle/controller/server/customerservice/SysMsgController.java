package com.len.kindle.controller.server.customerservice;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.SysSpeakerMessage;
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
import java.util.Date;

/**
 * @author sujianfeng
 * @date 2018/11/3 11:16 AM
 */
@Slf4j
@Controller
@RequestMapping("/server/cs/sysmsg")
public class SysMsgController {

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
        condition = condition == null ? new String[1] : condition;
        CloudUtil.logCondition(condition);

        Page<SysSpeakerMessage> sysSpeakerMessagePage = customerService.findAllSysMsg(condition, pageable);

        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, sysSpeakerMessagePage);
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_CS_SYSTEMMESSAGE.getPage();
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") Integer id) {

        SysSpeakerMessage sysSpeakerMessage = id == 0 ? SysSpeakerMessage.builder().build() : customerService.getSysSpeakMsg(id);
        log.info(sysSpeakerMessage.toString());
        request.setAttribute(Constant.SYS_SPEAK_MSG_KEY, sysSpeakerMessage);
        return PageEnum.PAGE_EDIT_CS_SYSTEMMESSAGE.getPage();
    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response, SysSpeakerMessage sysSpeakerMessage) {

        String operation = sysSpeakerMessage.getId() != null ? "编辑" : "新增";
        log.info(sysSpeakerMessage.toString());


        SysSpeakerMessage dbSysSpeakerMessage = SysSpeakerMessage.builder().build();
        if (sysSpeakerMessage.getId() != null) {
            dbSysSpeakerMessage = customerService.getSysSpeakMsg(sysSpeakerMessage.getId());
            //编辑
            dbSysSpeakerMessage.setModifyID(0);
            dbSysSpeakerMessage.setModifyTime(new Date());
        } else {
            dbSysSpeakerMessage.setModifyID(0);
            dbSysSpeakerMessage.setModifyTime(new Date());
            dbSysSpeakerMessage.setCreateTime(new Date());
        }
        dbSysSpeakerMessage.setContent(sysSpeakerMessage.getContent());
        dbSysSpeakerMessage.setInterval(sysSpeakerMessage.getInterval());
        dbSysSpeakerMessage.setEnable(sysSpeakerMessage.getEnable());
        dbSysSpeakerMessage.setStartDate(sysSpeakerMessage.getStartDate());
        dbSysSpeakerMessage.setStartTime(sysSpeakerMessage.getStartTime());
        dbSysSpeakerMessage.setEndDate(sysSpeakerMessage.getEndDate());
        dbSysSpeakerMessage.setEndTime(sysSpeakerMessage.getEndTime());

        dbSysSpeakerMessage = customerService.saveSysSpeakMsg(dbSysSpeakerMessage);

        return CloudUtil.buildAjaxOperationJson(dbSysSpeakerMessage != null, dbSysSpeakerMessage != null ? operation + "消息成功" : operation + "消息失败");
    }

}
