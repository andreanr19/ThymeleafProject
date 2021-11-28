package co.edu.icesi.services;

import java.util.Optional;

import co.edu.icesi.model.Document;
import co.edu.icesi.model.ProductdocumentPK;

public interface DocumentService {

	public void saveCorrect(Document document, Integer productId);
	public Document findById(Long id);
	public Iterable<Document> findAll();
	public void editCorrect(Document document, Integer productId);
	public void delete(Document document);
}
