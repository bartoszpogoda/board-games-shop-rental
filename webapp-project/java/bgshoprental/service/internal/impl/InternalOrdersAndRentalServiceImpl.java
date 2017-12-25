package bgshoprental.service.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrder;
import bgshoprental.entity.Rental;
import bgshoprental.repository.InternalOrderRepository;
import bgshoprental.service.internal.InternalOrdersAndRentalService;

@Service
public class InternalOrdersAndRentalServiceImpl implements InternalOrdersAndRentalService {

	@Autowired
	InternalOrderRepository internalOrderRepository;
	
	@Override
	public Iterable<InternalOrder> findAllInternalOrdersByClient(Client client) {
		return client.getOrders();
	}

	@Override
	public Iterable<Rental> findAllRentalsByClient(Client client) {
		return client.getRentals();
	}

	@Override
	public Iterable<InternalOrder> findAllInternalOrders() {
		return internalOrderRepository.findAll();
	}

}
