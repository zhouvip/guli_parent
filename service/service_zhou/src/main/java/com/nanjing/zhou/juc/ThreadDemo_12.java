package com.nanjing.zhou.juc;

import java.util.concurrent.TimeUnit;

class Phone12 {

    public synchronized void sendSMS() throws Exception {
        TimeUnit.SECONDS.sleep(4);//或者Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"---发短信---sendSMS");
    }


    //这里没有锁，不是同步方法，不受锁的影响
    public void getHello(){
        System.out.println(Thread.currentThread().getName()+"------getHello");
    }
}

public class ThreadDemo_12 {

    public static void main(String[] args) throws Exception {

        Phone12 phone12 = new Phone12();

        new Thread(() -> {
            try {
                phone12.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone12.getHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
