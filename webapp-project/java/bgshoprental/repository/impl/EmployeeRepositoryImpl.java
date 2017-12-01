package bgshoprental.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bgshoprental.entity.Employee;
import bgshoprental.repository.EmployeeRepositoryCustom;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public Employee findByEmail(String email) {

		List<Employee> resultList = em
				.createQuery("SELECT e FROM Employee e WHERE e.email LIKE :employeeEmail", Employee.class)
				.setParameter("employeeEmail", email).getResultList();

		return resultList.isEmpty() ? null : resultList.get(0);
	}

}
