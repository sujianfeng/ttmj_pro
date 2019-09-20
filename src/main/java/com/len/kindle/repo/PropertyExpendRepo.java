package com.len.kindle.repo;

import com.len.kindle.entity.PropertyExpend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface PropertyExpendRepo extends JpaRepository<PropertyExpend, Integer>, JpaSpecificationExecutor<PropertyExpend> {
}
