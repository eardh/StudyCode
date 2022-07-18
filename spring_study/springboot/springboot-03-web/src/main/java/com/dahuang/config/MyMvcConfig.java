package com.dahuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

// 如果我们要扩展springmvc，官方建议我们这样做！
// 如果，你想diy一些定制的功能，只要写这个组件，然后交给springboot，springboot就会帮我们自动转配
// 扩展 springmvc    dispatchservlet
@Configuration
//@EnableWebMvc // 这玩意就导入了一个类 ，DelegatingWebMvcConfiguration:从容器中接管所有的 WebMvcConfigurer
public class MyMvcConfig implements WebMvcConfigurer {

    //ViewResolver 实现视图解析器接口的类，我们就可以把它看作视图解析器
    @Bean //放到bean中
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    //自定义一个自己的视图解析器 MyViewResolver
    //我们写一个静态内部类，视图解析器就需要实现ViewResolver接口
    private static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

    //试图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/dahuang").setViewName("test");
    }
}
