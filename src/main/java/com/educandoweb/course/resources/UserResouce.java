/*
 * Essa a a classe controladore REST
 */

package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping  //esse metodo define no http que é uma inserção
	public ResponseEntity<User> insert(@RequestBody User obj) {  //@RequestBody para o java saber que esse objeto vio da requisição e será desserializado para ser inserido no banco
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();															//gerendo URI esperado na requisição created que gera o codigo 201, que significa que um novo objeto/usuario foi criado, isso é visualisado la no json quando criado um no

		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")  	
	 public ResponseEntity<Void> delete(@PathVariable Long id){
		 service.delete(id);
		 return ResponseEntity.noContent().build();
	 }
}
