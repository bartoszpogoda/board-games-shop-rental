package bgshoprental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Employee;
import bgshoprental.repository.EmployeeRepository;
import bgshoprental.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee getEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

}
