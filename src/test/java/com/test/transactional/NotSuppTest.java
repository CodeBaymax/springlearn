package com.test.transactional;

import com.learn.Application;
import com.learn.transactional.notsupported.NotSuppServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class NotSuppTest {
    @Autowired
    private NotSuppServiceA notSuppServiceA;

    @Test
    public void test() {
        notSuppServiceA.addReq();
    }

    @Test
    public void testNoTrans() {
        notSuppServiceA.addReqNoTrans();
    }

    @Test
    public void testBExp() {
        notSuppServiceA.addReqBExp();
    }
}
