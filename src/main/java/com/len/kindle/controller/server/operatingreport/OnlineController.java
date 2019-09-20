package com.len.kindle.controller.server.operatingreport;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.operatingreport.OnlineService;
import com.len.kindle.vo.OnlineVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/or/online")
public class OnlineController {

    @Autowired
    private OnlineService onlineService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       String[] condition) {
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_OR_ONLINE.getPage();
    }

    @RequestMapping("/getOnlieData")
    @ResponseBody
    public List<OnlineVo> getOnlieData(@RequestParam(name = "starttime") String starttime,
                                       @RequestParam(name = "endtime") String endtime) {

        log.info(starttime + "——" + endtime);

        return onlineService.getOnlieVoList(starttime, endtime);
    }
}
