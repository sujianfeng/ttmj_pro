package com.len.kindle.controller.server.operatingreport;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.ChannelInfo;
import com.len.kindle.entity.PlatformInfo;
import com.len.kindle.service.operatingreport.ChannelProjectService;
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
 */
@Slf4j
@Controller
@RequestMapping("/server/or/channelproject")
public class ChannelProjectController {

    @Autowired
    private ChannelProjectService channelProjectService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        condition = (condition == null || condition.length == 0) ? new String[]{""} : condition;
        CloudUtil.logCondition(condition);

        Page<PlatformInfo> platformInfoPage = channelProjectService.findAll(condition, pageable);

        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, platformInfoPage);
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_OR_CHANNELPROJECT.getPage();
    }

    @RequestMapping("/editPlatform")
    public String editPlatform(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") Integer id) {

        PlatformInfo platformInfo = id == 0 ? PlatformInfo.builder().build() : channelProjectService.getPlatform(id);
        log.info(platformInfo.toString());
        request.setAttribute(Constant.PLATFORM_KEY, platformInfo);
        return PageEnum.PAGE_OR_PLATFORM_EDIT.getPage();
    }

    @RequestMapping("/savePlatform")
    @ResponseBody
    public String savePlatform(HttpServletRequest request, HttpServletResponse response, PlatformInfo platformInfo) {

        String operation = platformInfo.getId() != null ? "编辑" : "新增";
        log.info(platformInfo.toString());


        PlatformInfo dbPlatformInfo = PlatformInfo.builder().build();
        if (platformInfo.getId() != null) {
            dbPlatformInfo = channelProjectService.getPlatform(platformInfo.getId());

        } else {
            dbPlatformInfo.setCreateTime(new Date());
        }
        dbPlatformInfo.setPlatform(platformInfo.getPlatform());
        dbPlatformInfo.setPlatformName(platformInfo.getPlatformName());


        dbPlatformInfo = channelProjectService.savePlatformInfo(dbPlatformInfo);

        return CloudUtil.buildAjaxOperationJson(dbPlatformInfo != null, dbPlatformInfo != null ? operation + "平台成功" : operation + "平台失败");
    }

    @RequestMapping("/channellist")
    public String channellist(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                              String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        //初始化查询条件
        PlatformInfo platformInfo = channelProjectService.getPlatform(Integer.parseInt(condition[0]));

        CloudUtil.logCondition(condition);

        Page<ChannelInfo> channelInfoPage = channelProjectService.findAllChannel(condition, pageable);

        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, channelInfoPage);
        request.setAttribute(Constant.PLATFORM_KEY, platformInfo);
        request.setAttribute(Constant.CONDITION_KEY, condition);

        return PageEnum.PAGE_OR_CHANNEL_LIST.getPage();
    }

    @RequestMapping("/editChannel")
    public String editChannel(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") Integer id, Integer platformId) {

        ChannelInfo channelInfo = id == 0 ? ChannelInfo.builder().platform(channelProjectService.getPlatform(platformId)).build() : channelProjectService.getChannel(id);

        log.info(channelInfo.toString());

        PlatformInfo platformInfo = channelProjectService.getPlatform(platformId);

        request.setAttribute(Constant.PLATFORM_KEY, platformInfo);
        request.setAttribute(Constant.CHANNEL_KEY, channelInfo);
        return PageEnum.PAGE_OR_CHANNEL_EDIT.getPage();
    }


    @RequestMapping("/saveChannel")
    @ResponseBody
    public String saveChannel(HttpServletRequest request, HttpServletResponse response, ChannelInfo channelInfo) {

        String operation = channelInfo.getId() != null ? "编辑" : "新增";
        log.info(channelInfo.toString());


        ChannelInfo dbChannelInfo = ChannelInfo.builder().build();
        if (channelInfo.getId() != null) {
            dbChannelInfo = channelProjectService.getChannel(channelInfo.getId());

        } else {
            dbChannelInfo.setCreateTime(new Date());
        }
        dbChannelInfo.setChannel(channelInfo.getChannel());
        dbChannelInfo.setChannelName(channelInfo.getChannelName());
        dbChannelInfo.setPlatform(channelInfo.getPlatform());


        dbChannelInfo = channelProjectService.saveChannelInfo(dbChannelInfo);

        return CloudUtil.buildAjaxOperationJson(dbChannelInfo != null, dbChannelInfo != null ? operation + "渠道成功" : operation + "渠道失败");
    }


}
