package com.example.servicios;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileItemFactory;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.example.beans.ArticuloBean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.jdbc.Conexion;

public class Operaciones {

	/*
	 * Grabar un nuevo art�culo
	 */
	public static void grabar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Esto es parte de la lib de commons-fileupload
		// Comprueba si la peticion es de tipo 'multipart'
		boolean isMultipart = JakartaServletFileUpload.isMultipartContent(request);

		// Si el formulario se env�a con el atributo
		// enctype='multipart/form-data'
		if (isMultipart) {

			// Ceamos un objeto para manejar la peticion que
			// incluye el fichero de subida
			JakartaServletFileUpload upload = new JakartaServletFileUpload();			
			
			ArticuloBean articulo = new ArticuloBean();
			
			// Parse the request
			upload.getItemIterator(request).forEachRemaining(item -> {				
			    String name = item.getFieldName();
			    InputStream stream = item.getInputStream();
			 
			    String codArt = "", descArt = "";
			    
			    if (item.isFormField()) {
			    	if (name.equals("codart")) {
						codArt = IOUtils.toString(stream, StandardCharsets.UTF_8);
						articulo.setCodart(codArt);
						System.out.println(codArt);
			    	}
					else if (name.equals("descart")) {
						descArt = IOUtils.toString(stream, StandardCharsets.UTF_8);
						articulo.setDescart(descArt);
						System.out.println(descArt);
					}
			    } else {
			    	try {
						Operaciones.addArticulo(articulo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						Operaciones.insertarImagenArticulo(articulo.getCodart(), stream, stream.available()*2);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			});
			
		}
	}

	/*
	 * A�adir un nuevo articulo.
	 */
	private static void addArticulo(ArticuloBean articulo)
			throws Exception {

			PreparedStatement ps = Conexion.getConexion().prepareStatement(
					"insert into testimagenes values (?,?,?)");

			ps.setString(1, articulo.getCodart());
			ps.setString(2, articulo.getDescart());
			ps.setString(3, null); // sin imagen

			ps.executeUpdate();
			ps.close();

	}

	/*
	 * Inserta una imagen en la tabla 'testimagenes' a partir de una ruta
	 */
	private static void insertarImagenArticulo(String codart, InputStream inps,
			long size) throws Exception {

		PreparedStatement ps = null;

			ps = Conexion.getConexion().prepareStatement(
					"UPDATE testimagenes SET imagen = ? WHERE"
							+ " idarticulo = ?");

			ps.setBinaryStream(1, inps, (int) size);
			ps.setString(2, codart);
			ps.executeUpdate();
			ps.close();
	}

	/*
	 * 
	 */
	public static byte[] obtenerImagenArticulo(String codart) {

		ResultSet rst = null;
		PreparedStatement pstm = null;
		byte[] buffer = null;

		try {

			String sql = "select imagen from testimagenes where idarticulo = ?";
			pstm = Conexion.getConexion().prepareStatement(sql);
			pstm.setString(1, codart);
			rst = pstm.executeQuery();

			while (rst.next()) {
				Blob bin = rst.getBlob("imagen");
				if (bin != null) {
					InputStream inStream = bin.getBinaryStream();
					int size = (int) bin.length();
					// System.out.println(" El tama�o en bytes " + size);
					buffer = new byte[size];
					/*int length = -1;
					int k = 0;*/
					try {
						inStream.read(buffer, 0, size);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {			
			rst = null;
			pstm = null;
		}
		return buffer;
	}

	
	public static ArrayList<ArticuloBean> obtenerArticulo(String codart) {
		ArrayList<ArticuloBean> lista = null;
		Statement st;
		ResultSet rs;
		try {
			st = Conexion.getConexion().createStatement();
			String tsql;
			tsql = "select idarticulo, desarticulo from testimagenes";
			
			if (codart!=null)
				tsql+=" where idarticulo='" + codart + "'";
			
			rs = st.executeQuery(tsql);
			lista = new ArrayList<ArticuloBean>();
			// para cada articulo encontrado crea un objeto
			// ArticuloBean y lo a�ade a la colecci�n ArrayList
			while (rs.next()) {
				ArticuloBean m = new ArticuloBean(rs.getString("idarticulo"), 
						rs.getString("desarticulo"));
				lista.add(m);
			}
			Conexion.desconecta();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (lista);
	}
}