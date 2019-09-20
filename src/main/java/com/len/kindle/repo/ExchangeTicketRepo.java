package com.len.kindle.repo;

import com.len.kindle.entity.ExchangeTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author sujianfeng
 */
@Repository
public interface ExchangeTicketRepo extends JpaRepository<ExchangeTicket, Integer>, JpaSpecificationExecutor<ExchangeTicket> {
}
