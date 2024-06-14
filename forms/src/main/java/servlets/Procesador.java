package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Procesador
 */
public class Procesador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Procesador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ejecutado metodo doPOST");
		
		//Con el requerimietno se captura el valor que el usuario a introducido
		//Se captura mediante el nombre del campo "form_nom" que se le dio en el HTML al input del campo
		//En el request se coje lo del cliente, del usuario
		//Resultados se muestra por terminal
		String nomParam = request.getParameter("form_nom");
		System.out.println(nomParam);
		
		
		//Recoger los turnos del formulario:
		String turnoParm= request.getParameter("turno");
		
		
		
		//Recoger la experiencia del formulario:
		String[] expeParam= request.getParameterValues("expe");
		
		
		//response es para mostrar algo del backend al cliente = Osea, se muestra mensaje en pantalla del cliente
		PrintWriter out = response.getWriter();
		out.write("Hola " + nomParam + " Turno seleccionado: " + turnoParm);
		out.write("\nExperiencia: ");
		
		for(String experiencia: expeParam) {
			out.write("\n" + "->" + experiencia);
		}
		out.close();
	}

}
