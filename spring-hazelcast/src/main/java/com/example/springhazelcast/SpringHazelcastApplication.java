package com.example.springhazelcast;

import java.lang.management.ManagementFactory;

import javax.annotation.PostConstruct;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.export.MBeanExportException;
import org.springframework.jmx.export.MBeanExporter;

@SpringBootApplication
//@EnableCaching
@EnableJpaRepositories
public class SpringHazelcastApplication {
	
	@Autowired
	public ApplicationContext context;
	
	@Autowired
	public EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public MBeanExporter mbeanExportor;
	
	@Autowired
	private HibernateStatisticsMBean statistisMBean;
	
	
	@Bean
	public SessionFactory sessionFactory(){
		return entityManagerFactory.unwrap(SessionFactory.class);
	}
	
	@PostConstruct
	public void registerBean() throws MBeanExportException, MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException{
		mbeanExportor.registerManagedResource(statistisMBean);
	}

	
	public static void main(String[] args) {
		SpringApplication.run(SpringHazelcastApplication.class, args);
	}
	
	//Spring will automatically setup the hazelcast instance, if found both hazelcast in the classpath and config bean.
    /*@Bean
    HazelcastInstance hazelcastInstance() {
        ClientConfig config = new ClientConfig();
        config.getGroupConfig().setName("dev").setPassword("dev-pass");
        config.getNetworkConfig().addAddress("localhost");
        config.setInstanceName("cache-1");
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(config);
        return instance;
    }
 
    @Bean
    CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }*/
}
