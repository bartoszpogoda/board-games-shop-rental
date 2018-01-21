package bgshoprental.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import bgshoprental.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	@Query("SELECT e FROM Employee e WHERE e.email LIKE :email")
	Employee findByEmail(@Param("email") String email);
}
