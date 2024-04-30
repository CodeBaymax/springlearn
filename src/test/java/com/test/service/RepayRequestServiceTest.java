package com.test.service;

import com.learn.Application;
import com.learn.entity.RepayRequest;
import com.learn.service.IRepayRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Application.class)
public class RepayRequestServiceTest {
    @Autowired
    private IRepayRequestService iRepayRequestService;
    @Test
    public void testAddReq() {
        List<RepayRequest> requestList = new ArrayList<>();
        RepayRequest request1 = new RepayRequest();
        request1.setId("4");
        request1.setRepaySeq("r4");
        request1.setContNo("c4");
        request1.setTransAmt(new BigDecimal("14"));
        request1.setRealAmt(new BigDecimal("14"));

        RepayRequest request2 = new RepayRequest();
        request2.setId("5");
        request2.setRepaySeq("r5");
        request2.setContNo("c5");
        request2.setTransAmt(new BigDecimal("14"));
        request2.setRealAmt(new BigDecimal("14"));
        requestList.add(request1);
        requestList.add(request2);

        iRepayRequestService.addReq(requestList);
    }
}
