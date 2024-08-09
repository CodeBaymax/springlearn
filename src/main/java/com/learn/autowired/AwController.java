package com.learn.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AwController {
    @Autowired
    @Qualifier("awService")
    private IAwService iAwService;


}
