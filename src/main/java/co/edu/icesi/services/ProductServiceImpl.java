package co.edu.icesi.services;


import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

	private ProductRepositoryInterface productRepository;

	// categoria
	private ProductCategoryRepositoryInterface productCategoryRepository;
	// subcategoria
	private ProductSubcategoryRepositoryInterface productSubCategoryRepository;
	// unidades de medida
	private UnitmeasureRepositoryInterface unitMeasureRepository;

	public ProductServiceImpl(ProductRepositoryInterface productRepository,
			ProductCategoryRepositoryInterface productCategoryRepository,
			ProductSubcategoryRepositoryInterface productSubCategoryRepository,
			UnitmeasureRepositoryInterface unitMeasureRepository) {
		this.productRepository = productRepository;
		this.productCategoryRepository = productCategoryRepository;
		this.productSubCategoryRepository = productSubCategoryRepository;
		this.unitMeasureRepository = unitMeasureRepository;
	}

	public <S extends Product> S save(S product) {
		if (product.getProductnumber().isEmpty() || !product.getSellstartdate().before(product.getSellenddate())
				|| !(product.getDaystomanufacture() > 0)) {

			if (product.getProductnumber().isEmpty()) {
				throw new IllegalArgumentException("The product number argument shouldn't be empty");
			} else if (!product.getSellstartdate().before(product.getSellenddate())) {
				throw new IllegalArgumentException(
						"Product sell end date should be greater than product sell start date");
			} else {
				throw new IllegalArgumentException("Product's days to manufacture should be greater than 0");
			}

		}
		productRepository.save(product);
		return product;
	}

	public void saveCorrect(Product product, Integer prCategoryId, Integer prSCategoryId, Long unitMId) {
		Optional<Productcategory> productcategory = productCategoryRepository.findById(prCategoryId);
		Optional<Productsubcategory> productsubcategory = productSubCategoryRepository.findById(prSCategoryId);
		Optional<Unitmeasure> unitmeasure = unitMeasureRepository.findById(unitMId);

		if (product == null) {
			throw new RuntimeException();
		} else if (productcategory.isEmpty()) {
			throw new RuntimeException();
		} else if (product.getProductnumber().isEmpty()) {
			throw new IllegalArgumentException("The product number argument shouldn't be empty");
		} else if (!product.getSellstartdate().before(product.getSellenddate())) {
			throw new IllegalArgumentException("Product sell end date should be greater than product sell start date");
		} else if (!(product.getDaystomanufacture() > 0)) {
			throw new IllegalArgumentException("Product's days to manufacture should be greater than 0");
		} else if (productsubcategory.isEmpty()) {
			throw new RuntimeException();
		} else if (unitmeasure.isEmpty()) {
			throw new RuntimeException();
		} else {
			product.setProductsubcategory(productsubcategory.get());
			
			product.getProductsubcategory().setProductcategory(productcategory.get());
			product.setUnitmeasure1(unitmeasure.get());
			productRepository.save(product);
			log.info(product.getProductsubcategory().getProductcategory().getName());
		}
	}

	public <S extends Product> Iterable<S> saveAll(Iterable<S> ps) {
		for (Product p : ps) {
			save(p);
		}
		return ps;
	}

	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}

	public boolean existsById(Integer id) {
		return productRepository.existsById(id);
	}

	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	public Iterable<Product> findAllById(Iterable<Integer> ids) {
		return productRepository.findAllById(ids);
	}

	public long count() {
		return productRepository.count();
	}

	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public void deleteAll(Iterable<? extends Product> ps) {
		productRepository.deleteAll(ps);
	}

	public void deleteAll() {
		productRepository.deleteAll();
	}

