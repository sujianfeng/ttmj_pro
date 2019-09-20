package com.len.kindle.repo;

import com.len.kindle.entity.GoodsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface GoodsInfoRepo extends JpaRepository<GoodsInfo, Integer>, JpaSpecificationExecutor<GoodsInfo> {

     GoodsInfo findGoodsInfoByGoodsId(int goodsId);
}
