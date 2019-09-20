package com.len.kindle.repo;

import com.len.kindle.entity.SysSpeakerMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface SysSpeakerMessageRepo extends JpaRepository<SysSpeakerMessage, Integer>, JpaSpecificationExecutor<SysSpeakerMessage> {
}
