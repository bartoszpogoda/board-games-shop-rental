package bgshoprental.repository;

import org.springframework.data.repository.CrudRepository;

import bgshoprental.entity.InternalOrder;

public interface InternalOrderRepository extends CrudRepository<InternalOrder, Integer> {

}
