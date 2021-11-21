package co.edu.icesi.services;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Vendor;
import co.edu.icesi.repositories.VendorRepositoryInterface;

@Service
public class VendorServiceImpl implements VendorService {

	private VendorRepositoryInterface vendorrepository;

	public VendorServiceImpl(VendorRepositoryInterface vendorrepository) {
		this.vendorrepository = vendorrepository;
	}
	
	public void save(Vendor vendor) {
		vendorrepository.save(vendor);
	}
	public Iterable<Vendor> findAll(){
		return vendorrepository.findAll();
	}

	@Override
	public Vendor findById(Integer id) {
		return vendorrepository.findById(id).get();
	}
	
}
