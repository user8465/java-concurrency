package com.volatile关键字;

/**
 * @program: java-concurrency
 * @author: 8465
 * @createTime: 2023-07-26 17:56
 * @description: 禁止指令重排：Volatole修饰的变量，在对这个变量进行读写操作的时候，会通过插入特定的 内存屏障 的方式来禁止指令重排序。
 * 1、为 volatoleReranking 分配内存空间
 * 2、初始化 volatoleReranking
 * 3、将 volatoleReranking 指向分配的内存地址
 * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。
 * 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
 * 例如，线程 A执行了 1 和 3，此时B调用 getVolatoleReranking() 后发现 volatoleReranking 不为空，
 * 因此返回 volatoleReranking，但此时 volatoleReranking 还未被初始化。
 * @Version: 1.0
 **/
public class VolatoleReranking {
    public volatile static VolatoleReranking volatoleReranking;

    public VolatoleReranking(){}

    public static VolatoleReranking getVolatoleReranking() {
        if (volatoleReranking == null){
            synchronized (VolatoleReranking.class){
                if (volatoleReranking == null){
                    volatoleReranking = new VolatoleReranking();
                }
            }
        }
        return volatoleReranking;
    }

    public static void main(String[] args) {
        new Thread(() ->{
            VolatoleReranking volatoleReranking = getVolatoleReranking();
            System.out.println(volatoleReranking);
        }).start();

        new Thread(() ->{
            VolatoleReranking volatoleReranking = getVolatoleReranking();
            System.out.println(volatoleReranking);
        }).start();
    }
}
