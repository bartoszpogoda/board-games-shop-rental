package bgshoprental.service;

import bgshoprental.entity.Employee;


public interface EmployeeService {
	Iterable<Employee> getAllEmployees();
	
	Employee findEmployeeById(Integer employeeId);

	void register(String firstName, String lastName, String email, String password);
	
	void removeEmployee(Integer employeeId);
}
