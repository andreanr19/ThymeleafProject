package co.edu.icesi.businessdelegate;

import java.util.List;

import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.Transactionhistory;
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
	
	public List<Transactionhistory> transactionhistoriesFindAll();
	public Transactionhistory transactionhistoryFindById(Integer id);
	public Transactionhistory saveTransactionhistory(Transactionhistory th, Integer productId);
	public void setTransactionhistory(Transactionhistory th);
	public void deleteTransactionhistory(Integer transactionid);
	public List<Document> documentFindAll();
	public Document documentFindById(long documentnode);
	public Document saveDocument(Document document, long documentnode);
	public void setDocument(Document document);
	public void deleteDocument(long documentnode);
	public List<Productvendor> productVendorFindAll();
	public Productvendor productvendorFindById(Integer id);
	public Productvendor saveProductvendor(Productvendor productvendor, Integer id);
	public void setProductvendor(Productvendor productvendor);
	public void deleteProductvendor(Integer id);
}
