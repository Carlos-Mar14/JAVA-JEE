<%@ page import="com.example.beans.*,java.util.*"%>
<html>
<head>
<title>Cat�logo de art�culos</title>
</head>

<%
String respuesta=request.getParameter("sinarticulos");

if (respuesta!=null) {%>

<body>
<h1>No hay art�culos en el cat�logo</h1>
<a href="index.html">Inicio</a>
<%
} else { %>
<body>
<center>    
<h1>Cat�logo de art�culos</h1>

<table border=1>
<tr><th>C�digo</th><th>Descripci�n</th></tr>

<%

boolean catalogo=false;
ArrayList<ArticuloBean> lista=
	(ArrayList<ArticuloBean>)request.getAttribute("lista");

if(lista!=null)
  //si existen mensajes para ese destinatario
  //se generar� una tabla con los mismos
  for(int i=0;i<lista.size();i++) {
	ArticuloBean articulo=(ArticuloBean)lista.get(i);
    catalogo=true;
    %>
    <tr><td><%=articulo.getCodart()%></td>
        <td><a href='Controlador?servicio=detalle&codart=<%=articulo.getCodart()%>'>
        <%=articulo.getDescart()%></a></td></tr>
    <%}

if(!catalogo){%>
    <!--si no hay articulos se env�a al usuario a esta misma pagina
    pero informandole de tal hecho -->
    <jsp:forward page="ver.jsp?sinarticulos=cierto"/>
<%}%>
</table>
<br/><br/>
<a href="index.html">Inicio</a>
</center>
<%} %>
</body>
</html>