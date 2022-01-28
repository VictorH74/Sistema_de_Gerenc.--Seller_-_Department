package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.OrderItem;
import com.myproject.demo.repositories.OrderItemRepository;
import com.myproject.demo.services.exceptions.DatabaseException;
import com.myproject.demo.services.exceptions.ResourceNotFoundException;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	public void saveAll(List<OrderItem> list) {
		repository.saveAll(list);
	}
	
	public List<OrderItem> findAll(){
		return repository.findAll();
	}
	
	public OrderItem insert(OrderItem obj) {
		return repository.save(obj);
	}
	
	public OrderItem update(Long id, OrderItem obj) {
		try {
			OrderItem entity = repository.getById(id);
			//->Instanciar um usuario para o objeto "entity" monitorado
			// para fazer alterações ao objeto diferente do ".findById()"
			
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(OrderItem entity, OrderItem obj) {
		entity.setPrice(obj.getPrice());
		entity.setQuantity(obj.getQuantity());
		
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
	
	public OrderItem findById(Long id) {
		Optional<OrderItem> obj = repository.findById(id);
		return obj.get();
	}
}
