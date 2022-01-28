package com.myproject.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.myproject.demo.entities.Category;
import com.myproject.demo.entities.Order;
import com.myproject.demo.entities.OrderItem;
import com.myproject.demo.entities.Payment;
import com.myproject.demo.entities.Product;
import com.myproject.demo.entities.User;
import com.myproject.demo.entities.enums.OrderStatus;
import com.myproject.demo.services.CategoryService;
import com.myproject.demo.services.OrderItemService;
import com.myproject.demo.services.OrderService;
import com.myproject.demo.services.ProductService;
import com.myproject.demo.services.UserService;

@Configuration // -> Indicar pro Spring que essa é uma classe especifica de configuração
@Profile("test") // -> Indicar que essa classe vai ser uma configuração especifica para o perfil de teste
public class TestConfig implements CommandLineRunner{

	@Autowired // -> Para que o Spring consiga resolver essa dependencia e associar uma instancia do "userRepository" nessa Classe 
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CategoryService catService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemService oiService;

	// Tudo que for colocado nesse método vai ser executado quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Marcenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Mac Book Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		catService.saveAll(Arrays.asList(cat1, cat2, cat3));
//		productService.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productService.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Victor Almeida", "victorh.almeida7@gmail.com", "999999999", "123456");
		User u2 = new User(null, "Gabrielly Silva", "gabiiSilva@gmail.com", "999999999", "123456");
		
		userService.saveAll(Arrays.asList(u1, u2));
		
		// Horario no padrão UTC
		Order o1 = new Order(null, Instant.parse("2022-01-25T13:10:00Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2022-01-25T14:20:50Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2022-01-27T16:35:10Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		
		orderService.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		oiService.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2022-01-25T15:10:00Z"), o1);
		o1.setPayment(pay1);
		
		orderService.save(o1);
	}
}
