package com.len.kindle.service.customerservice;

import com.len.kindle.config.Constant;
import com.len.kindle.entity.ExchangeTicket;
import com.len.kindle.entity.ServiceMessage;
import com.len.kindle.entity.SysSpeakerMessage;
import com.len.kindle.repo.ExchangeTicketRepo;
import com.len.kindle.repo.ServiceMessageRepo;
import com.len.kindle.repo.SysSpeakerMessageRepo;
import com.len.kindle.repo.UserMessageRepo;
import com.len.kindle.util.FantasticUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sujianfeng
 * @date 2018/11/4 7:55 PM
 */
@Slf4j
@Service
public class CustomerService {

    @Autowired
    private ExchangeTicketRepo exchangeTicketRepo;
    @Autowired
    private ServiceMessageRepo serviceMessageRepo;
    @Autowired
    private SysSpeakerMessageRepo sysSpeakerMessageRepo;
    @Autowired
    private UserMessageRepo userMessageRepo;

    public Page<ExchangeTicket> findAllExchangeTicket(String[] condition, Pageable pageable) {
        return exchangeTicketRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }


    public void exchange(Integer id) {

        ExchangeTicket exchangeTicket = exchangeTicketRepo.getOne(id);

        exchangeTicket.setStatus(Constant.EXCHANGETICKET_SUCCESS);
        exchangeTicket.setExchangeTime(new Date());
        exchangeTicket.setExchangeID(0);

        exchangeTicketRepo.saveAndFlush(exchangeTicket);

    }

    public boolean saveUserMsg(int userId, String userMsg) {
        try {
            ServiceMessage serviceMessage = ServiceMessage.builder().srcID(0).destID(userId).message(userMsg)
                    .read(0).createTime(new Date()).build();

            serviceMessageRepo.saveAndFlush(serviceMessage);

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }


        return false;

    }

    public Page<SysSpeakerMessage> findAllSysMsg(String[] condition, Pageable pageable) {
        return sysSpeakerMessageRepo.findAll((root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }

    public SysSpeakerMessage getSysSpeakMsg(Integer id) {
        return sysSpeakerMessageRepo.getOne(id);
    }

    public SysSpeakerMessage saveSysSpeakMsg(SysSpeakerMessage dbSysSpeakerMessage) {
        return sysSpeakerMessageRepo.saveAndFlush(dbSysSpeakerMessage);
    }

    public Page<ServiceMessage> findAllUserMsg(String[] condition, Pageable pageable) {
        return serviceMessageRepo.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<>();

            //日期条件
            if (StringUtils.isNotBlank(condition[0]) && StringUtils.isNotBlank(condition[1])) {
                try {
                    Date dateBefore = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[0]);
                    Date dateAfter = FantasticUtil.SDF_4_NOTIFY.get().parse(condition[1]);
                    if (dateBefore.after(dateAfter)) {
                        Date tmpDate = dateBefore;
                        dateBefore = dateAfter;
                        dateAfter = tmpDate;
                    }
                    list.add(cb.between(root.get("createTime").as(Date.class), dateBefore, dateAfter));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (StringUtils.isNotBlank(condition[2])) {
                list.add(cb.equal(root.get("srcID").as(Integer.class), Integer.parseInt(condition[2])));
            }

            if (StringUtils.isNotBlank(condition[3])) {
                list.add(cb.equal(root.get("destID").as(Integer.class), Integer.parseInt(condition[3])));
            }
            Predicate[] predicate = new Predicate[list.size()];
            return cb.and(list.toArray(predicate));
        }, pageable);
    }

    public void readUserMsg(int id) {
        ServiceMessage serviceMessage = serviceMessageRepo.getOne(id);
        serviceMessage.setRead(1);
        serviceMessageRepo.saveAndFlush(serviceMessage);
    }
}
