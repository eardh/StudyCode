package com.dahuang.lambda_stream;

import java.util.stream.IntStream;

public class StreamDemo1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        // 外部迭代
        int sum1 = 0;
        for (int i : nums) {
            sum1 += i;
        }
        System.out.println(sum1);

        // 使用Stream的内部迭代
        // map就是中间操作
        // sum就是终止操作
        int sum2 = IntStream.of(nums).map(i -> i * 2).sum();
        System.out.println(sum2);

        // 惰性求值就是终止没有调用的情况下，中间操作不会执行
        IntStream.of(nums).map(StreamDemo1::doubleNum);
    }

    public static int doubleNum(int i) {
        System.out.println("执行了乘以2");
        return i * 2;
    }
}
