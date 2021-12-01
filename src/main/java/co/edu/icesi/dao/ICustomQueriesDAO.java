package co.edu.icesi.dao;

import java.sql.Timestamp;
import java.util.List;

import co.edu.icesi.model.Product;

public interface ICustomQueriesDAO {
	public List<Product> findProductByDate(Timestamp sellstartdate, Timestamp sellenddate);
	public List<Product> findProductByDocument();
	public List<Product> findProductByDate2(Timestamp sellstartdate, Timestamp sellenddate);

}
