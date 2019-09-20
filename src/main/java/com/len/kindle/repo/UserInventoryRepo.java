package com.len.kindle.repo;

import com.len.kindle.entity.UserInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface UserInventoryRepo extends JpaRepository<UserInventory, Integer>, JpaSpecificationExecutor<UserInventory> {

}
