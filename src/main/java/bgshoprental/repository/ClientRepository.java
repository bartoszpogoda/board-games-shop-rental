package bgshoprental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import bgshoprental.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>{
	
	@Query("SELECT c FROM Client c WHERE c.email LIKE :email")
	Client findByEmail(@Param("email") String email);
	
	@Query("select c from Client c where c.lastName like %?1%")
	List<Client> findByLastNameContains(String lastNamePortion);

}
