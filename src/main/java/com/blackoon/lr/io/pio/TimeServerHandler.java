package com.blackoon.lr.io.pio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author zhangy
 * @E-mail:blackoon88@gmail.com
 * @version 创建时间：2016年7月25日 上午11:43:01 note
 */
public class TimeServerHandler implements Runnable {
	private Socket socket;

	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out =null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
			String currentTime=null;
			String body = null;
			while(true){
				body = in.readLine();
				if(body == null)
					break;
				System.out.println("the time server receive order : "+body);
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
				out.print(currentTime);
			}
		} catch (Exception e) {
			if(in!=null){
				try {
					in.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
					out=null;
				} catch (Exception e2) {
				}
			}
			if(this.socket!=null){
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				socket=null;
			}
		}
	}

}
