package com.nanjing.zhou.juc;

import java.util.concurrent.TimeUnit;

class Phone15 {

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

public class ThreadDemo_15 {

    public static void main(String[] args) throws Exception {

        Phone15 phone = new Phone15();
        Phone15 phone2 = new Phone15();

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
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
