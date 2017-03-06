package com.blackoon.lr.jdk.thread;  

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** 
 * @author zhangy
 * @E-mail:blackoon88@gmail.com 
 * @version 创建时间：2017年3月6日 下午3:00:34 
 * 多个生产者 多个消费者
 * 
 * Lock 使用的默认 为非公平锁；condition对象继承了与之相关的锁的共平性特性，如果是公平的锁，
 * 线程会依照FIFO的顺序从Condition.wait中被释放;ArrayBlockingQueue中有一个比较不好的地方，生产者每次生产完之后，都要通知消费者，至于有没有性能损失TODO
 */
public class TestBlockingQueues2 {
	public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(20);
        for (int i = 0; i < 10; i++) {
            Thread pro = new Thread(new Producer2(queue), "生产者" + i);
            pro.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Concumer2(queue), "消费者 " + i);
            t.start();
        }

    }
}
class Producer2 implements Runnable {
    BlockingQueue<String> queue;

    public Producer2(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        int i = 0;
        while (true) {
            try {
               
                    System.out.println(Thread.currentThread().getName()
                            + "生产食物， 食物编号为：" + Thread.currentThread().getName()
                            + i);
                    queue.put(" 食物 " + Thread.currentThread().getName() + i++);
                    Thread.sleep(10000);
               
            } catch (InterruptedException e) {
                System.out.println("生产者被中断");
            }
        }
    }
}

class Concumer2 implements Runnable {
    BlockingQueue<String> queue;

    public Concumer2(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " 请求消费");
            try {
                System.out.println(Thread.currentThread().getName() + "消费："
                        + queue.take());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("消费者被中断");
            }
        }
    }
}
