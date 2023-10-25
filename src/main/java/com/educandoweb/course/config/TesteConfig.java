package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Configuration  							//para classes de configurações
@Profile("test")  							//defindo que a classe é especifica para testes
public class TesteConfig implements CommandLineRunner {
	//inicialmente essa classe sera utilizada para popular os objeto
	
	//fazendo a injeção de dependencia do repositorio via framework
	@Autowired  //faz a junação de depdendecia
	private UserRepository userRepository;

	//metodo da interface "CommandLineRunner" extendida, tudo que for inserido dentro desse metodo rodara quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//salvando as instancias no banco/ instanciado o banco
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
	

}
