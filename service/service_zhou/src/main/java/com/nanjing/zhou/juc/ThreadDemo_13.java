package com.nanjing.zhou.juc;

import java.util.concurrent.TimeUnit;

class PhoneNew {

    public synchronized void sendSMS() throws Exception {
        TimeUnit.SECONDS.sleep(4);//或者Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"---发短信---sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName()+"---发邮件---sendEmail");
    }
}

public class ThreadDemo_13 {

    public static void main(String[] args) throws Exception {

        /**
         * 两个对象，两个调用者，两把锁！
         */
        PhoneNew phone1 = new PhoneNew();
        PhoneNew phone2 = new PhoneNew();

        new Thread(() -> {
            try {
                phone1.sendSMS();
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
