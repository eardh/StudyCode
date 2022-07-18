package com.dahuang.reactive;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FlowDemo1 {

    public static void main(String[] args) throws InterruptedException {

        // 1、定义发布者，发布的数据类型是Integer，直接使用jdk自带的，它实现了Publisher的接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2、定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {

            private Flow.Subscription subscription;

            // 3、发布者和订阅者建立订阅关系
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("建立订阅者关系");
                this.subscription = subscription;
                this.subscription.request(1);
            }

            // 业务处理
            @Override
            public void onNext(Integer item) {
                System.out.println("接收到数据：" + item);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

        //  4、建立订阅
        publisher.subscribe(subscriber);

        // 5、生产数据并发布
        // submit是一个block方法
        Stream.generate(() -> new Random().nextInt(100))
                .peek(i -> {
                    System.out.println("发布数据：" + i);
                    publisher.submit(i);
                })
                .limit(260)
                .count();

        // 主线程延时处理，否则没有消息就退出
        Thread.currentThread().join(1000);
    }
}
