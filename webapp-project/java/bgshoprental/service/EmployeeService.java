package bgshoprental.service;

import bgshoprental.entity.Employee;


public interface EmployeeService {
	Iterable<Employee> getAllEmployees();

	void register(String firstName, String lastName, String email, String password);
}
