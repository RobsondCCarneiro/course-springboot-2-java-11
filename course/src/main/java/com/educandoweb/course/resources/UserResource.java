package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

//Para inficar que é implementado por um controlador Rest, então utilizo essa notações.	
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	public ResponseEntity<User> findAll(){
		//Para indicar que responde a requisição Catch do HTTP, então coloca a notation GetMapping
		//@GetMapping
		User u = new User(1L, "Maria", "maria@gmail.com", "999999", "12345");
		return ResponseEntity.ok().body(u);
	}
}
