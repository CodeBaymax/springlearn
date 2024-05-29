package com.test.mttransaction;

import com.learn.Application;
import com.learn.mttransaction.AddReqBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = Application.class)
public class AddReqBaseTest {
    @Autowired
    private AddReqBase addReqBase;

    @Test
    public void test() {
        // 7658ms
        addReqBase.addReq();
    }
}
