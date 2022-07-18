package com.dahuang.pojo;

/**
 * @author dahuang
 * @date 2021/10/2 9:47
 */
public class Client {

    private int port;

    public Client() {
        System.out.println("构造方法");
    }

    public void setPort(int port) {
        this.port = port;
        System.out.println("setter方法");
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Client{" +
                "port=" + port +
                ", user=" + user +
                '}';
    }

    public void initial() {
        System.out.println("初始化方法");
    }

    public void destroy() {
        System.out.println("销毁方法");
    }

    private User user;

}
