package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class AddArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext contexto = request.getServletContext();
        Boolean autenticado = (Boolean) request.getAttribute("autenticado");
        if (!autenticado) {
            RequestDispatcher paginaError =
                    contexto.getRequestDispatcher("error.html");
            paginaError.forward(request, response);
        } else {
            List<String> catalogo = (List<String>) contexto.getAttribute("catalogo");
            if (catalogo == null) {
            	catalogo = new LinkedList<String>();
                contexto.setAttribute("catalogo", catalogo);
            }
            catalogo.add((String) request.getAttribute("articulo"));
            RequestDispatcher listarArticulosServlet =
                    contexto.getNamedDispatcher("ListarArticulosServlet");
            listarArticulosServlet.forward(request, response);
        }

	}

}
