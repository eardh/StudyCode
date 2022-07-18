package com.dahuang.lambda_stream;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class LambdaDemo1 {

    public static void main(String[] args) {
        interface1 t1 = (t) -> {
            return t * t;
        };
        System.out.println(t1.doubleNum(10));
        System.out.println(t1.add(10, 10));

        Dog dog = new Dog();
        BiFunction<Dog, Integer, String> t3 = Dog::eat;
        System.out.println(t3.apply(dog, 10));

        Supplier<Dog> supplier = Dog::new;
        Dog dog1 = supplier.get();

    }
}

class Dog {
    private String name;

    String eat(int a) {
        return "吃屎：" + a + "kg";
    }

    public Dog() {
        System.out.println("狗狗");
    }

    public Dog(String name) {
        this.name = name;
    }
}

@FunctionalInterface
interface interface1 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}
