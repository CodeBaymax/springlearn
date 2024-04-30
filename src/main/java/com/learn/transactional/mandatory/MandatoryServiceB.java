package com.learn.transactional.mandatory;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MandatoryServiceB {
    @Autowired
    private RepayRequestDao repayRequestDao;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void addReqCalled() {
        RepayRequest request = new RepayRequest();
        request.setId("5");
        request.setRepaySeq("r5");
        request.setContNo("c5");
        request.setTransAmt(new BigDecimal("14"));
        request.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request);
    }
}
