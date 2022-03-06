package com.myproject.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myproject.demo.entities.User;
import com.myproject.demo.services.UserService;
// CONTROLADOR REST QUE RESPONDE NO CAMINHO "/users"



// Recurso básico baseado na classe "User"
@Controller //->Para indicar que essa classe é um controlador Rest
@RequestMapping("/users")//-> Definir o nome para o Recurso
public class UserResource {
	@Autowired
	private UserService service;
	
//	@GetMapping
//	public ModelAndView index(){
//		return new ModelAndView("usuarios");
//	}
	@GetMapping
	public String getUsers() {
		return "usuarios";
	}

	@GetMapping(value = "listarTodos") //->Indicar que esse vai ser um método request do http / Solicitar algo
	@ResponseBody
	public ResponseEntity<List<User>> listarTodos(){
	// ResponseEntity<T> -> Tipo especifico do Spring para retornar respostas em requisições web
		
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		//ResponseEntity.ok() -> Para retornar a resposta com sucesso no http
		//ResponseEntity.ok().body(body) -> Para retornar o "corpo" da resposta (body = List<T>/Entity/etc)
	}
	
	@GetMapping("/encontrarPorId")//->Indicar que a request vai aceitar o "id" dentro da URL Ex.: ".../users/7"
	public ResponseEntity<User> findById(@RequestParam(name = "id") Long id){
	//@PathVariable->para o parametro ser reconhecido pelo Spring como uma variavel da URL
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/salvar")//->Indicar que esse vai ser um método para inserir algo do http
	public ResponseEntity<User> insert(@RequestBody User obj){
		//@RequestBody-> Indicar que o User obj vai chegar em formato Json na hora da requisição e vai ser deserializado para um obj User
		
		obj = service.insert(obj);
		
		//URI location / Endereço do novo recurso(obj) inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		// "201 OK" -> Codigo especifico do http que significa que foi criado um novo recurso
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/excluir") //->Metodo HTTP para deletar no padrão Rest
	@ResponseBody//-> Descrição da responsta
	public ResponseEntity<String> delete(@RequestParam(name = "id") Long id){
		
		service.delete(id);
		return new ResponseEntity<String>("Usuário Deletado!", HttpStatus.OK);
			// ResponseEntity.noContent()-> Resposta sem corpo(body)
			// Codigo http de uma resposta sem conteudo: 204 OK
	}
	
	@PutMapping(value = "/{id}") //->Metodo HTTP para atualizar um recurso no padrão Rest
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
