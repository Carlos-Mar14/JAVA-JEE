<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="domain.User"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col">
				<%
				Enumeration<String> enumeracion = request.getAttributeNames();
				while (enumeracion.hasMoreElements()) {
					System.out.println(enumeracion.nextElement());
				}

				List<User> lista = (List<User>) request.getAttribute("usuarios_actuales");
				for (User user : lista) {
				%>
				<%=user.getLogin()%>
				<%
				}
				%>




				<!--  -->
				<!-- en el form ponemos el accion ponemos el nombre del componenete que vamos trabajr con este fromulario en el backend  -->
				<form action="controller" method="post">
					<div class="mb-3">

						<!--  -->
						<label for="login" class="form-label">Login</label> <input
							name="login_form" class="form-control" required id="login"
							type="text" placeholder="Login" size="50"> <br> <label
							for="pass" class="form-label">Password</label> <input
							name="pass_form" class="form-control" required id="pass"
							type="password" placeholder="password" size="25"> <br>
					</div>

					<div class="mb-3">
						<label for="empresa" class="form-label">Empresa</label> <select
							class="form-slect" arial-label="Default select example"
							name="empresa_form" id="empresa" size="3" multiple>
							<option>Barcelona</option>
							<option>Madrid</option>
							<option selected>Tarragona</option>
						</select> <br> <label for="obs" class="form-label">Observaciones</label>
						<textarea name="obs" id="obs" rows="5" cols="20"></textarea>

						<input class="form-control" type="submit" value="Acceder">
						<br> <label> <input class="form-control"
							type="checkbox" checked> Remember me
						</label>
					</div>
				</form>
			</div>
		</div>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>