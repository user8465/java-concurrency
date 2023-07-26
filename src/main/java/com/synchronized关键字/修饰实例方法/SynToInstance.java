package com.synchronized关键字.修饰实例方法;

/**
 * @Author tanyu
 * @Date 2023/7/26 22:04
 * @PackageName: com.synchronized关键字.修饰实例方法
 * @ClassName: demo
 * @Description: synchronized修饰实例方法时，锁住的是当前对象
 * @Version 1.0
 */
public class SynToInstance {
    public static int c = 0;

    public SynToInstance() {
    }

    public synchronized void incrementC() {
        //可以看到B对象获取到的共享变量c的值不为5万,说明B对象和A对象的锁是分开的
        System.out.println(c);
        for (int i = 0; i < 50000; i++) {
            c++;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SynToInstance synToInstanceA = new SynToInstance();
        SynToInstance synToInstanceB = new SynToInstance();
        new Thread(synToInstanceA::incrementC).start();
        new Thread(synToInstanceB::incrementC).start();
        Thread.sleep(1000);
        System.out.println(c);
    }
}
