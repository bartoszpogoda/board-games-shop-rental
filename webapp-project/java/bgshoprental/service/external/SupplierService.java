package bgshoprental.service.external;

import bgshoprental.entity.Supplier;

public interface SupplierService {
	Iterable<Supplier> getAllSuppliers();
	boolean saveSupplierIfDoesntExist(Supplier supplier);
}
