package com.test.transactional;

import com.learn.Application;
import com.learn.transactional.supports.SupportsServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class SupportsTest {
    @Autowired
    private SupportsServiceA supportsServiceA;
    @Test
    public void test() {
        supportsServiceA.addReq();
    }

    @Test
    public void testANoTrans() {
        supportsServiceA.addReqNoTrans();
    }
}
