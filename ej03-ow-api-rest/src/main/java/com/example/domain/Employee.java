package com.example.domain;


public class Employee {
	
	private Long id;
	
	private String name;
	private String nif;
	private Integer age;
	
	public Employee() {
		
	}
	
	public Employee(Long id, String name, String nif, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.nif = nif;
		this.age = age;
	}
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public String getNif() {
		return nif;
	}
	public Integer getAge() {
		return age;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Employee [Id=" + id + ", name=" + name + ", nif=" + nif + ", age=" + age + "]";
	}
	
	
}
