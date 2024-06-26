package servlets;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import data.JpaUtils;
import data.UserDao;
import data.UserDaoHibernate;
import data.UserDaoJdbc;
import domain.User;

//Acà estamos enlazando el nombre del controlador "Controller" con el nombre que le dimos en el html "controller"
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public Controller() {
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao userDao = new UserDaoHibernate();
		List<User> usuarios = userDao.getAll();
		
		request.setAttribute("usuarios_actuales", usuarios);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(request, response);
		
		//response.sendRedirect("login.jsp");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Resposanbilidad Acá abajo
		// Se encarga de recoger los datos del navegador
		String login = request.getParameter("login_form");
		String pwd = request.getParameter("pass_form");

		// Obetener las empresasw a aprtir de la lista deplegable multiple
		String[] empresas = request.getParameterValues("empresa_form");
		// Arriba ^ HAta acà arriba se recoger los datos del navegador

		// ABAJO: Se esta creano un objeot java en memoria y pitnadolo con los datos que
		// viene de la web
		String empresa = "";
		for (String emp : empresas)
			empresa += emp + " - ";

		User user = new User();
		user.setLogin(login);
		user.setPwd(pwd);
		user.setEmpresa(empresa);
		// ARRIBA: Se esta creano un objeot java en memoria y pitnadolo con los datos
		// que viene de la web

		// ABAJO
		// Se esta haciendo conexion cn la db de forma "DAOHibernate o JDBC"
		// Con estas dos lineas creo un nuevo usuario

		UserDao userDao = new UserDaoHibernate();
		// UserDao userDao = new UserDaoJdbc();
		userDao.create(user);

		// ARRIBA

		doGet(request, response);
		
		//System.out.println("login: " + login + " pdw: " + pwd);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
