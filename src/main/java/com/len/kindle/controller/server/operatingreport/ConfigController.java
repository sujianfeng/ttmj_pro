package com.len.kindle.controller.server.operatingreport;

import com.len.kindle.bean.ConfigTypeBean;
import com.len.kindle.config.Constant;
import com.len.kindle.config.SystemData;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.GameConfig;
import com.len.kindle.service.operatingreport.ConfigService;
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
import java.util.List;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/or/config")
public class ConfigController {


    @Autowired
    private ConfigService configService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        condition = (condition == null || condition.length == 0) ? new String[]{"-1"} : condition;
        CloudUtil.logCondition(condition);

        Page<GameConfig> gameConfigPage = configService.findAll(condition, pageable);

        List<ConfigTypeBean> configTypeBeanList = SystemData.reInitConfigTypeList();
        configTypeBeanList.add(0, ConfigTypeBean.builder().type(-1).typeName("全部").build());


        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, gameConfigPage);
        request.setAttribute(Constant.CONFIG_TYPE_LIST, configTypeBeanList);
        request.setAttribute(Constant.CONDITION_KEY, condition);

        return PageEnum.PAGE_OR_CONFIG.getPage();
    }


    @RequestMapping("/editConfig")
    public String editConfig(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") Integer id) {

        GameConfig gameConfig = id == 0 ? GameConfig.builder().build() : configService.getGameConfig(id);
        log.info(gameConfig.toString());
        List<ConfigTypeBean> configTypeBeanList = SystemData.reInitConfigTypeList();
        request.setAttribute(Constant.CONFIG_TYPE_LIST, configTypeBeanList);
        request.setAttribute(Constant.GAMECONFIG_KEY, gameConfig);
        return PageEnum.PAGE_OR_GAMECONFIG_EDIT.getPage();
    }


    @RequestMapping("/saveConfig")
    @ResponseBody
    public String saveConfig(HttpServletRequest request, HttpServletResponse response, GameConfig gameConfig) {

        String operation = gameConfig.getId() != null ? "编辑" : "新增";
        log.info(gameConfig.toString());


        GameConfig dbGameConfig = GameConfig.builder().build();
        if (gameConfig.getId() != null) {
            dbGameConfig = configService.getGameConfig(gameConfig.getId());

        } else {
            dbGameConfig.setCreateTime(new Date());
        }
        dbGameConfig.setModifyTime(new Date());
        dbGameConfig.setModifyID(0);
        dbGameConfig.setType(gameConfig.getType());
        dbGameConfig.setPriority(gameConfig.getPriority());
        dbGameConfig.setDescription(gameConfig.getDescription());
        dbGameConfig.setConfig(gameConfig.getConfig());
        dbGameConfig.setPlatfrom(gameConfig.getPlatfrom());
        dbGameConfig.setChannel(gameConfig.getChannel());
        dbGameConfig.setEnable(gameConfig.getEnable());
        dbGameConfig.setOn(gameConfig.getOn());
        dbGameConfig.setStartDate(gameConfig.getStartDate());
        dbGameConfig.setEndDate(gameConfig.getEndDate());
        dbGameConfig.setStartTime(gameConfig.getStartTime());
        dbGameConfig.setEndTime(gameConfig.getEndTime());


        dbGameConfig = configService.saveGameConfig(dbGameConfig);

        return CloudUtil.buildAjaxOperationJson(dbGameConfig != null, dbGameConfig != null ? operation + "配置成功" : operation + "配置失败");
    }
}
