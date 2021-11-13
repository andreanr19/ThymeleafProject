package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Document;


public interface DocumentRepositoryInterface extends CrudRepository<Document,Long>{
    
}
