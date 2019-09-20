package com.len.kindle.repo;

import com.len.kindle.entity.NicknameConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface NicknameConfigRepo extends JpaRepository<NicknameConfig, Integer>, JpaSpecificationExecutor<NicknameConfig> {
}
