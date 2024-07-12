package com.example.beans;

public class ArticuloBean {
	
	private String codart;
	private String descart;
	
	public ArticuloBean() {
		super();
	}
	
	public ArticuloBean(String id, String texto) {
		codart=id;
		descart=texto;
	}
	
	public String getCodart() {
		return codart;
	}
	public void setCodart(String codart) {
		this.codart = codart;
	}
	public String getDescart() {
		return descart;
	}
	public void setDescart(String descart) {
		this.descart = descart;
	}
	
}
