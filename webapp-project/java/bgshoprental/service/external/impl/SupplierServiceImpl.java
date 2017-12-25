package bgshoprental.service.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bgshoprental.entity.Supplier;
import bgshoprental.repository.SupplierRepository;
import bgshoprental.service.external.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public Iterable<Supplier> findAllSuppliers() {
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

	@Override
	public Supplier getSupplierByName(String supplierName) {
		return supplierRepository.findOne(supplierName);
	}

}
