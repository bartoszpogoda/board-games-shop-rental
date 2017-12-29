package bgshoprental.service.internal.impl;

import org.springframework.stereotype.Service;

import bgshoprental.entity.Client;
import bgshoprental.entity.Rental;
import bgshoprental.service.internal.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Override
	public Iterable<Rental> findAllRentalsByClient(Client client) {
		return client.getRentals();
	}
}
