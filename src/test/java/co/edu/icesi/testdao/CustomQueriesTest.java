package co.edu.icesi.testdao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
import co.edu.icesi.services.ProductCategoryService;
import co.edu.icesi.services.ProductCategoryServiceImpl;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductSubcategoryService;
import co.edu.icesi.services.ProductSubcategoryServiceImpl;
import co.edu.icesi.services.ProductdocumentService;
import co.edu.icesi.services.TransactionHistoryService;
import co.edu.icesi.services.TransactionHistoryServiceImpl;
import co.edu.icesi.services.UnitMeasureService;
import co.edu.icesi.services.UnitMeasureServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller1PruebasApplication.class)
@Rollback(false)
public class CustomQueriesTest {

	private ProductService productService;
	private ProductDAO productDAO;
	private ProductSubcategoryService productsubcategoryService;
	private ProductCategoryService productcategoryService;
	private UnitMeasureService unitmeasureService;

	private ProductdocumentService productdocumentService;

	private TransactionHistoryService transactionhistoryService;
	private TransactionhistoryDAO transactionhistoryDAO;

	private ICustomQueriesDAO customqueriesDAO;

	@Autowired
	public CustomQueriesTest(ProductService productService, ProductDAO productDAO,
			ProductSubcategoryService productsubcategoryService, ProductCategoryService productcategoryService,
			UnitMeasureService unitmeasureService, ProductdocumentService productdocumentService,
			TransactionHistoryService transactionhistoryService, TransactionhistoryDAO transactionhistoryDAO,
			ICustomQueriesDAO customqueriesDAO) {
		this.productService = productService;
		this.productDAO = productDAO;
		this.productsubcategoryService = productsubcategoryService;
		this.productcategoryService = productcategoryService;
		this.unitmeasureService = unitmeasureService;
		this.productdocumentService = productdocumentService;
		this.transactionhistoryService = transactionhistoryService;
		this.transactionhistoryDAO = transactionhistoryDAO;
		this.customqueriesDAO = customqueriesDAO;
	}

	/*
	 * Los productos con sus datos y cantidad de transacciones (para un rango de
	 * fechas dadas considerando la modificación), ordenado por el nombre del
	 * producto. Recibe como parámetro las fechas dadas y muestra todos los
	 * productos que tienen al menos una transacción en el rango.
	 */
	@Test
	@Order(1)
	@Transactional
	public void findProductByDate() {
		// Init product subcategory
		Productsubcategory ps1 = new Productsubcategory();
		ps1.setName("Transport");
		ps1 = productsubcategoryService.save(ps1);

		// Init product category
		Productcategory pc1 = new Productcategory();
		pc1.setName("Transport");
		pc1 = productcategoryService.save(pc1);

		// Init unit measure
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("Kilometers");
		um1 = unitmeasureService.save(um1);

		try {
			// Init product1
			Product product1 = new Product();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date1;
			Date date2;
			date1 = df.parse("23/09/2020");
			date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time1);
			Timestamp sellend = new Timestamp(time2);
			product1.setSellstartdate(sellstart);
			product1.setSellenddate(sellend);
			product1.setClass_("Bus");
			product1.setName("Bus");
			product1.setProductnumber("2");
			product1.setDaystomanufacture(4);
			productService.saveCorrect(product1, pc1.getProductcategoryid(), ps1.getProductsubcategoryid(),
					um1.getUnitmeasurecode());

			// Init product2

			Product product2 = new Product();
			product2.setClass_("Car");
			product2.setName("Automovil");
			product2.setColor("Red");
			product2.setProductnumber("2");
			product2.setDaystomanufacture(4);
			date1 = df.parse("23/09/2020");
			date2 = df.parse("23/10/2020");
			long time1_1 = date1.getTime();
			long time2_2 = date2.getTime();
			Timestamp sellstart_1 = new Timestamp(time1_1);
			Timestamp sellend_1 = new Timestamp(time2_2);
			product2.setSellstartdate(sellstart_1);
			product2.setSellenddate(sellend_1);

			productService.saveCorrect(product2, pc1.getProductcategoryid(), ps1.getProductsubcategoryid(),
					um1.getUnitmeasurecode());

			// Init product3
			Product product3 = new Product();
			product3.setClass_("Car");
			product3.setName("Car");
			product3.setColor("Red");
			product3.setProductnumber("2");
			product3.setDaystomanufacture(4);
			date1 = df.parse("23/09/2020");
			date2 = df.parse("23/12/2020");
			long time1_1_1 = date1.getTime();
			long time2_2_2 = date2.getTime();
			Timestamp sellstart_1_1 = new Timestamp(time1_1_1);
			Timestamp sellend_1_1 = new Timestamp(time2_2_2);
			product3.setSellstartdate(sellstart_1_1);
			product3.setSellenddate(sellend_1_1);

			productService.saveCorrect(product3, pc1.getProductcategoryid(), ps1.getProductsubcategoryid(),
					um1.getUnitmeasurecode());

			// Init transaction history product1
			Transactionhistory th1 = new Transactionhistory();
			th1.setProduct(product1);
			th1.setQuantity(10);
			th1.setActualcost(new BigDecimal("5"));
			th1.setTransactiondate(sellstart);

			transactionhistoryService.saveCorrect(th1, product1.getProductid());

			// Init transaction history product2
			Transactionhistory th2 = new Transactionhistory();
			th2.setProduct(product1);
			th2.setQuantity(10);
			th2.setActualcost(new BigDecimal("5"));
			th2.setTransactiondate(sellstart_1);

			transactionhistoryService.saveCorrect(th2, product2.getProductid());

			// Init transaction history product3
			Transactionhistory th3 = new Transactionhistory();
			th3.setProduct(product1);
			th3.setQuantity(10);
			th3.setActualcost(new BigDecimal("5"));
			th3.setTransactiondate(sellend_1_1);

			transactionhistoryService.saveCorrect(th3, product3.getProductid());

			Date initialDate = df.parse("23/08/2020");
			Date finalDate = df.parse("23/11/2020");
			long timeInitial = initialDate.getTime();
			long timeFinal = finalDate.getTime();
			Timestamp sellstartTest = new Timestamp(timeInitial);
			Timestamp sellendTest = new Timestamp(timeFinal);
			List<Product> productList = customqueriesDAO.findProductByDate2(sellstartTest, sellendTest);

			//para verificar que  la lista no esté nula
			assertNotNull(productList);
			// como el producto 3 no cumple con las condiciones entonces no debería estar en la lista
			assertFalse(productList.contains(product3));
			
			assertTrue(productList.contains(product2));
			assertTrue(productList.contains(product1));


		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
