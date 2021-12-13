package co.edu.icesi.backrestcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dao.DocumentDAO;
import co.edu.icesi.model.Document;

@RestController
@RequestMapping("/api/documents")
public class DocumentRestController {

	@Autowired
	private DocumentDAO documentDAO;

	
	@GetMapping
	public Iterable<Document> getDocuments(){
		return documentDAO.findAll();
	}
	
	@GetMapping("/{documentnode}")
	public Document documentFindById(@PathVariable("documentnode") long documentnode) {
		return documentDAO.findById(documentnode);
	}
	
	@PostMapping
	public void saveDocument(@RequestBody Document document) {
		
		documentDAO.save(document);
	}
	
	@PutMapping
	public void updateDocument(@RequestBody Document document) {
		documentDAO.update(document);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {
		documentDAO.delete(documentDAO.findById(id));
	}
	
	
	
}
