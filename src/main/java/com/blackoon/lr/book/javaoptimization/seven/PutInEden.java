package com.blackoon.lr.book.javaoptimization.seven;  
/** 
 * @author zhangy
 * @E-mail:blackoon88@gmail.com 
 * @version 创建时间：2017年3月29日 下午3:15:55 
 * 
 * -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn6M
 * 要是年轻代撑不住 可以调大-Xmn
 * -XX
 */
public class PutInEden {
	public static void main(String[] args){
		byte[] b1,b2,b3,b4;//定义变量
		b1=new byte[1024*1024*10];
		b2=new byte[1024*1024*5];
		b3=new byte[1024*1024];
		b4=new byte[1024*1024];
		
	}
}
  