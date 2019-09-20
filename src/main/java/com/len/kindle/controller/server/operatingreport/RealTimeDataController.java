package com.len.kindle.controller.server.operatingreport;

import com.len.kindle.bean.GameBean;
import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.operatingreport.RealTimeDataService;
import com.len.kindle.vo.RealTimeDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/or/realtimedata")
public class RealTimeDataController {

    @Autowired
    private RealTimeDataService realTimeDataService;


    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       String[] condition) {
        request.setAttribute(Constant.CONDITION_KEY, condition);

        List<GameBean> gameBeanList = new ArrayList<>();
        gameBeanList.add(GameBean.builder().gameId(0).gameName("二人场").build());
        gameBeanList.add(GameBean.builder().gameId(3).gameName("推筒子").build());
        gameBeanList.add(GameBean.builder().gameId(6).gameName("血流成河").build());
        gameBeanList.add(GameBean.builder().gameId(7).gameName("豹子王").build());
        gameBeanList.add(GameBean.builder().gameId(9).gameName("血战到底").build());
        request.setAttribute("gameBeanList", gameBeanList);
        return PageEnum.PAGE_OR_REALTIMEDATA.getPage();
    }

    @RequestMapping("/getOnlineData")
    @ResponseBody
    public List<RealTimeDataVo> getOnlineData(@RequestParam(name = "gametype") int gametype,
                                              @RequestParam(name = "starttime") String starttime,
                                              @RequestParam(name = "endtime") String endtime) {

        log.info(gametype + "--" + starttime + "——" + endtime);

        return realTimeDataService.getOnlineVoList(gametype, starttime, endtime);
    }
}
