package co.edu.icesi.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Product;

@Repository
@Scope("singleton")
public class ProductDAO implements IProductDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Product save(Product product) {
		entityManager.persist(product);
		return product;
	}

	@Override
	public Product update(Product product) {
		entityManager.merge(product);
		return product;
	}

	@Transactional
	@Override
	public void delete(Product product) {
		entityManager.remove(product);
	}

	@Override
	public Product findById(Integer productId) {
		String jpql = "Select p from Product p where p.productid=:productId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("productId", productId);
		Product p = null;
		try {
			p = (Product) query.getSingleResult();
		} catch (NoResultException e) {

		}
		return p;
	}

	@Override
	public List<Product> findAll() {
		String query = "Select p from Product p";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<Product> findBySellstartdate(Timestamp sellstartdate) {
		String queryjpql = "SELECT p FROM Product p WHERE p.sellstartdate = :sellstartdate";
		Query query = entityManager.createQuery(queryjpql);
		query.setParameter("sellstartdate", sellstartdate);
		return query.getResultList();

	}

	@Override
	public List<Product> findBySellenddate(Timestamp sellenddate) {
		String queryjpql = "SELECT p FROM Product p WHERE p.sellenddate = :sellenddate";
		Query query = entityManager.createQuery(queryjpql);
		query.setParameter("sellstartdate", sellenddate);
		return query.getResultList();
	}

}
