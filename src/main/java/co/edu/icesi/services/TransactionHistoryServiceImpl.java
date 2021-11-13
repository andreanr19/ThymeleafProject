package co.edu.icesi.services;

import java.math.BigDecimal;
import java.sql.Timestamp;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.model.Product;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.TransactionHistoryRepositoryInterface;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	private TransactionHistoryRepositoryInterface transactionHistoryRepository;

	private ProductRepositoryInterface productRepository;

	public TransactionHistoryServiceImpl(TransactionHistoryRepositoryInterface transactionHistoryRepository,
			ProductRepositoryInterface productRepository) {
		this.transactionHistoryRepository = transactionHistoryRepository;
		this.productRepository = productRepository;
	}

	public <S extends Transactionhistory> S save(S transactionhistory) {

//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Date ahora = new Date();
//		df.format(ahora);
//
//		long time = ahora.getTime();
		long time = System.currentTimeMillis();
		Timestamp actualdate = new Timestamp(time);
		if (!(transactionhistory.getQuantity() > 0)
				|| (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == -1)
				|| (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0)
				|| (Math.abs(actualdate.compareTo(transactionhistory.getTransactiondate())) > 5)) {

			if (!(transactionhistory.getQuantity() > 0)) {
				throw new IllegalArgumentException("Quantity should be greater than 0");
			} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == -1) {
				throw new IllegalArgumentException("The cost shouldn't be less than 0");
			} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0) {
				throw new IllegalArgumentException("The cost shouldn't be 0");

			} else if (!(actualdate.compareTo(transactionhistory.getTransactiondate()) == 0)) {
				System.out.println(actualdate);
				System.out.println(transactionhistory.getTransactiondate());

				throw new IllegalArgumentException("transaction history date should be the actual date ");

			}

		}
		transactionHistoryRepository.save(transactionhistory);
		return transactionhistory;
	}

	public void saveCorrect(Transactionhistory transactionhistory, Integer productId) {
		Optional<Product> product = productRepository.findById(productId);

		long time = System.currentTimeMillis();
		Timestamp actualdate = new Timestamp(time);
		transactionhistory.setTransactiondate(actualdate);
		if (product.isEmpty()) {
			throw new RuntimeException();
		} else if (!(transactionhistory.getQuantity() > 0)) {
			throw new IllegalArgumentException("Quantity should be greater than 0");

		} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0) {
			throw new IllegalArgumentException("The cost shouldn't be less than 0");

		} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0) {
			throw new IllegalArgumentException("The cost shouldn't be 0");

		} else if (!(actualdate.compareTo(transactionhistory.getTransactiondate()) == 0)) {
			System.out.println(actualdate);
			System.out.println(transactionhistory.getTransactiondate());

			throw new IllegalArgumentException("transaction history date should be the actual date ");

		} else {
			transactionhistory.setProduct(product.get());
			transactionHistoryRepository.save(transactionhistory);
		}

	}

	public <S extends Transactionhistory> Iterable<S> saveAll(Iterable<S> ths) {
		for (Transactionhistory th : ths) {
			save(th);
		}
		return ths;
	}

	public Optional<Transactionhistory> findById(Integer id) {
		return transactionHistoryRepository.findById(id);
	}

	public boolean existsById(Integer id) {
		return transactionHistoryRepository.existsById(id);
	}

	public Iterable<Transactionhistory> findAll() {
		return transactionHistoryRepository.findAll();
	}

	public Iterable<Transactionhistory> findAllById(Iterable<Integer> ids) {
		return transactionHistoryRepository.findAllById(ids);
	}

	public long count() {
		return transactionHistoryRepository.count();
	}

	public void deleteById(Integer id) {
		transactionHistoryRepository.deleteById(id);
	}

	public void delete(Transactionhistory transactionhistory) {
		transactionHistoryRepository.delete(transactionhistory);
	}

	public void deleteAll(Iterable<? extends Transactionhistory> ths) {
		transactionHistoryRepository.deleteAll(ths);
	}

	public void deleteAll() {
		transactionHistoryRepository.deleteAll();
	}

	public void editTransactionHistory(Integer id, BigDecimal actualcost, Timestamp modifieddate, Integer quantity,
			Integer refereceorderid, Integer referencerorderlineid, Timestamp transactiondate, String trasactiontype) {

//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Date ahora = new Date();
//		df.format(ahora);
//
//		long time = ahora.getTime();
//		long time = System.currentTimeMillis();
//
//		Timestamp actualdate = new Timestamp(time);
//		System.out.println(actualdate + "," + transactiondate);
//		|| (Math.abs(actualdate.compareTo(transactiondate)) > 5)
		if (!(quantity > 0) || (actualcost.compareTo(new BigDecimal("0")) == -1)
				|| (actualcost.compareTo(new BigDecimal("0")) == 0)) {
//			System.out.println(actualdate);
//			System.out.println(transactiondate);
			throw new IllegalArgumentException("One argument is invalid");

		}
		Transactionhistory th = transactionHistoryRepository.findById(id).get();
		th.setActualcost(actualcost);
		th.setModifieddate(modifieddate);
		th.setQuantity(quantity);
		th.setReferenceorderid(refereceorderid);
		th.setReferenceorderlineid(referencerorderlineid);
		th.setTransactiondate(transactiondate);
		th.setTransactiontype(trasactiontype);
		save(th);
	}

	public void editCorrect(Transactionhistory transactionhistory, Integer productId) {
		Optional<Product> product = productRepository.findById(productId);

		long time = System.currentTimeMillis();
		Timestamp modifieddate = new Timestamp(time);
		transactionhistory.setModifieddate(modifieddate);

		Optional<Transactionhistory> th = transactionHistoryRepository.findById(transactionhistory.getTransactionid());

		if (product.isEmpty()) {
			throw new RuntimeException();
		} else if (!(transactionhistory.getQuantity() > 0)) {
			throw new IllegalArgumentException("Quantity should be greater than 0");

		} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0) {
			throw new IllegalArgumentException("The cost shouldn't be less than 0");

		} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0) {
			throw new IllegalArgumentException("The cost shouldn't be 0");

//			} else if (!(actualdate.compareTo(transactionhistory.getTransactiondate()) == 0)) {
//				System.out.println(actualdate);
//				System.out.println(transactionhistory.getTransactiondate());
//
//				throw new IllegalArgumentException("transaction history date should be the actual date ");

		} else {
			Transactionhistory thentity = th.get();
			thentity.setQuantity(transactionhistory.getQuantity());
			thentity.setActualcost(transactionhistory.getActualcost());
			thentity.setModifieddate(transactionhistory.getModifieddate());
			thentity.setProduct(product.get());
			transactionHistoryRepository.save(thentity);
		}
	}

}
