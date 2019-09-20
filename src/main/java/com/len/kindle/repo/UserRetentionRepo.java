package com.len.kindle.repo;

import com.len.kindle.entity.UserRetention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface UserRetentionRepo extends JpaRepository<UserRetention, Integer>, JpaSpecificationExecutor<UserRetention> {
}
