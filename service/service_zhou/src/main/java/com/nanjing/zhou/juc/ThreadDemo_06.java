package com.nanjing.zhou.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//第一步 创建资源类，定义属性和操作方法
class LockShareResoure{
    //定义标志位 1代表AA线程 2代表BB线程 3代表CC线程 4代表DD线程
    private int flag = 1;

    //创建Lock锁
    private Lock lock = new ReentrantLock();

    //创建3个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印5次的方法，参数loop代表第几轮
    public void print5(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //第二步 判断 干活 通知
            while(flag != 1){
                //如果不是1，等待
                c1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+" ===== "+i+"===轮数==="+loop);
            }
            //通知，先修改标志位，然后通知BB线程
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    //打印10次的方法，参数loop代表第几轮
    public void print10(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //第二步 判断 干活 通知
            while(flag != 2){
                //如果不是2，等待
                c2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" ===== "+i+"===轮数==="+loop);
            }
            //通知，先修改标志位，然后通知CC线程
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    //打印15次的方法，参数loop代表第几轮
    public void print15(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //第二步 判断 干活 通知
            while(flag != 3){
                //如果不是3，等待
                c3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+" ===== "+i+"===轮数==="+loop);
            }
            //通知，先修改标志位，然后通知AA线程
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo_06 {
    public static void main(String[] args) {
        //第三步 创建多个线程，调用资源类的操作方法
        LockShareResoure lockShareResoure = new LockShareResoure();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    lockShareResoure.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    lockShareResoure.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    lockShareResoure.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

    }

}
