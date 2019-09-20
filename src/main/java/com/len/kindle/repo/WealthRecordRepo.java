package com.len.kindle.repo;

import com.len.kindle.entity.WealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface WealthRecordRepo extends JpaRepository<WealthRecord, Integer>, JpaSpecificationExecutor<WealthRecord> {
}
