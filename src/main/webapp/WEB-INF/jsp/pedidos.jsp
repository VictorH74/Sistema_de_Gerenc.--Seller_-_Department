<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Pagina de Pedidos</title>
		<link rel="stylesheet" type="text/css" href="/css/entidades.css">
	</head>
	<body>
		<div class="container">
	        <header>
	            <div class="logo">
	                <h1>Pedidos | VH</h1>
	            </div>
	            
	            <nav>
	                <ul class="menu">
	                    <li><a href="/">Home</a></li>
	                    <li><a href="/users">Usuários</a></li>
	                    <li><a href="/orders" id="current-page">Pedidos</a></li>
	                    <li><a href="/products">Produtos</a></li>
	                    <li><a href="/categories">Categorias</a></li>
	                    <li><a href="#sobre">Sobre</a></li>
	                </ul>
	            </nav>
	        </header>
	
	        <main>
	            <div class="form-area">
	                <h1 id="titulo-form">Cadastrar Pedido</h1>
	                <form action="">
	                    <input type="text" form="form-control" id="id" readonly="readonly"placeholder="ID"> 
	                    <select name="status" id="status">
	                        <option value="" selected disabled>Status</option>
	                        <option value="1" >Esperando Pagamento</option>
	                        <option value="2" >Pago</option>
	                        <option value="3" >Enviado</option>
	                        <option value="4" >Entregue</option>
	                        <option value="5" >Cancelado</option>
	                    </select>
	
	                    <div class="form-buttons">
	                        <input type="button" value="Salvar" id="bt-form-salvar" onclick="salvarPedido()">
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
                            <th scope="col">Momento</th>
                            <th scope="col">Status</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Produtos</th>
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
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"
			integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
			crossorigin="anonymous"></script>
		
		<script type="text/javascript">
			function setDef() {
				document.getElementById('novo').style.backgroundColor = 'orange'
			}
			
			function limparForm(){
				$("#id").val("");
				$("#client-id").val("");
				$("#status").val("");
				document.getElementById('titulo-form').innerText = ' Cadastrar Pedido';
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
		
		<script type="text/javascript">
			function listarTodos(){
				$.ajax({
					
					method : "GET",
					
					url : "orders/listarTodos",
					
					success : function(response){
						
						$('#tabela-result > tbody > tr').remove();
						
						for(var i=0; i<response.length; i++){
						$('#tabela-result > tbody').append(
								'<tr>'
										+ '<td>'+ response[i].id+ '</td>'
										+ '<td>'+ response[i].moment +'</td>'
										+ '<td>'+ response[i].orderStatus +'</td>'
										+ '<td>'+ response[i].client.name +'</td>'
										+ '<td> <button class="btn btn-primary" id="bt-ver">Ver</button> </td>'
										+ '<td>'
											+'<div class="bt-table">'
												+'<button class="bt_list" id="bt_editar_list" onclick="editarUsuario('+ response[i].id +')">Editar</button>'
												+'<button class="bt_list" id="bt-excluir_list" onclick="excluirUsuario('+ response[i].id +')">Excluir</button>'
											+'</div>'
										+'</td>'
								+'</tr>');
						}
						
					}
				}).fail(function(xhr, status, errorThrow){
					alert("Erro: " + xhr.responseText);
				});
			}
			
			function salvarPedido(){
				
				$.ajax({
					
					method: "POST",
					url: "orders/salvar",
					data: JSON.stringify({
						id : id,
						orderStatus : status,
					})
					
				}).fail(function(xhr, status, errorThrow){
					alert("Erro: " + xhr.responseText);
				});
			}
		</script>
	</body>
</html>