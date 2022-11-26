package com.nanjing.zhou.juc;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * set集合线程不安全
 */
public class ThreadDemo_08 {
    public static void main(String[] args) {
        //Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                //向集合中添加内容
                set.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
