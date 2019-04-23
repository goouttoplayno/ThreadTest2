package com.example.threadtest;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    private static final String TAG = "ThreadPoolTest";

    //newCachedThreadPool创建一个可缓存的线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，如无回收，则新建线程
    // 线程池为无限大，当执行第二个任务时第一个任务已经完成，就会重用第一个任务的线程，不用每次新建线程。
    @Test
    public void cacheThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = 1;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    System.out.println(Thread.currentThread().getId());
                    System.out.println();
                }
            });
        }
    }

    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    @Test
    public void fixedThreadPoolTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        //每次执行三个线程，
        final int index = 1;
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(index);
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //创建一个定长线程池，支持定时及周期性任务执行
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

    @Test
    public void scheduledThreadPoolTest1() {

        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
        //延迟三秒执行
    }

    @Test
    public void scheduledThreadPoolTest2(){
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and execute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
        //延迟1秒后每三秒执行一次
    }

    @Test
    // 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行
    public void singleThreadPoolTest(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i =1; i<10; i++){
            final int index = 1;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
