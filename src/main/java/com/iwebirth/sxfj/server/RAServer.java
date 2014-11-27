package com.iwebirth.sxfj.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwebirth.sxfj.codec.MyProtocolCodecFactory;
import com.iwebirth.sxfj.codec.WhitelistFilter;
import com.iwebirth.sxfj.service.InsertObjectService;

/**
 * 剑杆织机启动
 * 端口为6667
 * **/
public class RAServer {
	private static Logger log = Logger.getLogger(RAServer.class);
	public static InsertDbThread insertThread;
	private static ApplicationContext spring;	
	static{
		spring = new ClassPathXmlApplicationContext("classpath:spring-hibernate.xml");
	}
	public static void main(String[] args){
		IoAcceptor ioAcceptor = new NioSocketAcceptor();
		//ioAcceptor.getFilterChain().addFirst("whiteListFilter", new ReferenceCountingFilter(spring.getBean(WhitelistFilter.class))); //白名单过滤器
		ioAcceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MyProtocolCodecFactory(Charset.forName("UTF-8"))));  //协议解码器
		ioAcceptor.setHandler(new ServerIoHandler()); //业务处理
		ioAcceptor.getSessionConfig().setReadBufferSize(2048);
		ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 5*60); //读空闲，若终端超过5分钟未响应服务端请求，则触发读空闲事件，结束会话。
		ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE, 60); //写空闲，若距离上次Server发送请求空闲时间超过1分钟，则触发写空闲，再向终端发送一次数据请求 RA_DATA_REQUEST
		try {
			ioAcceptor.bind(new InetSocketAddress(6667));
			insertThread = new InsertDbThread(spring.getBean(InsertObjectService.class));
			insertThread.start();
			System.out.println("server start @port6667");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(insertThread != null && insertThread.isAlive()){
				insertThread = null;
			}	
			log.error("server启动错误，错误异常: "+e.getMessage());
			System.exit(0);
		}
	}
}
