//package co.edu.icesi.controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import co.edu.icesi.model.Productcategory;
//import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
//
//@Controller
//@RequestMapping("productcategories")
//public class CategoryController {
//
//	
//	ProductCategoryRepositoryInterface productCategoryRepository;
//
//	@Autowired
//	public CategoryController(ProductCategoryRepositoryInterface productCategoryRepository) {
//		this.productCategoryRepository = productCategoryRepository;
//	}
//
//	@GetMapping("{id}")
//	public String getProductcategory(Model model, @PathVariable("id") Integer id) {
//
//		Optional<Productcategory> productcategory = productCategoryRepository.findById(id);
//
//		if (productcategory.isEmpty()) {
//			throw new IllegalArgumentException("Invalid id:" + id);
//		}
//		model.addAttribute("productcategory", productcategory.get());
//
//		return "categories/information";
//
//	}
//}
