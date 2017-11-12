package bgshoprental.repository;

import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>{
}
