package com.test.mttransaction;

import com.learn.Application;
import com.learn.mttransaction.AddReqBase;
import com.learn.mttransaction.MultiThreadDb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class MultiThreadDbTest {

    @Autowired
    private MultiThreadDb multiThreadDb;

    @Test
    public void test() {
        // 4367ms
        multiThreadDb.addReq();
    }
}
