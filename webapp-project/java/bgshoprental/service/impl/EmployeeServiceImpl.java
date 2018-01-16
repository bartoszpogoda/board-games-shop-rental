package bgshoprental.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Employee;
import bgshoprental.repository.EmployeeRepository;
import bgshoprental.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private Md5PasswordEncoder md5PasswrdEncoder;

	@Override
	public Iterable<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void register(String firstName, String lastName, String email, String password) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmail(email);
		employee.setPassword(md5PasswrdEncoder.encodePassword(password, null));
		employee.setHireDate(Calendar.getInstance());
		
		employeeRepository.save(employee);
	}
}
