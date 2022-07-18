package com.dahuang.lambda_stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo4 {

    public static void main(String[] args) {
        String str = "my name is 008";

        // 使用并行流
        str.chars()
                .parallel()
                .forEach(i -> System.out.print((char) i));

        // 使用forEachOrdered保证顺序
        System.out.println();
        str.chars()
                .parallel()
                .forEachOrdered(i -> System.out.print((char) i));

        // collect 收集元素
        System.out.println();
        List<String> collect = Stream.of(str.split(" "))
                .collect(Collectors.toList());
        System.out.println(collect);

        // 使用reduce拼接字符串
        Optional<String> reduce = Stream.of(str.split(" "))
                .reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce.orElse("")); // 判断是否有空串

        // 带有初始值的reduce
        String reduce1 = Stream.of(str.split(" "))
                .reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce1);

        // 计算所有单词总长度
        Integer reduce2 = Stream.of(str.split(" "))
                .map(s -> s.length())
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(reduce2);

        // max 使用
        Optional<String> max = Stream.of(str.split(" "))
                .max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());

        // 使用findFirst短路操作
        OptionalInt first = new Random()
                .ints()
                .findFirst();
        System.out.println(first.getAsInt());
    }
}
