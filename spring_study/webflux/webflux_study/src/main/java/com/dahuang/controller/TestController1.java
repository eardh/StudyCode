package com.dahuang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class TestController1 {

    @RequestMapping("/t1")
    private String test1 () {
        log.info("test1 start");
        String result = creatStr();
        log.info("test1 end");
        return result;
    }


    /**
     * 返回 0-1个
     * @return
     */
    @RequestMapping("/t2")
    private Mono<String> test2 () {
        log.info("test2 start");
        Mono<String> result = Mono.fromSupplier( () -> creatStr()) ;
        log.info("test2 end");
        return result;
    }

    /**
     *  返回 0-N个
     *  produces返回内容响应形式
     * @return
     */
    @RequestMapping(value = "/t3",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> test3 () {
        Flux<String> result = Flux.fromStream(IntStream.range(1,5).mapToObj( o -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Flux data-->" + o;
        })) ;
        return result;
    }

    private String creatStr () {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some thing";
    }

}
