package bgshoprental.repository;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;

public interface ExternalOrderRepositoryCustom {
	void removeElement(ExternalOrder externalOrder, int elementId);

	void addElement(ExternalOrder externalOrder, ExternalOrderElement externalOrderElement);
}
