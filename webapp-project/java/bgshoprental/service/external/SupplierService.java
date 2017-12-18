package bgshoprental.service.external;

import bgshoprental.entity.Supplier;

public interface SupplierService {
	Iterable<Supplier> getAllSuppliers();
	Supplier getSupplierByName(String supplierName);
	boolean saveSupplierIfDoesntExist(Supplier supplier);
}
