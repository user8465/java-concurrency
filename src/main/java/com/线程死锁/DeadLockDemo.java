package com.线程死锁;

/**
 * @program: java-concurrency
 * @author: 8465
 * @createTime: 2023-07-10 16:24
 * @description: 死锁：多个线程竞争资源导致互相阻塞的现象
 * 线程A占用resource1对象的锁，导致线程B无法获取resource1对象的锁，所以导致阻塞
 * 用 resource1.wait()和resource1.notify()可以解决此问题
 * sleep()是Thread中的一个静态方法，该方法让当前线程进行睡眠,但并没有释放锁
 *
 * @Version: 1.0
 **/
public class DeadLockDemo {
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
//                    resource1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");

                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
//                    resource1.notify();
                }
            }
        }, "线程 2").start();
    }
}
