package co.edu.icesi.testdao;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.Taller1PruebasApplication;
import co.edu.icesi.dao.ICustomQueriesDAO;
import co.edu.icesi.dao.ProductDAO;
import co.edu.icesi.dao.TransactionhistoryDAO;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller1PruebasApplication.class ) 
@Rollback(false)
public class CustomQueriesTest {

	private ProductDAO productDAO;
	
	private ProductDocumentRepositoryInterface productdocumentRepository;
	
	private TransactionhistoryDAO transactionhistoryDAO;
	
	private ICustomQueriesDAO customqueriesDAO;

	@Autowired
	public CustomQueriesTest(ProductDAO productDAO, ProductDocumentRepositoryInterface productdocumentRepository,
			TransactionhistoryDAO transactionhistoryDAO, ICustomQueriesDAO customqueriesDAO) {
		this.productDAO = productDAO;
		this.productdocumentRepository = productdocumentRepository;
		this.transactionhistoryDAO = transactionhistoryDAO;
		this.customqueriesDAO = customqueriesDAO;
	}
	
	
	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findProductByDate() {
		
	}
	
}
