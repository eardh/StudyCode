package com.dahuang.api;

import com.dahuang.annotation.ApiServer;
import com.dahuang.domain.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ApiServer("http://localhost:9090/user")
public interface IUserApi {

    @GetMapping("/")
    Flux<User> getAllUser();

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    @DeleteMapping("/{id}")
    Mono<User> deleteUserById(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);

    @PutMapping("/{id}")
    Mono<User> updateUser(
            @PathVariable("id") String id,
            @RequestBody Mono<User> user
    );
}
