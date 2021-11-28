package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.Add;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
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
	public DocumentController(DocumentService documentService, ProductService productService,
			ProductdocumentService productdocumentService) {
		this.documentService = documentService;
		this.productService = productService;
		this.productdocumentService = productdocumentService;
	}

	@GetMapping("")
	public String documentIndex(Model model) {
		model.addAttribute("documents", documentService.findAll());
		
		return "documents/index";
	}

	@GetMapping("/edit/{id}")
	public String editDocument(Model model, @PathVariable("id") long id) {
		model.addAttribute("doc", documentService.findById(id));
		model.addAttribute("products", productService.findAll());
		return "documents/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditDocument(@ModelAttribute("doc") @Validated(Add.class) Document doc, BindingResult result,
			Model model, @PathVariable("id") long id, @RequestParam(value = "action", required = true) String action) {

		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("products", productService.findAll());
				return "documents/edit";
			}

			doc.setProduct(doc.getProduct());
			documentService.editCorrect(doc, doc.getProduct().getProductid());
		}
		return "redirect:/documents";
	}
	@GetMapping("/add")
	public String addDocument(Model model) {
		model.addAttribute("doc", new Document());
		model.addAttribute("products", productService.findAll());

		return "/documents/add";
	}

	@PostMapping("/add")
	public String addDocumentPost(@ModelAttribute("doc") @Validated(Add.class)Document doc,
			BindingResult result, @RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("products", productService.findAll());
				return "/documents/add";
			}
			doc.setProduct(doc.getProduct());
			documentService.saveCorrect(doc, doc.getProduct().getProductid());
		}
		return "redirect:/documents";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Document document = documentService.findById(id);
		documentService.delete(document);
		model.addAttribute("documents", documentService.findAll());
		return "documents/index";
	}

	@GetMapping("/{id}")
	public String getProduct(Model model, @PathVariable("id") long id) {
		Document document = documentService.findById(id);

		model.addAttribute("document", document);

		return "documents/information";
	}

}
