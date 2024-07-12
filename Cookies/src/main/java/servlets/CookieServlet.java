package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombreNuevaCookie = request.getParameter("cookie").trim();
		String valor = request.getParameter("valor").trim();
		int duracion;
		try {
			duracion = Integer.parseInt(request.getParameter("duracion"));
		} catch (NumberFormatException e) {
			duracion = -1;
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet que muestra cookies</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>El contenido de tu sesion es:</h1>");
			out.println("<ul>");
			if (nombreNuevaCookie != null && valor != null && !nombreNuevaCookie.equals("")) {
				
				Cookie nuevaCookie = new Cookie(nombreNuevaCookie, valor);
				nuevaCookie.setMaxAge(duracion);
				
				response.addCookie(nuevaCookie);
				out.println("<li><b>" + nuevaCookie.getName() + ":</b > " + nuevaCookie.getValue()
						+ "; fecha de expiracion: " + nuevaCookie.getMaxAge() + "</li>");
			}
			Cookie[] todasLasCookies = request.getCookies();
			if (todasLasCookies != null) {
				for (Cookie cookie : todasLasCookies) {
					out.println("<li><b>" + cookie.getName() + ":</b>" + cookie.getValue() + "-, fecha de expiracion: "
							+ cookie.getMaxAge() + "</li>");
				}
			}
			out.println("</ul>");
			out.println("<a href=index.html>" + "Volver a la pagina anterior</a><br/>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
