package com.test.transactional;

import com.learn.Application;
import com.learn.transactional.never.NeverServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class NeverTest {
    @Autowired
    private NeverServiceA neverServiceA;

    @Test
    public void test() {
        neverServiceA.addReq();
    }

    @Test
    public void testNoTrans() {
        neverServiceA.addReqNoTrans();
    }
}
