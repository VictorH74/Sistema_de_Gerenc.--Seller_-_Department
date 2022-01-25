package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.User;
import com.myproject.demo.repositories.UserRepository;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.get();
	}
}
