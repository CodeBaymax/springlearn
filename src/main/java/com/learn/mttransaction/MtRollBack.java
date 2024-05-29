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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MtRollBack {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;
    @Autowired
    private RepayRequestDao repayRequestDao;

    public void addReq() {
        long start = System.currentTimeMillis();

        // 线程数
        final Integer threadCount = 5;

        List<RepayRequest> requestList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            RepayRequest request = new RepayRequest();
            request.setId(String.valueOf(40000 + i));
            request.setContNo(UUID.randomUUID().toString().replace("-", ""));
            request.setRepaySeq("SEQ" + request.getId());
            request.setTransAmt(BigDecimal.TEN);
            request.setRealAmt(BigDecimal.TEN);

            requestList.add(request);
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch threadLatch = new CountDownLatch(threadCount);
        CountDownLatch mainLatch = new CountDownLatch(1);

        TaskError taskError = new TaskError();

        // 每个线程处理的数量
        final Integer dataPartSize = (requestList.size() + threadCount - 1) / threadCount;

        for (int i = 0; i < threadCount; i++) {
            List<RepayRequest> list = requestList.stream()
                    .skip(i * dataPartSize).limit(dataPartSize).collect(Collectors.toList());
            threadPool.execute(() -> {
                insReqThread(list, mainLatch, threadLatch, taskError);
            });
        }

        try {
            boolean await = threadLatch.await(30, TimeUnit.SECONDS);
            if (!await)
                taskError.setErrorFlag();
        } catch (Exception e) {
            log.info("异常 {}", e);
            taskError.setErrorFlag();
        }

        mainLatch.countDown();
        threadPool.shutdown();

        long end = System.currentTimeMillis();
        log.info("插入10000条数据耗时：{}", end - start);
    }

    public void insReqThread(List<RepayRequest> reqList, CountDownLatch mainLatch, CountDownLatch threadLatch, TaskError taskError) {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        System.out.println("子线程：" + Thread.currentThread().getName());

        try {
            for (RepayRequest request : reqList) {
                repayRequestDao.insert(request);
            }
        } catch (Exception e) {
            taskError.setErrorFlag();
        } finally {
            threadLatch.countDown();
        }

        try {
            mainLatch.await();
        } catch (Exception e) {
            taskError.setErrorFlag();
        }

        if (taskError.getErrorFlag().get()) {
            dataSourceTransactionManager.rollback(transactionStatus);
        } else {
            dataSourceTransactionManager.commit(transactionStatus);
        }
    }
}
