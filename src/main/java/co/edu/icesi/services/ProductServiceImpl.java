package co.edu.icesi.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dao.IProductDAO;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
//import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;

@Service
public class ProductServiceImpl implements ProductService {

//	private ProductRepositoryInterface productRepository;
	private IProductDAO productDAO;

	// categoria
	private ProductCategoryRepositoryInterface productCategoryRepository;
	// subcategoria
	private ProductSubcategoryRepositoryInterface productSubCategoryRepository;
	// unidades de medida
	private UnitmeasureRepositoryInterface unitMeasureRepository;

	@Autowired
	public ProductServiceImpl(IProductDAO productDAO, ProductCategoryRepositoryInterface productCategoryRepository,
			ProductSubcategoryRepositoryInterface productSubCategoryRepository,
			UnitmeasureRepositoryInterface unitMeasureRepository) {
//		this.productRepository = productRepository;
		this.productDAO = productDAO;
		this.productCategoryRepository = productCategoryRepository;
		this.productSubCategoryRepository = productSubCategoryRepository;
		this.unitMeasureRepository = unitMeasureRepository;
	}

	@Transactional
	@Override
	public void saveCorrect(Product product, Integer prCategoryId, Integer prSCategoryId, Long unitMId) {
		Optional<Productcategory> productcategory = productCategoryRepository.findById(prCategoryId);
		Optional<Productsubcategory> productsubcategory = productSubCategoryRepository.findById(prSCategoryId);
		Optional<Unitmeasure> unitmeasure = unitMeasureRepository.findById(unitMId);

		if (product == null) {
			throw new RuntimeException("Product is null");
		} else if (productcategory.isEmpty()) {
			throw new RuntimeException("Product category is null");
		} else if (product.getProductnumber().isEmpty()) {
			throw new IllegalArgumentException("The product number argument shouldn't be empty");
		} else if (!product.getSellstartdate().before(product.getSellenddate())) {
			throw new IllegalArgumentException("Product sell end date should be greater than product sell start date");
		} else if (!(product.getDaystomanufacture() > 0)) {
			throw new IllegalArgumentException("Product's days to manufacture should be greater than 0");
		} else if (productsubcategory.isEmpty()) {
			throw new RuntimeException("Product subcategory does not exist");
		} else if (unitmeasure.isEmpty()) {
			throw new RuntimeException("Unit measure doesn't exist");
		} else {
			productsubcategory.get().setProductcategory(productcategory.get());
			product.setProductsubcategory(productsubcategory.get());

			product.setUnitmeasure1(unitmeasure.get());
//			productRepository.save(product);
			productDAO.save(product);
		}
	}

//	public <S extends Product> Iterable<S> saveAll(Iterable<S> ps) {
//		for (Product p : ps) {
//			save(p);
//		}
//		return ps;
//	}

	public Product findById(Integer id) {
//		return productRepository.findById(id).get();
		return productDAO.findById(id);
	}

//	public boolean existsById(Integer id) {
//		return productRepository.existsById(id);
//		
//	}

	public Iterable<Product> findAll() {
//		return productRepository.findAll();
		return productDAO.findAll();
	}

//	public Iterable<Product> findAllById(Iterable<Integer> ids) {
//		return productRepository.findAllById(ids);
//	}

//	public long count() {
//		return productRepository.count();
//	}

//	public void deleteById(Integer id) {
//		productRepository.deleteById(id);
//	}

	public void delete(Product product) {
//		productRepository.delete(product);
		productDAO.delete(product);

	}

//	public void deleteAll(Iterable<? extends Product> ps) {
//		productRepository.deleteAll(ps);
//	}
//
//	public void deleteAll() {
//		productRepository.deleteAll();
//	}

	@Transactional
	public void editCorrect(Product product, Integer prCategoryId, Integer prSCategoryId, Long unitMId) {
		Optional<Productcategory> productcategory = productCategoryRepository.findById(prCategoryId);
		Optional<Productsubcategory> productsubcategory = productSubCategoryRepository.findById(prSCategoryId);
		Optional<Unitmeasure> unitmeasure = unitMeasureRepository.findById(unitMId);

		if (product == null) {
			throw new RuntimeException();
		} else {
//			Optional<Product> p = productRepository.findById(product.getProductid());
			if (productcategory.isEmpty()) {
				throw new RuntimeException("Product category is empty");

			} else if (product.getProductnumber().isEmpty()) {
				throw new IllegalArgumentException("The product number argument shouldn't be empty");
			} else if (!product.getSellstartdate().before(product.getSellenddate())) {
				throw new IllegalArgumentException(
						"Product sell end date should be greater than product sell start date");
			} else if (!(product.getDaystomanufacture() > 0)) {
				throw new IllegalArgumentException("Product's days to manufacture should be greater than 0");
			} else if (productsubcategory.isEmpty()) {
				throw new RuntimeException("Product subcategory is empty");
			} else if (unitmeasure.isEmpty()) {
				throw new RuntimeException("Unit measure is empty");
			} else {
//				Product productEntity = p.get();
				Product productEntity = productDAO.findById(product.getProductid());

				productEntity.setProductsubcategory(productsubcategory.get());
				productEntity.getProductsubcategory().setProductcategory(productcategory.get());
				productEntity.setUnitmeasure1(unitmeasure.get());
				productEntity.setName(product.getName());
				productEntity.setProductnumber(product.getProductnumber());
				productEntity.setDaystomanufacture(product.getDaystomanufacture());
				productEntity.setSellstartdate(product.getSellstartdate());
				productEntity.setSellenddate(product.getSellenddate());
//				productRepository.save(productEntity);
				productDAO.update(productEntity);

			}
		}
	}

	public List<Product> findByProductSellstardate(Timestamp sellstartdate) {
		return productDAO.findBySellstartdate(sellstartdate);
	}

	public List<Product> findByProductSellenddate(Timestamp sellenddate) {
		return productDAO.findBySellstartdate(sellenddate);
	}
}
