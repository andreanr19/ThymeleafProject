package co.edu.icesi.dao;

import java.sql.Timestamp;
import java.util.List;

import co.edu.icesi.model.Product;

public interface IProductDAO {
	
	Product save(Product product);
	Product update(Product product);
	void delete (Product product);
	Product findById(Integer productId);
	List<Product> findAll();
	List<Product> findBySellstartdate(Timestamp sellstartdate);
	List<Product> findBySellenddate(Timestamp sellenddate);


}
