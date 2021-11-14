package co.edu.icesi.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.ProductvendorPK;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.model.Employee;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.ProductVendorRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;

@Service
public class ProductVendorServiceImpl {

	private ProductVendorRepositoryInterface productVendorRepository;
	private UnitmeasureRepositoryInterface unitmeasureRepository;
	private ProductRepositoryInterface productRepository;

	public ProductVendorServiceImpl(ProductVendorRepositoryInterface productVendorRepository,
			UnitmeasureRepositoryInterface unitmeasureRepository, ProductRepositoryInterface productRepository) {
		this.productVendorRepository = productVendorRepository;
		this.unitmeasureRepository = unitmeasureRepository;
		this.productRepository = productRepository;
	}

	public void save(Productvendor productVendor, long unitmeasureid, Integer productid) {
		Optional<Unitmeasure> unitmeasure = unitmeasureRepository.findById(unitmeasureid);
		Optional<Product> product = productRepository.findById(productid);

		if (productVendor == null) {
			throw new RuntimeException();

		} else if (unitmeasure.isEmpty()) {
			throw new RuntimeException();

		} else if (product.isEmpty()) {
			throw new RuntimeException();

		} else if ((productVendor.getMaxorderqty() < productVendor.getMinorderqty())
				|| !(productVendor.getStandardprice().compareTo(new BigDecimal("0")) == 1)
				|| (productVendor.getUnitmeasurecode()>0)) {
			throw new IllegalArgumentException("Theres is one invalid argument");

		}else {
			ProductvendorPK pvPK = new ProductvendorPK();
			pvPK.setProductid(product.get().getProductid());
			productVendor.setUnitmeasurecode(unitmeasure.get().getUnitmeasurecode());
			productVendor.setId(pvPK);
			productVendorRepository.save(productVendor);
	
		}
	}

//	public <S extends Productvendor> Iterable<S> saveAll(Iterable<S> pvs) {
//		for (Productvendor pv : pvs) {
//			save(pv);
//		}
//		return pvs;
//	}

	public Optional<Productvendor> findById(ProductvendorPK id) {
		return productVendorRepository.findById(id);
	}

	public boolean existsById(ProductvendorPK id) {
		return productVendorRepository.existsById(id);
	}

	public Iterable<Productvendor> findAll() {
		return productVendorRepository.findAll();
	}

	public Iterable<Productvendor> findAllById(Iterable<ProductvendorPK> ids) {
		return productVendorRepository.findAllById(ids);
	}

	public long count() {
		return productVendorRepository.count();
	}

	public void deleteById(ProductvendorPK id) {
		productVendorRepository.deleteById(id);
	}

	public void delete(Productvendor productvendor) {
		productVendorRepository.delete(productvendor);
	}

	public void deleteAll(Iterable<? extends Productvendor> pvs) {
		productVendorRepository.deleteAll(pvs);
	}

	public void deleteAll() {
		productVendorRepository.deleteAll();
	}

//	public void editProductvendor(ProductvendorPK id, Integer averageleadtime, BigDecimal lastreceiptcost,
//			Timestamp lastreceiptdate, Integer maxorderqty, Integer minorderqty, Timestamp modifieddate,
//			Integer onorderqty, BigDecimal standardprice, String unitmeasurecode) {
//
//		if ((maxorderqty < minorderqty) || !(standardprice.compareTo(new BigDecimal("0")) == 1)
//				|| unitmeasurecode.equals(null)) {
//			throw new IllegalArgumentException("Theres is one invalid argument");
//
//		}
//		Productvendor pv = findById(id).get();
//		pv.setAverageleadtime(averageleadtime);
//		pv.setLastreceiptcost(lastreceiptcost);
//		pv.setLastreceiptdate(lastreceiptdate);
//		pv.setMaxorderqty(maxorderqty);
//		pv.setMinorderqty(minorderqty);
//		pv.setLastreceiptcost(lastreceiptcost);
//		pv.setOnorderqty(onorderqty);
//		pv.setStandardprice(standardprice);
//		pv.setUnitmeasurecode(unitmeasurecode);
//		save(pv);
//	}

}
