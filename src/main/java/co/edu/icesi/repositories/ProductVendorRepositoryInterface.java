package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.ProductvendorPK;

public interface ProductVendorRepositoryInterface extends CrudRepository<Productvendor,Integer> {
    
}
