package com.iwebirth.sxfj.server;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.iwebirth.sxfj.codec.CopyOnReadModelList;
import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.util.DateParser;
import com.iwebirth.sxfj.util.JParam;

public class ServerIoHandler extends IoHandlerAdapter {
	private final static Logger log = Logger.getLogger(ServerIoHandler.class);
	private Map<String,IoSession> sessionMap = new HashMap<String,IoSession>();
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		int command = (Integer)session.getAttribute("command");
		switch(command){
			case JParam.AIR_CONNECT_COMMAND: 
				String msg = (String)message;
				String machineSno = msg.substring(8, 12);//机器型号
				if(sessionMap.containsKey(machineSno)){       //同一个客户端重复连接，则关闭旧会话 
					IoSession oldSession = (IoSession)sessionMap.get(machineSno);
					//sessionMap.remove(machineSno); 
					if(!oldSession.isClosing() || oldSession.isConnected())
						oldSession.close(true);
				}
				sessionMap.put(machineSno, session);            
				session.setAttribute("name", machineSno); //为会话增添名字(机器的名字)
				session.write(JParam.DATA_CONNECT_OK);
				ClearTaskManager.addSession(session);     //清零的task中添加该session
				break;
			case JParam.AIR_DATA_COMMAND:
				AirJetModel model = (AirJetModel)message;
				CopyOnReadModelList.getAirJetModelInstance().add(model);
				break;
			
			default:break;
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(DateParser.getCurrentWholeDate(new Date(), "MM-dd HH:mm:ss")+
				"发送<"+message+"> 到"+session.getAttribute("name"));
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		log.info(session.getAttribute("name")+"--->closed");
		removeSessionFromMap(session);  //从会话map中移除该会话
		ClearTaskManager.removeSession(session);
		showSessionMap();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("session@"+session.getId()+"--->opened ip="+session.getRemoteAddress());
	}
	
	public void removeSessionFromMap(IoSession session){
		Set<String> keys = sessionMap.keySet();
		for(Iterator<String> iter = keys.iterator();iter.hasNext();){
			String key = iter.next();
			if(sessionMap.get(key) == session){
				iter.remove();
			}
		}
	}
	public void showSessionMap(){
		Set<String> keys = sessionMap.keySet();
		StringBuilder sb = new StringBuilder();
		sb.append("当前会话: ");
		for(Iterator<String> iter = keys.iterator();iter.hasNext();){
			String key = iter.next();
			sb.append(key+"---"+sessionMap.get(key).getAttribute("name")).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	
}
