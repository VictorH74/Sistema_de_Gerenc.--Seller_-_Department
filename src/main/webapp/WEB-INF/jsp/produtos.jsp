<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Pagina de Produtos</title>
		<link rel="stylesheet" type="text/css" href="/css/entidades.css">
	</head>

	<body>
		<div class="container">
	        <header>
	            <div class="logo">
	                <h1>Produtos | VH</h1>
	            </div>
	            
	            <nav>
	                <ul class="menu">
	                    <li><a href="/">Home</a></li>
	                    <li><a href="/users">Usuários</a></li>
	                    <li><a href="/orders">Pedidos</a></li>
	                    <li><a href="/products" id="current-page">Produtos</a></li>
	                    <li><a href="/categories">Categorias</a></li>
	                    <li><a href="#sobre">Sobre</a></li>
	                </ul>
	            </nav>
	        </header>
	
	        <main>
	            <div class="form-area">
	                <h1 id="titulo-form">Cadastrar Produto</h1>
	                <form action="">
	                    <input type="text" form="form-control" id="id" readonly="readonly"placeholder="ID"> 
	                    <input type="text" form="form-control" id="name" required placeholder="Nome"> 
	                    <input type="text" form="form-control" id="description" required placeholder="Descrião">
	                    <input type="text" form="form-control" id="price" required placeholder="Preço">
	                    <div class="form-buttons">
	                        <input type="button" value="Salvar" id="bt-form-salvar" onclick="salvarProduto()">
	                        <input type="button" value="Limpar" id="bt-form-limpar" onclick="limparForm()">
	                    </div>
	                </form>
	            </div>
	
	            <div class="menu-db-area">
	                <div class="menu-db">
	                    <a href="#"><input type="button" value="Listar Todos" onclick="clickListarTodos()"></a>
	                    <a href="#"><input type="button" value="Encontrar Por Id" onclick="alerta()"></a>
	                    <a href="#"><input type="button" value="Nada" onclick="alerta()"></a>
	                </div>
	            </div>
	        </main>
	    </div>
	
	    <div id="lista-modal" class="modal-c">
	        <div id="lista-div">
	            
				<div class="button-close">
					<input type="button" onclick="closeModal()" value="X" >
				</div>
				
                <table class="table" id="tabela-result">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Preço</th>
                            <th scope="col">Editar</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    </tbody>
                </table>
	        </div>
	    </div>
	
	    <div id="sobre" class="modal-c">
	        <div id="sobre-div" class="modal-padrao">
	            <a href="#" title="Close" class="close">X</a>
	            <hr>
	            <div>
	                <h1>Web Application Demo</h1>
	                <p>Essa é apenas uma aplicação web básica feito em JAVA com
	                    Spring Boot</p>
	            </div>
	
	        </div>
	    </div>
		
	
		<script type="text/javascript">
			function setDef() {
				document.getElementById('novo').style.backgroundColor = 'orange'
			}
			function limparForm(){
				$("#id").val("");
				$("#name").val("");
				$("#description").val("");
				$("#price").val("");
				document.getElementById('titulo-form').innerText = ' Cadastrar Produto';
				document.getElementById('titulo-form').style.color= 'black';
			}
			function closeModal() {
				document.getElementById('lista-modal').style.opacity = '0';
				document.getElementById('lista-modal').style.pointerEvents = 'none';
				document.getElementById('lista-modal').style.transform = 'translatey(20px)';
			}
			function alerta() {
				alert("Em desenvolvimento!");
			}
			function clickListarTodos(){
				document.getElementById('lista-modal').style.opacity = '1';
				document.getElementById('lista-modal').style.pointerEvents = 'auto';
				document.getElementById('lista-modal').style.opacity = '1';
				document.getElementById('lista-modal').style.transition = '400ms';
				document.getElementById('lista-modal').style.transform = 'translatey(-20px)';
				listarTodos();
				
			}
		
		</script>
	
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"
			integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
			crossorigin="anonymous"></script>
			
		<script type="text/javascript">
			function salvarProduto(){
				var id = $("#id").val();
				var name = $("#name").val();
				var description = $("#description").val();
				var price = $("#price").val();
				
				if(id != null)
					document.getElementById('titulo-form').innerText = ' Cadastrar Usuário';
				document.getElementById('titulo-form').style.color= 'black';
	
				if (name == "" || price == "") {
					alert("Campos (Nome, Preço) devem ser preenchidos!")
	
				} else {
					$.ajax({
	
						method : "POST",
	
						url : "products/salvar",
	
						data : JSON.stringify({
							id : id,
							name : name,
							description : description,
							price : price,
						}),
	
						contentType : "application/json; charset=utf-8",
	
						success : function(response) {
							alert("Produto salvo com sucesso!");
						}
	
					}).fail(function(xhr, status, errorThrow) {
						alert("Erro ao salvar produto: " + xhr.responseText);
					});
				}
			}
	
			function excluirProduto(id) {
				$.ajax({
					method : "DELETE",
					url : "products/excluir",
					data : "id=" + id,
					success : function(response) {
	
						listarTodos();
					}
				}).fail(function(xhr, status, errorThrow) {
					alert("Erro ao excluir produto: " + xhr.responseText);
				});
	
			}
			
			function editarProduto(id){
				$.ajax({
					method : "GET",
					url : "products/encontrarPorId",
					data : "id=" + id,
					success : function(response) {
						document.getElementById('titulo-form').innerText = ' Alterar Dados';
						document.getElementById('titulo-form').style.color= '#0a68f5';
						
						$("#id").val(response.id);
						$("#name").val(response.name);
						$("#description").val(response.description);
						$("#price").val(response.price);
						
						closeModal();
					}
				}).fail(function(xhr, status, errorThrow) {
					alert("Erro ao salvar produto: " + xhr.responseText);
				});
			}
			
			function listarTodos(){
				$.ajax({
	
						method : "GET",
						
						url : "products/listarTodos",
	
						success : function(response) {
	
							$('#tabela-result > tbody > tr').remove();
	
							for (var i = 0; i < response.length; i++) {
								$('#tabela-result > tbody').append(
										'<tr>'
											+ '<td>'+ response[i].id+ '</td>'
											+ '<td>'+ response[i].name +'</td>'
											+ '<td>'+ response[i].description +'</td>'
											+ '<td>'+ response[i].price +'</td>'
											+ '<td>'
												+'<div class="bt-table">'
													+'<button class="bt_list" id="bt_editar_list" onclick="editarProduto('+ response[i].id +')">Editar</button>'
													+'<button class="bt_list" id="bt-excluir_list" onclick="excluirProduto('+ response[i].id +')">Excluir</button>'
												+'</div>'
											+'</td>'
									+'</tr>');
							}
						}
	
						}).fail(function(xhr, status, errorThrow) {
								alert("Erro ao listar usuários: " + xhr.responseText);
							});
			}
			
		</script>
	
	</body>
</html>