package com.zgm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgm
 * @description
 * @date 2018/10/5 19:26
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello wechat !";
    }

}
