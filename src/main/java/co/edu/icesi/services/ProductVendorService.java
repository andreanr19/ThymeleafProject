package co.edu.icesi.services;

import java.util.Optional;

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.ProductvendorPK;

public interface ProductVendorService {

	public void save(Productvendor productVendor, long unitmeasureid, Integer productid, Integer vendorid);

	public Optional<Productvendor> findById(ProductvendorPK id);

	public Iterable<Productvendor> findAll();

}
