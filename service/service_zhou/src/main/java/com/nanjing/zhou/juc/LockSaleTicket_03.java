package com.nanjing.zhou.juc;

import java.util.concurrent.locks.ReentrantLock;

class LockTicket{
    //票的数量
    private int number = 100;
    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();
    //操作方法：卖票
    public void sale(){
        //上锁
        lock.lock();
        //判断是否有票
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+" ：卖出："+(number--)+" 剩下："+number);
            }
        } finally {
            //解锁
            lock.unlock();
        }

    }
}

public class LockSaleTicket_03 {
    //创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        //创建LockTicket对象
        LockTicket lockTicket = new LockTicket();
        //创建三个线程
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票的方法
                for (int i = 0; i < 300; i++) {
                    ticket.sale();
                }
            }
        },"刘德华").start();*/

        //lambda写法
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lockTicket.sale();
            }
        },"刘德华").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lockTicket.sale();
            }
        },"张学友").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lockTicket.sale();
            }
        },"黎明").start();

    }
}
