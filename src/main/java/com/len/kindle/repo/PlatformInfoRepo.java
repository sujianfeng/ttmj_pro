package com.len.kindle.repo;

import com.len.kindle.entity.PlatformInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface PlatformInfoRepo extends JpaRepository<PlatformInfo, Integer>, JpaSpecificationExecutor<PlatformInfo> {
}
