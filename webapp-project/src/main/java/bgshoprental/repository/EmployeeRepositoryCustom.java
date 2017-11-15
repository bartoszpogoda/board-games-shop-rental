package bgshoprental.repository;

import bgshoprental.entity.Employee;

public interface EmployeeRepositoryCustom {
	Employee findByEmail(String email);
}
