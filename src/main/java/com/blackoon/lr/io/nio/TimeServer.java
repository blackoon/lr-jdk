package com.blackoon.lr.io.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


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
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
	}
}
