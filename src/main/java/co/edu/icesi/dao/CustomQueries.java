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
public class CustomQueries implements ICustomQueriesDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * Los productos con sus datos y cantidad de transacciones (para un rango de
	 * fechas dadas considerando la modificación), ordenado por el nombre del
	 * producto. Recibe como parámetro las fechas dadas y muestra todos los
	 * productos que tienen al menos una transacción en el rango.
	 */
	public List<Product> findProductByDate(Timestamp sellstartdate, Timestamp sellenddate) {

		String jpql = "SELECT p FROM Product p WHERE p.sellstartdate >= :sellstartdate AND p.sellenddate <= :sellenddate AND (Select count(t) from Transactionhistory t"
				+ " WHERE t.product.productid = p.productid AND t.transactiondate >= :sellstartdate AND t.transactiondate <= :sellenddate) > 1 ORDER BY p.name ASC";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("sellstartdate", sellstartdate);
		query.setParameter("sellenddate", sellenddate);
		return query.getResultList();
	}

	public List<Product> findProductByDate2(Timestamp sellstartdate, Timestamp sellenddate) {

		String jpql = "SELECT p FROM Product p, Transactionhistory t WHERE t.product.productid = p.productid AND p.sellstartdate >= :sellstartdate AND p.sellenddate <= :sellenddate ORDER BY p.name ASC";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("sellstartdate", sellstartdate);
		query.setParameter("sellenddate", sellenddate);
		return query.getResultList();
	}

	/*
	 * Mostrar el listado de productos que tienen documentos asociados y tienen al
	 * menos dos documentos.
	 */
	public List<Product> findProductByDocument() {

		String jpql = "SELECT p FROM Product p, Productdocument pd WHERE p.productid = pd.product.productid AND (Select count(d) from Document d WHERE d.documentnode = pd.document.documentnode) > 2 ";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

}
