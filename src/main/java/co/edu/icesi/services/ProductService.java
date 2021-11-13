package co.edu.icesi.services;

import co.edu.icesi.model.Product;

public interface ProductService {

	public void saveCorrect(Product product, Integer prCategoryId, Integer prSCategoryId, Long unitMId);
	
	public void editCorrect(Product product, Integer prCategoryId, Integer prSCategoryId, Long unitMId);
	public Iterable<Product> findAll();
	
	public Product findById(Integer id);
	
	public void delete(Product product);
	}
