package com.dahuang.lambda_stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamDemo5 {

    public static void main(String[] args) {

        // 调用parallel产生并行流
//        IntStream.range(1,100)
//                .parallel()
//                .peek(StreamDemo5::debug1)
//                .count();

        // 现在实现这样一个效果：线并行再串行
        // 多次调用parallel/sequential，以最后一次调用为准
//        IntStream.range(1,100)
//                .parallel()
//                .peek(StreamDemo5::debug1)
//                .sequential() // sequential 产生串行流
//                .peek(StreamDemo5::debug2)
//                .count();


        // 并行流使用的线程池：ForkJoinPool.commonPool
        // 默认的线程数为 当前机器的CPU个数
        // 使用这个属性可以修改默认的线程数
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","10");
//        IntStream.range(1,100)
//                .parallel()
//                .peek(StreamDemo5::debug1)
//                .count();

        // 使用自己的线程池，不使用默认线程池，防止任务阻塞
        // 线程名字：ForkJoinPool-1
        ForkJoinPool pool = new ForkJoinPool(5);
        pool.submit(() -> IntStream.range(1, 100)
                .parallel()
                .peek(StreamDemo5::debug1)
                .count());
        pool.shutdown();

        // 防止主线程停止
        synchronized (pool) {
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void debug1(int i) {
        System.out.println(Thread.currentThread().getName() + "debug1-" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug2(int i) {
        System.err.println("debug2-" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
