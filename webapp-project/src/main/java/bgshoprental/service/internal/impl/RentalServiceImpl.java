package bgshoprental.service.internal.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrderElement;
import bgshoprental.entity.Rental;
import bgshoprental.entity.RentalStatus;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.RentalRepository;
import bgshoprental.service.exception.NotEnoughItemsAvailableException;
import bgshoprental.service.internal.InternalOrderService;
import bgshoprental.service.internal.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	
	@Autowired
	RentalRepository rentalRepository;
	
	@Autowired
	BoardGamesRepository boardGamesRepository;
	
	@Autowired
	InternalOrderService internalOrderService;
	
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

	@Override
	public Iterable<Rental> findAllRentals() {
		return rentalRepository.findAll();
	}
	
	@Override
	public boolean canBeExtended(Rental rental) {
		TimeZone tz = rental.getCreationDate().getTimeZone();
		DateTimeZone jodaTz = DateTimeZone.forID(tz.getID());
		DateTime creationDate = new DateTime(rental.getCreationDate().getTimeInMillis(), jodaTz);
		
		TimeZone tz2 = rental.getRentUntilDate().getTimeZone();
		DateTimeZone jodaTz2 = DateTimeZone.forID(tz2.getID());
		DateTime rentUntilDate = new DateTime(rental.getRentUntilDate().getTimeInMillis(), jodaTz2);
		
		return Days.daysBetween(creationDate, rentUntilDate).getDays() < 28;	// a limit
	}

	@Override
	public Rental findRentalById(Integer rentalId) {
		return rentalRepository.findOne(rentalId);
	}

	@Override
	@Transactional
	public void cancelRental(Integer rentalId) {
		Rental rental = findRentalById(rentalId);
		BoardGame rentBoardGame = rental.getBoardGame();
		
		rentBoardGame.setRentalQuantity(rentBoardGame.getRentalQuantity() + 1);
		rental.setStatus(RentalStatus.CANCELED);
	}

	@Override
	@Transactional
	public void realizeRental(Integer rentalId) {
		Rental rental = findRentalById(rentalId);
		BoardGame rentBoardGame = rental.getBoardGame();
		
		rentBoardGame.setRentalQuantity(rentBoardGame.getRentalQuantity() + 1);
		rental.setStatus(RentalStatus.REALIZED);
	}

	@Override
	@Transactional
	public void markAsReadyToReceive(Integer rentalId) {
		Rental rental = findRentalById(rentalId);
		rental.setStatus(RentalStatus.READY);
	}

	@Override
	@Transactional
	public void markAsReceived(Integer rentalId) {
		Rental rental = findRentalById(rentalId);
		rental.setStatus(RentalStatus.RECEIVED);
	}

	@Override
	public boolean doesBelongToClient(Integer rentalId, Client client) {
		Rental rental = rentalRepository.findOne(rentalId);
		return rental.getClient().equals(client);
	}
	
	@Override
	public int getMaxExtensionDays(Integer rentalId) {
		Rental rental = rentalRepository.findOne(rentalId);
		
		TimeZone tz = rental.getCreationDate().getTimeZone();
		DateTimeZone jodaTz = DateTimeZone.forID(tz.getID());
		DateTime creationDate = new DateTime(rental.getCreationDate().getTimeInMillis(), jodaTz);
		
		TimeZone tz2 = rental.getRentUntilDate().getTimeZone();
		DateTimeZone jodaTz2 = DateTimeZone.forID(tz2.getID());
		DateTime rentUntilDate = new DateTime(rental.getRentUntilDate().getTimeInMillis(), jodaTz2);
		
		return 28 - Days.daysBetween(creationDate, rentUntilDate).getDays();				
	}

	@Override
	@Transactional
	public void extendRental(Client client, Integer rentalId, int numberOfDaysToExtend) {
		if(doesBelongToClient(rentalId, client) && getMaxExtensionDays(rentalId) >= numberOfDaysToExtend) {
			Rental rental = rentalRepository.findOne(rentalId);
			rental.getRentUntilDate().add(Calendar.DAY_OF_MONTH, numberOfDaysToExtend);
			rental.setPrice(rental.getBoardGame().getRentalPrice().multiply(new BigDecimal(numberOfDaysToExtend)).add(rental.getPrice()));
		}
	}
	
	@Override
	@Transactional
	public void processBuyRentGame(Client client, Integer rentalId) {
		
		Rental rental = rentalRepository.findOne(rentalId);
		
		if(doesBelongToClient(rentalId, client) && rental.getBoardGame().getSellQuantity() > 0) {
			
			InternalOrderElement element = internalOrderService.createElement(rental.getBoardGame().getId(), 1);
			internalOrderService.createAndSaveInternalOrder(new ArrayList<InternalOrderElement>(Arrays.asList(element)), client);
			
			rental.setPrice(BigDecimal.ZERO);
		}
	}
}
