package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.Businessentity;

public interface BusinessEntityRepositoryInterface extends CrudRepository<Businessentity, Integer> {

}
