<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista de contatos</title>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/sticky-footer-navbar.css" rel="stylesheet">
</head>
<body>
	
	<center>
		<h3>Seja bem vindo ${nomeUsuario} a sua agenda de contatos virtual! <a href='AutenticacaoController?acao=efetuarLogout'><img width='30' height='30' src='imagens/logout.png' title='Efetuar logoff'/></a></h3>
	</center>
	
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Lista de contatos</div>

		<!-- Table -->
		<table class="table">
			<TR>
				<TH>Nome</TH>
				<TH>Telefone</TH>
				<TH>Email</TH>
				<TH>Facebook</TH>
				<TH>Twitter</TH>
				<TH>Observações</TH>
				<TH></TH>
			</TR>
			<c:forEach var="c" items="${contatos}">
			  	 <TR>
			  	 	<td><c:out value="${c.nome}"/></td>
			  	 	<td><c:out value="${c.telefone}"/></td>
			  	 	<td><c:out value="${c.email}"/></td>
			  	 	<td><c:out value="${c.facebook}"/></td>
			  	 	<td><c:out value="${c.twitter}"/></td>
			  	 	<td><c:out value="${c.observacoes}"/></td>
			  	 </TR>
			</c:forEach>	
		</table>
	</div>
	

</body>
</html>