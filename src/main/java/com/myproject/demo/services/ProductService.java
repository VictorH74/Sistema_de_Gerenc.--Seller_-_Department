package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.Product;
import com.myproject.demo.repositories.ProductRepository;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class ProductService {

	@Autowired
	private ProductRepository userRepository;
	
	public void saveAll(List<Product> list) {
		userRepository.saveAll(list);
	}
	
	public List<Product> findAll(){
		return userRepository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = userRepository.findById(id);
		return obj.get();
	}
}
