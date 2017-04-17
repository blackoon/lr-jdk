package com.blackoon.lr.book.javaoptimization.six;  

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.Vector;

/** 
 * @author zhangy
 * @E-mail:blackoon88@gmail.com 
 * @version 创建时间：2017年3月14日 下午5:33:28 
 * note
 */
public class DemoCode {
	public static class HoldCPUTask implements Runnable{
		public static Object[] lock=new Object[100];
		public static Random r =new Random();
		static{
			for(int i=0;i<lock.length;i++){
				lock[i]=new Object();
			}
		}
		@Override
		public void run() {
//			int loop=0;
			while(true){
				//随机占用CPU资源
				int loopNum=(int)(Math.random()*100);
				int a[] =new int[loopNum];
				for(int i=0;i<loopNum;i++){
					a[i]=(int)(Math.random()*100);
				}
				//开始冒泡方式排序
				for(int i=1;i<loopNum;i++){
					for(int j=0;j<loopNum-i;j++){
						if(a[j]>a[j+1]){
							//比较交换相邻元素
							int temp;
							temp=a[j];
							a[j]=a[j+1];
							a[j+1]=temp;
						}
					}
				}
				
				//随机占用磁盘I/O
				int fileloop=(int)(Math.random()*10000);
				try {
					FileOutputStream fos =new FileOutputStream(new File("temp.txt"));
					for(int i=0;i<fileloop;i++){
						fos.write(i);
					}
					fos.close();
					@SuppressWarnings("resource")
					FileInputStream fis= new FileInputStream(new File("temp.txt"));
					while(fis.read()!=-1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//随机开始持有锁
				int x=(int)(Math.random()*100);
				synchronized (lock[x]) {
					if(x%2==0){
						try {
							lock[x].wait(r.nextInt(10));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						lock[x].notifyAll();//通知
					}
				}
				
				//随机开始占用内存
				int memSize=(int)(Math.random()*100);
				Vector<byte[]> v =new Vector<>();
				for(int i=0;i<=10;i++){
					byte[] b = new byte[memSize*memSize];
					v.add(b);
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
	}
	public static void main(String[] args) {
		int threadNum =(int)(Math.random()*100);
		System.out.println(threadNum);
		for(int i=0;i<threadNum;i++){
			new Thread(new HoldCPUTask()).start();
		}
	}
}
  