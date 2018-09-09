package com.example.springhazelcast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.example.springhazelcast.entity.Address;
import com.example.springhazelcast.entity.Department;
import com.example.springhazelcast.entity.Person;
import com.example.springhazelcast.entity.PersonInfo;

public enum EntitiesToCacheType {
	
	DEPARTMENT("from Department", Department.class), 
	ADDRESS("from Address", Address.class), 
	PERSON("from Person", Person.class, DEPARTMENT, ADDRESS),  
	PERSONINFO("from PersonInfo", PersonInfo.class, PERSON);
	
	private final List<EntitiesToCacheType> waitFor;
	
	private final Class<?> entityClass;
	
	private final String allEntitiesQuery;
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private EntitiesToCacheType(String allEntitiesQuery, Class<?> entityClass){
		this.allEntitiesQuery = allEntitiesQuery;
		this.entityClass = entityClass;
		this.waitFor = Collections.emptyList();
	}
	
	private EntitiesToCacheType(String allEntitiesQuery, Class<?> entityClass, EntitiesToCacheType... waitFor){
		this.allEntitiesQuery = allEntitiesQuery;
		this.entityClass = entityClass;
		this.waitFor = Arrays.asList(waitFor);
	}

	public List<EntitiesToCacheType> getWaitFor() {
		return waitFor;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}
	
	public ReadWriteLock getReadWriteLock(){
		return readWriteLock;
	}

	public String getAllEntitiesQuery() {
		return allEntitiesQuery;
	}
	
}
