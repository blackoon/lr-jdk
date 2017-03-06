package com.blackoon.lr.jdk.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhangy
 * @E-mail:blackoon88@gmail.com
 * 一个生产者 多个消费者
 * @version 创建时间：2017年3月6日 下午2:54:19 用ArrayBlockingQueue解决生产者消费者问题；默认使用的是非公平锁
 * 
 *          take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,
 *          阻断进入等待状态直到Blocking有新的对象被加入为止，若请求不到此线程被加入阻塞队列；
 * 
 *          如果使用公平锁，当有内容可以消费时，会从队首取出消费者线程进行消费(即等待时间最长的线程)
 * 
 *          add(anObject):把anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true
 *          ,否则造成异常
 */
public class TestBlockingQueues {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(20);
		Thread pro = new Thread(new Producer(queue), "生产者");
		pro.start();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Concumer(queue), "消费者 " + i);
			t.start();
		}

	}
}

class Producer implements Runnable {
	BlockingQueue<String> queue;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		int i = 0;
		while (true) {
			try {
				System.out.println("生产者生产食物， 食物编号为：" + i);
				queue.put(" 食物 " + i++);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("生产者被中断");
			}
		}
	}
}

class Concumer implements Runnable {
	BlockingQueue<String> queue;

	public Concumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(Thread.currentThread().getName() + "消费："+ queue.take());
			} catch (InterruptedException e) {
				System.out.println("消费者被中断");
			}
		}
	}
}