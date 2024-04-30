package com.test.dao;

import com.learn.Application;
import com.learn.dao.RepayRequestDao;
import com.learn.entity.RepayRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class RepayRequestDaoTest {
    @Autowired
    private RepayRequestDao repayRequestDao;

    @Test
    public void test() {
        RepayRequest repayRequest = new RepayRequest();
        repayRequest.setRepaySeq("1");

        RepayRequest request = repayRequestDao.selReq(repayRequest);
        System.out.println(request);
    }
}
