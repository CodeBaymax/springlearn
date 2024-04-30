package com.learn.transactional.required;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
 *
 * 如果A、B两个方法都加了@Transactional注解，默认是REQUIRED传播行为。那么如果A方法调用B方法，
 * 它们会共用一个事务，因为默认会使用同一条连接，相当于一个事务里执行。
 */
@Service
public class ServiceA {
    @Autowired
    private RepayRequestDao repayRequestDao;
    @Autowired
    private ServiceB serviceB;

    @Transactional(rollbackFor = Exception.class)
    public void addReq() {
        RepayRequest request1 = new RepayRequest();
        request1.setId("4");
        request1.setRepaySeq("r4");
        request1.setContNo("c4");
        request1.setTransAmt(new BigDecimal("14"));
        request1.setRealAmt(new BigDecimal("14"));
        repayRequestDao.insert(request1);

//        serviceB.addReqCalled();
        serviceB.addReqCalledTrans();
        int num = 1/0;
    }
}
