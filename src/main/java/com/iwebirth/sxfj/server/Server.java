package com.iwebirth.sxfj.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwebirth.sxfj.codec.MyProtocolCodecFactory;
import com.iwebirth.sxfj.codec.WhitelistFilter;
import com.iwebirth.sxfj.util.JParam;



public class Server {
	private final static Logger log = Logger.getLogger(Server.class);
	public static InsertDbThread insertThread;
	public static ApplicationContext spring;
	static {
		spring = new ClassPathXmlApplicationContext("classpath:spring-hibernate.xml");
	}
	public static void main(String[] args) throws UnknownHostException{
		WhitelistFilter whitelistFilter = new WhitelistFilter(((JParam)spring.getBean("jParam")).getWhitelistRegex());
		IoAcceptor ioAcceptor = new NioSocketAcceptor();
		ioAcceptor.getFilterChain().addFirst("blacklist", new ReferenceCountingFilter(whitelistFilter));
		ioAcceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MyProtocolCodecFactory(Charset.forName("UTF-8"))));
		ioAcceptor.setHandler(new ServerIoHandler());
		//设置全局会话的属性(当然也可以在handler中单独修改，session.getConfig().setXXX)
		ioAcceptor.getSessionConfig().setReadBufferSize(2048);
		//ioAcceptor.getSessionConfig().setReaderIdleTime(10);//10秒
		//ioAcceptor.getSessionConfig().setWriterIdleTime(10);//10秒	
		try {
			ioAcceptor.bind(new InetSocketAddress(6668));
			insertThread = new InsertDbThread();
			insertThread.start();
			ClearTaskManager.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(insertThread != null && insertThread.isAlive()){
				insertThread = null;
			}	
			ClearTaskManager.cancel();
			log.error("server启动错误，错误异常: "+e.getMessage());
			System.exit(0);
		}
	}
}
