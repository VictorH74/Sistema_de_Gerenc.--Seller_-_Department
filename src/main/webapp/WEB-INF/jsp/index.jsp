<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pagina Inicial</title>
    	<link href="/css/entidades.css" rel="stylesheet" type="text/css" >
    	<style>
    		main{
	            padding-left: 5em;
	            align-items: center;
        	}
        	main h2{
	            border-left: 30px double rgba(20, 20, 20, 0.712);
	            color: rgba(20, 20, 20, 0.712);
	            font-size: 20em;
	            letter-spacing: 50px;
        	}

       	 	@media(max-width: 500px){
            	.container{
                	height: 100vh;
           		}
            	main{
                	padding-left: 2em;
            	}
            	main h2{
	                margin-top: 1em;
	                font-size: 5em;
	                letter-spacing: 4px;
            	}
        	}
    	</style>
    </head>
    
    <body>
        <div class="container">
        <header>
            <div class="logo">
                <h1>Home | VH</h1>
            </div>
            
            <nav>
                <ul class="menu">
                    <li><a href="/" id="current-page">Home</a></li>
                    <li><a href="/users">Usuários</a></li>
                    <li><a href="/orders">Pedidos</a></li>
                    <li><a href="/products">Produtos</a></li>
                    <li><a href="/categories">Categorias</a></li>
                    <li><a href="#sobre">Sobre</a></li>
                </ul>
            </nav>
        </header>

        <main>
        <%= "<h2>Hello!</h2>" %>
        </main>
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
    </body>
</html>