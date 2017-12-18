package bgshoprental.service.external.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.entity.ExternalOrderStatus;
import bgshoprental.repository.ExternalOrderElementRepository;
import bgshoprental.repository.ExternalOrderRepository;
import bgshoprental.service.external.ExternalOrderService;

@Service
public class ExternalOrderServiceImpl implements ExternalOrderService {

	@Autowired
	private ExternalOrderRepository externalOrderRepository;
	@Autowired
	private ExternalOrderElementRepository externalOrderElementRepository;
	

	@Override
	public boolean add(ExternalOrder externalOrder) {
		return externalOrderRepository.save(externalOrder) != null;
	}

	@Override
	public boolean cancel(ExternalOrder externalOrder) {
		externalOrder.setStatus(ExternalOrderStatus.CANCELED);
		return externalOrderRepository.save(externalOrder) != null;
	}

	@Override
	public boolean finalize(ExternalOrder externalOrder) {
		externalOrder.setStatus(ExternalOrderStatus.REALIZED);
		return externalOrderRepository.save(externalOrder) != null;
	}

	@Override
	public Iterable<ExternalOrder> getAll() {
		return externalOrderRepository.findAll();
	}

	@Override
	public Iterable<ExternalOrderElement> getElements(int externalOrderId) {
		return getById(externalOrderId).getElements();
	}

	@Override
	public ExternalOrder getById(int externalOrderId) {
		return externalOrderRepository.findOne(externalOrderId);
	}

	@Override
	public void removeExternalOrderElement(int externalOrderId, int elementId) {
		//ExternalOrderElement elementToRemove = this.getById(externalOrderId).getElements().get(elementId);
		//externalOrderElementRepository.removeElement(elementToRemove);
		
		externalOrderRepository.removeElement(this.getById(externalOrderId), elementId);
	}

	@Override
	public void addElement(int externalOrderId, ExternalOrderElement element) {
		externalOrderRepository.addElement(this.getById(externalOrderId), element);
	}

}
