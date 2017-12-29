package bgshoprental.service.internal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrder;
import bgshoprental.entity.InternalOrderElement;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.InternalOrderRepository;
import bgshoprental.service.BoardGamesService;
import bgshoprental.service.internal.InternalOrderService;

@Service
public class InternalOrderServiceImpl implements InternalOrderService {

	@Autowired
	InternalOrderRepository internalOrderRepository;
	
	@Autowired
	BoardGamesService boardGamesService;
	
	@Override
	public Iterable<InternalOrder> findAllInternalOrdersByClient(Client client) {
		return client.getOrders();
	}

	@Override
	public Iterable<InternalOrder> findAllInternalOrders() {
		return internalOrderRepository.findAll();
	}

	@Override
	public InternalOrder findInternalOrderById(Integer internalOrderId) {
		return internalOrderRepository.findOne(internalOrderId);
	}

	@Override
	public List<InternalOrderElement> findElementsForInternalOrderId(Integer internalOrderId) {
		return internalOrderRepository.findOne(internalOrderId).getElements();
	}

	@Override
	public InternalOrder findInternalOrderByIdAndClient(Integer internalOrderId, Client client) {
		InternalOrder internalOrder = internalOrderRepository.findOne(internalOrderId);
		
		if(internalOrder.getOrderedBy().equals(client)) {
			return internalOrder;
		} else {
			return null;
		}
	}

	@Override
	public InternalOrderElement createElement(Integer boardGameId, Integer quantity) {
		InternalOrderElement internalOrderElement = new InternalOrderElement();
		internalOrderElement.setBoardGame(boardGamesService.findBoardGameById(boardGameId));
		internalOrderElement.setQuantity(quantity);
		
		return internalOrderElement;
	}
	
}
