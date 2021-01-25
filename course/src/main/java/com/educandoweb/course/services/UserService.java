//Camada "Service Layer" não é obrigatória, mas convém para implementar as regras de negócio
package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

/*
 * @Component eh para registrar como componente do Spring
 * de forma que pode ser injetado automaticamente no @autowired.
 * Há outras notações que são analógas a @Component, como a de
 * @Repository e a de @Service, cada uma para ficar semanticamente
 * específico.
 */
@Service
public class UserService {
	//@Autowired é para fazer a injeção de dependência transparente
	@Autowired
	private UserRepository repository;
	
	//Operação para buscar todos os usuários
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//Operação para buscar os usuários pelo id
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	//Operação para inserir usuários
	public User insert(User obj) {
		return repository.save(obj);
	}

}
