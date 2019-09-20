package com.len.kindle.repo;

import com.len.kindle.entity.ActivityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface ActivityInfoRepo extends JpaRepository<ActivityInfo, Integer>, JpaSpecificationExecutor<ActivityInfo> {
}
