package co.edu.icesi.services;


import co.edu.icesi.model.Transactionhistory;

public interface TransactionHistoryService {

	public void saveCorrect(Transactionhistory transactionhistory, Integer productId);

	public Iterable<Transactionhistory> findAll();

	public Transactionhistory findById(Integer id);

	public void editCorrect(Transactionhistory transactionhistory, Integer productId);

	public void delete(Transactionhistory transactionhistory);

}
