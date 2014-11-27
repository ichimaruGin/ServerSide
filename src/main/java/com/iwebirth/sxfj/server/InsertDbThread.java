package com.iwebirth.sxfj.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwebirth.sxfj.codec.CopyOnReadModelList;
import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.model.RapierModel;
import com.iwebirth.sxfj.service.InsertObjectService;

public class InsertDbThread extends Thread{	
	InsertObjectService insertObjectService;
	public InsertDbThread(InsertObjectService insertObjectService){
		this.insertObjectService = insertObjectService;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long lastTime = System.currentTimeMillis();	
		List<AirJetModel> airJetList = null;
		List<RapierModel> rapierList = null;
		while(true){
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastTime) > 10*1000 || CopyOnReadModelList.getAirJetModelInstance().getSize() > 100 ||CopyOnReadModelList.getRapierModelInstance().getSize() > 100){
				//20s || models.size() > 100
				airJetList = CopyOnReadModelList.getAirJetModelInstance().makeSnapShot();
				rapierList = CopyOnReadModelList.getRapierModelInstance().makeSnapShot();
				lastTime = currentTime;
			}
			if(airJetList != null && airJetList.size() > 0){
				insertObjectService.insertAirJetList(airJetList);
				airJetList = null;
				lastTime = currentTime;
			}
			if(rapierList != null && rapierList.size() > 0){
				insertObjectService.insertRapierList(rapierList);
				rapierList = null;
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
