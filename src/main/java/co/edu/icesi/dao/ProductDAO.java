package co.edu.icesi.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

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

	@Override
	public void delete(Product product) {
		entityManager.remove(product);
	}

	@Override
	public Product findById(Integer productId) {
		return entityManager.find(Product.class, productId);
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
