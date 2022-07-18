package com.dahuang.lambda_stream;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 验证stream运行机制
 * <p>
 * 1、所有操作链式调用，一个元素只迭代一次
 * 2、每一个中间操作返回一个流，流里面有一个属性sourceStage指向同一个地方，就是链表的头（Head）
 * 3、Head -> nextStage -> nextStage -> ... -> null
 * 4、有状态操作会把无状态操作截断，单独处理
 * 5、并行环境下，有状态的中间操作不一定能并行
 * 6、parallel/sequential 这两个操作也是中间操作（返回stream），但他们不创建流，只修改Head的并行标志
 */
public class RunStream {

    public static void main(String[] args) {

        Random random = new Random();
        // 随机产生数据
        Stream<Integer> stream = Stream.generate(() -> random.nextInt())
                .limit(500) // 产生500个（无限流需要短路操作）
                .peek(s -> print("peek1:" + s)) //第一个无状态操作
                .filter(s -> {
                    print("filter:" + s);
                    return s > 100000;
                }) // 第二个无状态操作
                .sorted((x, y) -> {
                    print("排序:" + x + "," + y);
                    return x.compareTo(y);
                })  // 有状态操作
                .peek(s -> print("peek2:" + s)) //又一个无状态操作
                .parallel();

        stream.count(); // 终止操作

    }

    public static void print(String s) {
        //System.out.println(s);
        System.out.println(Thread.currentThread().getName() + "-->" + s);
        try {
            TimeUnit.SECONDS.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
