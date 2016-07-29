package com.blackoon.lr.io.net.aio;

import java.io.IOException;

/** 
 * @author zhangy
 * @E-mail:blackoon88@gmail.com 
 * @version 创建时间：2016年7月25日 上午11:30:16 
 * 同步阻塞
 */
public class TimeServer {
	
	public static void main(String[] args) throws IOException {
		int port = 8080;
		if(args != null && args.length > 0){
			try {
				port=Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
			}
		}
		AsyncTimeServerHandler timeServer=new AsyncTimeServerHandler(port);
		new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
	}
}
