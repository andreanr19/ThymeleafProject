package co.edu.icesi.services;

import co.edu.icesi.model.Businessentity;

public interface BusinessEntityService {
	public void save(Businessentity be);
	public Iterable<Businessentity>  findAll();

	public Businessentity findById(Integer id);
	
}
