package bgshoprental.service;

import bgshoprental.entity.Employee;

public interface UserService {
	Employee getEmployeeByEmail(String email);
}
