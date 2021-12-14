//package co.edu.icesi.businessdelegate;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import co.edu.icesi.model.Document;
//import co.edu.icesi.model.Product;
//import co.edu.icesi.model.Productcategory;
//import co.edu.icesi.model.Productsubcategory;
//import co.edu.icesi.model.Productvendor;
//import co.edu.icesi.model.Transactionhistory;
//import co.edu.icesi.model.Unitmeasure;
//
//@Component
//public class BusinessDelegateImpl implements BusinessDelegate {
//
//	public static final String REST_URL = "http://localhost:8080";
//	private RestTemplate restTemplate;
//
//	
//	public BusinessDelegateImpl() {
//		this.restTemplate = new RestTemplate();
//	}
//
//	// --------------------
//	// PRODUCTSUBCATEGORY
//	// --------------------
//	@Override
//	public List<Productsubcategory> productsubcategoryFindAll() {
//		String endpoint = REST_URL + "/productsubcategories/";
//
//		Productsubcategory[] ps = restTemplate.getForObject(endpoint, Productsubcategory[].class);
//		List<Productsubcategory> response = Arrays.asList(ps);
//		return response;
//	}
//
//	// --------------------
//	// PRODUCTCATEGORY
//	// --------------------
//
//	@Override
//	public List<Productcategory> productcategoryFindAll() {
//		String endpoint = REST_URL + "/productcategories/";
//
//		Productcategory[] pc = restTemplate.getForObject(endpoint, Productcategory[].class);
//		List<Productcategory> response = Arrays.asList(pc);
//		return response;
//	}
//
//	// -------------------
//	// UNITMEASURE
//	// -------------------
//
//	@Override
//	public List<Unitmeasure> unitmeasureFindAll() {
//		String endpoint = REST_URL + "/unitmeasures/";
//
//		Unitmeasure[] um = restTemplate.getForObject(endpoint, Unitmeasure[].class);
//		List<Unitmeasure> response = Arrays.asList(um);
//		return response;
//	}
//
//	// -------------------
//	// PRODUCT
//	// -------------------
//	@Override
//	public List<Product> productFindAll() {
//		String endpoint = REST_URL + "/products";
//
//		Product[] p = restTemplate.getForObject(endpoint, Product[].class);
//		List<Product> response = Arrays.asList(p);
//		return response;
//	}
//
//	@Override
//	public Product productFindById(Integer productId) {
//
//		String endpoint = REST_URL + "/products/" + productId;
//		Product response = restTemplate.getForObject(endpoint, Product.class);
//
//		return response;
//	}
//
//	@Override
//	public Product saveProduct(Product product, Integer productcategoryId, Integer productsubcategoryId,
//			long unitmeasurecode) {
//
//		String endpoint = REST_URL + "/products/";
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
//				.queryParam("productcategoryId", productcategoryId)
//				.queryParam("productsubcategoryId", productsubcategoryId)
//				.queryParam("unitmeasurecode", unitmeasurecode);
//		endpoint = builder.toUriString();
//
//		HttpEntity<Product> request = new HttpEntity<>(product);
//
//		Product response = restTemplate.postForObject(endpoint, request, Product.class);
//		return response;
//	}
//
//	@Override
//	public void setProduct(Product product) {
//
//		String endpoint = REST_URL + "/products";
//
//		restTemplate.put(endpoint, product, Product.class);
//	}
//
//	@Override
//	public void deleteProduct(Integer productId) {
//
//		String endpoint = REST_URL + "/products/" + productId;
//
//		restTemplate.delete(endpoint);
//	}
//
//	// -------------------
//	// TRANSACTION HISTORY
//	// -------------------
//	// GET
//	public List<Transactionhistory> transactionhistoriesFindAll() {
//
//		String endpoint = REST_URL + "/transaction-history";
//
//		Transactionhistory[] th = restTemplate.getForObject(endpoint, Transactionhistory[].class);
//		List<Transactionhistory> response = Arrays.asList(th);
//
//		return response;
//	}
//
//	// GET
//	public Transactionhistory transactionhistoryFindById(Integer id) {
//		String endpoint = REST_URL + "/transaction-history" + id;
//		Transactionhistory response = restTemplate.getForObject(endpoint, Transactionhistory.class);
//		return response;
//	}
//
//	// POST
//	public Transactionhistory saveTransactionhistory(Transactionhistory th, Integer productId) {
//
//		String endpoint = REST_URL + "/transaction-history";
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint).queryParam("productId", productId);
//		endpoint = builder.toUriString();
//
//		HttpEntity<Transactionhistory> request = new HttpEntity<>(th);
//
//		Transactionhistory response = restTemplate.postForObject(endpoint, request, Transactionhistory.class);
//
//		return response;
//	}
//
//	// PUT
//	public void setTransactionhistory(Transactionhistory th) {
//
//		String endpoint = REST_URL + "/transaction-history";
//
//		restTemplate.put(endpoint, th, Transactionhistory.class);
//	}
//
//	// DELETE
//	public void deleteTransactionhistory(Integer transactionid) {
//
//		String endpoint = REST_URL + "/transaction-history" + transactionid;
//
//		restTemplate.delete(endpoint);
//	}
//
//	// -------------------
//	// DOCUMENT
//	// -------------------
//	// GET
//	public List<Document> documentFindAll() {
//
//		String endpoint = REST_URL + "/documents";
//
//		Document[] d = restTemplate.getForObject(endpoint, Document[].class);
//		List<Document> response = Arrays.asList(d);
//
//		return response;
//	}
//
//	// GET
//	public Document documentFindById(long documentnode) {
//
//		String endpoint = REST_URL + "/documents/" + documentnode;
//
//		Document response = restTemplate.getForObject(endpoint, Document.class);
//
//		return response;
//	}
//
//	// POST
//	public Document saveDocument(Document document, long documentnode) {
//		String endpoint = REST_URL + "/documents";
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint).queryParam("documentnode",
//				documentnode);
//		endpoint = builder.toUriString();
//
//		Document response = restTemplate.postForObject(endpoint, document, Document.class);
//
//		return response;
//	}
//
//	// PUT
//	public void setDocument(Document document) {
//		String endpoint = REST_URL + "/documents";
//
//		restTemplate.put(endpoint, document, Document.class);
//	}
//
//	// DELETE
//	public void deleteDocument(long documentnode) {
//
//		String endpoint = REST_URL + "/documents/" + documentnode;
//
//		restTemplate.delete(endpoint);
//	}
//
//	// -------------------
//	// PRODUCT VENDOR
//	// -------------------
//	// GET
//	public List<Productvendor> productVendorFindAll() {
//		String endpoint = REST_URL + "/product-vendors";
//
//		Productvendor[] pv = restTemplate.getForObject(endpoint, Productvendor[].class);
//		List<Productvendor> response = Arrays.asList(pv);
//
//		return response;
//	}
//
//	// GET
//	public Productvendor productvendorFindById(Integer id) {
//		String endpoint = REST_URL + "/product-vendors/" + id;
//
//		Productvendor response = restTemplate.getForObject(endpoint, Productvendor.class);
//		return response;
//	}
//
//	// POST
//	public Productvendor saveProductvendor(Productvendor productvendor, Integer id) {
//
//		String endpoint = REST_URL + "/product-vendors";
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint).queryParam("id", id);
//		endpoint = builder.toUriString();
//
//		Productvendor response = restTemplate.postForObject(endpoint, productvendor, Productvendor.class);
//
//		return response;
//	}
//
//	// PUT
//	public void setProductvendor(Productvendor productvendor) {
//		String endpoint = REST_URL + "/product-vendors";
//
//		restTemplate.put(endpoint, productvendor, Productvendor.class);
//	}
//
//	// DELETE
//	public void deleteProductvendor(Integer id) {
//		String endpoint = REST_URL + "/product-vendors/" + id;
//
//		restTemplate.delete(endpoint);
//	}
//}
