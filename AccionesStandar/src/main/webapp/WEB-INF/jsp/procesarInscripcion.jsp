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
	<jsp:setProperty name="usuario" property="*" />
	<jsp:forward page="/WEB-INF/jsp/listadoRegistro.jsp" />

</body>
</html>