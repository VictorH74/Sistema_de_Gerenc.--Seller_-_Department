<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Pagina de Usu�rios</title>
		<link rel="stylesheet" type="text/css" href="/css/entidades.css">
	</head>

	<body>
		<div class="container">
	        <header>
	            <div class="logo">
	                <h1>Usu�rios | VH</h1>
	            </div>
	            
	            <nav>
	                <ul class="menu">
	                    <li><a href="/">Home</a></li>
	                    <li><a href="/users" id="current-page">Usu�rios</a></li>
	                    <li><a href="/orders">Pedidos</a></li>
	                    <li><a href="/products">Produtos</a></li>
	                    <li><a href="/categories">Categorias</a></li>
	                    <li><a href="#sobre">Sobre</a></li>
	                </ul>
	            </nav>
	        </header>
	
	        <main>
	            <div class="form-area">
	                <h1 id="titulo-form">Cadastrar Usuario</h1>
	                <form action="">
	                    <input type="text" form="form-control" id="id" readonly="readonly" placeholder="ID"> 
	                    <input type="text" form="form-control" id="name" required placeholder="Nome"> 
	                    <input type="email" form="form-control" id="email" required placeholder="E-mail"> 
	                    <input type="text" form="form-control" id="phone" required placeholder="Telefone">
	                    <input type="text" form="form-control" id="password" required placeholder="Senha">
	
	                    <div class="form-buttons">
	                        <input type="button" value="Salvar" id="bt-form-salvar" onclick="salvarUsuario()">
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
							<th scope="col">E-mail</th>
							<th scope="col">Telefone</th>
							<th scope="col">Senha</th>
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
	                <p>Essa � apenas uma aplica��o web b�sica feito em JAVA com
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
				$("#email").val("");
				$("#phone").val("");
				$("#password").val("");
				document.getElementById('titulo-form').innerText = ' Cadastrar Usu�rio';
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
			function salvarUsuario() {
				
				var id = $("#id").val();
				var name = $("#name").val();
				var email = $("#email").val();
				var phone = $("#phone").val();
				var password = $("#password").val();
				
				
				if(id != null)
					document.getElementById('titulo-form').innerText = ' Cadastrar Usu�rio';
				document.getElementById('titulo-form').style.color= 'black';
	
				if (name == "" || email == "" || phone == "" || password == "") {
					alert("Campos (Nome, E-mail, Telefone, Senha) devem ser preenchidos!")
	
				} else {
					$.ajax({
	
						method : "POST",
	
						url : "users/salvar",
	
						data : JSON.stringify({
							id : id,
							name : name,
							email : email,
							phone : phone,
							password : password
						}),
	
						contentType : "application/json; charset=utf-8",
	
						success : function(response) {
	
							$("#id").val(response.id);
							alert("Usu�rio salvo com sucesso!");
						}
	
					}).fail(function(xhr, status, errorThrow) {
						alert("Erro ao salvar usu�rio: " + xhr.responseText);
					});
				}
	
			}
	
			function excluirUsuario(id) {
				$.ajax({
					method : "DELETE",
					url : "users/excluir",
					data : "id=" + id,
					success : function(response) {
						alert(response);
						listarTodos();
					}
				}).fail(function(xhr, status, errorThrow) {
					alert("Erro ao excluir usu�rio: " + xhr.responseText);
				});
	
			}
			
			function editarUsuario(id) {
				
				$.ajax({
					
					method : "GET",
					
					url : "users/encontrarPorId",
					
					data : "id=" + id,
					
					success : function(response) {
						document.getElementById('titulo-form').innerText = ' Alterar Dados';
						document.getElementById('titulo-form').style.color= '#0a68f5';
						
						$("#id").val(response.id);
						$("#name").val(response.name);
						$("#email").val(response.email);
						$("#phone").val(response.phone);
						$("#password").val(response.password);
						
						closeModal();
					}
				}).fail(function(xhr, status, errorThrow) {
					alert("Erro ao salvar usu�rio: " + xhr.responseText);
				});
			}
			
			function listarTodos() {
				
				$.ajax({
	
						method : "GET",
	
						url : "users/listarTodos",
	
						success : function(response) {
							$('#tabela-result > tbody > tr').remove();
							for (var i = 0; i < response.length; i++) {
								$('#tabela-result > tbody').append(
									'<tr>'
											+ '<td>'+ response[i].id + '</td>'
											+ '<td>'+ response[i].name +'</td>'
											+ '<td>'+ response[i].email +'</td>'
											+ '<td>'+ response[i].phone +'</td>'
											+ '<td>'+ response[i].password +'</td>'
											+ '<td>'
												+'<div class="bt-table">'
													+'<button class="bt_list" id="bt_editar_list" onclick="editarUsuario('+ response[i].id +')">Editar</button>'
													+'<button class="bt_list" id="bt-excluir_list" onclick="excluirUsuario('+ response[i].id +')">Excluir</button>'
												+'</div>'
											+'</td>'
									+'</tr>');
							}
						}
	
						}).fail(function(xhr, status, errorThrow) {
								alert("Erro ao listar usu�rios: " + xhr.responseText);
							});
			}
		</script>
	
	</body>
</html>