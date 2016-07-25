package com.blackoon.lr.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** 
 * @author zhangy
 * @E-mail:blackoon88@gmail.com 
 * @version 创建时间：2016年7月25日 下午12:47:47 
 * note
 */
public class TimeClient {
	public static void main(String[] args) {
		int port=8080;
		if(args !=null && args.length>0){
			try {
				port=Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
			}
		}
		Socket socket = null;
		BufferedReader in =null;
		PrintWriter out = null;
		try {
			socket =new Socket("127.0.0.1",port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("QUERY TIME ORDER");
			System.out.println("Send order 2 server succeed.");
			String resp = in.readLine();
			System.out.println("Now is :"+resp);
		} catch (Exception e) {
		}finally{
			if(out !=null){
				out.close();
				out = null;
			}
			
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in=null;
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				socket=null;
			}
		}
	}
}
