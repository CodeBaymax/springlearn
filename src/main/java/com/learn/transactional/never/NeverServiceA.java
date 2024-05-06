package com.learn.transactional.never;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import com.learn.transactional.required.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 以非事务方式运行，如果当前存在事务，则抛出异常
 */
@Service
public class NeverServiceA {

    @Autowired
    private RepayRequestDao repayRequestDao;
    @Autowired
    private NeverServiceB neverServiceB;

    /**
     * A方法存在事务（加了@Transactional注解），B方法配置了NEVER传播行为，那么B方法将会抛出异常。
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

        neverServiceB.addReqCalled();
        int num = 1/0;
    }

    /**
     * A方法没有事务（没有加@Transactional注解），B方法配置了NEVER传播行为，那么B方法会正常以非事务方式执行。
     */
    public void addReqNoTrans() {
        neverServiceB.addReqCalled();
    }
}
