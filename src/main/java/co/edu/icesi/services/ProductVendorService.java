package co.edu.icesi.services;

import java.util.Optional;

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.ProductvendorPK;

public interface ProductVendorService {

	public void save(Productvendor productVendor, long unitmeasureid, Integer productid, Integer vendorid);

	public Optional<Productvendor> findById(Integer id);

	public Iterable<Productvendor> findAll();
	public void edit(Productvendor productVendor, long unitmeasureid, Integer productid, Integer vendorid);

}
