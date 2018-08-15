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

import com.example.springhazelcast.entity.Address;
import com.example.springhazelcast.entity.Department;
import com.example.springhazelcast.entity.Person;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Controller
public class PersonController {
	
	private static final String ALPHABETIC = "abcdefghijklmno";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired 
	private PersonRepository personRepository;
	
	@Autowired 
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
    private HazelcastInstance instance;
 
    
	@PostConstruct
	public void initializePersonInDB(){
		for(int i=1;i<=10;i++){
			Department department = new Department();
			department.setName("department-" + RandomStringUtils.random(5, ALPHABETIC));
			//department.setId((long)i);
			departmentRepository.save(department);
			
			Person person = new Person();
			person.setAge(RandomUtils.nextInt(10, 30));
			person.setFirstName(RandomStringUtils.random(5, ALPHABETIC));
			person.setLastName(RandomStringUtils.random(5, ALPHABETIC));
			person.setPesel("persel" + (int) (Math.random()*10));
			person.addDepartment(department);			
			
			person = personRepository.save(person);
			Address address = new Address();
			//address.setId((long)i);
			address.setAddress("Address " + RandomStringUtils.random(10, ALPHABETIC));
			address.setPerson(person);
		    addressRepository.save(address);
			LOGGER.info("Save the person {} into the database", person);
		}
	}
	
	@GetMapping(value="/person/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Person getPersonById(@PathVariable("id") int id){
		Person person = personRepository.findOne(id);
		if(person != null){
			return person;
		}
		throw new ResourceNotFoundException("The person with id " +id+" can't be found");
	}
	
	@GetMapping(value="/person/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Person> getPersons(){
		List<Person> lists = new ArrayList<>();
		personRepository.findAll().forEach(t -> lists.add(t) );
		return lists;
	}
	
	@GetMapping(value="/delete/person/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void deletePersonById(@PathVariable("id") int id){
		personRepository.delete(id);
	}
	
	@GetMapping(value="/update/person/{id}/{age}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void updatePersonById(@PathVariable("id") int id, @PathVariable("age") int age){
		Person person = this.getPersonById(id);
		person.setAge(age);
		personRepository.save(person);
	}
	
	@GetMapping(value="/add/person", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Person addPerson(){
		Person person = new Person();
		person.setAge(RandomUtils.nextInt(10, 30));
		person.setFirstName(RandomStringUtils.random(5, ALPHABETIC));
		person.setLastName(RandomStringUtils.random(5, ALPHABETIC));
		person.setPesel("persel" + (int) (Math.random()*10));
		person = personRepository.save(person);
		LOGGER.info("Save the person {} into the database", person);
		return person;
	}
	
	
	@GetMapping(value="/pesel/{pesel}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Person> getPersonByPesel(@PathVariable("pesel") String pesel){
		List<Person> persons = personRepository.findByPesel(pesel);
		if(CollectionUtils.isEmpty(persons)){
			throw new ResourceNotFoundException("The person with pesel " +pesel+" can't be found");
		}
		return persons;
	}
	
	@GetMapping(value="/getmap/{mapname}", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<?> getMap(@PathVariable("mapname") String mapname){
		IMap<?,?> map = instance.getMap(mapname);
		if(map == null){
			throw new ResourceNotFoundException("Map " + mapname +" is not found");
		}
		return new ArrayList<>(map.values());
	}
}
