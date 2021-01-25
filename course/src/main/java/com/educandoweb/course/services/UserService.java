//Camada "Service Layer" não é obrigatória, mas convém para implementar as regras de negócio
package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

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
		//return obj.get();
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//Operação para inserir usuários
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	//Para deletar usuários
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	//Para atualizar usuários
	public User update(Long id, User obj) {
		/*
		 * O getOne instancia o usuário sem ir ao banco de dados previamente,
		 * deixando o objeto monitorado pelo JPA para trabalhar e em seguida
		 * possa efetuar uma operação no Banco de Dados
		 */
		try {
			User entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e){
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}
