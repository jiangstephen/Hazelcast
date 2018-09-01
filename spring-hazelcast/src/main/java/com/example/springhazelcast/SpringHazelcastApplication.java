package com.example.springhazelcast;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@SpringBootApplication
//@EnableCaching
@EnableJpaRepositories
public class SpringHazelcastApplication {
	
	@Autowired
	public ApplicationContext context;

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
