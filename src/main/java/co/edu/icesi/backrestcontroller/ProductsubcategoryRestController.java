package co.edu.icesi.backrestcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.services.ProductSubcategoryService;

@RestController
@RequestMapping("/api/subcategories/")
public class ProductsubcategoryRestController {

	@Autowired
	private ProductSubcategoryService productSubcategoryService;
	

	@GetMapping
	public Iterable<Productsubcategory> getProductcategories(){
		return productSubcategoryService.findAll();
	}
	
	@PostMapping
	public void saveProductcategory(@RequestBody Productsubcategory productsubcategory) {
		productSubcategoryService.save(productsubcategory);
	}
	
	@PutMapping
	public void updateProduct(@RequestBody Productsubcategory productcategory) {
		productSubcategoryService.editProductsubcategory(productcategory);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Integer id) {
		productSubcategoryService.delete(productSubcategoryService.findById(id).get());
	}
	
}
