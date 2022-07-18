package com.dahuang.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestfulController {

    //原来的  ：  http://localhost:9090/springmvc_03/add?a=1&b=2
    @RequestMapping("/add1")
    public String test1(int a, int b, Model model){

        int res = a + b;
        model.addAttribute("msg","结果为："+res);
        return "test";
    }


    //RestFul风格 :  http://localhost:9090/springmvc_03/add/a/b
    @RequestMapping(value = "/add2/{a}/{b}",method = RequestMethod.GET)
    public String test2(@PathVariable int a,@PathVariable int b, Model model){

        int res = a + b;
        model.addAttribute("msg","结果为："+res);
        return "test";
    }
}
