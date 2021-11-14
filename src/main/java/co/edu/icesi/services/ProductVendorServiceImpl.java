package co.edu.icesi.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.ProductvendorPK;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.model.Vendor;
import co.edu.icesi.model.Businessentity;
import co.edu.icesi.model.Employee;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.repositories.BusinessEntityRepositoryInterface;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.ProductVendorRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
import co.edu.icesi.repositories.VendorRepositoryInterface;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductVendorServiceImpl implements ProductVendorService {

	private ProductVendorRepositoryInterface productVendorRepository;
	private UnitmeasureRepositoryInterface unitmeasureRepository;
	private ProductRepositoryInterface productRepository;
	private BusinessEntityRepositoryInterface businessentityrepository;
	private VendorRepositoryInterface vendorrepository;

	public ProductVendorServiceImpl(ProductVendorRepositoryInterface productVendorRepository,
			UnitmeasureRepositoryInterface unitmeasureRepository, ProductRepositoryInterface productRepository,
			BusinessEntityRepositoryInterface businessentityrepository, VendorRepositoryInterface vendorrepository) {
		this.productVendorRepository = productVendorRepository;
		this.unitmeasureRepository = unitmeasureRepository;
		this.productRepository = productRepository;
		this.businessentityrepository = businessentityrepository;
		this.vendorrepository = vendorrepository;
	}

	public void save(Productvendor productVendor, long unitmeasureid, Integer productid, Integer vendorId) {
		Optional<Unitmeasure> unitmeasure = unitmeasureRepository.findById(unitmeasureid);
		Optional<Product> product = productRepository.findById(productid);
		Optional<Businessentity> businessentity = businessentityrepository.findById(vendorId);
		Optional<Vendor> vendor = vendorrepository.findById(vendorId);

		if (productVendor == null) {
			throw new RuntimeException();

		} else if (unitmeasure.isEmpty()) {
			throw new RuntimeException();

		} else if (product.isEmpty()) {
			throw new RuntimeException();

		} else if (vendor.isEmpty()) {
			throw new RuntimeException();

		} else if (businessentity.isEmpty()) {
			throw new RuntimeException();

		} else if ((productVendor.getMaxorderqty() < productVendor.getMinorderqty())
				|| !(productVendor.getStandardprice().compareTo(new BigDecimal("0")) == 1)) {
			throw new IllegalArgumentException("Theres is one invalid argument");

		} else {
			ProductvendorPK pvPK = new ProductvendorPK();
			pvPK.setProductid(product.get().getProductid());
			productVendor.setUnitmeasurecode(unitmeasure.get().getUnitmeasurecode());
			log.info(businessentity.get().getBusinessentityid());
			vendor.get().setBusinessentityid(businessentity.get().getBusinessentityid());
			productVendor.setVendor(vendor.get());
			productVendor.getVendor().setBusinessentityid(vendor.get().getBusinessentityid());
			//productVendor.setId(pvPK);
			//productVendorRepository.save(productVendor);

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
