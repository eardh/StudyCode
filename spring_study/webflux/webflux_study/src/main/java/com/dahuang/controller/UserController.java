package com.dahuang.controller;

import com.dahuang.domain.User;
import com.dahuang.repository.UserRepository;
import com.dahuang.util.CheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     *  以数组形式一次性返回数据
     * @return
     */
    @GetMapping("/")
    public Flux<User> getAllUser() {
        return repository.findAll();
    }


    /**
     *  以SSE形式多次返回数据
     * @return
     */
    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAllUser() {
        return repository.findAll();
    }


    /**
     *  新增数据
     * @param user
     * @return
     */
    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        // spring data jpa 里面，新增和修改都是save.有id是修改，id为空是新增
        // 根据实际情况是否置空id
        user.setId(null);
        CheckUtil.checkName(user.getName());
        return this.repository.save(user);
    }

    /**
     *  根据Id删除用户
     *  存在返回200，不存在返回404
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        // deleteByID，没有返回值，不能判断数据是否存在
        // this.repository.deleteById(id)
        return this.repository.findById(id)
                // 操作数据并返回一个Mono，这是用flatMap
                // 如果不操作数据，只是数据转换，使用map
                .flatMap(user -> this.repository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    /**
     *  修改数据
     *  存在的时候返回200和修改后的数据，不存在的时候返回404
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(
            @PathVariable("id") String id,
            @Valid @RequestBody User user
    ){
        CheckUtil.checkName(user.getName());
        return this.repository.findById(id)
                // flatMap操作数据
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.repository.save(u);
                })
                // map转换数据
                .map(u -> new ResponseEntity<User>( u , HttpStatus.OK ))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     *  查找用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findUserById(
            @PathVariable("id") String id
    ) {
        return this.repository.findById(id)
                .map( u -> new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 查找指定年龄段用户
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAge(
            @PathVariable("start") int start,
            @PathVariable("end") int end
    ) {
        return this.repository.findByAgeBetween(start,end);
    }

    @GetMapping(value = "/stream/age/{start}/{end}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(
            @PathVariable("start") int start,
            @PathVariable("end") int end
    ) {
        return this.repository.findByAgeBetween(start,end);
    }
}
