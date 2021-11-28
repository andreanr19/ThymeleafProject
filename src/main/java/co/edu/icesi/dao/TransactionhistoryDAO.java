package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Transactionhistory;

@Repository
@Scope("singleton")
public class TransactionhistoryDAO implements ITransactionhistoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Transactionhistory> findByProductId(Integer productid) {
		String jpql = "SELECT t FROM Transactionhistory t WHERE t.product.productid = :productid";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("productid", productid);

		return query.getResultList();
	}

	@Override
	public Transactionhistory findById(Integer id) {
		String jpql = "SELECT t FROM Transactionhistory t where t.transactionid=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Transactionhistory th = null;
		try {
			th = (Transactionhistory) query.getSingleResult();
		} catch (NoResultException e) {

		}
		return th;
	}

	@Override
	public List<Transactionhistory> findByReferenceOrderId(Integer referencerorder) {
		String jpql = "SELECT t FROM Transactionhitory t WHERE t.referenceorderid =: referenceorder";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("referencerorder", referencerorder);
		return query.getResultList();
	}

	@Override
	public Transactionhistory save(Transactionhistory transactionhistory) {
		entityManager.persist(transactionhistory);
		return transactionhistory;
	}

	@Override
	public Transactionhistory update(Transactionhistory transactionhistory) {
		entityManager.merge(transactionhistory);
		return transactionhistory;
	}

	@Override
	@Transactional 
	public void delete(Transactionhistory transactionhistory) {

		entityManager.remove(transactionhistory);
	}

	@Override
	public List<Transactionhistory> findAll() {
		String jpql = "SELECT t FROM Transactionhistory t";
		return entityManager.createQuery(jpql).getResultList();
	}

}
