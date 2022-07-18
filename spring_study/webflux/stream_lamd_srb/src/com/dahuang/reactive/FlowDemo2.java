package com.dahuang.reactive;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Stream;

public class FlowDemo2 {

    public static void main(String[] args) throws InterruptedException {
        // 1.创建一个发布者
        SubmissionPublisher publisher = new SubmissionPublisher();

        // 2.创建处理器
        ReactiveProcessor processor = new ReactiveProcessor();

        // 3.发布者与中间处理器建立订阅关系
        publisher.subscribe(processor);

        // 2.创建一个订阅者
        Flow.Subscriber subscriber = new Flow.Subscriber() {

            Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("订阅者与中间处理器建立关系");
                this.subscription = subscription;
                subscription.request(1); // 第一次需要，发布者发送消息给订阅者
            }

            @Override
            public void onNext(Object o) {
                System.out.println("接收数据：" + o);
                // 业务处理
                subscription.request(5); // 背压，订阅者可以接受多少数据
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("接受数据完成");
            }
        };

        // 3.中间处理器与订阅者建立关系
        processor.subscribe(subscriber);

        // 4.发送数据
        Stream.generate(() -> new Random().nextInt(100))
                .peek(i -> {
                    System.out.println("发布数据：" + i);
                    publisher.submit(i);
                })
                .limit(100)
                .count();

        Thread.currentThread().join(1000);
    }

}

// 中间处理器
class ReactiveProcessor<T> extends SubmissionPublisher<T> implements Flow.Processor<T, T> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Processor建立订阅者模式");
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(T s) {
        System.out.println("Processor接收数据：" + s + ";处理中...,发送：" + s);
        // 中间处理
        this.submit(s); // 发给订阅者
        // 背压实现核心
        this.subscription.request(100);

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Processor出现异常");
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("Processor数据接收完成");
    }
}
