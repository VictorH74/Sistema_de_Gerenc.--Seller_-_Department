package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.Category;
import com.myproject.demo.repositories.CategoryRepository;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class CategoryService {

	@Autowired
	private CategoryRepository userRepository;
	
	public void saveAll(List<Category> list) {
		userRepository.saveAll(list);
	}
	
	public List<Category> findAll(){
		return userRepository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = userRepository.findById(id);
		return obj.get();
	}
}
