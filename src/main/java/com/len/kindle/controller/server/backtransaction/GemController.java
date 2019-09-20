package com.len.kindle.controller.server.backtransaction;

import com.len.kindle.bean.UserInfoBean;
import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.service.backtransaction.BackTransactionService;
import com.len.kindle.util.CloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sujianfeng
 * @date 2018/11/3 11:15 AM
 */
@Slf4j
@Controller
@RequestMapping("/server/bt/gem")
public class GemController {

    @Value("${url.userinfo.update}")
    private String url_userinfo_update;
    @Autowired
    private BackTransactionService backTransactionService;


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

        return PageEnum.PAGE_BT_GEM.getPage();
    }

    @RequestMapping("/saveGem")
    @ResponseBody
    public String saveGem(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "gem") Integer gem) {
        log.info("userID:{}", userId);
        log.info("gem:{}", gem);
        UserInfoBean userInfoBean = UserInfoBean.builder().userId(userId).gem(gem).et(0).gold(0).ts(0).zd(0).build();
        boolean result = CloudUtil.updateUserInfo(url_userinfo_update, userInfoBean);
        return CloudUtil.buildAjaxOperationJson(result, result ? "更新成功" : "更新失败");
    }

    @RequestMapping("/saveET")
    @ResponseBody
    public String saveET(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "et") Integer et) {
        log.info("userID:{}", userId);
        log.info("et:{}", et);
        UserInfoBean userInfoBean = UserInfoBean.builder().userId(userId).gem(0).et(et).gold(0).ts(0).zd(0).build();
        boolean result = CloudUtil.updateUserInfo(url_userinfo_update, userInfoBean);
        return CloudUtil.buildAjaxOperationJson(result, result ? "更新成功" : "更新失败");
    }
}
