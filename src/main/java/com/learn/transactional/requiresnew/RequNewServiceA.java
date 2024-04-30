package com.learn.transactional.requiresnew;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import com.learn.transactional.mandatory.MandatoryServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 创建一个新的事务,如果当前存在事务,则把当前事务挂起。
 */
@Service
public class RequNewServiceA {
    @Autowired
    private RepayRequestDao repayRequestDao;
    @Autowired
    private RequNewServiceB requNewServiceB;

    /**
     * A开启了事务，B的传播机制为REQUIRES_NEW，B开启新的事务，A事务异常不影响B事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void addReq() {
        RepayRequest request1 = new RepayRequest();
        request1.setId("4");
        request1.setRepaySeq("r4");
        request1.setContNo("c4");
        request1.setTransAmt(new BigDecimal("14"));
        request1.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request1);

        requNewServiceB.addReqCalled();
        int num = 1/0;
    }

    /**
     * A开启了事务，B的传播机制为REQUIRES_NEW，B开启新的事务，B事务异常，A B两个事务都回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void addReqBExp() {
        RepayRequest request1 = new RepayRequest();
        request1.setId("4");
        request1.setRepaySeq("r4");
        request1.setContNo("c4");
        request1.setTransAmt(new BigDecimal("14"));
        request1.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request1);

        requNewServiceB.addReqCalledExp();
//        int num = 1/0;
    }

    /**
     * A开启了事务，B的传播机制为REQUIRES_NEW，B开启新的事务，B事务异常，但是异常捕获后并未抛出，A B两个事务都成功
     * 异常捕获后不抛出，等于没有异常，不影响事务提交
     */
    @Transactional(rollbackFor = Exception.class)
    public void addReqBExpCatch() {
        RepayRequest request1 = new RepayRequest();
        request1.setId("4");
        request1.setRepaySeq("r4");
        request1.setContNo("c4");
        request1.setTransAmt(new BigDecimal("14"));
        request1.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request1);

        requNewServiceB.addReqCalledExpCatch();
//        int num = 1/0;
    }
}
