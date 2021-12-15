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

import co.edu.icesi.model.Productcategory;
import co.edu.icesi.services.ProductCategoryService;

@RestController
@RequestMapping("/api/categories")
public class ProductcategoryRestController {
	
	@Autowired
	private ProductCategoryService productcategoryService;
	
	@GetMapping
	public Iterable<Productcategory> getProductcategories(){
		return productcategoryService.findAll();
	}
	
	@PostMapping
	public void saveProductcategory(@RequestBody Productcategory productcategory) {
		productcategoryService.save(productcategory);
	}
	
	@PutMapping
	public void updateProduct(@RequestBody Productcategory productcategory) {
		productcategoryService.editProductcategory(productcategory);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Integer id) {
		productcategoryService.delete(productcategoryService.findById(id).get());
	}
	
	
	

}


























