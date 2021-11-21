package co.edu.icesi.services;

import co.edu.icesi.model.Vendor;

public interface VendorService {

	public void save(Vendor vendor);
	public Iterable<Vendor> findAll();
	public Vendor findById(Integer id);
}
