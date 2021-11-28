package co.edu.icesi.dao;

import java.util.List;

import co.edu.icesi.model.Document;

public interface IDocumentDAO {
	
	 public Document save(Document document);
	 public Document update(Document document);
	 public void delete(Document document);
	 public List<Document> findAll();
	 public Document findById(long id);

}
