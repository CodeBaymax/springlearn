package com.learn.transactional.mandatory;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import com.learn.transactional.supports.SupportsServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
 */
@Service
public class MandatoryServiceA {
    @Autowired
    private RepayRequestDao repayRequestDao;
    @Autowired
    private MandatoryServiceB mandatoryServiceB;

    /**
     * A方法存在事务（加@Transactional注解），B方法配置了MANDATORY传播行为，那么B方法将加入到该存在的事务来执行。
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

        mandatoryServiceB.addReqCalled();
        int num = 1/0;
    }

    /**
     * A方法没有事务（没有加@Transactional注解），B方法配置了MANDATORY传播行为，那么B方法将会抛出异常。
     */
    public void addReqNoTrans() {
        mandatoryServiceB.addReqCalled();
        int num = 1/0;
    }
}
