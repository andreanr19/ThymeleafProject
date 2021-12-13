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

import co.edu.icesi.dao.ProductvendorDAO;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.services.ProductVendorService;

@RestController
@RequestMapping("/api/product-vendors")
public class ProductvendorRestController {

	@Autowired
	private ProductvendorDAO productvendorDAO;
	
	@Autowired
	public ProductvendorRestController(ProductvendorDAO productvendorDAO) {
		this.productvendorDAO=productvendorDAO;
	}
	
	@GetMapping
	public Iterable<Productvendor> getProductvendors(){
		return productvendorDAO.findAll();
	}
	@GetMapping("{productvendorid}")
	public Productvendor productvendorFindById(@PathVariable("productvendorid") Integer productvendorid) {
		return productvendorDAO.findById(productvendorid);
	}
	

	
	@PostMapping
	public Productvendor saveProductvendor(@RequestBody Productvendor productvendor) {
		
		return productvendorDAO.save(productvendor);
	}
	
	@PutMapping
	public void updateProductvendor(@RequestBody Productvendor productvendor) {
		productvendorDAO.update(productvendor);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productvendorDAO.delete(productvendorDAO.findById(id));
	}

}
