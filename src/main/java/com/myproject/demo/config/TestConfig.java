package com.myproject.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.myproject.demo.entities.Order;
import com.myproject.demo.entities.User;
import com.myproject.demo.repositories.OrderRepository;
import com.myproject.demo.repositories.UserRepository;

@Configuration // -> Indicar pro Spring que essa é uma classe especifica de configuração
@Profile("test") // -> Indicar que essa classe vai ser uma configuração especifica para o perfil de teste
public class TestConfig implements CommandLineRunner{

	@Autowired // -> Para que o Spring consiga resolver essa dependencia e associar uma instancia do "userRepository" nessa Classe 
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	// Tudo que for colocado nesse método vai ser executado quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Victor Almeida", "victorh.almeida7@gmail.com", "999999999", "123456");
		User u2 = new User(null, "Gabrielly Silva", "gabiiSilva@gmail.com", "999999999", "123456");
		
		// Horario no padrão UTC
		Order o1 = new Order(null, Instant.parse("2022-01-25T13:10:00Z"), u1);
		Order o2 = new Order(null, Instant.parse("2022-01-25T14:20:50Z"), u2);
		Order o3 = new Order(null, Instant.parse("2022-01-27T16:35:10Z"), u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}
}
