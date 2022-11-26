package com.nanjing.zhou.juc;

import java.util.concurrent.TimeUnit;

class Phone14 {

    // static 静态方法
    // 类一加载就有了！锁的是Class

    public static synchronized void sendSMS() throws Exception {
        TimeUnit.SECONDS.sleep(4);//或者Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"---发短信---sendSMS");
    }

    public static synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName()+"---发邮件---sendEmail");
    }
}

public class ThreadDemo_14 {

    public static void main(String[] args) throws Exception {

        Phone14 phone = new Phone14();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
