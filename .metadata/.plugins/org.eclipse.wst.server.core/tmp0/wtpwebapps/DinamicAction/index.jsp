<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingreso al CLUB</title>

<script type="text/javascript">
	function setOperacion(nomOperacion) {
		document.forms[0].operacion.value = nomOperacion;
		document.forms[0].submit();
	}
</script>
</head>

<body>
<form method="post" action="Controlador">
	<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
		cellpadding="5">
		<tr>
			<td align="right" width="20%">Id</td>
			<td width="60%"><input type="text" name="id"></td>
		<tr>
			<td align="right" width="20%">Password</td>
			<td width="60%"><input type="password" name="password"></td>
		</tr>
		<tr>
			<td align="right" width="20%"><input type="button" align="middle"
				value="Autenticar" onclick="javascript:setOperacion('autenticar');">
			</td>
			<td width="20%"><input type="button" align="middle"
				value="Registrar" onclick="javascript:setOperacion('registrar');">
			</td>
		</tr>
	</table><br />
	<input type="hidden" name="operacion" value="" />
</form>
</body>
</html>