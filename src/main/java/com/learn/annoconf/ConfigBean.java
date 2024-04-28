package com.learn.annoconf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ConfigBean {

    @Bean(initMethod = "initMethod")
    public User userAnno() {
        return new User();
    }

    @Bean(name = "user123")
    public User user() {
        return new User();
    }

    @Bean({"u1", "u2"})
    public User user1() {
        return new User();
    }
}
