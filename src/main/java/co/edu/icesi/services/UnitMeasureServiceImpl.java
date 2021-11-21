package co.edu.icesi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;

@Service
public class UnitMeasureServiceImpl implements UnitMeasureService {

	@Autowired
	private UnitmeasureRepositoryInterface unitmeasureRepository;

	public <S extends Unitmeasure> S save(S unitmeasure) {
		return unitmeasureRepository.save(unitmeasure);

	}
	public Unitmeasure findById(long id) {
		return unitmeasureRepository.findById(id).get();
	}
	@Override
	public Iterable<Unitmeasure> findAll() {
		return unitmeasureRepository.findAll();
	}

}
