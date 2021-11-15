package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.services.BusinessEntityService;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductVendorService;
import co.edu.icesi.services.UnitMeasureService;
import co.edu.icesi.services.VendorService;

@Controller
@RequestMapping("product-vendors")
public class ProductVendorController {

	private ProductService productService;
	private UnitMeasureService unitmeasureService;
	private BusinessEntityService businessentityService;
	private VendorService vendorService;
	private ProductVendorService productvendorService;
	@Autowired
	public ProductVendorController(ProductService productService, UnitMeasureService unitmeasureService,
			BusinessEntityService businessentityService, VendorService vendorService,
			ProductVendorService productvendorService) {
		this.productService = productService;
		this.unitmeasureService = unitmeasureService;
		this.businessentityService = businessentityService;
		this.vendorService = vendorService;
		this.productvendorService = productvendorService;
	}
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", productvendorService.findAll());
		return "/product-vendors/index";
	}
	
	
	
}
