package com.len.kindle.repo;

import com.len.kindle.entity.HeaderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface HeaderInfoRepo extends JpaRepository<HeaderInfo, Integer>, JpaSpecificationExecutor<HeaderInfo> {
    HeaderInfo findByUserid(String userid);
}
