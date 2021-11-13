package co.edu.icesi.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	private ProductCategoryRepositoryInterface productCategoryRepository;

	public ProductCategoryServiceImpl(ProductCategoryRepositoryInterface productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}
	
	public <S extends Productcategory> S save(S productcategory) {
		productCategoryRepository.save(productcategory);
		return productcategory;
	}
	
	public <S extends Productcategory> Iterable<S> saveAll(Iterable<S> pcs) {
		for(Productcategory pc : pcs) {
			save(pc);
		}
		return pcs;
	}

	
	public Optional<Productcategory> findById(Integer id) {
		return productCategoryRepository.findById(id);
	}

	
	public boolean existsById(Integer id) {
		return productCategoryRepository.existsById(id);
	}

	
	public Iterable<Productcategory> findAll() {
		return productCategoryRepository.findAll();
	}

	
	public Iterable<Productcategory> findAllById(Iterable<Integer> ids) {
		return productCategoryRepository.findAllById(ids);
	}

	
	public long count() {
		return productCategoryRepository.count();
	}

	
	public void deleteById(Integer id) {
		productCategoryRepository.deleteById(id);
	}

	
	public void delete(Productcategory productCategory) {
		productCategoryRepository.delete(productCategory);
	}

	
	public void deleteAll(Iterable<? extends Productcategory> pcs) {
		productCategoryRepository.deleteAll(pcs);
	}

	
	public void deleteAll() {
		productCategoryRepository.deleteAll();
	}
	
	public void editProductcategory(Integer id, String name, Integer rowguid) {
		
		if(name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Productcategory pc = findById(id).get();
		pc.setName(name);
		pc.setRowguid(rowguid);
		save(pc);
	}
	
	
}
