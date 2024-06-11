package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion=request.getParameter("operacion");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		if (operacion!=null) {
			if (operacion.equals("autenticar")) {
				System.out.println("Operacion: AUTENTICAR");				
			} else {
				System.out.println("Operacion: REGISTRAR");
			}
			System.out.println("id: "+id);
			System.out.println("password: "+password);
		} else { 
			System.out.println("Operacion nula!");
		}
		
		PrintWriter out=response.getWriter();
		out.write("Formulario procesado");
		out.close();
	}


}
