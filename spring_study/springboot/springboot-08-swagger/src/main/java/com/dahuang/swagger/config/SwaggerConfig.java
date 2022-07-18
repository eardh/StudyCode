package com.dahuang.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2   // 开启swagger2
public class SwaggerConfig {

    //配置 swagger 的 Docket 的 Bean 实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");
        //通过environment.acceptsProfiles来判断是否处在设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())

                //enable(false):是否启动swagger，如果为false，则swagger在浏览器不能访问
                //.enable(false)

                .groupName("大黄")

                .select()
                // RequestHandlerSelectors ，配置要扫描的接口的方式
                // basePackage 指定要扫描的包
                // any() :扫描全部
                // none() ：不扫描
                // withClassAnnotation : 扫描类上的注解，参数是一个注解的反射
                // withMethodAnnotation: 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.dahuang.swagger.controller"))

                // paths() :过滤什么路径
                //.paths(PathSelectors.ant("/dahuang/**"))

                .build();
    }

    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){

        Contact contact = new Contact("大黄","http","915323422@qq.com");

        return new ApiInfo(
                " 大黄日记",
                "无敌",
                "v1.0",
                "http://***",
                contact,
                "Apache2.0",
                "http",
                new ArrayList<>());
    }
}
