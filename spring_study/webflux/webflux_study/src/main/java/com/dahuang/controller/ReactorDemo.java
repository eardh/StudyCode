package com.dahuang.controller;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class ReactorDemo {

    public static void main(String[] args) {

        // reactor = jdk8 stream + jdk9 reactive stream
        // Mono  0-1个元素
        // Flux  0-N个元素
        String[] str = {"123","456","789"};

        // 2、定义订阅者
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {

            private Subscription subscription;

            // 3、发布者和订阅者建立订阅关系
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("建立订阅者关系");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            // 业务处理
            @Override
            public void onNext(Integer item) {
                System.out.println("接收到数据：" + item);
                subscription.request(1);
            }

            // 错误处理
            @Override
            public void onError(Throwable throwable) {
                System.out.println("接收错误");
            }

            // 接收数据完成
            @Override
            public void onComplete() {
                System.out.println("数据处理完成");
            }
        };

        // 这里就是jdk8的stream
        Flux.fromArray(str).map(s -> Integer.parseInt(s))
                // 最终操作，这里就是jdk9的reactive stream
                .subscribe( subscriber );
    }
}
