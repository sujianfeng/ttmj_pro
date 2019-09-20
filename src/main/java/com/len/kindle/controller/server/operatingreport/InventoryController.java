package com.len.kindle.controller.server.operatingreport;

import com.len.kindle.config.Constant;
import com.len.kindle.config.enums.PageEnum;
import com.len.kindle.entity.UserInventory;
import com.len.kindle.service.operatingreport.InventoryService;
import com.len.kindle.util.CloudUtil;
import com.len.kindle.util.FantasticUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sujianfeng
 */
@Slf4j
@Controller
@RequestMapping("/server/or/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = Constant.PAGE_SIZE) Integer size,
                       String[] condition) {

        //初始化分页条件
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page < 0 ? 0 : page, size, sort);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

        //初始化查询条件
        condition = condition == null ? new String[]{sdf1.format(new Date()), sdf2.format(new Date()), ""} : condition;
        CloudUtil.logCondition(condition);

        UserInventory sumInventory = UserInventory.builder().channel("合计").etCount(0).goldCount(0)
                .gemCount(0).tsCount(0).zdCount(0).build();
        Page<UserInventory> inventoryPage = inventoryService.findAll(condition, pageable, sumInventory);
        List<UserInventory> userInventoryList = new ArrayList<>();


        try {
//            Date curDate = new Date();
//            if (StringUtils.isNotBlank(condition[0]) && StringUtils.isNotBlank(condition[1])) {
//                Date dateBefore = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[0]);
//                Date dateAfter = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[1]);
//                if (dateBefore.after(dateAfter)) {
//                    Date tmpDate = dateBefore;
//                    dateBefore = dateAfter;
//                    dateAfter = tmpDate;
//                }
//                if (curDate.before(dateAfter) && curDate.after(dateBefore)) {
//                    userInventoryList = inventoryService.getCurInventoryList();
//                }
//            } else {
            userInventoryList = inventoryService.getCurInventoryList();
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //页面数据存储
        request.setAttribute(Constant.DATA_SUMMARY_LIST_KEY, userInventoryList);
        //页面数据存储
        request.setAttribute(Constant.PAGE_KEY, inventoryPage);
        request.setAttribute(Constant.USERINVENTORY, sumInventory);
        request.setAttribute(Constant.CONDITION_KEY, condition);
        return PageEnum.PAGE_OR_INVENTORY.getPage();
    }
}
