package co.edu.icesi.businessdelegate;

import java.util.List;

import co.edu.icesi.frontmodel.*;


public interface BusinessDelegate {

	public void deleteProduct(Product p);

	public Product findProductById(Integer productid);

	public List<Product> findAllProducts();

	public Product saveProduct(Product product);

	public void editProduct(Product product);

	public List<Productsubcategory> findAllProductsubcategories();
	public Productsubcategory findByProductsubcategoryId(Integer id);

	public List<Productcategory> findAllProductcategories();
	public Productcategory findByProductcategoryId(Integer id);
	public Productcategory saveProductcategory(Productcategory category);
	public void deleteProductcategory(Productcategory category);
	public void editCategory(Productcategory category);

	public List<Unitmeasure> findAllUnitmeasures();
	public Unitmeasure findByUnitmeasureId(long id);
	public Transactionhistory transactionhistory_get(Integer transactionid);

	public List<Transactionhistory> transactionhistory_FindAll();

	public Transactionhistory transactionhistory_save(Transactionhistory th);

	public void transactionhistory_delete(Transactionhistory th);

	public Transactionhistory transactionhistory_findById(Integer id);

	public void editTransactionhistory(Transactionhistory th);

	public Document document_get(long id);

	public List<Document> document_findAll();

	public Document document_save(Document doc);

	public void document_delete(Document doc);

	public Document document_findById(long id);

	public void editDocument(Document doc);

	public Productvendor productvendors_get(Integer id);

	public List<Productvendor> productvendor_findAll();

	public Productvendor productvendor_save(Productvendor pv);

	public void productvendor_delete(Productvendor pv);

	public Productvendor productvendor_findById(Integer id);

	public void editProductvendor(Productvendor pv);

}
