package co.edu.icesi.backrestcontroller;

import java.sql.Timestamp;
import java.util.List;

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

import co.edu.icesi.dao.ProductDAO;
import co.edu.icesi.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

	@Autowired
	private ProductDAO productDAO;

	@GetMapping
	public Iterable<Product> getProducts() {
		return productDAO.findAll();
	}

	@GetMapping("/{productid}")
	public Product productFindById(@PathVariable("productid") Integer productid) {
		return productDAO.findById(productid);
	}

	@PostMapping
	public void saveProduct(@RequestBody Product product) {

		productDAO.save(product);

	}

	@PutMapping
	public void updateProduct(@RequestBody Product product) {
		productDAO.update(product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productDAO.delete(productDAO.findById(id));
	}

	@GetMapping("/search/findBySellstartdate")
	public List<Product> findAllBySellstartdate(@RequestParam("sellstartdate") Timestamp sellstartdate) {
		return productDAO.findBySellstartdate(sellstartdate);
	}

	@GetMapping("/search/findBySellenddate")
	public List<Product> findAllBySellenddate(@RequestParam("sellenddate") Timestamp sellenddate) {
		return productDAO.findBySellenddate(sellenddate);
	}

}
