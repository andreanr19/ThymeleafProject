package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Product;
import co.edu.icesi.services.ProductService;

@RestController
@RequestMapping("/")
public class ProductRestController {

	private ProductService productService;

	@Autowired
	public ProductRestController(ProductService productService) {

		this.productService = productService;
	}

	@GetMapping("/products/")
	public Iterable<Product> getProducts() {
		return productService.findAll();
	}

	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product product,
			@RequestParam(value = "productcategoryid", required = true) Integer productcategoryid,
			@RequestParam(value = "productsubcategoryid", required = true) Integer productsubcategoryid,
			@RequestParam(value = "unitmeasurecode", required = true) long unitmeasurecode) {

		productService.saveCorrect(product, productcategoryid, productsubcategoryid, unitmeasurecode);
		
		return new Product();
	}

}
