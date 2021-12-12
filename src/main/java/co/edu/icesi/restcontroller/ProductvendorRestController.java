package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.services.ProductVendorService;

@RestController
@RequestMapping("/")
public class ProductvendorRestController {

	private ProductVendorService productvendorService;
	
	@Autowired
	public ProductvendorRestController(ProductVendorService productvendorService) {
		this.productvendorService=productvendorService;
	}
	
	@GetMapping("/productvendorsRest")
	public Iterable<Productvendor> getProductvendors(){
		return productvendorService.findAll();
	}
	
	@GetMapping("/productvendorsRest/{productvendorid}")
	public Productvendor productvendorFindById(@PathVariable("productvendorid") Integer productvendorid) {
		return productvendorService.findById(productvendorid).get();
	}
	
	@PostMapping("/productvendorsRest")
	public Productvendor saveProductvendor(@RequestBody Productvendor productvendor,
			@RequestParam(value= "unitmeasurecode",required= true)long unitmeasurecode,
			@RequestParam(value= "productid", required=true)Integer productid,
			@RequestParam (value="vendorid", required=true)Integer vendorid) {
		productvendorService.save(productvendor, unitmeasurecode, productid, vendorid);
		return productvendor;
	}
}
