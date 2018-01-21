package bgshoprental.repository;

import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, String> {
	
}
