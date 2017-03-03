package com.blackoon.lr.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/** 
 * @author zhangy
 * @E-mail:blackoon88@gmail.com 
 * @version 创建时间：2016年8月22日 下午5:18:01 
 * note
 */
public class TimeServer {
	public void bind(int port) throws Exception{
		//配置服务器的NIO线程组
		EventLoopGroup bossGroup =new NioEventLoopGroup();
		EventLoopGroup workerGroup =new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childHandler(new ChildChannelhandler());
			//绑定端口，同步等待成功
			ChannelFuture f=b.bind(port).sync();
			//等待服务器监听端口关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	private class ChildChannelhandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			arg0.pipeline().addLast(new TimeServerHandler());
		}
	}
	
	public static void main(String[] args) throws Exception{
		int port = 8080;
		if(args !=null &&args.length >0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		new TimeServer().bind(port);
	}
}
