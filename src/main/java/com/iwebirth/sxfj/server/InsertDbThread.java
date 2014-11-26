package com.iwebirth.sxfj.server;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwebirth.sxfj.codec.CopyOnReadModelList;
import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.service.InsertObjectService;

public class InsertDbThread extends Thread{
	private final static Logger log = Logger.getLogger(InsertDbThread.class);
	@Autowired
	private InsertObjectService insertObjectService;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ApplicationContext ctx = Server.spring;
		long lastTime = System.currentTimeMillis();		
		List<AirJetModel> airJetList = null;
		while(true){
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastTime) > 10*1000 || CopyOnReadModelList.getAirJetModelInstance().getSize() > 100){
				//10s || models.size() > 100
				airJetList = CopyOnReadModelList.getAirJetModelInstance().makeSnapShot();
				lastTime = currentTime;
			}
			if(airJetList != null && airJetList.size() > 0){
				insertObjectService.insertAirJetList(airJetList);
				airJetList = null;
				lastTime = currentTime;
			}
			try {
				Thread.sleep(2*1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
