package com.iwebirth.sxfj.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.model.RapierModel;
@Component
public class InsertObjectService {
	@Autowired	
	private  SessionFactory sf;
	
	public void insertSingleModel(Object model){
		try{
			Session session = sf.getCurrentSession();
			session.save(model);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public  void insertAirJetList(List<AirJetModel> airJetList){
		try{
			Session session = sf.getCurrentSession();
			int i = 0;
			for(AirJetModel model : airJetList){
				session.save(model);
				if(++i > 20){
					session.flush();
					i = 0;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertRapierList(List<RapierModel> rapierList){
		try{
			Session session = sf.getCurrentSession();
			int i = 0;
			for(RapierModel model : rapierList){
				session.save(model);
				if(++i > 20){
					session.flush();
					i = 0;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
