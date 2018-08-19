package com.example.springhazelcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springhazelcast.entity.Address;

@Controller
public class AddressController {
	
	@Autowired 
	private AddressRepository addressRepository;
	
	@GetMapping("/address/{id}")
	public @ResponseBody Address getAddress(@PathVariable Long id){
		Address address = addressRepository.findOne(id);
		if(address == null){
			throw new ResourceNotFoundException("address with id "+id+" doesn't exist");
		}
		return address;
	}
}
