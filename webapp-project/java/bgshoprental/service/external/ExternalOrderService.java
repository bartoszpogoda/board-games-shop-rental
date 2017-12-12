package bgshoprental.service.external;

import bgshoprental.entity.ExternalOrder;

public interface ExternalOrderService {
	ExternalOrder getById(int externalOrderId);
	
	boolean add(ExternalOrder externalOrder);
	boolean cancel(ExternalOrder externalOrder);
	boolean finalize(ExternalOrder externalOrder);
}
