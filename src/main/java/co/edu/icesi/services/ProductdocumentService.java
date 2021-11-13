package co.edu.icesi.services;

import java.sql.Timestamp;
import java.util.Optional;

import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;

public interface ProductdocumentService {

	public <S extends Productdocument> S save(S productdocument);

	public <S extends Productdocument> Iterable<S> saveAll(Iterable<S> pds);

	public Optional<Productdocument> findById(ProductdocumentPK id);

	public boolean existsById(ProductdocumentPK id);

	public Iterable<Productdocument> findAll();

	public Iterable<Productdocument> findAllById(Iterable<ProductdocumentPK> ids);

	public long count();

	public void deleteById(ProductdocumentPK id);

	public void delete(Productdocument productdocument);

	public void deleteAll(Iterable<? extends Productdocument> pcs);

	public void deleteAll();

	public void editProductdocument(ProductdocumentPK id, Timestamp modifieddate);
}
