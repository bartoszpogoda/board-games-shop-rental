package bgshoprental.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.repository.ExternalOrderRepositoryCustom;

public class ExternalOrderRepositoryImpl implements ExternalOrderRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public void removeElement(ExternalOrder externalOrder, int elementId) {
		externalOrder.getElements().remove(elementId);
		
		em.persist(externalOrder);
	}

	@Override
	@Transactional
	public void addElement(ExternalOrder externalOrder, ExternalOrderElement externalOrderElement) {
		externalOrder.getElements().add(externalOrderElement);
		externalOrderElement.setExternalOrder(externalOrder);
		
		em.persist(externalOrder);
	}

}
