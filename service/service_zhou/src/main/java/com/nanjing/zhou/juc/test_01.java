package com.nanjing.zhou.juc;

public class test_01 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while(true){

            }
        },"aa");
        //设置守护线程
        t.setDaemon(true);
        t.start();
        System.out.println(Thread.currentThread().getName()+"主线程结束");
    }
}
