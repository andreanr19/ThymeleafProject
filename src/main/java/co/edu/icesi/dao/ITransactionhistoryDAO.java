package co.edu.icesi.dao;

import java.util.List;

import co.edu.icesi.model.Transactionhistory;

public interface ITransactionhistoryDAO {

	public List<Transactionhistory> findByProductId(Integer productid);
	public Transactionhistory findById(Integer id);
	
	public List<Transactionhistory> findByReferenceOrderId(Integer referencerorder);
	
	public Transactionhistory save(Transactionhistory transactionhistory);
	public Transactionhistory update(Transactionhistory transactionhistory);
	public void delete(Transactionhistory transactionhistory);
	public List<Transactionhistory> findAll();
	
	
}
