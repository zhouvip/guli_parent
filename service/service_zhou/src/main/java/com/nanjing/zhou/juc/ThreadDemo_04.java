package com.nanjing.zhou.juc;

//第一步 创建资源类，定义属性和操作方法
class Share{
    //初始值
    private int number = 0;
    //+1的方法
    public synchronized void add() throws InterruptedException {
        //第二步 判断 干活 通知
        while(number != 0){
            //判断number的值是否是0，如果不是0，等待
            this.wait();
        }
        //如果number是0，+1
        number++;
        System.out.println(Thread.currentThread().getName()+" ===== "+number);
        //通知其他的线程
        this.notifyAll();
    }
    //-1的方法
    public synchronized void delete() throws InterruptedException {
        //判断
        while(number != 1){
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName()+" ===== "+number);
        //通知其他的线程
        this.notifyAll();
    }

}
public class ThreadDemo_04 {
    public static void main(String[] args) {
        //第三步 创建多个线程，调用资源类的操作方法
        Share share = new Share();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"刘德华").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"张学友").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"黎明").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"郭富城").start();
    }

}
