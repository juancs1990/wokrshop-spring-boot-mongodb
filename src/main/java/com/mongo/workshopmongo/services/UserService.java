package com.mongo.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.workshopmongo.domain.User;
import com.mongo.workshopmongo.dto.UserDTO;
import com.mongo.workshopmongo.repository.UserRepository;
import com.mongo.workshopmongo.sevices.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}
	

	public User findById(String id) {
		 Optional<User> obj = repo.findById(id);
		 return obj.orElseThrow(() -> new ResourceNotFoundException(obj));

	}
	
	public User insert (User obj) {
		return repo.insert(obj);
	}
	
	public void delete (String id) {
		  findById(id);
	      repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		}
	
	public void updateData (User newObj, User obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setName(obj.getName());
	}
	
	public User fromDTO (UserDTO obj) {
		return new User (obj.getId(),obj.getName(),obj.getEmail());
		
	}
	
}