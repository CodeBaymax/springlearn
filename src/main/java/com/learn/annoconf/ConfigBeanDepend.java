package com.learn.annoconf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 有依赖关系的@Configuration
 * 是否有@Configuration 执行testDepend会有不同的输出（Service的对象不同）
 */
@Configuration
public class ConfigBeanDepend {

    @Bean
    public Service service() {
        System.out.println("service 被调用");
        return new Service();
    }

    @Bean
    public Controller controller() {
        System.out.println("controller 被调用");
        return new Controller(this.service());
    }

    @Bean
    public Controller controller1() {
        System.out.println("controller1 被调用");
        return new Controller(this.service());
    }
}
