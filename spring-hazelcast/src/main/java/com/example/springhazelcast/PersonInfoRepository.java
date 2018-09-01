package com.example.springhazelcast;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springhazelcast.entity.PersonInfo;

@Repository
public interface PersonInfoRepository extends CrudRepository<PersonInfo, Long> {

}