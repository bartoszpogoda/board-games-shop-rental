package bgshoprental.service.external;

import bgshoprental.entity.Supplier;

public interface SupplierService {
	Iterable<Supplier> findAllSuppliers();
	Supplier getSupplierByName(String supplierName);
	boolean saveSupplierIfDoesntExist(Supplier supplier);
}
