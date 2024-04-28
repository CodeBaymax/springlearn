package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        String[] beans = context.getBeanDefinitionNames();
        Arrays.stream(beans).forEach(beanName -> {
            Object bean = context.getBean(beanName);
            if (bean.getClass().getCanonicalName().contains("com.learn.annoconf")) {
                System.out.println(bean.getClass().getCanonicalName() + " -- " + beanName);
            }
        });
    }
}
