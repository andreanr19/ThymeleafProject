package co.edu.icesi.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;

@Service
public class ProductdocumentServiceImpl implements ProductdocumentService {

	private ProductDocumentRepositoryInterface productDocumentRepository;

	public ProductdocumentServiceImpl(ProductDocumentRepositoryInterface productDocumentRepository) {
		this.productDocumentRepository = productDocumentRepository;
	}

	public <S extends Productdocument> S save(S productdocument) {
		productDocumentRepository.save(productdocument);
		return productdocument;

	}

	public <S extends Productdocument> Iterable<S> saveAll(Iterable<S> pds) {
		for (Productdocument pd : pds) {
			save(pd);
		}
		return pds;
	}

	public Optional<Productdocument> findById(ProductdocumentPK id) {
		return productDocumentRepository.findById(id);
	}

	public boolean existsById(ProductdocumentPK id) {
		return productDocumentRepository.existsById(id);
	}

	public Iterable<Productdocument> findAll() {
		return productDocumentRepository.findAll();
	}

	public Iterable<Productdocument> findAllById(Iterable<ProductdocumentPK> ids) {
		return productDocumentRepository.findAllById(ids);
	}

	public long count() {
		return productDocumentRepository.count();
	}

	public void deleteById(ProductdocumentPK id) {
		productDocumentRepository.deleteById(id);
	}

	public void delete(Productdocument productdocument) {
		productDocumentRepository.delete(productdocument);
	}

	public void deleteAll(Iterable<? extends Productdocument> pcs) {
		productDocumentRepository.deleteAll(pcs);
	}

	public void deleteAll() {
		productDocumentRepository.deleteAll();
	}

	public void editProductdocument(ProductdocumentPK id, Timestamp modifieddate) {

		Productdocument pd = findById(id).get();
		pd.setModifieddate(modifieddate);
		save(pd);
	}
}
