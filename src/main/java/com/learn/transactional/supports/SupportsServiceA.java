package com.learn.transactional.supports;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
 *
 * 如果A方法没有事务（没有加@Transactional注解），B方法配置了SUPPORTS传播行为，那么B方法也会以非事务方式执行。
 * 如果A方法存在事务（加了@Transactional注解），B方法配置了SUPPORTS传播行为，那么B方法会挂起自己的事务，加入到A方法的事务来执行。
 */
@Service
public class SupportsServiceA {
    @Autowired
    private RepayRequestDao repayRequestDao;
    @Autowired
    private SupportsServiceB supportsServiceB;

    /**
     * A方法存在事务（加了@Transactional注解），B方法配置了SUPPORTS传播行为，那么B方法会挂起自己的事务，加入到A方法的事务来执行。
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

        supportsServiceB.addReqCalled();
        int num = 1/0;
    }

    /**
     * A方法没有事务（没有加@Transactional注解），B方法配置了SUPPORTS传播行为，那么B方法也会以非事务方式执行。
     */
    public void addReqNoTrans() {
        RepayRequest request1 = new RepayRequest();
        request1.setId("4");
        request1.setRepaySeq("r4");
        request1.setContNo("c4");
        request1.setTransAmt(new BigDecimal("14"));
        request1.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request1);

        supportsServiceB.addReqCalledExp();
    }
}
