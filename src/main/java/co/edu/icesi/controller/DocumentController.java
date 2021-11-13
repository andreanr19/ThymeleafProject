package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.services.DocumentService;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductdocumentService;

@Controller
@RequestMapping("/documents")
public class DocumentController {

	private DocumentService documentService;
	private ProductService productService;
	private ProductdocumentService productdocumentService;
	@Autowired
	public DocumentController(DocumentService documentService, ProductService productService, ProductdocumentService productdocumentService) {
		this.documentService = documentService;
		this.productService = productService;
		this.productdocumentService= productdocumentService;
	}
	
	@GetMapping("")
	public String documentIndex(Model model) {
		model.addAttribute("documents",documentService.findAll());
		return "documents/index";
	}
	
	
}
