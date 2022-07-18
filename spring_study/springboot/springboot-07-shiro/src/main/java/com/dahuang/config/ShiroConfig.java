package com.dahuang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //第一步
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /**
         * anon:例子/admins/**=anon 没有参数，表示可以匿名使用。
         * authc:例如/admins/user/**=authc表示需要认证(登录)才能使用，没有参数
         * roles(角色)：例子/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，
         * 并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
         * perms（权限）：例子/admins/user/**=perms[user:add:*],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
         * rest：例子/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
         * port：例子/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString
         * 是你访问的url里的？后面的参数。
         * authcBasic：例如/admins/user/**=authcBasic没有参数表示httpBasic认证
         * ssl:例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
         * user:例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
         */

        Map<String, String> filterMap = new HashMap<>();

        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        bean.setLoginUrl("/toLogin");

        //未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    //第二部
    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        System.out.println(userRealm);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //第一步
    //创建 realm 对象，需要自定义类
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合 ShiroDialect ：用来整合 shiro和thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
