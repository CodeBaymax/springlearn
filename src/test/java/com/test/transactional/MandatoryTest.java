package com.test.transactional;

import com.learn.Application;
import com.learn.transactional.mandatory.MandatoryServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class MandatoryTest {
    @Autowired
    private MandatoryServiceA mandatoryServiceA;
    @Test
    public void test() {
        mandatoryServiceA.addReq();
    }

    @Test
    public void testNoTrans() {
        mandatoryServiceA.addReqNoTrans();
    }
}
