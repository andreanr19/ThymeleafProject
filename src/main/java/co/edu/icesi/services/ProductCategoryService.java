package co.edu.icesi.services;

import java.util.Optional;

import co.edu.icesi.model.Productcategory;

public interface ProductCategoryService {

	public <S extends Productcategory> S save(S productcategory);

	public <S extends Productcategory> Iterable<S> saveAll(Iterable<S> pcs);

	public Optional<Productcategory> findById(Integer id);

	public boolean existsById(Integer id);

	public Iterable<Productcategory> findAll();

	public Iterable<Productcategory> findAllById(Iterable<Integer> ids);

	public long count();

	public void deleteById(Integer id);

	public void delete(Productcategory productCategory);

	public void deleteAll(Iterable<? extends Productcategory> pcs);

	public void deleteAll();

	public void editProductcategory(Integer id, String name, Integer rowguid);
}
