package com.volatile关键字;

/**
 * @program: java-concurrency
 * @author: 8465
 * @createTime: 2023-07-26 15:44
 * @description: volatile 关键字能保证变量的可见性，但不能保证对变量的操作是原子性的
 * c++操作是一个复合操作，valatile并不能保证自加操作的原子性
 * * 1.读取 c 的值。
 * * 2.对 c 加 1。
 * * 3.将 c 的值写回内存。
 * 线程A,B同时读到c的值，当线程A执行到步骤2时，线程B已经执行完步骤3,线程A此时执行步骤3，2个线程执行完，最后c也只加了1
 * @Version: 1.0
 **/
public class VolatoleAtomicityDemo {
    //被volatile修饰的
    public volatile static int c = 0;

    //得加synchronized就能保证自加的原子性
    public synchronized static void increase() {
        c++;
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 500; j++) {
                    increase();
                }
            }).start();
        }
        //等待1秒，等上面的线程任务执行完成
        Thread.sleep(1000);
        //得出的结果始终小于2500
        System.out.println(c);
    }
}
