<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date, java.text.SimpleDateFormat"%>
<!-- Uso la clase Date y SimpleDateFormat del paquete java.util y java.text -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo JSP</title>
</head>
<body>
	<h1>Hola mundo JSP</h1>
	<p>
		La fecha actual en el servidor es
		<%= printPretty(new Date()) %>
	</p>
	<%
	int ultimo = 0;
	%>
	<%
	//Esto es un scriptlet
	out.print(++ultimo);
	%>

	<%!
	// Definición del método printPretty
	public String printPretty(Date fecha) {
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    return formato.format(fecha);
	}
	%>

</body>
</html>
