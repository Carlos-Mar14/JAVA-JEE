package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutenticacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String passwordAdministrador;

	@Override
    public void init(ServletConfig config) {
    	
        String ficheroUsuarios =
                config.getInitParameter("ficheroUsuarios");
        ServletContext contexto = config.getServletContext();
        
        InputStream is =
                contexto.getResourceAsStream(ficheroUsuarios);
        Properties ficheroPropiedades = new Properties();
                
        try {
            ficheroPropiedades.load(is);
            passwordAdministrador =
                    ficheroPropiedades.getProperty("administradorPassword");
        } catch (Exception ex) {
            Logger.getLogger(AutenticacionServlet.class.getName()).log(
                    Level.SEVERE, "No se pudo cargar el fichero de passwords", ex);
        }
    }

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String articulo = request.getParameter("articulo");
		ServletContext contexto = request.getServletContext();
		if (usuario != null && usuario.equals("administrador") && password.equals(passwordAdministrador)) {
			request.setAttribute("autenticado", true);
			request.setAttribute("articulo", articulo);
			RequestDispatcher addArticuloServlet = contexto.getNamedDispatcher("AddArticuloServlet");
			addArticuloServlet.forward(request, response);
		} else {
			RequestDispatcher paginaError = contexto.getRequestDispatcher("/error.html");
			paginaError.forward(request, response);
		}
	}

}
