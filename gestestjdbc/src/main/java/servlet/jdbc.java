package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class jdbc
 */
public class jdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public jdbc() {
		super();
		// APso 1: carga del driver:
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 2 Establecer conexion con la db
		final String fabricante = "mysql", servidor = "localhost", puerto = "8889";
		final String db = "dbalumnos", usuario = "root", pwd = "root";
		final String opciones = "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		final String cadenaConexion = "jdbc:" + fabricante + "://" + servidor + ":" + puerto + "/" + db + "?"
				+ opciones;

		try {
			Connection con = DriverManager.getConnection(cadenaConexion, usuario, pwd);

			// Paso 3.A // Obtener informacion de metadata
//     			demoMetaData(con);

			// Paso 3.B = Ejecutar SQL
//     			Statement comando = con.createStatement();
			PreparedStatement comando = null;
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
