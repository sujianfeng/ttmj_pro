package com.len.kindle.repo;

import com.len.kindle.entity.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface FriendListRepo extends JpaRepository<FriendList, Integer>, JpaSpecificationExecutor<FriendList> {
}
