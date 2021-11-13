package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;

public interface ProductDocumentRepositoryInterface extends CrudRepository<Productdocument, ProductdocumentPK> {

}
