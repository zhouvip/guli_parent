package com.nanjing.zhou.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//第一步 创建资源类，定义属性和操作方法
class LockShare{
    //初始值
    private int number = 0;
    //创建Lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //+1的方法
    public void add() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //第二步 判断 干活 通知
            while(number != 0){
                //判断number的值是否是0，如果不是0，等待
                condition.await();
            }
            //如果number是0，+1
            number++;
            System.out.println(Thread.currentThread().getName()+" ===== "+number);
            //通知其他的线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    //-1的方法
    public synchronized void delete() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断
            while(number != 1){
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+" ===== "+number);
            //通知其他的线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo_05 {
    public static void main(String[] args) {
        //第三步 创建多个线程，调用资源类的操作方法
        LockShare lockShare = new LockShare();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    lockShare.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"刘德华").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    lockShare.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"张学友").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    lockShare.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"黎明").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    lockShare.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"郭富城").start();
    }

}
