package bgshoprental.service.external;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.service.exception.ExternalOrderNotFoundException;

public interface ExternalOrderService {
	/**
	 * Finds external order for given ID
	 * 
	 * @param externalOrderId
	 * @return found external object or null if ID doesn't stand for one
	 */
	ExternalOrder findExternalOrderById(int externalOrderId);
	
	/**
	 * Finds all existing external orders
	 * 
	 * @return Iterable containing all existing orders found
	 */
	Iterable<ExternalOrder> findAllExternalOrders();
	
	/**
	 * Finds elements of external order
	 * 
	 * @param externalOrderId the ID of external order which elements will be returned
	 * @return Iterable containing found elements
	 */
	Iterable<ExternalOrderElement> findElementsForExternalOrderId(int externalOrderId); 
	
	/**
	 * Adds element to existing external order
	 * 
	 * @param externalOrderId
	 * @param element
	 * 
	 * @throws ExternalOrderNotFoundException when external order wasn't found for given id
	 */
	void addElementToExternalOrder(int externalOrderId, ExternalOrderElement element) throws ExternalOrderNotFoundException;

	void removeExternalOrderElement(int externalOrderId, int elementId);
	
	boolean add(ExternalOrder externalOrder);
	
	void processRealisation(int externalOrderId);
	
	void processCancelation(int externalOrderId);
}
