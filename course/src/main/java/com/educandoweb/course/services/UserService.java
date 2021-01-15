//Camada "Service Layer" não é obrigatória, mas convém para implementar as regras de negócio
package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

/*
 * @Component eh para registrar como componente do Spring
 * de forma que pode ser injetado automaticamente no @autowired.
 * Há outras notações que são analógas a @Component, como a de
 * @Repository e a de @Service, cada uma para ficar semanticamente
 * específico.
 */
@Component
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}

}
