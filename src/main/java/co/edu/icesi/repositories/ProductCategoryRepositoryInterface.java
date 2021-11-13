package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.Productcategory;

public interface ProductCategoryRepositoryInterface extends CrudRepository<Productcategory,Integer>{
    
}
