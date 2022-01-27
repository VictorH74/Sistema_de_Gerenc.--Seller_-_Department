package com.myproject.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.demo.entities.Category;
import com.myproject.demo.services.CategoryService;
// CONTROLADOR REST QUE RESPONDE NO CAMINHO "/users"

// Recurso básico baseado na classe "Category"
@RestController // -> Para indicar que essa classe é um controlador Rest
@RequestMapping(value = "/categories") // -> Definir o nome para o Recurso
public class CategoryResource {
	
	@Autowired
	private CategoryService service;

	@GetMapping//->Indicar que esse vai ser um método request do http / Solicitar algo
	public ResponseEntity<List<Category>> findAll(){
	// ResponseEntity<T> -> Tipo especifico do Spring para retornar respostas em requisições web
		
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		//ResponseEntity.ok() -> Para retornar a resposta com sucesso no http
		//ResponseEntity.ok().body(body) -> Para retornar o "corpo" da resposta (body = List<T>/Entity/etc)
	}
	
	@GetMapping(value = "/{id}") //-> Indicar que a request vai aceitar o "id" dentro da URL Ex.: ".../categories/7"
	public ResponseEntity<Category> findById(@PathVariable Long id){
	//@PathVariable->para o parametro ser reconhecido pelo Spring como uma variavel da URL
		
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
