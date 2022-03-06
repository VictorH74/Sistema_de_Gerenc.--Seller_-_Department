<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pagina Inicial</title>
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
        	@charset "ISO-8859-1";

			*{
			    padding: 0;
			    margin: 0;
			    box-sizing: border-box;
			    font-family: Arial, Helvetica, sans-serif;
			}
			
			input[type=button]:hover{
			    cursor: pointer;
			    background: #0170703d;
			}
			
			hr{ clear: both}
			
			td{ width: 80px; height: 45px; text-align: center;}
			
			.bt-table{display: inline-flex}
			
			#bt-close{
				position: fixed;
				width: 50px;
				right: 5px;
				top: 5px;
			}
			
			.bt_list{
			    height: 30px;
			    width: 55px;
			    border: none;
			    border-radius: 6px;
			}
			#bt-excluir_list{
				background-color: rgb(201, 44, 44);
				margin-left: 10px;
			}
			#bt_editar_list{
			    background-color: rgb(52, 163, 228);
			}
			
			#bt_editar_list:hover{ background-color: rgb(31, 95, 133);}
			#bt-excluir_list:hover{ background-color: rgb(138, 44, 44);}
			
			#bt-excluir{
				background-color: red;
				margin-left: 5px;
				border: none;
			}
			
			#bt-excluir:hover{ background-color: #c40000}
			
			#bt-form-salvar{ background: #00bec4}
			#bt-form-limpar{ background: #ffef12}
			
			#bt-form-salvar:hover{ background: #00bec449}
			#bt-form-limpar:hover{ background: #ffef124b}
			
			
			#current-page{ color: rgb(41, 133, 133)}
			
			a{ text-decoration: none}
			
			.container{
			    background: linear-gradient(rgb(205, 227, 230), rgb(58, 121, 129));
			    display: flex;
			    flex-direction: column;
			    height: 100vh;
			}
			
			header{
			    height: 7em;
			    padding: 20px;
			    display: flex;
			    justify-content: space-between;
			}
			
			.logo{ display: flex}
			
			.menu{
			    display: flex;
			    align-items: flex-end;
			    height: 100%;
			}
			
			.menu li{ list-style: none}
			
			.menu li a{
			    color: rgb(44, 53, 54);
			    font-size: 1.4em;
			    padding: 10px;
			}
			
			.menu li a:hover{ color: rgb(41, 133, 133)}
			
			main{
			    height: 100%;
			    display: grid;
			    grid-template-columns: 30em 1fr;
			}
			
			.form-area{
			    padding: 30px 4em;
			}
			
			form{
			    margin-top: 30px;
			    display: flex;
			    flex-direction: column;
			}
			
			form input[type=text], input[type=email], select{
			    height: 3em;
			    margin-bottom: 2em;
			    padding-left: 10px;
			    border-radius: 8px;
			    box-shadow: 3px 3px 10px black;
			    font-size: .9em;
			    border: none;
			    outline: none;
			}
			
			form input[id=id]{ background: rgb(198, 207, 209)}
			
			form input[type=text]:focus, form input[type=text]:valid{
			    background: rgb(198, 207, 209);
			}
			
			.form-buttons input{
			    background: rgb(76, 155, 175);
			    height: 2.8em;
			    width: 5em;
			    margin: 10px 20px 0 0;
			    border-radius: 10px;
			    border: none;
			}
			
			.menu-db-area{
			    background: rgba(160, 164, 165, 0);
			    border-radius: 10px;
			    padding: 3em 20px 0 0;
			    display: flex;
			    justify-content: end;
			}
			
			.menu-db{
			    display: flex;
			    flex-direction: column;
			    width: 16.5em;
			}
			
			.menu-db input{
			    width: 100%;
			    min-width: 10em;
			    height: 4em;
			    margin-bottom: 1.8em;
			    border-radius: 15px;
			    border: none;
			    box-shadow: 2px 2px 10px black;
			    font-size: 1em;
			}
			
			#tabela-result{color: rgb(255, 255, 255)}
			
			.modal-padrao{
			    height: 300px;
			    width: 45%;
			    margin: 10em auto;
			    border-radius: 12px;
			    background-color: #fff; 
			}
			
			.modal-c{
			    position: fixed;
			    font-family: Verdana, Geneva, Tahoma, sans-serif;
			    top: 0;
			    right: 0;
			    bottom: 0;
			    left: 0;
			    background: rgba(27, 27, 27, 0.493);
			    z-index: 9999;
			    opacity: 0;
			    pointer-events: none;
			}
			
			.modal-c:target{
			    display: block;
			    opacity: 1;
			    pointer-events: auto;
			    transition: opacity 200ms ease-in;
			}
			
			.close{
			    margin: 5px 15px 20px 0;
			    float: right;
			    color: #000000;
			    text-decoration: none;
			    font-size: 1.5em;
			    font-family: Verdana, Geneva, Tahoma, sans-serif;
			}
			
			.close:hover{ color: #b50909; transition: 200ms;}
			
			#lista-modal{ top:20px; bottom:-20px}
			
			#lista-div{
			    display: flex;
			    flex-direction: column;
			    height: 100vh;
			    overflow-y: hidden;
			}
			
			.button-close{
			    display: flex;
			    justify-content: end;
			    padding: 10px;
			}
			
			.button-close input{
			    height: 2em;
			    width: 4em;
			    background: none;
			    border-radius: 5px;
			    border: none;
			    background: #b5090957;
			    font-size: 1em;
			    transition: 200ms;
			}
			.button-close input:hover{ background: #b50909}
			
			#sobre-div div{margin: 20px 20px;}
			
			#sobre p{margin-top: 5%; margin-bottom: 5%;}
			
			
			
			@media (max-width: 500px){
			
			.container{ width: 100vw; height: 100%}
			
			header{ height: 20em}
			
			input{ font-size: 1em}
			
			.logo{
			    background: rgb(194, 216, 219);
			    justify-content: center;
			    align-items: center;
			    border-radius: 20px;
			    padding-left: 1em;
			    margin-bottom: 1em;
			    width: 15em;
			}
			
			.menu{ display: block;}
			
			.menu li{ margin-bottom: 1em;}
			
			.form-area{ padding: 2em;}
			
			form{ margin-right: .9em;}
			
			.form-buttons{
			    display: flex;
			    justify-content: space-around;
			    padding: 0;
			}
			
			.form-buttons input{
			    height: 3.5em;
			    width: 30%;
			    margin: 0;
			}
			
			main{ display: block}
			
			.menu-db-area{
			    justify-content: center;
			    padding: 30px;
			}
			
			.menu-db{ width: 100%}
			
			.modal-padrao{
			    width: 90%;
			    margin: 10em auto;
			}
			
			#sobre-div a{ padding: 10px}
			
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