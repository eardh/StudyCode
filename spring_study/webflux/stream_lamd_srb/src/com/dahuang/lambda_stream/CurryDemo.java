package com.dahuang.lambda_stream;

import java.util.function.Function;

/**
 * 级联表达式和柯里化
 */
public class CurryDemo {

    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> t1 = x -> y -> x + y;
        System.out.println(t1.apply(2).apply(3));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> t2 = x -> y -> z -> x + y + z;
        System.out.println(t2.apply(2).apply(3).apply(4));

        int[] nums = {2, 3, 4};
        Function f = t2;
        for (int i = 0; i < nums.length; i++) {
            if (f instanceof Function) {
                Object obj = f.apply(nums[i]);
                if (obj instanceof Function) {
                    f = (Function) obj;
                } else {
                    System.out.println(obj);
                }
            }
        }
    }
}
