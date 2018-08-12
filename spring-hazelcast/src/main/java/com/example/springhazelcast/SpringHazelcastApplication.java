package com.example.springhazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@SpringBootApplication
@EnableCaching()
@EnableHazelcastRepositories
public class SpringHazelcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHazelcastApplication.class, args);
	}
	
	//spring will automatically setup the hazelcast instance, if found both hazelcast in the classpath and config bean.
	@Bean
	public Config config() {
	    Config c = new Config();
	    c.setInstanceName("cache-1");
	    c.getGroupConfig().setName("dev").setPassword("dev-pass");
	    ManagementCenterConfig mcc = new ManagementCenterConfig().setUrl("http://localhost:8080/hazelcast-mancenter").setEnabled(true);
	    c.setManagementCenterConfig(mcc);
	    return c;
	}

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance); // (3)
    }
}
