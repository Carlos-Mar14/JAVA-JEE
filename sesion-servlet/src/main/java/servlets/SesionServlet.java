package servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/SessionServlet")
public class SesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("accion");
		String nuevoAtributo = request.getParameter("parametro");
		String valor = request.getParameter("valor");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet que muestra el contenido de la sesion</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>El contenido de tu sesion es:</h1>");
			HttpSession s = request.getSession();

			if (action.equals("invalidar")) {
				s.invalidate();
				out.println("<h1>Sesion invalidada:</h1>");
			} else {
				s.setAttribute(nuevoAtributo, valor);
				out.println("<ul>");
				Enumeration<String> nombresDeAtributos = s.getAttributeNames();
				while (nombresDeAtributos.hasMoreElements()) {
					String atributo = nombresDeAtributos.nextElement();
					out.println("<li><b>" + atributo + ": </b>" + s.getAttribute(atributo) + "</li>");
				}
				out.println("</ul>");
			}
			out.println("<a href=index.html>" + "Volver a la página anterior</a><br/>");
			out.println(request.getQueryString());
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}
}
