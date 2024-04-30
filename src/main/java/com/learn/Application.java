package com.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.learn.dao")
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
