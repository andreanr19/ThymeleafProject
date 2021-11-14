package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductVendorService;

@Controller
@RequestMapping("/product-vendors")
public class ProductVendorController {

	private ProductVendorService productvendorService;
	private ProductService productService;
	
	@Autowired
	public ProductVendorController(ProductVendorService productvendorService, ProductService productService) {
		this.productvendorService = productvendorService;
		this.productService = productService;
	}
	
	@GetMapping("")
	public String productvendorIndex(Model model) {
		model.addAttribute("productvendors", productService.findAll());
		return "/product-vendors/index";
	}
	
	
}
