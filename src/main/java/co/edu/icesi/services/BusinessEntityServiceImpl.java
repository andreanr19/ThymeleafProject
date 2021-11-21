package co.edu.icesi.services;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Businessentity;
import co.edu.icesi.repositories.BusinessEntityRepositoryInterface;

@Service
public class BusinessEntityServiceImpl implements BusinessEntityService {

	private BusinessEntityRepositoryInterface bussinessentityrepository;

	public BusinessEntityServiceImpl(BusinessEntityRepositoryInterface bussinessentityrepository) {
		this.bussinessentityrepository = bussinessentityrepository;
	}
	
	public void save(Businessentity be) {
		bussinessentityrepository.save(be);
	}

	public Iterable<Businessentity> findAll(){
		return bussinessentityrepository.findAll();
	}
	
	public Businessentity findById(Integer id) {
		return bussinessentityrepository.findById(id).get();
	}
	
}
