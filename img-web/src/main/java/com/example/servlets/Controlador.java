package com.example.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.beans.ArticuloBean;
import com.example.jdbc.Conexion;
import com.example.servicios.Operaciones;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Controlador() {
		super();		
	}

	public void init(ServletConfig servletconfig) {
		Conexion
				.setURL("jdbc:mysql://localhost:8889/dbtestimagenes?user=root&password=root");
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("servicio");

		if (operacion.equals("nuevo")) {
			response.sendRedirect("nuevo.html");
		} else if (operacion.equals("grabar")) {	
			RequestDispatcher rq = null;
			try {
				Operaciones.grabar(request, response);
				rq = request.getRequestDispatcher("/WEB-INF/jsp/nuevoarticulo.jsp?grabado=ok");
			} catch (Exception e) {
				request.getSession().setAttribute("excep", e);
				rq = request.getRequestDispatcher("/WEB-INF/jsp/nuevoarticulo.jsp?grabado=err");
				//response.sendRedirect("/WEB-INF/jsp/nuevoarticulo.jsp?grabado=err");
			} finally {
				rq.forward(request, response);
			}
			
		} else if (operacion.equals("recuperar")) {
						
			ArrayList<ArticuloBean> lista = Operaciones.obtenerArticulo(null);
								
			request.setAttribute("lista", lista);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ver.jsp");
			rd.forward(request, response);			
		} else if (operacion.equals("detalle")) {
						
			String codart=request.getParameter("codart");
			
			ArrayList<ArticuloBean> lista = 
					Operaciones.obtenerArticulo(codart);
			
			request.setAttribute("bean", lista.get(0));
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detalle.jsp");
			rd.forward(request, response);			
		}
	}

}
