package com.example.servlets;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.servicios.Operaciones;

@WebServlet("/Imagenes")
public class Imagenes extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String codart = request.getParameter("codart");
		byte[] b_imagen;
		try {
			b_imagen=Operaciones.obtenerImagenArticulo(codart);
			InputStream is = new ByteArrayInputStream(b_imagen);

			if (is == null) {
				String pathImag = this.getServletContext().getRealPath("/")
						+ "images/no_disponible.jpg";
				FileInputStream in = new FileInputStream(pathImag);
				is = in;
			}
			
			response.setContentType("image/jpeg");
			response.setContentLength(b_imagen.length);
			response.getOutputStream().write(b_imagen);

			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}