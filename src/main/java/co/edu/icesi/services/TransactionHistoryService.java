package co.edu.icesi.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import co.edu.icesi.model.Transactionhistory;

public interface TransactionHistoryService {
	
	public void saveCorrect(Transactionhistory transactionhistory, Integer productId);
	public void editTransactionHistory(Integer id, BigDecimal actualcost, Timestamp modifieddate, Integer quantity,
			Integer refereceorderid, Integer referencerorderlineid, Timestamp transactiondate, String trasactiontype);
	
	public Iterable<Transactionhistory> findAll(); 
	public Optional<Transactionhistory> findById(Integer id);
	public void editCorrect(Transactionhistory transactionhistory, Integer productId);
	public void delete(Transactionhistory transactionhistory);
	

}
