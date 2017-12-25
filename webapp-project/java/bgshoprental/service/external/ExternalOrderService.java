package bgshoprental.service.external;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;

public interface ExternalOrderService {
	ExternalOrder getById(int externalOrderId);
	
	Iterable<ExternalOrder> getAll();
	
	Iterable<ExternalOrderElement> getElements(int externalOrderId); 
	
	void removeExternalOrderElement(int externalOrderId, int elementId);
	
	void addElement(int externalOrderId, ExternalOrderElement element);
	
	boolean add(ExternalOrder externalOrder);
	void realise(int externalOrderId);
	void cancel(int externalOrderId);
}
