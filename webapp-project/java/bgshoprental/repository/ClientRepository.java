package bgshoprental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>, ClientRepositoryCustom{
	@Query("select c from Client c where c.lastName like %?1%")
	List<Client> findByLastNameContains(String lastNamePortion);
}
