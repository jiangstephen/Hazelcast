package com.example.springhazelcast;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springhazelcast.entity.Department;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Controller
public class DepartmentController {
	
	private static final String ALPHABETIC = "abcdefghijklmno";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired 
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/department/{id}")
	public @ResponseBody Department getDepartment(@PathVariable Long id){
		Department department = departmentRepository.findOne(id);
		if(department == null){
			throw new ResourceNotFoundException("department with id "+id+" doesn't exist");
		}
		return department;
	}
 
    
}
