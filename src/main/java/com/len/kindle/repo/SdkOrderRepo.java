package com.len.kindle.repo;

import com.len.kindle.entity.SdkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface SdkOrderRepo extends JpaRepository<SdkOrder, Integer>, JpaSpecificationExecutor<SdkOrder> {

    SdkOrder findBySdkOrderId(String notifyId);

    SdkOrder findByOrOrderId(String partnerOrder);
}
