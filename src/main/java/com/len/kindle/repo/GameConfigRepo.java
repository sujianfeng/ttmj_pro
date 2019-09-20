package com.len.kindle.repo;

import com.len.kindle.entity.GameConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface GameConfigRepo extends JpaRepository<GameConfig, Integer>, JpaSpecificationExecutor<GameConfig> {
}
