package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.Product;
import com.myproject.demo.repositories.ProductRepository;
import com.myproject.demo.services.exceptions.DatabaseException;
import com.myproject.demo.services.exceptions.ResourceNotFoundException;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public void saveAll(List<Product> list) {
		repository.saveAll(list);
	}
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	public Product update(Long id, Product obj) {
		try {
			Product entity = repository.getById(id);
			//->Instanciar um usuario para o objeto "entity" monitorado
			// para fazer alterações ao objeto diferente do ".findById()"
			
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setPrice(obj.getPrice());
		entity.setDescription(obj.getDescription());
		entity.setImgUrl(obj.getImgUrl());
		
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
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
