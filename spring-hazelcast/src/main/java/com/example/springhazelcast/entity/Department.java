package com.example.springhazelcast.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
@Entity
@Data
@NoArgsConstructor
public class Department {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

}
