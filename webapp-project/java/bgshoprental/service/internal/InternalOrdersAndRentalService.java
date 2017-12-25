package bgshoprental.service.internal;

import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrder;
import bgshoprental.entity.Rental;

public interface InternalOrdersAndRentalService {
	Iterable<InternalOrder> findAllInternalOrdersByClient(Client client);
	Iterable<Rental> findAllRentalsByClient(Client client);
	Iterable<InternalOrder> findAllInternalOrders();
}
