<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.text.SimpleDateFormat" %>
<%@ page import= "java.util.Date" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina de Usuários</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    	<link href="/css/index.css" rel="stylesheet" type="text/css" >
    </head>
    
    <body>
        <main>
            <header>
                <div>
                    <h1>My Project | VH</h1>
                </div>
                <nav>
                    <ul>
                        <li><a href="#Home">Home</a></li>
                        <li><a href="users">Usuários</a></li>
                        <li><a href="#sobre">Sobre</a></li>
                    </ul>
                </nav>
            </header>
            <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); %>
            <h2><%= sdf.format(new Date()) %></h2>
                  	
            </div>
            <div id="sobre" class="modal-c">
                <div id="sobre-div" class="modal-padrao">
                    <a href="#" title="Close" class="close">X</a>
                    <hr>
                    <div>
                        <h1>Web Application Demo</h1>
                        <p>Essa é apenas uma aplicação web básica feito em JAVA com Spring Boot</p> 
                    </div>
                    
                </div>   
            </div>

            <footer>

            </footer>
        </main>
        
    </body>
</html>