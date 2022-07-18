package com.dahuang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//AOP 拦截器
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //首页所有人可以访问，功能也只有对应有权限的人才能访问
        //请求权限的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认跳到登录页面
        // 跳到 /login页面
        // 定制登录页 .loginPage("/tologin")
        // 登录成功跳回首页 .successForwardUrl("/")
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");

        //开启记住我功能 cookie 默认保存两周
        http.rememberMe();

        //防止网站攻击， get post
        http.csrf().disable();//关闭csrf

        //注销，开启了注销功能
        //注销成功回到首页 logoutSuccessUrl("/")
        http.logout().logoutSuccessUrl("/");
    }

    //认证，springboot 2.1.x 可以直接使用
    //密码编码：PasswordEncoder
    //在 springboot Security 5.x 新增加了加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("dahuang").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
