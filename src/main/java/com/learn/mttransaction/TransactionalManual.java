package com.learn.mttransaction;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 手动提交事务
 * 大量循环数据库提交操作，添加手动事务可以有效提高操作效率
 */
@Service
@Slf4j
public class TransactionalManual {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;
    @Autowired
    private RepayRequestDao repayRequestDao;

    public void addReq() {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        long start = System.currentTimeMillis();
        RepayRequest request = new RepayRequest();
        try {
            for (int i = 0; i < 10000; i++) {
                request.setId(String.valueOf(20000 + i));
                request.setContNo(UUID.randomUUID().toString().replace("-", ""));
                request.setRepaySeq("SEQ" + request.getId());
                request.setTransAmt(BigDecimal.TEN);
                request.setRealAmt(BigDecimal.TEN);

                repayRequestDao.insert(request);
                request = new RepayRequest();
            }

            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            log.info("发生异常 {}", e);
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        long end = System.currentTimeMillis();
        log.info("插入10000条数据耗时：{}", end - start);
    }
}
