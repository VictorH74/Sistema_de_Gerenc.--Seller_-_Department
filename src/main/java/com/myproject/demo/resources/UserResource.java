package com.myproject.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myproject.demo.entities.User;
import com.myproject.demo.services.UserService;
// CONTROLADOR REST QUE RESPONDE NO CAMINHO "/users"

// Recurso básico baseado na classe "User"
// Classe para testar se o Rest da aplicação Spring boot está funcionando
@RestController //->Para indicar que essa classe é um recurso web que é implementado por um controlador Rest
@RequestMapping(value = "/users")//-> Definir o nome para o Recurso
public class UserResource {
	
	@Autowired
	private UserService service;

	// Metodo para testar esse recurso "users" 
	@GetMapping //->Indicar que esse vai ser um método request do http / Solicitar algo
	public ResponseEntity<List<User>> findAll(){
	// ResponseEntity<T> -> Tipo especifico do Spring para retornar respostas em requisições web
		
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		//ResponseEntity.ok() -> Para retornar a resposta com sucesso no http
		//ResponseEntity.ok().body(body) -> Para retornar o "corpo" da resposta (body = List<T>/Entity/etc)
	}
	
	@GetMapping(value = "/{id}")//->Indicar que a request vai aceitar o "id" dentro da URL Ex.: ".../users/7"
	public ResponseEntity<User> findById(@PathVariable Long id){
	//@PathVariable->para o parametro ser reconhecido pelo Spring como uma variavel da URL
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping()//->Indicar que esse vai ser um método para inserir algo do http
	public ResponseEntity<User> insert(@RequestBody User obj){
		//@RequestBody-> Indicar que o User obj vai chegar no modo Json na hora da requisição e vai ser deserializado para um obj User
		
		obj = service.insert(obj);
		
		//URI location / Endereço do novo recurso(obj) inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		// "201 OK" -> Codigo especifico do http que significa que foi criado um novo recurso
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}") //->Metodo HTTP para deletar no padrão Rest
	public ResponseEntity<Void> delete(@PathVariable Long id){
	//@PathVariable->para o parametro ser reconhecido pelo Spring como uma variavel da URL
		
		service.delete(id);
		return ResponseEntity.noContent().build();
			// ResponseEntity.noContent()-> Resposta sem corpo(body)
			// Codigo http de uma resposta sem conteudo: 204 OK
	}
	
	@PutMapping(value = "/{id}") //->Metodo HTTP para atualizar um recurso no padrão Rest
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
