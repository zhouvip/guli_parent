package com.nanjing.zhou.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list集合线程不安全
 */
public class ThreadDemo_07 {
    public static void main(String[] args) {
        //List<String> list = new ArrayList<String>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                //向集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
