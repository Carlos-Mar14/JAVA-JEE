<%@ page import="com.example.beans.*,java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Detalle del artículo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Detalle del artículo</h1>

<%

ArticuloBean articulo=(ArticuloBean)request.getAttribute("bean");

%>

<table>
<tr>
<td><%= articulo.getCodart() %></td>
<td><%= articulo.getDescart() %></td>
</tr>
<tr><td colspan="2">
<img border=1 
	src="Imagenes?codart=<%= articulo.getCodart() %>">
</td></tr>
</table>

<a href="javascript:history.back( )">Retrocer</a><br/>
<a href="index.html">Inicio</a>

</body>
</html>

