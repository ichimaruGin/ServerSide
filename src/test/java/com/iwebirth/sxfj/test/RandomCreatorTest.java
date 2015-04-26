package com.iwebirth.sxfj.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.model.RapierModel;
import com.iwebirth.sxfj.service.InsertObjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-hibernate.xml")
public class RandomCreatorTest {
	@Autowired
	InsertObjectService insertObjectService;
	/**
	 * 每个30s向本地表插入一个airjet
	 * **/
	@Test
	public void startAirjetModelRandomInsert(){
		while(true){
			AirJetModel model = RandomModelCreator.createRandomAirjetModel();
			insertObjectService.insertSingleModel(model);
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 每个30s向本地表插入一个rapier
	 * **/
	@Test
	public void startRapierModelRandomInsert(){
		while(true){
			RapierModel model = RandomModelCreator.createRandomRapierModel();
			insertObjectService.insertSingleModel(model);
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
}
