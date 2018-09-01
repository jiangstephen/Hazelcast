package com.example.springhazelcast;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springhazelcast.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	//@Cacheable("findByPesel")
	public List<Person> findByPesel(String pesel);
}