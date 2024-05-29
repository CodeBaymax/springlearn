package com.test.mttransaction;

import com.learn.Application;
import com.learn.mttransaction.MtRollBack;
import com.learn.mttransaction.MultiThreadDb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class MtRollBackTest {
    @Autowired
    private MtRollBack mtRollBack;

    @Test
    public void test() {
        // 3821ms
        mtRollBack.addReq();
    }
}
