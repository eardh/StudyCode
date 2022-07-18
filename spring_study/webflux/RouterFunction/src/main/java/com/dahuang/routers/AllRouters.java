package com.dahuang.routers;

import com.dahuang.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class AllRouters {

    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return RouterFunctions.nest(
                // @RequestMapping("/user")
                RequestPredicates.path("user"),
                // 下面的相当于@GetMapping("/"),得到所有用户
                RouterFunctions.route(RequestPredicates.GET("/"),handler::getAllUser)
                        // 创建用户
                       .andRoute(POST("/").and(accept(MediaType.APPLICATION_JSON)),handler::createUser)
                        // 删除用户
                       .andRoute(DELETE("/{id}"),handler::deleteUserById));

    }
}
