package bgshoprental.service.internal.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrder;
import bgshoprental.entity.InternalOrderElement;
import bgshoprental.entity.InternalOrderStatus;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.InternalOrderRepository;
import bgshoprental.service.BoardGamesService;
import bgshoprental.service.exception.NotEnoughItemsAvailableException;
import bgshoprental.service.internal.InternalOrderService;

@Service
public class InternalOrderServiceImpl implements InternalOrderService {

	@Autowired
	InternalOrderRepository internalOrderRepository;
	
	@Autowired
	BoardGamesService boardGamesService;
	
	// dbg
	@Autowired
	EntityManager entityManager;
	
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

	@Override
	@Transactional
	public InternalOrder createAndSaveInternalOrder(List<InternalOrderElement> elements, Client client) {
		
		InternalOrder internalOrder = createEmptyInternalOrder(client);
		
		for(InternalOrderElement element : elements) {
			
			BoardGame boardGame = boardGamesService.findBoardGameById(element.getBoardGame().getId());
			
			if(boardGame.getSellQuantity() < element.getQuantity()) {
				throw new NotEnoughItemsAvailableException();
			}
			
			boardGame.setSellQuantity(boardGame.getSellQuantity() - element.getQuantity());
			
			element.setInternalOrder(internalOrder);
			internalOrder.getElements().add(element);
		}
		
		return internalOrder;
	}

	@Transactional
	private InternalOrder createEmptyInternalOrder(Client client) {
		InternalOrder internalOrder = new InternalOrder();
		internalOrder.setOrderedBy(client);
		internalOrder.setCreationDate(Calendar.getInstance());
		internalOrder.setStatus(InternalOrderStatus.CREATED);
		
		return internalOrderRepository.save(internalOrder);
	}
	
	@Override
	public BigDecimal calculateTotalPrice(InternalOrder order) {
		BigDecimal total = new BigDecimal(0);
		
		for(InternalOrderElement elem : order.getElements()) {
			total = total.add(elem.getBoardGame().getSellPrice().multiply(new BigDecimal(elem.getQuantity())));
		}
		
		return total;
	}

	@Override
	@Transactional
	public void markAsReadyToPickup(Integer internalOrderId) {
		InternalOrder internalOrder = internalOrderRepository.findOne(internalOrderId);
	
		if(internalOrder.getStatus() == InternalOrderStatus.CREATED) {
			internalOrder.setStatus(InternalOrderStatus.READY);
		}
	}

	@Override
	@Transactional
	public void processCancelation(Integer internalOrderId) {
		InternalOrder internalOrder = internalOrderRepository.findOne(internalOrderId);
		
		if(internalOrder.getStatus() != InternalOrderStatus.REALIZED) {
			
			internalOrder.getElements().forEach((elem) -> {
				elem.getBoardGame().setSellQuantity(elem.getBoardGame().getSellQuantity() + elem.getQuantity());
			});
			
			internalOrder.setStatus(InternalOrderStatus.CANCELED);
		}
	}
	
	@Override
	@Transactional
	public void processRealization(Integer internalOrderId) {
		InternalOrder internalOrder = internalOrderRepository.findOne(internalOrderId);
		
		if(internalOrder.getStatus() == InternalOrderStatus.READY) {
			internalOrder.setStatus(InternalOrderStatus.REALIZED);
		}
	}
}
