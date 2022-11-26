package com.nanjing.zhou.juc;

class Phone {

    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName()+"---发短信---sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName()+"---发邮件---sendEmail");
    }

}

public class ThreadDemo_10 {

    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();

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
