<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="usuario" class="model.Usuario" scope="request" />
	
	nombre: <jsp:getProperty name="usuario" property="nombre" />
	Email: <jsp:getProperty name="usuario" property="email" />
	Edad: <jsp:getProperty name="usuario" property="edad" />
	
</body>
</html>