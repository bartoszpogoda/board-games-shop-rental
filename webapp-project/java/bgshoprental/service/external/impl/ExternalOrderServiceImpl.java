package bgshoprental.service.external.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.entity.ExternalOrderStatus;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.ExternalOrderRepository;
import bgshoprental.service.external.ExternalOrderService;

@Service
public class ExternalOrderServiceImpl implements ExternalOrderService {

	@Autowired
	private ExternalOrderRepository externalOrderRepository;

	@Autowired
	private BoardGamesRepository boardGamesRepository;

	@Override
	public boolean add(ExternalOrder externalOrder) {
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
	@Transactional
	public void removeExternalOrderElement(int externalOrderId, int elementId) {
		externalOrderRepository.removeElement(this.getById(externalOrderId), elementId);
	}

	@Override
	@Transactional
	public void addElement(int externalOrderId, ExternalOrderElement element) {
		externalOrderRepository.addElement(this.getById(externalOrderId), element);
	}

	@Override
	@Transactional
	public void realise(int externalOrderId) {
		ExternalOrder externalOrder = externalOrderRepository.findOne(externalOrderId);

		for (ExternalOrderElement element : externalOrder.getElements()) {
			BoardGame boardGame = element.getBoardGame();

			int currentQuantity = boardGame.getSellQuantity();
			int quantityToAdd = element.getQuantity();

			boardGame.setSellQuantity(currentQuantity + quantityToAdd);

			boardGamesRepository.save(boardGame);
		}

		externalOrder.setStatus(ExternalOrderStatus.REALIZED);
		externalOrderRepository.save(externalOrder);
	}

	@Override
	public void cancel(int externalOrderId) {
		ExternalOrder externalOrder = externalOrderRepository.findOne(externalOrderId);

		externalOrder.setStatus(ExternalOrderStatus.CANCELED);
		externalOrderRepository.save(externalOrder);
	}

}
