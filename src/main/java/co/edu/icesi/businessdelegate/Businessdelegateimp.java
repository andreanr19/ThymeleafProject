package co.edu.icesi.businessdelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Document;
import co.edu.icesi.frontmodel.Product;
import co.edu.icesi.frontmodel.Productcategory;
import co.edu.icesi.frontmodel.Productsubcategory;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.frontmodel.Unitmeasure;

@Component
public class Businessdelegateimp implements BusinessDelegate {

	private final static String URL = "http://localhost:8080/api";
	private final static String PRODUCT_URL = URL + "/products/";
	private final static String PRODUCTCATEGORY_URL = URL + "/categories/";
	private final static String TRANSACTIONHISTORY_URL = URL + "/transaction-histories/";
	private final static String DOCUMENT_URL = URL + "/documents/";
	private final static String PRODUCTVENDORS_URL = URL + "/product-vendors/";
	private final static String PRODUCTSUBCATEGORY_URL = URL + "/productsubcategories/";
	private final static String UNITMEASURE_URL = URL + "/unitmeasures/";

	private RestTemplate restTemplate;

	public Businessdelegateimp() {
		this.restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		this.restTemplate.setMessageConverters(messageConverters);
	}

	public void setRestTemplate(RestTemplate resttemplate) {
		this.restTemplate = resttemplate;
	}

	// =================
	// PRODUCT
	// =================
	public void deleteProduct(Product p) {
		restTemplate.delete(PRODUCT_URL + p.getProductid());
	}

	public Product findProductById(Integer productid) {
		return restTemplate.getForObject(PRODUCT_URL + productid, Product.class);
	}

	public List<Product> findAllProducts() {
		Product[] productarray = restTemplate.getForObject(PRODUCT_URL, Product[].class);
		return Arrays.asList(productarray);
	}

	public Product saveProduct(Product product) {
		HttpEntity<Product> request = new HttpEntity<>(product);
		return restTemplate.postForObject(PRODUCT_URL, request, Product.class);
	}

	public void editProduct(Product product) {
		restTemplate.put(PRODUCT_URL, product, Product.class);
	}

	// ==================
	// PRODUCTCATEGORY
	// ==================
	public List<Productcategory> findAllProductcategories() {
		Productcategory[] array = restTemplate.getForObject(PRODUCTCATEGORY_URL, Productcategory[].class);
		return Arrays.asList(array);
	}

	public Productcategory findByProductcategoryId(Integer id) {
		return restTemplate.getForObject(PRODUCTCATEGORY_URL+id, Productcategory.class);
		
	}
	
	public Productcategory saveProductcategory(Productcategory category) {
        HttpEntity<Productcategory> request = new HttpEntity<>(category);
        return restTemplate.postForObject(PRODUCT_URL, request, Productcategory.class);
    }
	public void deleteProductcategory(Productcategory category) {
        restTemplate.delete(PRODUCTCATEGORY_URL+category.getProductcategoryid());
    }
	public void editCategory(Productcategory category) {
		restTemplate.put(PRODUCTCATEGORY_URL, category, Productcategory.class);
	}
	// ==================
	// PRODUCTSUBCATEGORY
	// ==================
	public List<Productsubcategory> findAllProductsubcategories() {
		Productsubcategory[] array = restTemplate.getForObject(PRODUCTSUBCATEGORY_URL, Productsubcategory[].class);
		return Arrays.asList(array);
	}

	// ==================
	// UNITMEASURE
	// ==================
	public List<Unitmeasure> findAllUnitmeasures() {
		Unitmeasure[] array = restTemplate.getForObject(UNITMEASURE_URL, Unitmeasure[].class);
		return Arrays.asList(array);
	}

	// ==================
	// TRANSACTIONHISTORY
	// ==================

	public Transactionhistory transactionhistory_get(Integer transactionid) {

		return restTemplate.getForObject(TRANSACTIONHISTORY_URL + transactionid, Transactionhistory.class);
	}

	public List<Transactionhistory> transactionhistory_FindAll() {
		Transactionhistory[] transactionhistoryarray = restTemplate.getForObject(TRANSACTIONHISTORY_URL,
				Transactionhistory[].class);
		return Arrays.asList(transactionhistoryarray);
	}

	public Transactionhistory transactionhistory_save(Transactionhistory th) {
		HttpEntity<Transactionhistory> request = new HttpEntity<>(th);
		return restTemplate.postForObject(TRANSACTIONHISTORY_URL, request, Transactionhistory.class);
	}

	public void transactionhistory_delete(Transactionhistory th) {
		restTemplate.delete(TRANSACTIONHISTORY_URL + th.getTransactionid());
	}

	public Transactionhistory transactionhistory_findById(Integer id) {
		return restTemplate.getForObject(TRANSACTIONHISTORY_URL + id, Transactionhistory.class);
	}

	public void editTransactionhistory(Transactionhistory th) {
		restTemplate.put(TRANSACTIONHISTORY_URL, th, Transactionhistory.class);
	}

	// ==================
	// DOCUMENTS
	// ==================

	public Document document_get(long id) {
		return restTemplate.getForObject(DOCUMENT_URL + id, Document.class);
	}

	public List<Document> document_findAll() {
		Document[] array = restTemplate.getForObject(DOCUMENT_URL, Document[].class);
		return Arrays.asList(array);
	}

	public Document document_save(Document doc) {
		HttpEntity<Document> request = new HttpEntity<Document>(doc);
		return restTemplate.postForObject(DOCUMENT_URL, request, Document.class);
	}

	public void document_delete(Document doc) {
		restTemplate.delete(DOCUMENT_URL + doc.getDocumentnode());
	}

	public Document document_findById(long id) {
		return restTemplate.getForObject(DOCUMENT_URL + id, Document.class);

	}

	public void editDocument(Document doc) {
		restTemplate.put(DOCUMENT_URL, doc, Document.class);
	}

	// ==================
	// PRODUCT_VENDORS
	// ==================

	public Productvendor productvendors_get(Integer id) {
		return restTemplate.getForObject(PRODUCTVENDORS_URL + id, Productvendor.class);
	}

	public List<Productvendor> productvendor_findAll() {
		Productvendor[] array = restTemplate.getForObject(PRODUCTVENDORS_URL, Productvendor[].class);
		return Arrays.asList(array);
	}

	public Productvendor productvendor_save(Productvendor pv) {
		HttpEntity<Productvendor> request = new HttpEntity<Productvendor>(pv);
		return restTemplate.postForObject(PRODUCTVENDORS_URL, request, Productvendor.class);
	}

	public void productvendor_delete(Productvendor pv) {
		restTemplate.delete(PRODUCTVENDORS_URL + pv.getId());
	}

	public Productvendor productvendor_findById(Integer id) {
		return restTemplate.getForObject(PRODUCTVENDORS_URL + id, Productvendor.class);

	}

	public void editProductvendor(Productvendor pv) {
		restTemplate.put(PRODUCTVENDORS_URL, pv, Productvendor.class);
	}

}
