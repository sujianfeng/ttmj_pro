package com.len.kindle.service.operatingreport;

import com.len.kindle.entity.ChannelInfo;
import com.len.kindle.entity.PlatformInfo;
import com.len.kindle.repo.ChannelInfoRepo;
import com.len.kindle.repo.PlatformInfoRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/3 14:19
 */
@Slf4j
@Service
public class ChannelProjectService {

    @Autowired
    private PlatformInfoRepo platformInfoRepo;
    @Autowired
    private ChannelInfoRepo channelInfoRepo;

    public Page<PlatformInfo> findAll(String[] condition, Pageable pageable) {
        return platformInfoRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();


            if (StringUtils.isNotBlank(condition[0])) {
                list.add(cb.equal(root.get("platform").as(Integer.class), Integer.parseInt(condition[0])));
            }

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }

    public PlatformInfo getPlatform(Integer id) {
        return platformInfoRepo.getOne(id);
    }

    public PlatformInfo savePlatformInfo(PlatformInfo platformInfo) {
        return platformInfoRepo.saveAndFlush(platformInfo);
    }

    public Page<ChannelInfo> findAllChannel(String[] condition, Pageable pageable) {
        return channelInfoRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();


            if (StringUtils.isNotBlank(condition[0])) {
                PlatformInfo platformInfo = platformInfoRepo.getOne(Integer.parseInt(condition[0]));
                list.add(cb.equal(root.get("platform").as(PlatformInfo.class), platformInfo));
            }


            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);

    }

    public ChannelInfo getChannel(Integer id) {
        return channelInfoRepo.getOne(id);
    }

    public ChannelInfo saveChannelInfo(ChannelInfo channelInfo) {
        return channelInfoRepo.saveAndFlush(channelInfo);
    }
}
