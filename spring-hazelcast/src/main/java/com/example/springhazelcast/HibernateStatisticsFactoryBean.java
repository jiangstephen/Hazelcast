package com.example.springhazelcast;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "Hibernate:type=statistics")
public class HibernateStatisticsFactoryBean  implements FactoryBean<Statistics>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Statistics getObject() throws Exception {
		return sessionFactory.getStatistics();
	}

	@Override
	public Class<?> getObjectType() {
		return Statistics.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
