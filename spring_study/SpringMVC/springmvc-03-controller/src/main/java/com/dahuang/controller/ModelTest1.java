package com.dahuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
1、不使用视图解析器的转发及重定向操作，需要【forward:】和【redirect:】，注意路径问题

2、使用视图解析器操作：
 转发不需要：【forward:】
 重定向需加：【redirect:】
 */

@Controller
public class ModelTest1 {

    //转发一
    @RequestMapping("/m1/t1")
    public String test1(Model model){
        model.addAttribute("msg","ModelTest1");
        return "/WEB-INF/jsp/test.jsp";
    }

    //转发二
    @RequestMapping("/m1/t2")
    public String test2(Model model){
        model.addAttribute("msg","ModelTest2");
        return "forward:/WEB-INF/jsp/test.jsp";
    }

    //重定向
    @RequestMapping("/m1/t3")
    public String test3(Model model){
        model.addAttribute("msg","ModelTest3");
        return "redirect:/index.jsp";
    }
}
