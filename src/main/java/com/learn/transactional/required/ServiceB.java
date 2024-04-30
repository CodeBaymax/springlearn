package com.learn.transactional.required;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ServiceB {
    @Autowired
    private RepayRequestDao repayRequestDao;

    public void addReqCalled() {
        RepayRequest request = new RepayRequest();
        request.setId("5");
        request.setRepaySeq("r5");
        request.setContNo("c5");
        request.setTransAmt(new BigDecimal("14"));
        request.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addReqCalledTrans() {
        RepayRequest request = new RepayRequest();
        request.setId("5");
        request.setRepaySeq("r5");
        request.setContNo("c5");
        request.setTransAmt(new BigDecimal("14"));
        request.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request);
    }
}
