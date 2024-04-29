package com.learn.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 测试 CglibTest
 */
public class CglibProxy implements MethodInterceptor {

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("上班");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("下班");

        return result;
    }
}
