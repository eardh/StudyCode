package com.dahuang.util;

import com.dahuang.exceptions.CheckException;

import java.util.stream.Stream;

public class CheckUtil {

    private static final String[] INVALID_NAMES = {"admin","guanliyuan"};

    /**
     *  校验名字，不成功抛出校验异常
     * @param name
     */
    public static void checkName(String name) {
        Stream.of(INVALID_NAMES).filter(n -> n.equalsIgnoreCase(name))
                .findAny()
                .ifPresent(n -> {
                    throw new CheckException("name",name);
                });
    }
}
