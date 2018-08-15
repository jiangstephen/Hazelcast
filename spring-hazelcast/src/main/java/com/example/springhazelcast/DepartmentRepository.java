package com.example.springhazelcast;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springhazelcast.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

	@Cacheable("findByName")
	public Department findByName(String name);
}