package co.edu.icesi.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;

@Controller
@RequestMapping("/subcategories")
public class SubcategoryController {

	ProductSubcategoryRepositoryInterface productsubcategoryRepository;

	public SubcategoryController(ProductSubcategoryRepositoryInterface productsubcategoryRepository) {

		this.productsubcategoryRepository = productsubcategoryRepository;
	}

	@GetMapping("{id}")
	public String getProductSubcategory(Model model, @PathVariable("id") Integer id) {

		Optional<Productsubcategory> productsubcategory =productsubcategoryRepository.findById(id);
		
		if(productsubcategory.isEmpty()) {
			throw new IllegalArgumentException("Invalid id:" + id);
		}
		model.addAttribute("productsubcategory", productsubcategory.get());
		
		return "subcategories/information";
	
	}
}
