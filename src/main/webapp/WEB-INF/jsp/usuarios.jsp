<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina de Usuários</title>
<link rel="stylesheet" type="text/css" href="/css/usuarios.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

</head>

<body>
	<main>
		<header>
			<div>
				<h1>USERS | VH</h1>
			</div>

			<nav>
				<ul>
					<li><a href="/">Home</a></li>
					<li><a href="#usuarios">Usuários</a></li>
					<li><a href="#sobre">Sobre</a></li>
				</ul>
			</nav>
		</header>

		<div id="form-div">
			<form id="formCadastroUser" action="" method="post">
				<h2 id="titulo-form">Cadastrar Usuário</h2>
				<input type="text" form="form-control" id="id" readonly="readonly"placeholder="  ID"> 
				<input type="text" form="form-control" id="name" required placeholder="  Nome"> 
				<input type="email" form="form-control" id="email" required placeholder="  E-mail"> 
				<input type="text" form="form-control" id="phone" required placeholder="  Telefone">
				<input type="text" form="form-control" id="password" required
					placeholder="  Senha">
			</form>
		</div>

		<div id="buttons-form">
			<button type="button" class="btn btn-primary"
				onclick="salvarUsuario()">Salvar</button>
			<button id="novo" type="button" class="btn btn-secondary"
				onclick="limparForm()">Limpar</button>
		</div>

		<div id="menu-db">
			<input id="bt-listar" type="button" value="Listar Usuários" onclick="clickListarTodos()"> 
			<a href="#nada"><input type="button" value="Nada"></a>
		</div>

		<div id="listar-usuarios" class="modal-c">
			<div id="lista-div">
				<input id="bt-close" type="button" onclick="closeModal()" value="X" >
				<div>
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

		<div id="nada" class="modal-c">
			<div id="nada-div" class="modal-padrao">
				<a href="#" title="Close" class="close">X</a>
				<hr>
				<div>
					<h1>Nada!</h1>
					<p>Nada</p>
				</div>
			</div>
		</div>
	</main>

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
			document.getElementById('titulo-form').innerText = ' Cadastrar Usuário';
			document.getElementById('titulo-form').style.color= 'black';
		}
		function closeModal() {
			document.getElementById('listar-usuarios').style.opacity = '0';
			document.getElementById('listar-usuarios').style.pointerEvents = 'none';
			document.getElementById('listar-usuarios').style.transform = 'translatey(20px)';
		}
		function alerta() {
			alert("Em desenvolvimento!");
		}
		function clickListarTodos(){
			document.getElementById('listar-usuarios').style.opacity = '1';
			document.getElementById('listar-usuarios').style.pointerEvents = 'auto';
			document.getElementById('listar-usuarios').style.opacity = '1';
			document.getElementById('listar-usuarios').style.transition = '400ms';
			document.getElementById('listar-usuarios').style.transform = 'translatey(-20px)';
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
				document.getElementById('titulo-form').innerText = ' Cadastrar Usuário';
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
						alert("Usuário salvo com sucesso!");
					}

				}).fail(function(xhr, status, errorThrow) {
					alert("Erro ao salvar usuário: " + xhr.responseText);
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
				alert("Erro ao excluir usuário: " + xhr.responseText);
			});

		}
		
		function editarUsuario(id){
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
				alert("Erro ao salvar usuário: " + xhr.responseText);
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
										+ '<td>'+ response[i].id+ '</td>'
										+ '<td>'+ response[i].name +'</td>'
										+ '<td>'+ response[i].email +'</td>'
										+ '<td>'+ response[i].phone +'</td>'
										+ '<td>'+ response[i].password +'</td>'
										+ '<td>'
											+'<div class="bt-table">'
												+'<button class="btn btn-primary" onclick="editarUsuario('+ response[i].id +')">Editar</button>'
												+'<button class="btn btn-primary" id="bt-excluir" onclick="excluirUsuario('+ response[i].id +')">Excluir</button>'
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