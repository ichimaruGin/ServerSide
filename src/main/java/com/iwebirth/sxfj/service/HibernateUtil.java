package com.iwebirth.sxfj.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sf = null;
	static{
		Configuration cfg = new Configuration();
		cfg.configure();
		ServiceRegistryBuilder serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties());
		sf = cfg.buildSessionFactory(serviceRegistry.buildServiceRegistry());
	}
	private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();
	public static Session getCurrentSession(){
		Session s = sessions.get();
		if(s == null){
			s = sf.getCurrentSession();
			sessions.set(s);
		}
		return s;
	}
	public static void closeSession(){
		Session s = sessions.get();
		if(s != null)
			s.close();
		sessions.set(null);		
	}
}
