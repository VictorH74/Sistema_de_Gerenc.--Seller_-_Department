package com.myproject.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.demo.entities.Order;
import com.myproject.demo.services.OrderService;
// CONTROLADOR REST QUE RESPONDE NO CAMINHO "/users"

// Recurso básico baseado na classe "Order"
// Classe para testar se o Rest da aplicação Spring boot está funcionando
@RestController // -> Para indicar que essa classe é um recurso web que é implementado por um controlador Rest
@RequestMapping(value = "/orders") // -> Definir o nome para o Recurso
public class OrderResource {
	
	@Autowired
	private OrderService service;

	// Metodo para testar esse recurso "users" 
	@GetMapping // -> Indicar que esse vai ser um método que responde a uma requisição do tipo GET do http
	public ResponseEntity<List<Order>> findAll(){
	// ResponseEntity<T> -> Tipo especifico do Spring para retornar respostas em requisições web
		
		List<Order> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		//ResponseEntity.ok() -> Para retornar a resposta com sucesso no http
		//ResponseEntity.ok().body(body) -> Para retornar o "corpo" da resposta (body = List<T>/Entity/etc)
	}
	
	@GetMapping(value = "/{id}") //-> Indicar que a request vai aceitar o "id" dentro da URL Ex.: ".../users/7"
	public ResponseEntity<Order> findById(@PathVariable Long id){
	//@PathVariable->Para o Spring aceitar e considera-lo como parâmetro da URL
		
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
