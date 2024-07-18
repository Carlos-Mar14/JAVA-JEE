<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset=UTF-8>
<!--<meta http-equiv="refresh" content="1" >  -->
<%!int ultimo = 0;%>
<%!public String hora() {
		return (new java.util.Date()).toString();
	}%>
<html>
<head>
<title>Simple JSP</title>
</head>
<body>
	<h1>Una bonita tabla:</h1>
	<table border=2>
		<%
for (int i = ultimo; i < ultimo + 10; i++) {%>
		<tr>
			<td>Numero</td>
			<td><%=i%></td>
		</tr>
		<%}
ultimo += 10;
%>
	</table>
	<p>
		La hora actual es:
		<%=hora()%></p>
</body>
</html>
