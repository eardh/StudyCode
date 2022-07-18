package com.eardh.test;

import com.eardh.server.service.HelloService;
import com.eardh.server.service.ServicesFactory;

public class TestServicesFactory {
    public static void main(String[] args) {
        HelloService service = ServicesFactory.getService(HelloService.class);
        System.out.println(service.sayHello("hi"));
    }
}
