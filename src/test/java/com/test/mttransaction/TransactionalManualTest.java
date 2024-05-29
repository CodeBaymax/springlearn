package com.test.mttransaction;

import com.learn.Application;
import com.learn.mttransaction.AddReqBase;
import com.learn.mttransaction.TransactionalManual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class TransactionalManualTest {

    @Autowired
    private TransactionalManual transactionalManual;

    @Test
    public void test() {
        // 5179ms
        transactionalManual.addReq();
    }
}
