package com.test.annoconf;

import com.learn.annoconf.ConfigBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ConfigBeanTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);

        String[] beans = context.getBeanDefinitionNames();
        Arrays.stream(beans).forEach(beanName -> {
            Object bean = context.getBean(beanName);
            if (bean.getClass().getCanonicalName().contains("com.learn.annoconf")) {
                System.out.println(bean.getClass().getCanonicalName() + " -- " + beanName);
            }
        });

        System.out.println(context.getBean("configBean"));
        System.out.println(context.getBean("u1"));
        System.out.println(context.getBean("u2"));
        System.out.println(context.getBean("user123"));
    }
}
