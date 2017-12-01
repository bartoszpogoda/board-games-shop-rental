package bgshoprental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Supplier;
import bgshoprental.repository.SupplierRepository;
import bgshoprental.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public Iterable<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public boolean saveSupplierIfDoesntExist(Supplier supplier) {
		boolean supplierExists = supplierRepository.exists(supplier.getName());
		if(!supplierExists) {
			supplierRepository.save(supplier);
			return true;
		} else {
			return false;
		}
	}

}
