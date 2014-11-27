package com.iwebirth.sxfj.server;

import java.util.ArrayList;
import java.util.Calendar;
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
	//时间间隔
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	public static void addSession(IoSession session){
		if(session != null)
			sessionList.add(session);
	}
	public static void removeSession(IoSession session){
		if(session != null)
			sessionList.remove(session);
	}
	public static void start(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND,0);
		Date firstExecuteTime = c.getTime();
		//如果第一次执行定时任务的时间 小于 当前的时间
		//此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if(firstExecuteTime.before(new Date())){
			firstExecuteTime = addDay(firstExecuteTime, 1);
		}
		timer.schedule(new ClearTask(), firstExecuteTime, PERIOD_DAY); 	
		System.out.println("清零task 初始化成功 首次清零时间为:"+firstExecuteTime);
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
	 // 增加或减少天数
	 public static Date addDay(Date date, int num) {
	  Calendar startDT = Calendar.getInstance();
	  startDT.setTime(date);
	  startDT.add(Calendar.DAY_OF_MONTH, num);
	  return startDT.getTime();
	 }
}
