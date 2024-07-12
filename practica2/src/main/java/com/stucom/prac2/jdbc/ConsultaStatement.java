package com.stucom.prac2.jdbc;

import java.io.PrintWriter;
import java.sql.*;

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
			ResultSetMetaData resMD = res.getMetaData();
			int num_cols = resMD.getColumnCount();

			for (int i = 1; i <= num_cols; i++)
				out.println(resMD.getColumnName(i).toUpperCase() + "\t");

			out.println("<BR>");
			while (res.next()) {
				for (int i = 1; i <= num_cols; i++) {

					String tmp = resMD.getColumnTypeName(i);
					if (tmp.equals("VARCHAR")) {
						String str = res.getString(i).toString();
						out.println(str + " ");
					} else if (tmp.substring(0, 3).equals("INT")) {
						int cod = res.getInt(i);
						out.println(cod + " ");
					} else if (tmp.equals("DATE")) {
						java.util.Date fecha = res.getDate(i);
						out.println(fecha + " ");
					} else
						out.println(tmp);
				}

				out.println("<BR>");
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	// Metodo para grabar un registro en una tabla
	public static int grabarFila(String orden) {
		int fila = 0;

		try {
			Statement sentenciaSQL = Conexion.getConexion().createStatement();

			fila = sentenciaSQL.executeUpdate(orden);

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}

		return fila;
	}

}
