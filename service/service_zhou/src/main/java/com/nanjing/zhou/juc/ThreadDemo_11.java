package com.nanjing.zhou.juc;

import java.util.concurrent.TimeUnit;

class Phone1 {

    public synchronized void sendSMS() throws Exception {
        //TimeUnit.SECONDS.sleep(4);//或者Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"---发短信---sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);//或者Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"---发邮件---sendEmail");
    }

}

public class ThreadDemo_11 {

    public static void main(String[] args) throws Exception {

        Phone1 phone1 = new Phone1();

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
                phone1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
