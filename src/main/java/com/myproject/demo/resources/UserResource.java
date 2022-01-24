package com.myproject.demo.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.demo.entities.User;
// CONTROLADOR REST QUE RESPONDE NO CAMINHO "/users"

// Recurso básico baseado na classe "User"
// Classe para testar se o Rest da aplicação Spring boot está funcionando
@RestController // -> Para indicar que essa classe é um recurso web que é implementado por um controlador Rest
@RequestMapping(value = "/users") // -> Definir o nome para o Recurso
public class UserResource {

	// Metodo para testar esse recurso "users" 
	@GetMapping // -> Indicar que esse vai ser um método que responde a uma requisição do tipo GET do http
	public ResponseEntity<User> findAll(){
	// ResponseEntity<T> -> Tipo especifico do Spring para retornar respostas em requisições web
		
		User u = new User(1L, "Maria", "Maria@gmail.com", "999999999", "12345");
		return ResponseEntity.ok().body(u);
		//ResponseEntity.ok() -> Para retornar a resposta com sucesso no http
		//ResponseEntity.ok().body(body) -> Para retornar o "corpo" da resposta
	}
}
