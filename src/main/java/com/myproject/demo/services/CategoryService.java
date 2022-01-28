package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.Category;
import com.myproject.demo.repositories.CategoryRepository;
import com.myproject.demo.services.exceptions.DatabaseException;
import com.myproject.demo.services.exceptions.ResourceNotFoundException;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public void saveAll(List<Category> list) {
		repository.saveAll(list);
	}
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category insert(Category obj) {
		return repository.save(obj);
	}
	
	public Category update(Long id, Category obj) {
		try {
			Category entity = repository.getById(id);
			//->Instanciar um usuario para o objeto "entity" monitorado
			// para fazer alterações ao objeto diferente do ".findById()"
			
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
		
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
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
}
