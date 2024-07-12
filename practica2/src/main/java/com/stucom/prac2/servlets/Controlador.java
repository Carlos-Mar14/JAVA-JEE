package com.stucom.prac2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.stucom.prac2.jdbc.*;

public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		Conexion
		.setURL("jdbc:mysql://localhost:8889/dbalumnos?user=root&password=root");
	}
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{		
		response.setContentType("text/html");
		
		try (PrintWriter out = response.getWriter())
		{
			// Parametros recibidos desde el cliente		
			String consulta = request.getParameter("txtSelect");
			String id = request.getParameter("txtID");
			String curso = request.getParameter("txtCurso");
			String nombre = request.getParameter("txtNombre");

			// Creamos la pagina de resultados		
			out.println("<html>");
			out.println("<head><title>Ejemplo de Servlet y JDBC</title></head>");
			out.println("<body bgcolor=\"#FFFF9D\"><font color=\"#000080\" FACE=\"Arial,Helvetica,Times\" size=2>");
		
			if (request.getServletPath().equals("/consulta.go")) {
				out.println("<CENTER><H3>Usa JDBC para recuperar registros de una tabla</H3></CENTER><HR>");
				ResultSet res = ConsultaStatement.lanzarSelect(consulta);
				ConsultaStatement.mostrarSelect(res, out);
			
			} else if (request.getServletPath().equals("/alta.go")) {
				out.println("<CENTER><H3>Usa JDBC para grabar un registro en una tabla</H3></CENTER><HR>");			
				String orden = "INSERT INTO alumnos VALUES ("+ id +",'"+ curso +"','"+ nombre +"')";
				System.out.println(orden);
				int filas_afectadas = ConsultaStatement.grabarFila(orden);
				out.println("Filas afectadas: " + filas_afectadas);
			}
			out.println("<BR><A href=\"index.html\">Ir a la pantalla inicial</A>");
			out.println("</font></body></html>");			
		}	
	}
	
	/**
	 *  Al finalizar el servlet liberamos la conexion
	 */
	public void destroy() {
		Conexion.desconecta();	
	}
	
}
