package com.indi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 华峰
 * @create: 2020-04-06 11:05
 */

@Controller
//@RequestMapping("/Library")
public class BooksManageController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "HomePage";
    }

    @RequestMapping("/")
    public String test(){
        return "welcome";
    }
}
