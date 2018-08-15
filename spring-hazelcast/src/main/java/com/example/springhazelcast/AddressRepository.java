package com.example.springhazelcast;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springhazelcast.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

	@Cacheable("findByAddress")
	public Address findByAddress(String name);
}