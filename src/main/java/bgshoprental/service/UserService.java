package bgshoprental.service;

import java.security.Principal;

import bgshoprental.entity.Client;
import bgshoprental.entity.Employee;

public interface UserService {
	Employee getEmployeeByEmail(String email);
	
	Client getClientByEmail(String email);
}
