package com.myproject.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.demo.entities.Product;
import com.myproject.demo.services.ProductService;
// CONTROLADOR REST QUE RESPONDE NO CAMINHO "/users"

// Recurso básico baseado na classe "Product"
// Classe para testar se o Rest da aplicação Spring boot está funcionando
@RestController // -> Para indicar que essa classe é um recurso web que é implementado por um controlador Rest
@RequestMapping(value = "/products") // -> Definir o nome para o Recurso
public class ProductResource {
	
	@Autowired
	private ProductService service;

	// Metodo para testar esse recurso "users" 
	@GetMapping // -> Indicar que esse vai ser um método que responde a uma requisição do tipo GET do http
	public ResponseEntity<List<Product>> findAll(){
	// ResponseEntity<T> -> Tipo especifico do Spring para retornar respostas em requisições web
		
		List<Product> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		//ResponseEntity.ok() -> Para retornar a resposta com sucesso no http
		//ResponseEntity.ok().body(body) -> Para retornar o "corpo" da resposta (body = List<T>/Entity/etc)
	}
	
	@GetMapping(value = "/{id}") //-> Indicar que a request vai aceitar o "id" dentro da URL Ex.: ".../products/7"
	public ResponseEntity<Product> findById(@PathVariable Long id){
	//@PathVariable->Para o Spring aceitar e considera-lo como parâmetro da URL
		
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
