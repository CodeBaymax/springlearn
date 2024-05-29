package com.learn.mttransaction;

import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * spring事务
 */
@Service
@Slf4j
public class AddReqBase {
    @Autowired
    private RepayRequestDao repayRequestDao;

    @Transactional(rollbackFor = Exception.class)
    public void addReq() {
        long start = System.currentTimeMillis();
        RepayRequest request = new RepayRequest();
        for (int i = 0; i < 10000; i++) {
            request.setId(String.valueOf(10000 + i));
            request.setContNo(UUID.randomUUID().toString().replace("-", ""));
            request.setRepaySeq("SEQ" + request.getId());
            request.setTransAmt(BigDecimal.TEN);
            request.setRealAmt(BigDecimal.TEN);

            repayRequestDao.insert(request);
            request = new RepayRequest();
        }
        long end = System.currentTimeMillis();
        log.info("插入10000条数据耗时：{}", end - start);
    }
}
