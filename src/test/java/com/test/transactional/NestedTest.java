package com.test.transactional;

import com.learn.Application;
import com.learn.transactional.nested.NestedServiceA;
import com.learn.transactional.never.NeverServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class NestedTest {

    @Autowired
    private NestedServiceA nestedServiceA;

    @Test
    public void test() {
        nestedServiceA.addReq();
    }

    @Test
    public void testNoTrans() {
        nestedServiceA.addReqNoTrans();
    }
}
