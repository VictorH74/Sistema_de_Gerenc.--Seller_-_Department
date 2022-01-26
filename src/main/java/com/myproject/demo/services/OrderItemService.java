package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.OrderItem;
import com.myproject.demo.repositories.OrderItemRepository;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class OrderItemService {

	@Autowired
	private OrderItemRepository userRepository;
	
	public void saveAll(List<OrderItem> list) {
		userRepository.saveAll(list);
	}
	
	public List<OrderItem> findAll(){
		return userRepository.findAll();
	}
	
	public OrderItem findById(Long id) {
		Optional<OrderItem> obj = userRepository.findById(id);
		return obj.get();
	}
}
