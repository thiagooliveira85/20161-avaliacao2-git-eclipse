<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/sticky-footer-navbar.css" rel="stylesheet">
</head>
<body>
	
	<div class="container" style="width: 450px;">
      
      <div class="jumbotron">
        <h2><a href='inicio'>Acesso a Agenda Virtual</a></h2>

			<form class="form-signin" action="AutenticacaoController?acao=efetuarLogin" method="POST">
				
				<label for="inputEmail" class="sr-only">Login:</label> 
				<input type="login" id="login" name="login" class="form-control" placeholder="login" required> 
				<br />
				<label for="inputPassword" class="sr-only">Senha:</label> 
				<input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" required>
				<br />
				<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
			</form>
			<br />
			<c:if test="${msgErro ne null}">
				<Font color="red"><c:out value="${msgErro}"></c:out></Font>
			</c:if>
		</div>

    </div>

</body>
</html>