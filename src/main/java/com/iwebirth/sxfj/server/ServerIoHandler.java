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
import com.iwebirth.sxfj.model.RapierModel;
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
			case JParam.RA_CONNECT_COMMAND:
				String msg = (String)message;
				String machineSno = msg.substring(8, 12);//机器型号
				if(sessionMap.containsKey(machineSno)){       //同一个客户端重复连接，则关闭旧会话 
					IoSession oldSession = (IoSession)sessionMap.get(machineSno);
					if(!oldSession.isClosing() || oldSession.isConnected())
						oldSession.close(true);
				}
				sessionMap.put(machineSno, session);            
				session.setAttribute("name", machineSno); //为会话增添名字(机器的名字)
				
				if(machineSno.startsWith("AI")){
					session.write(JParam.DATA_CONNECT_OK);
					ClearTaskManager.addSession(session);     //清零task中添加该session(仅针对喷气织机)
				}
				else{
					session.write(JParam.RA_DATA_REQUEST);
				}
				break;
			case JParam.AIR_DATA_COMMAND:
				AirJetModel aiModel = (AirJetModel)message;
				CopyOnReadModelList.getAirJetModelInstance().add(aiModel);
				break;
			case JParam.RA_DATA_COMMAND:
				RapierModel raModel = (RapierModel)message;
				CopyOnReadModelList.getRapierModelInstance().add(raModel);
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
		if(session != null && ((String)session.getAttribute("name")).startsWith("AI"))
			ClearTaskManager.removeSession(session);//喷气织机需要从清零任务会话中移除
		if(session != null)
			removeSessionFromMap(session);  //从会话map中移除该会话		
		showSessionMap();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		if(status == IdleStatus.READER_IDLE){
			//读空闲（对于喷气织机的终端来讲，由于是终端主动发送数据给Server，故只设置了读空闲触发。若终端长时间未向Server端发数据，Server会主动断开连接）
			//读空闲（对于剑杆织机的终端来讲，Server端隔一段时间主动向终端请求数据。若终端长时间未响应请求，Server会主动断开连接）
			session.close(true);
		}
		if(status == IdleStatus.WRITER_IDLE){
			//写空闲（对于剑杆织机的终端来讲，需要Server端主动发送数据请求。写空闲的间隔就作为数据请求的间隔）
			session.write(JParam.RA_DATA_REQUEST);
		}
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
		sb.append("当前存在会话--->");
		for(Iterator<String> iter = keys.iterator();iter.hasNext();){
			String key = iter.next();
			sb.append(key+"---"+sessionMap.get(key).getAttribute("name")).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	
}
