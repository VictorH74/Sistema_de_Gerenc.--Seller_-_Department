package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.Order;
import com.myproject.demo.repositories.OrderRepository;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class OrderService {

	@Autowired
	private OrderRepository userRepository;
	
	public void saveAll(List<Order> list) {
		userRepository.saveAll(list);
	}
	
	public List<Order> findAll(){
		return userRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = userRepository.findById(id);
		return obj.get();
	}

	public void save(Order o1) {
		userRepository.save(o1);
		
	}
}
