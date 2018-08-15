package com.example.springhazelcast.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Data
@NoArgsConstructor
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	private String firstName;
	private String lastName;
	private String pesel;
	private int age;
	
	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)	
    @JoinTable(
        name = "Person_Department", 
        joinColumns = { @JoinColumn(name = "person_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
	private Set<Department> departments = new HashSet<>();
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy = "person")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Address> addresses = new ArrayList<>();
	
	public void addDepartment(Department department){
		departments.add(department);
	}
	
	public void addAddress(Address address){
		addresses.add(address);
		address.setPerson(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}