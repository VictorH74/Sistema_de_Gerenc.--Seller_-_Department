package com.myproject.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.myproject.demo.entities.User;
import com.myproject.demo.repositories.UserRepository;

@Configuration // -> Indicar pro Spring que essa é uma classe especifica de configuração
@Profile("test") // -> Indicar que essa classe vai ser uma configuração especifica para o perfil de teste
public class TestConfig implements CommandLineRunner{

	@Autowired // -> Para que o Spring consiga resolver essa dependencia e associar uma instancia do "userRepository" nessa Classe 
	private UserRepository userRepository;

	// Tudo que for colocado nesse método vai ser executado quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Victor Almeida", "victorh.almeida7@gmail.com", "999999999", "123456");
		User u2 = new User(null, "Gabrielly Silva", "gabiiSilva@gmail.com", "999999999", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}
}
