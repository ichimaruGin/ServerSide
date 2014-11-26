package com.iwebirth.sxfj.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwebirth.sxfj.model.AirJetModel;
@Service
public class InsertObjectService {
	@Autowired
	private  SessionFactory sf;
	public void insert(Object obj){
		Session session = sf.getCurrentSession();

		if(obj instanceof AirJetModel){
			System.out.println("insert");
			session.save((AirJetModel)obj);
			session.flush();
		}
	}
	
	public  void insertAirJetList(List<AirJetModel> airJetList){
		Session session = sf.getCurrentSession();
		int i = 0;
		for(AirJetModel model : airJetList){
			session.save(model);
			if(++i > 20){
				session.flush();
				i = 0;
			}
		}
	}
	
}
