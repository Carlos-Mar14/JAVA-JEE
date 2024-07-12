package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListarArticulosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext contexto = request.getServletContext();
		List<String> catalogo = (List<String>) contexto.getAttribute("catalogo");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>ServletListarArticulosServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Lista:</h1>");
			out.println("<ul>");
			if (catalogo != null) {
				for (String articulo : catalogo) {
					out.println("<li>" + articulo + "</li>");
				}
			} else {
				out.println("Has Roto Matrix... Aun no hay productos en el catalogo");
			}
			out.println("</ul>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}
}
