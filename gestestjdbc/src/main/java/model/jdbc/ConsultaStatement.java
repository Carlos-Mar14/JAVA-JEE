package model.jdbc;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaStatement {
	public static ResultSet lanzarSelect(String select) {

		ResultSet res = null;

		try {
			Statement sentenciaSQL = Conexion.getConexion().createStatement();
			res = sentenciaSQL.executeQuery(select);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}

		return res;
	}
	
	public static void mostrarSelect(ResultSet res, PrintWriter out) {
		try {
			ResultSetMetaData resMD=res.getMetaData();
			int num_cols=resMD.getColumnCount();
			
			for (int i=1;i<=num_cols;i++) 
				out.println(resMD.getColumnName(i)+"\t");				
						
			out.println("<BR>");
			while (res.next()) {
				for (int i=1;i<=num_cols;i++) {
					
					String tmp=resMD.getColumnTypeName(i);
					if (tmp.equals("VARCHAR")) {
						String str = res.getString(i).toString();
						//System.out.print(str+" ");
						out.println(str+" ");
					}else if (tmp.substring(0, 3).equals("INT")) {
						int cod = res.getInt(i);
						//System.out.print(cod+" ");
						out.println(cod+" ");
					}else if (tmp.equals("DATE")) {
						java.util.Date fecha=res.getDate(i);
						//System.out.print(fecha+" ");
						out.println(fecha+" ");
					}else
						//System.out.print(tmp);
						out.println(tmp);
				}
				//System.out.println();
				out.println("<BR>");
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	
	private static int getNuevoID(String tabla, String ID) {
		int newID=0;
		
		ResultSet res=null;
		
		try {
			Statement sentenciaSQL = 
					Conexion.getConexion().createStatement();
			
			res=sentenciaSQL.executeQuery("SELECT MAX("+ID+") FROM "+tabla);
		
			res.next();
			newID=res.getInt(1)+1;
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
				
		return newID;
	}
	
	// Metodo para grabar un registro en una tabla
	public static int grabarFila(String orden) {
		int fila=0;
		
		try {
			Statement sentenciaSQL = 
				Conexion.getConexion().createStatement();
			
			fila=sentenciaSQL.executeUpdate(orden);
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
		return fila;
	}
	
}