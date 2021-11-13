package co.edu.icesi.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;

@Service
public class ProductSubcategoryServiceImpl implements ProductSubcategoryService{

	private ProductSubcategoryRepositoryInterface productSubcategoryRepository;

	public ProductSubcategoryServiceImpl(ProductSubcategoryRepositoryInterface productSubcategoryRepository) {
		this.productSubcategoryRepository = productSubcategoryRepository;
	}
	
	public <S extends Productsubcategory> S save(S productsubcategory) {
		productSubcategoryRepository.save(productsubcategory);
		return productsubcategory;
	}
	
	public <S extends Productsubcategory> Iterable<S> saveAll(Iterable<S> pscs) {
		for(Productsubcategory psc : pscs) {
			save(psc);
		}
		return pscs;
	}

	
	public Optional<Productsubcategory> findById(Integer id) {
		return productSubcategoryRepository.findById(id);
	}

	
	public boolean existsById(Integer id) {
		return productSubcategoryRepository.existsById(id);
	}

	
	public Iterable<Productsubcategory> findAll() {
		return productSubcategoryRepository.findAll();
	}

	
	public Iterable<Productsubcategory> findAllById(Iterable<Integer> ids) {
		return productSubcategoryRepository.findAllById(ids);
	}

	
	public long count() {
		return productSubcategoryRepository.count();
	}

	
	public void deleteById(Integer id) {
		productSubcategoryRepository.deleteById(id);
	}

	
	public void delete(Productsubcategory productsubCategory) {
		productSubcategoryRepository.delete(productsubCategory);
	}

	
	public void deleteAll(Iterable<? extends Productsubcategory> pscs) {
		productSubcategoryRepository.deleteAll(pscs);
	}

	
	public void deleteAll() {
		productSubcategoryRepository.deleteAll();
	}
	
	public void editProductsubcategory(Integer id, Timestamp modifieddate,  String name, Integer rowguid) {
		if(name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Productsubcategory psc = findById(id).get();
		psc.setName(name);
		psc.setRowguid(rowguid);
		save(psc);
	}
	
	
}
