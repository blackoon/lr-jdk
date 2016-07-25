package com.blackoon.lr.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/** 
 * @author zhangy
 * @E-mail:blackoon88@gmail.com 
 * @version 创建时间：2016年7月25日 下午2:33:10 
 * note
 */
public class MultiplexerTimeServer implements Runnable{
	
	private Selector selector;
	private ServerSocketChannel servChannel;
	private volatile boolean stop;
	/**
	 * 初始化多路复用器、绑定监听端口
	 * @param port
	 */
	public MultiplexerTimeServer(int port) {
		try {
			selector =Selector.open();
			servChannel = ServerSocketChannel.open();
			
			servChannel.configureBlocking(false);
			servChannel.socket().bind(new InetSocketAddress(port), 1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port : "+port);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public void stop(){
		this.stop =true;
	}
	
	@Override
	public void run() {
		while(!stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys =selector.selectedKeys();
				Iterator<SelectionKey> it=selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						if(key !=null){
							key.cancel();
							if(key.channel() !=null)
								key.channel().close();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//selector close,所有注册在上面的channel和pipe等资源都会自动去注册并关闭
		if(selector !=null){
			try {
				selector.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()){
			//处理新接入的请求消息
			if(key.isAcceptable()){
				//Accept the new connection
				ServerSocketChannel ssc =(ServerSocketChannel)key.channel();
				SocketChannel sc=ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()){
				//read the data
				SocketChannel sc =(SocketChannel)key.channel();
				ByteBuffer readBuffer  = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if(readBytes >0){
					readBuffer.flip();
					byte[] bytes= new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("The time server receive order : "+body);
					String currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
					doWrite(sc,currentTime);
				}else if(readBytes <0){
					//对端链路关闭
					key.cancel();
					sc.close();
				}else{
					;
				}
			}
		}
	}
	private void doWrite(SocketChannel sc, String response) {
		if(response !=null){
			
		}
	}

}
