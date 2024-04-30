package com.test.transactional;

import com.learn.Application;
import com.learn.transactional.required.ServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class RequiredTest {
    @Autowired
    private ServiceA serviceA;

    @Test
    public void test() {
        serviceA.addReq();
    }
}
