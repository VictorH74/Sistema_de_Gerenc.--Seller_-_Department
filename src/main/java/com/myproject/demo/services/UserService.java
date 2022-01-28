package com.myproject.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.myproject.demo.entities.User;
import com.myproject.demo.repositories.UserRepository;
import com.myproject.demo.services.exceptions.DatabaseException;
import com.myproject.demo.services.exceptions.ResourceNotFoundException;
//@Component -> Super de @Service
@Service//->Para que essa classe seja injetado pelo mencanismo de injeção de dependencia do Spring (@Autowired)
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public void saveAll(List<User> list) {
		repository.saveAll(list);
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getById(id);
			//->Instanciar um usuario para o objeto "entity" monitorado
			// para fazer alterações ao objeto diferente do ".findById()"
			
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
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
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		
		Optional<User> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		//".orElseThrow()" tentar da o ".get()" do obj. Se não tiver nenhum objeto do tipo "User", irá lançar a exception definida como parametro
	}
}
