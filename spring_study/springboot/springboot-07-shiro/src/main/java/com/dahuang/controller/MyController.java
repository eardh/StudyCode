package com.dahuang.controller;

import com.dahuang.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model, String username, String password) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //执行登录的方法，如果没有异常就说明ok了
        try {
            subject.login(token);

//              用session域判断注销和登录按钮
//            User currentUser = (User) subject.getPrincipal();
//            Session session = subject.getSession();
//            session.setAttribute( "loginUser", currentUser );
            return "redirect:/";
        } catch (UnknownAccountException e) { //用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) { //密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }

    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权不能访问此页面";
    }
}
