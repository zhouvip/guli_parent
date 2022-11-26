package com.nanjing.zhou.juc;

import java.util.concurrent.Callable;

//比较两个接口
//实现Runnable接口
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

//实现Callable接口
class MyThread2 implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in callable");
        return 200;
    }
}

public class Callable_19 {

    public static void main(String[] args) {
        //Runnable接口创建线程
        new Thread(new MyThread1(),"AA").start();

        //Callable接口这样写行不通，报错
        //new Thread(new MyThread2(),"BB").start();
    }

}
