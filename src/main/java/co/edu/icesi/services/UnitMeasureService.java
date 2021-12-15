package co.edu.icesi.services;

import co.edu.icesi.model.Unitmeasure;

public interface UnitMeasureService {
	
	public <S extends Unitmeasure> S save(S unitmeasure);
	public Iterable<Unitmeasure> findAll();
	public Unitmeasure findById(long id);
	public void editUnitmeasure(Unitmeasure um);
	public void delete(Unitmeasure um);

}
