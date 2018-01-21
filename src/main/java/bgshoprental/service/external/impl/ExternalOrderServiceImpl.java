package bgshoprental.service.external.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.entity.ExternalOrderStatus;
import bgshoprental.repository.ExternalOrderRepository;
import bgshoprental.service.exception.ExternalOrderNotFoundException;
import bgshoprental.service.external.ExternalOrderService;

@Service
public class ExternalOrderServiceImpl implements ExternalOrderService {

	@Autowired
	private ExternalOrderRepository externalOrderRepository;

	@Override
	public boolean add(ExternalOrder externalOrder) {
		return externalOrderRepository.save(externalOrder) != null;
	}

	@Override
	public Iterable<ExternalOrder> findAllExternalOrders() {
		return externalOrderRepository.findAll();
	}

	@Override
	public Iterable<ExternalOrderElement> findElementsForExternalOrderId(int externalOrderId) {
		return findExternalOrderById(externalOrderId).getElements();
	}

	@Override
	public ExternalOrder findExternalOrderById(int externalOrderId) {
		return externalOrderRepository.findOne(externalOrderId);
	}

	@Override
	@Transactional
	public void removeExternalOrderElement(int externalOrderId, int elementId) {
		ExternalOrder externalOrder = externalOrderRepository.findOne(externalOrderId);

		externalOrder.getElements().remove(elementId);
	}

	@Override
	@Transactional
	public void addElementToExternalOrder(int externalOrderId, ExternalOrderElement element) {
		ExternalOrder externalOrder = findExternalOrderById(externalOrderId);

		if (externalOrder == null) {
			throw new ExternalOrderNotFoundException();
		} else {
			element.setExternalOrder(externalOrder);
			externalOrder.getElements().add(element);
		}
	}

	@Override
	@Transactional
	public void processRealisation(int externalOrderId) {
		ExternalOrder externalOrder = externalOrderRepository.findOne(externalOrderId);

		for (ExternalOrderElement element : externalOrder.getElements()) {
			BoardGame boardGame = element.getBoardGame();

			int currentQuantity = boardGame.getSellQuantity();
			int quantityToAdd = element.getQuantity();

			boardGame.setSellQuantity(currentQuantity + quantityToAdd);
		}

		externalOrder.setStatus(ExternalOrderStatus.REALIZED);
	}

	@Override
	public void processCancelation(int externalOrderId) {
		ExternalOrder externalOrder = externalOrderRepository.findOne(externalOrderId);

		externalOrder.setStatus(ExternalOrderStatus.CANCELED);
	}

}
