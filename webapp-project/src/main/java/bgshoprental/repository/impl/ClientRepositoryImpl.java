package bgshoprental.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bgshoprental.entity.Client;
import bgshoprental.repository.ClientRepositoryCustom;


public class ClientRepositoryImpl implements ClientRepositoryCustom {

	@PersistenceContext
    private EntityManager em;
	 
	@Override
	public Client findUserByEmail(String email) {
		List<Client> resultList = em.createQuery(
		        "SELECT c FROM Client c WHERE c.email LIKE :clientEmail", Client.class)
		        .setParameter("clientEmail", email)
		        .getResultList();
		
		return resultList.isEmpty() ? null : resultList.get(0);
	}

}
