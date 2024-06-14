package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.ConecctionJdbc;

import java.io.IOException;
import java.sql.SQLException;

public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Declaramos la varaible de la conexión acà!!!!
	private ConecctionJdbc conexionDb;
       
   
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		try {
	        // Intenta cargar el driver JDBC manualmente
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Configuración de la conexión a la base de datos
	        conexionDb = new ConecctionJdbc("mysql", "localhost", 8889, "dbalumnos", "root", "root", "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
	        
	        // Verifica la conexión exitosa imprimiendo un mensaje en la consola
	        System.out.println("Conexión exitosa!");

	    } catch (ClassNotFoundException e) {
	        throw new ServletException("MySQL JDBC Driver not found.", e);
	    } catch (SQLException e) {
	        throw new ServletException("Error al inicializar la conexión a la base de datos", e);
	    }
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String parmForm = request.getParameter("id");
		System.out.println(parmForm);
		
	}

}
