package com.synchronized关键字.修饰实例方法;

/**
 * @Author tanyu
 * @Date 2023/7/26 22:33
 * @PackageName: com.synchronized关键字.修饰实例方法
 * @ClassName: synToStatic
 * @Description: TODO
 * @Version 1.0
 */
public class SynToStatic {
    public static int c = 0;

    public SynToStatic() {

    }
    public synchronized static void incrementC(){
        for (int i = 0; i < 50000; i++) {
            c++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(SynToStatic::incrementC).start();
        new Thread(SynToStatic::incrementC).start();
        Thread.sleep(1000);
        System.out.println(c);
    }
}
