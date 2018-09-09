package com.example.springhazelcast;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class CacheInitializer {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheInitializer.class);
	
	@Autowired
	private EntityManager entityManager;	
	
	@PostConstruct
	public void initializeAllCache(){
		for(EntitiesToCacheType entitiesToCache : EntitiesToCacheType.values()){
			initializeCache(entitiesToCache);
		}
	}
	 
	
	@Async
	public void initializeCache(EntitiesToCacheType entitiesToCache){
		LOGGER.info("Start to initialize cache {}", entitiesToCache);
		Validate.notNull(entitiesToCache, "entitiesToCache should not be null");
		for(EntitiesToCacheType entityToCache : entitiesToCache.getWaitFor()){
			LOGGER.info("Wait for dependent lock {}" , entityToCache);
			entityToCache.getReadWriteLock().readLock().lock();
			LOGGER.info("dependent lock was released {}", entityToCache);
		}
		entitiesToCache.getReadWriteLock().writeLock().lock();
		entityManager.createQuery(entitiesToCache.getAllEntitiesQuery()).getResultList();
		LOGGER.info("Initialization finished for cache {}", entitiesToCache);
		entitiesToCache.getReadWriteLock().writeLock().unlock();
	}
	

}
