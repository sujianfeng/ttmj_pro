package com.len.kindle.repo;

import com.len.kindle.entity.BulletinMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface BulletinMessageRepo extends JpaRepository<BulletinMessage, Integer>, JpaSpecificationExecutor<BulletinMessage> {
}
