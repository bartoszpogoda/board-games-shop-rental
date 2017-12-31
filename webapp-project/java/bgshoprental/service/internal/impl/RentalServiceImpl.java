package bgshoprental.service.internal.impl;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.entity.Rental;
import bgshoprental.entity.RentalStatus;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.RentalRepository;
import bgshoprental.service.exception.NotEnoughItemsAvailableException;
import bgshoprental.service.internal.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	RentalRepository rentalRepository;
	
	@Autowired
	BoardGamesRepository boardGamesRepository;
	
	@Override
	public Iterable<Rental> findAllRentalsByClient(Client client) {
		return client.getRentals();
	}

	@Override
	@Transactional
	public void createRental(Client client, Integer boardGameId, Integer numberOfDays) {
		BoardGame boardGameToRent = boardGamesRepository.findOne(boardGameId);
		
		if(boardGameToRent.getRentalQuantity() < 1) {
			throw new NotEnoughItemsAvailableException();
		}
		boardGameToRent.setRentalQuantity(boardGameToRent.getRentalQuantity() - 1);
		
		Calendar creationDate = Calendar.getInstance();
		
		Calendar rentUntilDate = ((Calendar)creationDate.clone());
		rentUntilDate.add(Calendar.DAY_OF_MONTH, numberOfDays);
	
		Rental rental = new Rental();
		rental.setBoardGame(boardGameToRent);
		rental.setClient(client);
		rental.setCreationDate(creationDate);
		rental.setPrice(boardGameToRent.getRentalPrice().multiply(new BigDecimal(numberOfDays)));
		rental.setRentUntilDate(rentUntilDate);
		rental.setStatus(RentalStatus.CREATED);
		
		
		rentalRepository.save(rental);
	}
}
