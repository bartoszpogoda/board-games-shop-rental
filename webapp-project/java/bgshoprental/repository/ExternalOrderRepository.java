package bgshoprental.repository;

import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;

public interface ExternalOrderRepository extends CrudRepository<ExternalOrder, Integer>, ExternalOrderRepositoryCustom{


}
