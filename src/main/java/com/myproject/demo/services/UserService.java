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
	
	public void saveAll(List<User> list) {
		userRepository.saveAll(list);
	}
	
	public User insert(User obj) {
		return userRepository.save(obj);
	}
	
	public User update(Long id, User obj) {
		User entity = userRepository.getById(id);
		//->Instanciar um usuario para o objeto "entity" monitorado
		// para fazer alterações ao objeto diferente do ".findById()"
		
		updateData(entity, obj);
		return userRepository.save(entity);
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.get();
	}
}
