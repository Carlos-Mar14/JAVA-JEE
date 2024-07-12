package servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//@WebServlet("/cabeceras")
public class Cabeceras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cabeceras() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	ServletConfig config = getServletConfig();
    	String x = config.getInitParameter("ciudad");
    	System.out.println(x);
    	Enumeration<String> params = config.getInitParameterNames();
    	while(params.hasMoreElements()) {
    		String nombre = params.nextElement();
    		System.out.print(nombre + " : ");
    		System.out.println(getInitParameter(nombre));
    	}
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CabecerasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cabeceras: </h1>");
            out.println("<ul>");
            
            Enumeration<String> nombresDeCabeceras = request.getHeaderNames();
            
            while (nombresDeCabeceras.hasMoreElements()) {
                
            	String cabecera = nombresDeCabeceras.nextElement();
                out.print("<li><b>" + cabecera + ": </b>");
                Enumeration<String> valoresDeCabecera = request.getHeaders(cabecera);
                
                while (valoresDeCabecera.hasMoreElements()) {
                    out.println(valoresDeCabecera.nextElement() + " - ");
                }
                out.print("</li>");
		  }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
      } finally {
            out.close();
      }

	}

}
