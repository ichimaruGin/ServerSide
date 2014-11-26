package com.iwebirth.sxfj.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.mina.core.session.IoSession;

import com.iwebirth.sxfj.util.DateParser;
public class ClearTaskManager {
	private static List<IoSession> sessionList = new ArrayList<IoSession>(); 
	private static Timer timer = new Timer();
	public static void addSession(IoSession session){
		if(session != null)
			sessionList.add(session);
	}
	public static void removeSession(IoSession session){
		if(session != null)
			sessionList.remove(session);
	}
	public static void start(){
		timer.schedule(new ClearTask(), 10*1000, 60*60*1000);
	}
	public static void cancel(){
		timer.cancel();
	}
	public static class ClearTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(Iterator<IoSession> iter = sessionList.iterator();iter.hasNext();){				
				IoSession session = iter.next();
				session.write("clear "+session.getAttribute("name")+"@"+DateParser.getCurrentWholeDate(new Date(), "MM-dd HH:mm:ss"));
			}
		}
		
	}
}
