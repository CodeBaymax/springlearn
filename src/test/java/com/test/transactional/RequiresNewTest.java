package com.test.transactional;

import com.learn.Application;
import com.learn.transactional.requiresnew.RequNewServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class RequiresNewTest {
    @Autowired
    private RequNewServiceA requNewServiceA;
    @Test
    public void test() {
        requNewServiceA.addReq();
    }

    @Test
    public void testBExp() {
        requNewServiceA.addReqBExp();
    }

    @Test
    public void testBExpCatch() {
        requNewServiceA.addReqBExpCatch();
    }
}
