package bgshoprental.service.internal;

import java.util.List;

import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrder;
import bgshoprental.entity.InternalOrderElement;

public interface InternalOrderService {
	Iterable<InternalOrder> findAllInternalOrdersByClient(Client client);
	Iterable<InternalOrder> findAllInternalOrders();
	
	InternalOrder findInternalOrderById(Integer internalOrderId);
	List<InternalOrderElement> findElementsForInternalOrderId(Integer internalOrderId);
	
	InternalOrder findInternalOrderByIdAndClient(Integer internalOrderId, Client client);
	InternalOrderElement createElement(Integer boardGameId, Integer quantity);
}
