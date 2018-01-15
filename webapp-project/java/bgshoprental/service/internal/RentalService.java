package bgshoprental.service.internal;

import bgshoprental.entity.Client;
import bgshoprental.entity.Rental;

public interface RentalService {

	Iterable<Rental> findAllRentalsByClient(Client client);

	void createRental(Client client, Integer boardGameId, Integer numberOfDays);

	Iterable<Rental> findAllRentals();
	
	boolean canBeExtended(Rental rental);

	Rental findRentalById(Integer rentalId);

	void cancelRental(Integer rentalId);

	void realizeRental(Integer rentalId);

	void markAsReadyToReceive(Integer rentalId);

	void markAsReceived(Integer rentalId);

	boolean doesBelongToClient(Integer rentalId, Client client);

	int getMaxExtensionDays(Integer rentalId);

	void extendRental(Client client, Integer rentalId, int numberOfDaysToExtend);

	void processBuyRentGame(Client client, Integer rentalId);
}
