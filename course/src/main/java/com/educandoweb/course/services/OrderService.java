//Camada "Service Layer" não é obrigatória, mas convém para implementar as regras de negócio
package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

/*
 * @Component eh para registrar como componente do Spring
 * de forma que pode ser injetado automaticamente no @autowired.
 * Há outras notações que são analógas a @Component, como a de
 * @Repository e a de @Service, cada uma para ficar semanticamente
 * específico.
 */
@Service
public class OrderService {
	//@Autowired é para fazer a injeção de dependência transparente
	@Autowired
	private OrderRepository repository;
	
	//Operação para buscar todos os usuários
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	//Operação para buscar os usuários pelo id
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}

}
