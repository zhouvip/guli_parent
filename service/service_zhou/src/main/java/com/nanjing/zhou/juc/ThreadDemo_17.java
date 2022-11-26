package com.nanjing.zhou.juc;

class Phone17 {

    // static 静态方法
    // 类一加载就有了！锁的是Class

    // 静态的同步方法 锁的是 Class 类模板
    public static synchronized void sendSMS() throws Exception {
        //TimeUnit.SECONDS.sleep(4);//或者Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"---发短信---sendSMS");
    }

    // 普通的同步方法  锁的调用者
    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName()+"---发邮件---sendEmail");
    }
}

public class ThreadDemo_17 {

    public static void main(String[] args) throws Exception {

        Phone17 phone = new Phone17();
        Phone17 phone2 = new Phone17();

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
