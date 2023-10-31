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

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResouce {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")  										// indica que a requisição recebera um id na url
	public ResponseEntity<Category> finfById(@PathVariable Long id){      	//anotation para que o spring saiba que se trata de um id que vera da url
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);   						// "ResponseEntity.ok" para indicar que teve sucesso
	}
	
	
}
