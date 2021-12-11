package co.edu.icesi.businessdelegate;

import java.util.List;

import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Unitmeasure;

public interface BusinessDelegate {

	//PRODUCT SUBCATEGORY
	List<Productsubcategory> productsubcategoryFindAll();
	
	//PRODUCT CATEGORY
	List<Productcategory> productcategoryFindAll();
	
	//UNITMEASUREMENT
	List<Unitmeasure> unitmeasureFindAll();
	
	//PRODUCT
	List<Product> productFindAll();
	
	Product productFindById(Integer productId);
	Product saveProduct(Product product, Integer productcategoryId, Integer productsubcategoryId, long unitmeasurecode);
	void setProduct(Product product);
	void deleteProduct(Integer productId);
}
