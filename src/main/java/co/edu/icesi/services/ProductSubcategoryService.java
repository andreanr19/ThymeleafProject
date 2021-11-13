package co.edu.icesi.services;

import java.sql.Timestamp;
import java.util.Optional;

import co.edu.icesi.model.Productsubcategory;

public interface ProductSubcategoryService {

	
	
	public <S extends Productsubcategory> S save(S productsubcategory);
	
	
	public <S extends Productsubcategory> Iterable<S> saveAll(Iterable<S> pscs);

	
	public Optional<Productsubcategory> findById(Integer id);

	
	public boolean existsById(Integer id);
	
	public Iterable<Productsubcategory> findAll();

	
	public Iterable<Productsubcategory> findAllById(Iterable<Integer> ids);

	
	public long count();

	
	public void deleteById(Integer id);

	
	public void delete(Productsubcategory productsubCategory);

	
	public void deleteAll(Iterable<? extends Productsubcategory> pscs);

	
	public void deleteAll();
	
	public void editProductsubcategory(Integer id, Timestamp modifieddate,  String name, Integer rowguid);
}
