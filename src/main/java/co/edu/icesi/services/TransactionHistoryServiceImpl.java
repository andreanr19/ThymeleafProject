package co.edu.icesi.services;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.dao.IProductDAO;
import co.edu.icesi.dao.ITransactionhistoryDAO;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Transactionhistory;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	// private TransactionHistoryRepositoryInterface transactionHistoryRepository;

	// private ProductRepositoryInterface productRepository;

	private IProductDAO productDAO;
	private ITransactionhistoryDAO transactionhistoryDAO;

	@Autowired
	public TransactionHistoryServiceImpl(IProductDAO productDAO, ITransactionhistoryDAO transactionhistoryDAO) {
		this.productDAO = productDAO;
		this.transactionhistoryDAO = transactionhistoryDAO;
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
		// transactionHistoryRepository.save(transactionhistory);
		transactionhistoryDAO.save(transactionhistory);
		return transactionhistory;
	}

	@Transactional
	public void saveCorrect(Transactionhistory transactionhistory, Integer productId) {
		// Optional<Product> product = productRepository.findById(productId);
		Product product = productDAO.findById(productId);
		long time = System.currentTimeMillis();
		Timestamp actualdate = new Timestamp(time);
		transactionhistory.setTransactiondate(actualdate);
		if (product == null) {
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
			transactionhistory.setProduct(product);
			// transactionHistoryRepository.save(transactionhistory);
			transactionhistoryDAO.save(transactionhistory);
		}

	}

	public <S extends Transactionhistory> Iterable<S> saveAll(Iterable<S> ths) {
		for (Transactionhistory th : ths) {
			save(th);
		}
		return ths;
	}

//	public Optional<Transactionhistory> findById(Integer id) {
//		return transactionHistoryRepository.findById(id);
//	}
//	public boolean existsById(Integer id) {
//		return transactionHistoryRepository.existsById(id);
//	}

	public Transactionhistory findById(Integer id) {
		return transactionhistoryDAO.findById(id);
	}

	public Iterable<Transactionhistory> findAll() {
		// return transactionHistoryRepository.findAll();
		return transactionhistoryDAO.findAll();
	}

//	public Iterable<Transactionhistory> findAllById(Iterable<Integer> ids) {
//		return transactionHistoryRepository.findAllById(ids);
//	}

//	public long count() {
//		return transactionHistoryRepository.count();
//	}
//
//	public void deleteById(Integer id) {
//		transactionHistoryRepository.deleteById(id);
//	}

	public void delete(Transactionhistory transactionhistory) {
		// transactionHistoryRepository.delete(transactionhistory);
		transactionhistoryDAO.delete(transactionhistory);
	}

//	public void deleteAll(Iterable<? extends Transactionhistory> ths) {
//		transactionHistoryRepository.deleteAll(ths);
//	}
//
//	public void deleteAll() {
//		transactionHistoryRepository.deleteAll();
//	}

	@Transactional
	public void editCorrect(Transactionhistory transactionhistory, Integer productId) {
		Product product = productDAO.findById(productId);

		long time = System.currentTimeMillis();
		Timestamp modifieddate = new Timestamp(time);
		transactionhistory.setModifieddate(modifieddate);

		Transactionhistory th = transactionhistoryDAO.findById(transactionhistory.getTransactionid());

		if (product == null) {
			throw new RuntimeException();
		}else if(th ==null) {
			throw new RuntimeException();

		} else if (!(transactionhistory.getQuantity() > 0)) {
			throw new IllegalArgumentException("Quantity should be greater than 0");

		} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0) {
			throw new IllegalArgumentException("The cost shouldn't be less than 0");

		} else if (transactionhistory.getActualcost().compareTo(new BigDecimal("0")) == 0) {
			throw new IllegalArgumentException("The cost shouldn't be 0");

		} else {
			Transactionhistory thentity = th;
			thentity.setQuantity(transactionhistory.getQuantity());
			thentity.setActualcost(transactionhistory.getActualcost());
			thentity.setModifieddate(transactionhistory.getModifieddate());
			thentity.setProduct(product);
			transactionhistoryDAO.update(transactionhistory);
		}
	}

}
