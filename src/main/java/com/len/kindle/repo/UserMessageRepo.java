package com.len.kindle.repo;

import com.len.kindle.entity.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface UserMessageRepo extends JpaRepository<UserMessage, Integer>, JpaSpecificationExecutor<UserMessage> {
}
