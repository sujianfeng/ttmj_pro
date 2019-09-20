package com.len.kindle.repo;

import com.len.kindle.entity.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface TaskInfoRepo extends JpaRepository<TaskInfo, Integer>, JpaSpecificationExecutor<TaskInfo> {
}
