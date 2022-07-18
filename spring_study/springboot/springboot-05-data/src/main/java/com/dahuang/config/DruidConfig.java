package com.dahuang.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    //后台监控：web.xml  ,ServletRegistrationBean
    // 因为springboot 内置了servlet容器，所以没有 web.xml，替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登录，账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();

        //增加配置
        initParameters.put("loginUsername", "admin"); //登录key， 是固定的 loginUsername，loginPassword
        initParameters.put("loginPassword", "123456");

        //允许谁可以访问
        initParameters.put("allow", "");

        //禁止访问
        initParameters.put("dahuang", "127.0.0.1");

        bean.setInitParameters(initParameters);
        return bean;
    }

    //filter
    public FilterRegistrationBean wenStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();

        bean.setFilter(new WebStatFilter());

        //可以过滤哪些请求
        Map<String, String> initParameters = new HashMap<>();

        //这些东西不进行统计
        initParameters.put("exclusioons", "*.js,*.css,/druid/");

        bean.setInitParameters(initParameters);
        return bean;
    }
}
