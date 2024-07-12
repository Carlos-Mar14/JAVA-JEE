<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Nuevo</title>
</head>
<%
String respuesta=request.getParameter("grabado");

if (respuesta!=null) {
if (respuesta.equals("ok")) {
%>
<body>
<h1>Artículo grabado correctamente</h1>
<a href="index.html">Inicio</a>
<%
} else {
%>
<body>
<h1>Artículo NO grabado</h1>
<h3>
<%
Exception e=(Exception)request.getSession().getAttribute("excep");
out.println("Motivo: "+e.getMessage());
request.getSession().removeAttribute("excep");
%>
</h3>
<a href="index.html">Inicio</a>
<%	
}
}%>
</body>
</html>