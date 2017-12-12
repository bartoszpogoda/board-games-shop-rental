package bgshoprental.service.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.repository.ExternalOrderRepository;
import bgshoprental.service.external.ExternalOrderService;

@Service
public class ExternalOrderServiceImpl implements ExternalOrderService {

	@Autowired
	private ExternalOrderRepository externalOrderRepository;
	
	@Override
	public ExternalOrder getById(int externalOrderId) {
		return externalOrderRepository.findOne(externalOrderId);
	}

	@Override
	public boolean add(ExternalOrder externalOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancel(ExternalOrder externalOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finalize(ExternalOrder externalOrder) {
		// TODO Auto-generated method stub
		return false;
	}

}
