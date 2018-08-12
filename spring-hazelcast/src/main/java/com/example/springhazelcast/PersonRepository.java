package com.example.springhazelcast;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.hazelcast.repository.HazelcastRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends HazelcastRepository<Person, Integer> {

	@Cacheable("findByPesel")
	public List<Person> findByPesel(String pesel);
}