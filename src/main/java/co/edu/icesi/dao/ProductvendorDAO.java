package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Productvendor;

@Repository
@Scope("singleton")
public class ProductvendorDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Productvendor save(Productvendor pv) {
		entityManager.persist(pv);
		return pv;
	}

	public Productvendor update(Productvendor pv) {
		entityManager.merge(pv);
		return pv;
	}

	@Transactional
	public void delete(Productvendor pv) {
		entityManager.remove(pv);
	}

	public Productvendor findById(Integer id) {

		String jpql = "Select pv from Productvendor pv where pv.id=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Productvendor pv = null;
		try {
			pv = (Productvendor) query.getSingleResult();
		} catch (NoResultException e) {

		}
		return pv;
	}

	public List<Productvendor> findAll() {
		String query = "Select pv from Productvendor pv";
		return entityManager.createQuery(query).getResultList();
	}
}
