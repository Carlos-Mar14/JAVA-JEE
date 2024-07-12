package com.example.api;

import java.util.List;

import com.example.domain.Employee;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("employees")
@Produces("application/json")
@Consumes("application/json")
public class EmployeeResource {

	@GET
	public List<Employee> findAll() {
		return List.of(
			new Employee(1L, "Juan", "222", 52),
			new Employee(2L, "Fran", "333", 32)
				);
	}
}
