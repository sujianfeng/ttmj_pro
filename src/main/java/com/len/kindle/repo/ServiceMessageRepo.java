package com.len.kindle.repo;

import com.len.kindle.entity.ServiceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface ServiceMessageRepo extends JpaRepository<ServiceMessage, Integer>, JpaSpecificationExecutor<ServiceMessage> {
}
