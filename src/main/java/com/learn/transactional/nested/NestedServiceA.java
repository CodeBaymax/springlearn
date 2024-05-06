package com.learn.transactional.nested;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import com.learn.transactional.never.NeverServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于REQUIRED
 */
@Service
public class NestedServiceA {
    @Autowired
    private RepayRequestDao repayRequestDao;
    @Autowired
    private NestedServiceB nestedServiceB;

    /**
     * A方法存在事务（加@Transactional注解），B方法配置了NESTED传播行为，那么B方法将会在嵌套事务内执行
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

        nestedServiceB.addReqCalled();
    }

    /**
     * A方法没有事务（没有加@Transactional注解），B方法配置了NESTED传播行为，那么B方法将启动一个新的嵌套事务执行
     */
    public void addReqNoTrans() {
        RepayRequest request1 = new RepayRequest();
        request1.setId("4");
        request1.setRepaySeq("r4");
        request1.setContNo("c4");
        request1.setTransAmt(new BigDecimal("14"));
        request1.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request1);

        nestedServiceB.addReqCalledThrow();
    }
}
