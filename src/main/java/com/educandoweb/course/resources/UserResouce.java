/*
 * Essa a a classe controladore REST
 */

package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResouce {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")  										// indica que a requisição recebera um id na url
	public ResponseEntity<User> finfById(@PathVariable Long id){      	//anotation para que o spring saiba que se trata de um id que vera da url
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);   						// "ResponseEntity.ok" para indicar que teve sucesso
	}
	
	
}
