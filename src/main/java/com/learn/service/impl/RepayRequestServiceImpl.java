package com.learn.service.impl;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import com.learn.service.IRepayRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class RepayRequestServiceImpl implements IRepayRequestService {

    @Autowired
    private RepayRequestDao repayRequestDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addReq(List<RepayRequest> requestList) {
        log.info("1231231231231231231231........................");
        for (int i = 0; i < requestList.size(); i++) {
//            if (i == 1) {
//                throw new RuntimeException("抛出异常。。。");
//            }
            repayRequestDao.insert(requestList.get(i));
        }
    }
}
