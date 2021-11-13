package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.Product;

public interface ProductRepositoryInterface extends CrudRepository<Product, Integer> {
    
}
