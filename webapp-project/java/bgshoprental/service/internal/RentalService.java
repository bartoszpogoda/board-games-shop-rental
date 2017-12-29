package bgshoprental.service.internal;

import bgshoprental.entity.Client;
import bgshoprental.entity.Rental;

public interface RentalService {

	Iterable<Rental> findAllRentalsByClient(Client client);
}
