package com.len.kindle.repo;

import com.len.kindle.entity.ChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface ChannelInfoRepo extends JpaRepository<ChannelInfo, Integer>, JpaSpecificationExecutor<ChannelInfo> {
}
