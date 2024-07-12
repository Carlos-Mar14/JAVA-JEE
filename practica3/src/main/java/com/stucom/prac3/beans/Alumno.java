package com.stucom.prac3.beans;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stucom.prac3.jdbc.Conexion;
import com.stucom.prac3.jdbc.ConsultaStatement;

public class Alumno {
	
	private int id;
	private String curso;
	private String nombre;
	private static Vector<Alumno> alumnos=new Vector<Alumno>();
	
	public Alumno() {}
	
	public Alumno(int id, String curso, String nombre) {		
		this.id = id;
		this.curso = curso;
		this.nombre = nombre;
		alumnos.add(this); // anadir el nuevo alumno al vector
	}
	
	// Devolver el vector de alumnos
	private static Vector<Alumno> getAlumnos() {
		return alumnos;
	}
		
	/* Metodo publico estatico que recupera todos los alumnos desde la
	 * Base de Datos, los instancia y los anade a un vector para
	 * finalmente devolver este vector  
	*/ 
	public static Vector<Alumno> load(String query) {
		
		int id=0;
		String curso="";		
		String nombre="";
	
		ResultSet rs = ConsultaStatement.lanzarSelect(query);
		
		// Hacemos un reset del vector para no duplicar los elementos
		// en cada llamada a 'load'
		alumnos.clear();
		
		try {
			
			while (rs.next()) {
				id=rs.getInt("id");
				// curso puede ser nulo por definicion en la tabla				
				curso=rs.getString("curso");
				if (curso.length()==0) curso="sin especificar";
				
				nombre=rs.getString("nombre");				
				
				// Damos de alta un nuevo alumno en la clase
				new Alumno(id, curso, nombre);							
			}
						
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		Conexion.desconecta();
		return getAlumnos();
	}
	
	// Metodo publico de instancia que hace persistente
	// al alumno actual
	public int save() {
		
		int id=this.getId();
		String curso=this.getCurso();
		String nombre=this.getNombre();
		String orden="INSERT INTO alumnos VALUES ("+id+",'"+curso+"','"+nombre+"')";		
		int resultado=ConsultaStatement.grabarFila(orden);
		
		Conexion.desconecta();
		
		return resultado;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
