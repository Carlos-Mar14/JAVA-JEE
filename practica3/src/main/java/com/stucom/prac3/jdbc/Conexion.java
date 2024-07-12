package com.stucom.prac3.jdbc;

import java.sql.*;

/**
 * Clase Conexion 
 * Tiene un metodo estatico que da una conexion con la BD 
 * Se utiliza el patron de diseno SINGLETON: 
 * Si ya existe un objeto se devuelve y si no se crea uno
 */

public class Conexion {
	private static Connection conDB = null;
	private static final String DBUrl = 
			"jdbc:mysql://localhost:8889/dbalumnos?user=root&password=root";

	/** Devuelve un objeto del tipo Connection con la base de datos */
	public static Connection getConexion() {
		// Si no existe la conexion entonces se crea
		if (conDB == null) {
			try {
				// Registamos el driver
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {
				System.out.println("No se ha encontrado el driver JDBC");
			}

			try {
				// Obtenemos la conexion
				conDB = DriverManager.getConnection(DBUrl);
			} catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("SQLState: " + sqle.getSQLState());
				System.out.println("VendorError: " + sqle.getErrorCode());
			}
		}
		// Devolvemos un objeto Connection
		return conDB;
	}

	/** Desconecta de la base de datos */

	public static void desconecta() {
		if (conDB != null) {
			try {
				conDB.close();
				conDB=null;
			} catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("SQLState: " + sqle.getSQLState());
				System.out.println("VendorError: " + sqle.getErrorCode());

			}
		}
	}
}