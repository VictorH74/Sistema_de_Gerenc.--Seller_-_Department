package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.Order;
import com.myproject.demo.repositories.OrderRepository;
import com.myproject.demo.services.exceptions.DatabaseException;
import com.myproject.demo.services.exceptions.ResourceNotFoundException;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public void saveAll(List<Order> list) {
		repository.saveAll(list);
	}
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public Order update(Long id, Order obj) {
		try {
			Order entity = repository.getById(id);
			//->Instanciar um usuario para o objeto "entity" monitorado
			// para fazer alterações ao objeto diferente do ".findById()"
			
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Order entity, Order obj) {
		entity.setOrderStatus(obj.getOrderStatus());
		
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
			
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}

	public void save(Order o1) {
		repository.save(o1);
		
	}
}
