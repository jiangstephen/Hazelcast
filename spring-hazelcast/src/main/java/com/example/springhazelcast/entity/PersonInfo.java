package com.example.springhazelcast.entity;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
@Data
@NoArgsConstructor
@Entity
public class PersonInfo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String personInfoHolder;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="person_id", nullable = false, referencedColumnName = "id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonIgnore
	private Person person;
	
	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this, "person");
	}
	
	@Override
	public boolean equals(Object obj){
		return EqualsBuilder.reflectionEquals(this, obj, "person");
	}
}
