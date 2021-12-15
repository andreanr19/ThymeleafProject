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
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.services.ProductCategoryService;
import co.edu.icesi.services.ProductSubcategoryService;
import co.edu.icesi.services.UnitMeasureService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/products")
@Log4j2
public class ProductRestController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductSubcategoryService productsubcategoryService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private UnitMeasureService unitmeasureService;

	@GetMapping
	public Iterable<Product> getProducts() {
		return productDAO.findAll();
	}

	@GetMapping("/{productid}")
	public Product productFindById(@PathVariable("productid") Integer productid) {
		return productDAO.findById(productid);
	}

	@PostMapping("/add")
	public void saveProduct(@RequestBody Product product) {
		product.setProductsubcategory(productsubcategoryService.findById(product.getProductsubcategoryid()).get());
		product.getProductsubcategory().setProductcategory(
				productCategoryService.findById(product.getProductsubcategory().getProductsubcategoryid()).get());
		product.setUnitmeasure1(unitmeasureService.findById(product.getUnitmeasurecode()));
		log.info("DATOS DEL PRODUCTO " + product.toString());

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
