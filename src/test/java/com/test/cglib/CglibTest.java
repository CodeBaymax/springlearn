package com.test.cglib;

import com.learn.cglib.CglibProxy;
import com.learn.cglib.Coder;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

public class CglibTest {

    @Test
    public void test() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Coder.class);
        enhancer.setCallback(new CglibProxy());

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./cglib");
        Object object = enhancer.create();
        System.out.println(object.getClass());

        Coder coder = (Coder)object;
        coder.work();
    }
}
