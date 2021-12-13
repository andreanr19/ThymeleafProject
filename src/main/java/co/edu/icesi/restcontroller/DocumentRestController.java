package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Document;
import co.edu.icesi.services.DocumentService;

@RestController
@RequestMapping("/")
public class DocumentRestController {

	private DocumentService documentService;

	@Autowired
	public DocumentRestController(DocumentService documentService) {
		this.documentService = documentService;
	}
	
	@GetMapping("/documentsRest/")
	public Iterable<Document> getDocuments(){
		return documentService.findAll();
	}
	
	@GetMapping("/documentsRest/{documentnode}")
	public Document documentFindById(@PathVariable("documentnode") long documentnode) {
		return documentService.findById(documentnode);
	}
	
	@PostMapping("/documentsRest")
	public Document saveDocument(@RequestBody Document document,
			@RequestParam(value ="productid", required= true) Integer productid) {
		
		documentService.saveCorrect(document, productid);
		return document;
	}
	
}
