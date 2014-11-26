package com.iwebirth.sxfj.random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.service.InsertObjectService;

public class InsertRadomData {
	public void test() throws InterruptedException{

	}
	public static ApplicationContext spring;
	static {
			spring = new ClassPathXmlApplicationContext("classpath:spring-hibernate.xml");
	}	
	
	public static void main(String[] args) throws InterruptedException{
		InsertObjectService insertPOJO = (InsertObjectService)spring.getBean("insertPOJO");
		while(true){
			AirJetModel model = RandomAirjetModelCreator.createRandomAirjetModel();
			insertPOJO.insert(model);
			Thread.sleep(30*1000);
		}
	}
}
