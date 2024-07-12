package com.stucom.prac3.servlets;

import java.io.IOException;
import java.io.PrintWriter; 

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.stucom.prac3.beans.Alumno;
import java.util.*;

public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	
	/**************************************************************
	 * INIT: se obtiene el contexto
	 **************************************************************/
	public void init(ServletConfig config) throws ServletException {
		sc = config.getServletContext(); 
	}
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		// Parametros recibidos desde el cliente		
		String consulta = request.getParameter("txtSelect");
		String id = request.getParameter("txtID");
		String curso = request.getParameter("txtCurso");
		String nombre = request.getParameter("txtNombre");

		/*
		 * El usuario ha seleccionado ver un informe
		 */
		if (request.getServletPath().equals("/informe.go")) {

			// Obtener el tipo de informe seleccionado por el usuario
			String tipoInforme = request.getParameter("optInformes");
			
			/* Invocar al servlet encargado de mostrar los reports.
			 * Le pasamos el tipo de report que debe mostrar
			 */
			
			// Preparacion del direccionamiento
			RequestDispatcher dispatcher = 
				sc.getRequestDispatcher("/Report?tipo="+tipoInforme);
			
			// Redireccionamiento 
			dispatcher.forward(request, response);

		// El usuario ha seleccionado obtener una tabla con los alumnos	
		} else if (request.getServletPath().equals("/consulta.go")) {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// Creamos la pagina de resultados

			out.println("<html>");
			out
					.println("<head><title>Ejemplo de Servlet y JDBC</title></head>");
			out
					.println("<body bgcolor=\"#FFFF9D\"><font color=\"#000080\" FACE=\"Arial,Helvetica,Times\" SIZE=2>");
			out
					.println("<CENTER><H3>Usa JDBC para recuperar registros de una tabla</H3></CENTER><HR>");

			Controlador.printTable(Alumno.load(consulta), out);

			out
					.println("<BR><A href=\"index.html\">Ir a la pantalla inicial</A>");
			out.println("</font></body></html>");
			out.close();

		// El usuario quiere dar de alta un alumno	
		} else if (request.getServletPath().equals("/alta.go")) {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// Creamos la pagina de resultados

			out.println("<html>");
			out
					.println("<head><title>Ejemplo de Servlet y JDBC</title></head>");
			out
					.println("<body bgcolor=\"#FFFF9D\"><font color=\"#000080\" FACE=\"Arial,Helvetica,Times\" SIZE=2>");
			out
					.println("<CENTER><H3>Usa JDBC para grabar un registro en una tabla</H3></CENTER><HR>");

			Alumno a = new Alumno(Integer.parseInt(id), curso, nombre);

			int resultado = a.save();
			out.println("Filas afectadas: " + resultado);

			out
					.println("<BR><A href=\"index.html\">Ir a la pantalla inicial</A>");
			out.println("</font></body></html>");
			out.close();
		}

	}

	// Metodos auxiliares

	private static void printTable(Vector<Alumno> v, PrintWriter out) {
		out.println("<table");
		out
				.println("style=\"background-color: rgb(255, 255, 153); width: 50%; text-align: left; margin-left: auto; margin-right: auto;\"");
		out.println("border=\"1\" cellpadding=\"2\" cellspacing=\"2\">");
		out
				.println("<caption><big><big style=\"color: rgb(0, 0, 153);\">Listado");
		out.println("de alumnos</big></big></caption><tbody>");
		out.println("<tr>");
		out
				.println("<td style=\"text-align: center; color: rgb(0, 0, 153);\"><big>ID</big></td>");
		out
				.println("<td style=\"text-align: center; color: rgb(0, 0, 153);\"><big>Curso");
		out.println("actual</big></td>");
		out
				.println("<td style=\"text-align: center; color: rgb(0, 0, 153);\"><big>Nombre");
		out.println("del alumno</big></td>");
		out.println("</tr>");

		for (Alumno a : v) {
			out.println("<tr>");
			out.println("<td>" + a.getId() + "</td>");
			out.println("<td>" + a.getCurso() + "</td>");
			out.println("<td>" + a.getNombre() + "</td>");
			out.println("</tr>");
		}

		out.println("</tbody>");
		out.println("</table>");

	}	
}
