package com.iwebirth.sxfj.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwebirth.sxfj.service.InsertObjectService;
import com.iwebirth.sxfj.util.RandomModelCreator;

/**
 * 模拟参数启动的主类
 * **/
public class MockDataCreator {
	
	public static ApplicationContext spring;
	static {
		spring = new ClassPathXmlApplicationContext("classpath:spring-hibernate.xml");
	}
	
	public static void main(String[] args){
		
		InsertObjectService insertObjectService =spring.getBean(InsertObjectService.class);
		while(true){
			insertObjectService.insertSingleModel(RandomModelCreator.createRandomAirjetModel());
			insertObjectService.insertSingleModel(RandomModelCreator.createRandomRapierModel());
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
