package bgshoprental.repository;

import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>, EmployeeRepositoryCustom{

}
