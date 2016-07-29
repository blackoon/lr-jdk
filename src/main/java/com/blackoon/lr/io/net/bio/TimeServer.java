package com.blackoon.lr.io.net.bio;

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
		ServerSocket server = null;
		try {
			server =new ServerSocket(port);
			System.out.println("the time server is start in port : "+port);
			Socket socket = null;
			while(true){
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} finally{
			if(server!=null){
				System.out.println("the time server close");
				server.close();
				server = null;
			}
		}
	}
}
