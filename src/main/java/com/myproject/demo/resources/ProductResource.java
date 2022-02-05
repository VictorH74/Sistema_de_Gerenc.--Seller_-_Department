package com.myproject.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myproject.demo.entities.Product;
import com.myproject.demo.services.ProductService;
// CONTROLADOR REST QUE RESPONDE NO CAMINHO "/users"

// Recurso básico baseado na classe "Product"
@Controller // -> Para indicar que essa classe é um controlador Rest
@RequestMapping(value = "/products") // -> Definir o nome para o Recurso
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public String produtos() {
		return "produtos";
	}

	@GetMapping(value = "listarTodos") //->Indicar que esse vai ser um método request do http / Solicitar algo
	public ResponseEntity<List<Product>> findAll(){
	// ResponseEntity<T> -> Tipo especifico do Spring para retornar respostas em requisições web
		
		List<Product> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		//ResponseEntity.ok() -> Para retornar a resposta com sucesso no http
		//ResponseEntity.ok().body(body) -> Para retornar o "corpo" da resposta (body = List<T>/Entity/etc)
	}
	
	@GetMapping(value = "encontrarPorId") //-> Indicar que a request vai aceitar o "id" dentro da URL Ex.: ".../products/7"
	public ResponseEntity<Product> findById(@RequestParam(name = "id") Long id){
	//@PathVariable->para o parametro ser reconhecido pelo Spring como uma variavel da URL
		
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "salvar")//->Indicar que esse vai ser um método para inserir algo do http
	public ResponseEntity<Product> insert(@RequestBody Product obj){
		//@RequestBody-> Indicar que o Product obj vai chegar em formato Json na hora da requisição e vai ser deserializado para um obj Product
		obj = service.insert(obj);
		
		//URI location / Endereço do novo recurso(obj) inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		// "201 OK" -> Codigo especifico do http que significa que foi criado um novo recurso
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "excluir") //->Metodo HTTP para deletar no padrão Rest
	public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
			// ResponseEntity.noContent()-> Resposta sem corpo(body)
			// Codigo http de uma resposta sem conteudo: 204 OK
	}
	
}
