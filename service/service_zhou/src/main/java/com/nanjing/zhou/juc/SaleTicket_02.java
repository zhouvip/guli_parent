package com.nanjing.zhou.juc;

class Ticket{
    //票的数量
    private int number = 300;
    //操作方法：卖票
    public synchronized void sale(){
        //判断是否有票
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+" ：卖出："+(number--)+" 剩下："+number);
        }
    }
}

public class SaleTicket_02 {
    //创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        //创建Ticket对象
        Ticket ticket = new Ticket();
        //创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票的方法
                for (int i = 0; i < 300; i++) {
                    ticket.sale();
                }
            }
        },"刘德华").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票的方法
                for (int i = 0; i < 300; i++) {
                    ticket.sale();
                }
            }
        },"张学友").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票的方法
                for (int i = 0; i < 300; i++) {
                    ticket.sale();
                }
            }
        },"黎明").start();
    }
}