//	public void editProduct(Integer id, String class_, String color, Integer daystomanufacture,
//			Timestamp discontinueddate, String finishedgoodsflag, BigDecimal listprice, String makeflag,
//			Timestamp modifieddate, String name, String productline, String productnumber, Integer reorderpoint,
//			Integer rowguid, Integer safetystocklevel, Timestamp sellenddate, Timestamp sellstartdate, String size,
//			BigDecimal standardcost, String style, BigDecimal weight, Productsubcategory psc, Productcategory pc,
//			Unitmeasure um1, Unitmeasure um2) {
//
//		if (productnumber.isEmpty() || !sellstartdate.before(sellenddate) || !(daystomanufacture > 0)) {
//			throw new IllegalArgumentException("One argument is invalid");
//
//		}
//		Product p = findById(id).get();
//		p.setClass_(class_);
//		p.setColor(color);
//		p.setDaystomanufacture(daystomanufacture);
//		p.setDiscontinueddate(discontinueddate);
//		p.setFinishedgoodsflag(finishedgoodsflag);
//		p.setListprice(listprice);
//		p.setMakeflag(makeflag);
//		p.setModifieddate(modifieddate);
//		p.setName(name);
//		p.setProductline(productline);
//		p.setProductnumber(productnumber);
//		p.setReorderpoint(reorderpoint);
//		p.setRowguid(rowguid);
//		p.setSafetystocklevel(safetystocklevel);
//		p.setSellenddate(sellenddate);
//		p.setSellstartdate(sellstartdate);
//		p.setSize(size);
//		p.setStandardcost(standardcost);
//		p.setStyle(style);
//		p.setWeight(weight);
//		save(p);
//	}
//
//	public void editProduct2(Product product) {
//
//		if (product.getProductnumber().isEmpty() || !product.getSellstartdate().before(product.getSellenddate())
//				|| !(product.getDaystomanufacture() > 0)) {
//			throw new IllegalArgumentException("One argument is invalid");
//
//		}
//		Product productEntity = findById(product.getProductid()).get();
//		productEntity.setClass_(product.getClass_());
//		productEntity.setColor(product.getColor());
//		productEntity.setDaystomanufacture(product.getDaystomanufacture());
//		productEntity.setDiscontinueddate(product.getDiscontinueddate());
//		productEntity.setFinishedgoodsflag(product.getFinishedgoodsflag());
//		productEntity.setListprice(product.getListprice());
//		productEntity.setMakeflag(product.getMakeflag());
//		productEntity.setModifieddate(product.getModifieddate());
//		productEntity.setName(product.getName());
//		productEntity.setProductline(product.getProductline());
//		productEntity.setProductnumber(product.getProductnumber());
//		productEntity.setReorderpoint(product.getReorderpoint());
//		productEntity.setRowguid(product.getRowguid());
//		productEntity.setSafetystocklevel(product.getSafetystocklevel());
//		productEntity.setSellenddate(product.getSellenddate());
//		productEntity.setSellstartdate(product.getSellenddate());
//		productEntity.setSize(product.getSize());
//		productEntity.setStandardcost(product.getStandardcost());
//		productEntity.setStyle(product.getStyle());
//		productEntity.setWeight(product.getWeight());
//		save(productEntity);
//	}

	public void editCorrect(Product product, Integer prCategoryId, Integer prSCategoryId, Long unitMId) {
		Optional<Productcategory> productcategory = productCategoryRepository.findById(prCategoryId);
		Optional<Productsubcategory> productsubcategory = productSubCategoryRepository.findById(prSCategoryId);
		Optional<Unitmeasure> unitmeasure = unitMeasureRepository.findById(unitMId);

		if (product == null) {
			throw new RuntimeException();
		} else {
			Optional<Product> p = productRepository.findById(product.getProductid());

			if (productcategory.isEmpty()) {
				throw new RuntimeException();

			} else if (product.getProductnumber().isEmpty()) {
				throw new IllegalArgumentException("The product number argument shouldn't be empty");
			} else if (!product.getSellstartdate().before(product.getSellenddate())) {
				throw new IllegalArgumentException(
						"Product sell end date should be greater than product sell start date");
			} else if (!(product.getDaystomanufacture() > 0)) {
				throw new IllegalArgumentException("Product's days to manufacture should be greater than 0");
			} else if (productcategory.isEmpty()) {
				throw new RuntimeException();
			} else if (unitmeasure.isEmpty()) {
				throw new RuntimeException();
			} else {
				Product productEntity = p.get();
				productEntity.setProductsubcategory(productsubcategory.get());
				productEntity.getProductsubcategory().setProductcategory(productcategory.get());
				productEntity.setUnitmeasure1(unitmeasure.get());
				productEntity.setProductnumber(product.getProductnumber());
				productEntity.setDaystomanufacture(product.getDaystomanufacture());
				productEntity.setSellstartdate(product.getSellstartdate());
				productEntity.setSellenddate(product.getSellenddate());
				productRepository.save(productEntity);
				log.info(productEntity.getProductsubcategory().getProductcategory().getName());

			}
		}
	}
	



}
