package co.edu.icesi.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.Transactionhistory;

public interface TransactionHistoryRepositoryInterface extends CrudRepository<Transactionhistory, Integer> {

}
