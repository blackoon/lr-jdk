package com.blackoon.lr.jdk.thread;

/**
 * @author zhangy
 * @E-mail:blackoon88@gmail.com
 * @version 创建时间：2017年3月6日 下午2:39:56 
 * 抛出：IllegalMonitorStateException - 如果当前线程不是此对象监视器的所有者
 */
public class WaitTest {
	public static String a = "";// 作为监视器对象

	public static void main(String[] args) throws InterruptedException {
		WaitTest wa = new WaitTest();
		TestTask task = wa.new TestTask();
		Thread t = new Thread(task);
		t.start();
		Thread.sleep(12000);
		for (int i = 5; i > 0; i--) {
			System.out.println("快唤醒挂起的线程************");
			Thread.sleep(1000);
		}
		System.out.println("收到，马上！唤醒挂起的线程************");
		synchronized (a) {
			a.notifyAll();
		}
	}

	class TestTask implements Runnable {

		@Override
		public void run() {
			synchronized (a) {
				try {
					for (int i = 10; i > 0; i--) {
						Thread.sleep(1000);
						System.out.println("我在运行 ***************");
					}
					a.wait();
					for (int i = 10; i > 0; i--) {
						System.out.println("谢谢唤醒**********又开始运行了*******");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
