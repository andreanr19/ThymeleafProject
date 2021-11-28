package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Document;

@Repository
@Scope("singleton")
public class DocumentDAO implements IDocumentDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Document save(Document document) {

		entityManager.persist(document);
		return document;
	}

	@Override
	public Document update(Document document) {
		entityManager.merge(document);
		return document;
	}

	@Override
	@Transactional
	public void delete(Document document) {
		entityManager.remove(document);
	}

	@Override
	public List<Document> findAll() {
		String jpql = "Select d from Document d";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Document findById(long id) {
		String jpql = "select d from Document d where d.documentnode=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Document document = null;
		try {
			document = (Document) query.getSingleResult();
		} catch (NoResultException e) {

		}
		return document;
	}

}
