package bgshoprental.repository;

import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.Rental;

public interface RentalRepository extends CrudRepository<Rental, Integer>{

}
