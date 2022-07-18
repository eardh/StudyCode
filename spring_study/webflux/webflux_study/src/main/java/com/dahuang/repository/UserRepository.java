package com.dahuang.repository;

import com.dahuang.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {


    /**
     *  根据年龄段查找用户
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start,int end);
}
