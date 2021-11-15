package co.edu.icesi.services;

import java.util.Optional;

import co.edu.icesi.model.Productvendor;

public interface ProductVendorService {

	public void save(Productvendor productVendor, long unitmeasureid, Integer productid, Integer vendorid);

	public Optional<Productvendor> findById(Integer id);

	public Iterable<Productvendor> findAll();

	public void edit(Productvendor productVendor, long unitmeasureid, Integer productid, Integer vendorid);

	public void delete(Productvendor productvendor);

}
