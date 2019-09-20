package com.len.kindle.service;

import com.len.kindle.entity.SdkOrder;
import com.len.kindle.repo.SdkOrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sujianfeng
 */
@Slf4j
@Service
public class SdkOrderService {
    @Autowired
    private SdkOrderRepo sdkOrderRepo;

    public SdkOrder getSdkOrder(String sdkOrderId) {
        return sdkOrderRepo.findBySdkOrderId(sdkOrderId);
    }

    public SdkOrder saveSdkOrder(SdkOrder sdkOrder) {
        sdkOrderRepo.saveAndFlush(sdkOrder);
        return sdkOrder;
    }

    public SdkOrder getSdkOrderByCpOrderId(String cpOrderNumber) {
        return sdkOrderRepo.findByOrOrderId(cpOrderNumber);
    }
}
