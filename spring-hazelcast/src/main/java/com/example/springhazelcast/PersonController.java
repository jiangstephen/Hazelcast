package com.example.springhazelcast;

import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {
	
	private static final String ALPHABETIC = "abcdefghijklmno";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired 
	private PersonRepository personRepository;
	
	@PostConstruct
	public void initializePersonInDB(){
		for(int i=0;i<10;i++){
			Person person = new Person();
			person.setAge(RandomUtils.nextInt(10, 30));
			person.setFirstName(RandomStringUtils.random(5, ALPHABETIC));
			person.setLastName(RandomStringUtils.random(5, ALPHABETIC));
			person.setPesel("persel" + (int) (Math.random()*10));
			person = personRepository.save(person);
			LOGGER.info("Save the person {} into the database", person);
		}
	}
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.GET, 
            produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Person getPersonById(@PathVariable("id") int id){
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()){
			return person.get();
		}
		throw new ResourceNotFoundException("The person with id " +id+" can't be found");
	}
	
	@RequestMapping(value="/pesel/{pesel}", method=RequestMethod.GET, 
            produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Person> getPersonByPesel(@PathVariable("pesel") String pesel){
		List<Person> persons = personRepository.findByPesel(pesel);
		if(CollectionUtils.isEmpty(persons)){
			throw new ResourceNotFoundException("The person with pesel " +pesel+" can't be found");
		}
		return persons;
		
	}

}
