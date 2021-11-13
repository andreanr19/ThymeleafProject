//package co.edu.icesi;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoInteractions;
//import static org.mockito.Mockito.when;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.internal.verification.VerificationModeFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import co.edu.icesi.model.Employee;
//import co.edu.icesi.model.Businessentity;
//import co.edu.icesi.model.Person;
//import co.edu.icesi.model.Productvendor;
//import co.edu.icesi.model.ProductvendorPK;
//import co.edu.icesi.model.Document;
//import co.edu.icesi.model.Product;
//import co.edu.icesi.model.Productcategory;
//import co.edu.icesi.model.Productdocument;
//import co.edu.icesi.model.ProductdocumentPK;
//import co.edu.icesi.model.Productsubcategory;
//import co.edu.icesi.model.Transactionhistory;
//import co.edu.icesi.model.Unitmeasure;
//import co.edu.icesi.repositories.DocumentRepositoryInterface;
//import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
//import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
//import co.edu.icesi.repositories.ProductRepositoryInterface;
//import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
//import co.edu.icesi.repositories.ProductVendorRepositoryInterface;
//import co.edu.icesi.repositories.TransactionHistoryRepositoryInterface;
//import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
//import co.edu.icesi.services.DocumentServiceImpl;
//import co.edu.icesi.services.ProductCategoryServiceImpl;
//import co.edu.icesi.services.ProductServiceImpl;
//import co.edu.icesi.services.ProductSubcategoryServiceImpl;
//import co.edu.icesi.services.ProductVendorServiceImpl;
//import co.edu.icesi.services.ProductdocumentServiceImpl;
//import co.edu.icesi.services.TransactionHistoryServiceImpl;
//
//@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//@TestMethodOrder(OrderAnnotation.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = Taller1PruebasApplication.class)
//class Taller1PruebasApplicationTests {
//
//	// Mocks punto A
//	@Mock
//	private ProductRepositoryInterface productRepositoryM;
//	@InjectMocks
//	private ProductServiceImpl productService;
//	@Mock
//	private ProductSubcategoryRepositoryInterface productSubCategoryRepositoryM;
//	@Mock
//	private UnitmeasureRepositoryInterface unitmeasureRepositoryM;
//	@InjectMocks
//	private ProductSubcategoryServiceImpl productSubcategoryService;
//
//	@Mock
//	private ProductCategoryRepositoryInterface productCategoryRepositoryM;
//	@InjectMocks
//	private ProductCategoryServiceImpl productCategoryService;
//
//	// MOCKS PUNTO B
//	@Mock
//	private TransactionHistoryRepositoryInterface transactionHistoryRepositoryM;
//	@InjectMocks
//	private TransactionHistoryServiceImpl transactionHistoryService;
//
//	// MOCKS PUNTO C
//	@Mock
//	private DocumentRepositoryInterface documentRepositoryM;
//	@Mock
//	private ProductDocumentRepositoryInterface productDocumentRepositoryM;
//	@InjectMocks
//	private DocumentServiceImpl documentService;
//	@InjectMocks
//	private ProductdocumentServiceImpl productDocumentService;
//
//	@Mock
//	private ProductVendorRepositoryInterface productvendorRepositoryM;
//	@InjectMocks
//	private ProductVendorServiceImpl productvendorService;
//
//	@Autowired
//	public Taller1PruebasApplicationTests(ProductServiceImpl productService,
//			ProductSubcategoryServiceImpl productSubcategoryService, ProductCategoryServiceImpl productCategoryService,
//			TransactionHistoryServiceImpl transactionHistoryService, DocumentServiceImpl documentService,
//			ProductdocumentServiceImpl productDocumentService, ProductVendorServiceImpl productvendorService) {
//
//		this.productService = productService;
//		this.productSubcategoryService = productSubcategoryService;
//		this.productCategoryService = productCategoryService;
//		this.transactionHistoryService = transactionHistoryService;
//		this.documentService = documentService;
//		this.productDocumentService = productDocumentService;
//		this.productvendorService = productvendorService;
//
//	}
//
//	@BeforeAll
//	public void setUp() {
//		Productcategory pc = new Productcategory();
//		when(productCategoryRepositoryM.findById(01)).thenReturn(Optional.of(pc));
//		Productsubcategory psc = new Productsubcategory();
//		when(productSubCategoryRepositoryM.findById(01)).thenReturn(Optional.of(psc));
//
//	}
//
//	// Punto 5A: Test para el servicio de guardar un producto.
//	@Test
//	@Order(1)
//	public void saveProductTest() {
//		Product p = new Product();
//		when(productRepositoryM.findById(01)).thenReturn(Optional.of(p));
//		Productcategory pc = productCategoryService.findById(01).get();
//		Productsubcategory psc = productSubcategoryService.findById(01).get();
//
//		Integer idPc = pc.getProductcategoryid();
//		Unitmeasure um1 = new Unitmeasure();
//		Unitmeasure um2 = new Unitmeasure();
//		String idUm1 = um1.getUnitmeasurecode();
//		String idUm2 = um2.getUnitmeasurecode();
//
//		// como seteo productsc y productc y unitmeasure al product?
//
//		psc.setProductcategory(pc); // a productsubcategory le seteo productcategory
//		p.setProductsubcategory(psc); // a product le seteo productsubcategory
//		p.setUnitmeasure1(um1); // a product le seteo unitmeasure1
//		p.setUnitmeasure2(um2); // a product le seteo unitmeasure2
//
//		p.setProductnumber("Number of product");
//		p.setDaystomanufacture(10);
//
//		try {
//			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			Date date1 = df.parse("23/09/2020");
//			Date date2 = df.parse("23/10/2020");
//			long time1 = date1.getTime();
//			long time2 = date2.getTime();
//			Timestamp sellstart = new Timestamp(time1);
//			Timestamp sellend = new Timestamp(time2);
//			p.setSellstartdate(sellstart);
//			p.setSellenddate(sellend);
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		productService.save(p); // guardo s product a través del servicio
//
//		Product theProduct = productService.findById(01).get(); // me devuelve a p
//
//		assertFalse(theProduct.getProductnumber().isEmpty());
//		assertTrue(theProduct.getDaystomanufacture() > 0);
//		assertTrue(theProduct.getSellstartdate().before(theProduct.getSellenddate()));
//		assertEquals(theProduct.getProductsubcategory(), psc);
//
//		verify(productRepositoryM).save(p);
//		verify(productRepositoryM).findById(01);
//
//	}
//
//	// Punto 5A: Test para el servicio de editar un producto
//	@Test
//	@Order(2)
//	public void editProduct() {
//
//		// verifico que cuando se edite un producto con un argumento inválido, salte una
//		// excepción
//		try {
//			String productNumber = "";
//			Integer daysToManufacture = 0;
//			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			Date date1;
//			date1 = df.parse("23/09/2020");
//			Date date2 = df.parse("23/10/2020");
//			long time1 = date1.getTime();
//			long time2 = date2.getTime();
//			Timestamp sellstart = new Timestamp(time2);
//			Timestamp sellend = new Timestamp(time1);
//
//			assertThrows(IllegalArgumentException.class,
//					() -> productService.editProduct(01, "", "", daysToManufacture, null, "", null, "", null, "", "",
//							productNumber, null, null, null, sellend, sellstart, "", null, "", null, null, null, null,
//							null));
//		} catch (ParseException e) {
//
//			e.printStackTrace();
//		}
//
//		// Verifico que cuando edite un producto con todos los argumentos válidos, se
//		// edite realmente
//		try {
//			String productNumber = "correct product number";
//			Integer daysToManufacture = 3;
//			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			Date date1;
//			date1 = df.parse("23/09/2020");
//			Date date2 = df.parse("23/10/2021");
//			long time1 = date1.getTime();
//			long time2 = date2.getTime();
//			Timestamp sellstart = new Timestamp(time1);
//			Timestamp sellend = new Timestamp(time2);
//
//			productService.editProduct(01, "", "", daysToManufacture, null, "", null, "", null, "", "", productNumber,
//					null, null, null, sellend, sellstart, "", null, "", null, null, null, null, null);
//
//			Product theProduct = productService.findById(01).get();
//
//			assertEquals(productNumber, theProduct.getProductnumber());
//			assertEquals(daysToManufacture, theProduct.getDaystomanufacture());
//			assertEquals(sellstart, theProduct.getSellstartdate());
//			assertEquals(sellend, theProduct.getSellenddate());
//
//			// el método findById de produdctRepository es llamado 3 veces:
//			// 1. Cuando se llama desde el método test del save
//			// 2. Dentro de este método para obtener al producto
//			// 3. Dentro de este método para editar el producto
//			verify(productRepositoryM, VerificationModeFactory.times(3)).findById(01);
//		} catch (ParseException e) {
//
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test
//	@Order(2)
//	public void editProduct2() {
//
//		// verifico que cuando se edite un producto con un argumento inválido, salte una
//		// excepción
//		try {
//			String productNumber = "";
//			Integer daysToManufacture = 0;
//			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			Date date1;
//			date1 = df.parse("23/09/2020");
//			Date date2 = df.parse("23/10/2020");
//			long time1 = date1.getTime();
//			long time2 = date2.getTime();
//			Timestamp sellstart = new Timestamp(time2);
//			Timestamp sellend = new Timestamp(time1);
//			Product theProduct = productService.findById(01).get();
//			theProduct.setProductnumber(productNumber);
//			theProduct.setDaystomanufacture(daysToManufacture);
//			theProduct.setSellenddate(sellend);
//			theProduct.setSellstartdate(sellstart);
//			theProduct.setProductid(01);
//
//			assertThrows(IllegalArgumentException.class,
//					() -> productService.editProduct2(theProduct));
//		} catch (ParseException e) {
//
//			e.printStackTrace();
//		}
//
//		// Verifico que cuando edite un producto con todos los argumentos válidos, se
//		// edite realmente
//		try {
//			String productNumber = "correct product number";
//			Integer daysToManufacture = 3;
//			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			Date date1;
//			date1 = df.parse("23/09/2020");
//			Date date2 = df.parse("23/10/2020");
//			long time1 = date1.getTime();
//			long time2 = date2.getTime();
//			Timestamp sellstart = new Timestamp(time1);
//			Timestamp sellend = new Timestamp(time2);
//			
//			Product theProduct = productService.findById(01).get();
//			theProduct.setProductnumber(productNumber);
//			theProduct.setDaystomanufacture(daysToManufacture);
//			theProduct.setSellenddate(sellend);
//			theProduct.setSellstartdate(sellstart);
//			theProduct.setProductid(01);
//
//			productService.editProduct2(theProduct);
//			
//
//			theProduct = productService.findById(01).get();
//
//			assertEquals(productNumber, theProduct.getProductnumber());
//			assertEquals(daysToManufacture, theProduct.getDaystomanufacture());
//			assertEquals(sellstart, theProduct.getSellstartdate());
//			assertEquals(sellend, theProduct.getSellenddate());
//
//			// el método findById de produdctRepository es llamado 3 veces:
//			// 1. Cuando se llama desde el método test del save
//			// 2. Dentro de este método para obtener al producto
//			// 3. Dentro de este método para editar el producto
//			verify(productRepositoryM, VerificationModeFactory.times(3)).findById(01);
//		} catch (ParseException e) {
//
//			e.printStackTrace();
//		}
//
//	}
//	// PUNTO 5B: Test para el servicio de guardar una historia de transacciones
//
//	@Test
//	@Order(3)
//	public void saveTransactionHistoryTest() {
//
//		Product theProduct = productService.findById(01).get();
//		Transactionhistory th = new Transactionhistory();
//		when(transactionHistoryRepositoryM.findById(01)).thenReturn(Optional.of(th));
//
//		th.setQuantity(12);
//		th.setActualcost(new BigDecimal("20"));
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Date ahora = new Date();
//		df.format(ahora);
//		long time = ahora.getTime();
//		long time2 = System.currentTimeMillis();
//		Timestamp actualdate2 = new Timestamp(time2);
//		Timestamp actualdate = new Timestamp(time);
//		th.setTransactiondate(actualdate);
//
//		// probando con una fecha que no es la actual lanza error
////		DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
////		Date date1;
////		try {
////			date1 = df2.parse("23/09/2020");Date date2 = df2.parse("23/10/2020");
////			long time1 = date1.getTime();
////			long time2 = date2.getTime();
////			Timestamp sellstart = new Timestamp(time1);
////			th.setTransactiondate(sellstart);
////		} catch (ParseException e) {
////			e.printStackTrace();
////		}
//
//		// agregamos la transacción al producto
//		theProduct.addTransactionhistory(th);
//		// verificamos que se haya seteado correctamente la transacción
//		assertEquals(actualdate.compareTo(actualdate2), 0);
//		// guardamos la transacción a través del servicio
//		transactionHistoryService.save(th);
//
//		// Verificamos que se hayan asignado correctamente los objetos a través de los
//		// servicios y mocks
//		Transactionhistory thtest = transactionHistoryService.findById(01).get();
//		assertSame(th, thtest);
//		assertSame(theProduct, thtest.getProduct());
//		assertTrue(theProduct.getTransactionhistories().contains(thtest));
//		assertEquals(actualdate, thtest.getTransactiondate());
//
//		// Verificamos que los mocks de los repositorios cumplan su función
//		verify(transactionHistoryRepositoryM).save(th);
//		verify(transactionHistoryRepositoryM).findById(01);
//
//		// el método findById de produdctRepository es llamado 4 veces:
//		// 1. Cuando se llama desde el método test 1 del saveProductTest
//		// 2. Dentro del método editProductTest para obtener al producto
//		// 3. Dentro del método editProductTest para editar el producto
//		// 4. Dentro de este método para obtener el producto
//		verify(productRepositoryM, VerificationModeFactory.times(4)).findById(01);
//
//	}
//
//	@Test
//	@Order(4)
//	public void editTransactionHistoryTest() {
//
//		assertThrows(IllegalArgumentException.class, () -> transactionHistoryService.editTransactionHistory(01,
//				new BigDecimal("0"), null, 0, 0, 0, null, ""));
//
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Date ahora = new Date();
//		df.format(ahora);
//		long time = ahora.getTime();
//		Timestamp actualdate = new Timestamp(time);
//
//		transactionHistoryService.editTransactionHistory(01, new BigDecimal("2"), actualdate, 100, 100, 100, actualdate,
//				"smthng");
//		Transactionhistory th = transactionHistoryService.findById(01).get();
//
//		assertEquals(actualdate, th.getTransactiondate());
//		verify(transactionHistoryRepositoryM, VerificationModeFactory.times(3)).findById(01);
//
//	}
//
//	@Test
//	@Order(5)
//	public void saveDocumentTest() {
//
//		Document d = new Document();
//		String documentid = "iddocument";
//		ProductdocumentPK id = new ProductdocumentPK();
//		Productdocument pd = new Productdocument();
//		when(productDocumentRepositoryM.findById(id)).thenReturn(Optional.of(pd));
//		when(documentRepositoryM.findById(documentid)).thenReturn(Optional.of(d));
//
//		String fileName = "This is the file name";
//		String fileExtensionTest = "This is a test";
//		d.setFilename(fileName);
//		d.setFileextension(fileExtensionTest);
//
//		pd.setDocument(d);
//		Product theProduct = productService.findById(01).get();
//		theProduct.addProductdocument(pd);
//
//		assertEquals(fileName, d.getFilename());
//
//		documentService.save(d);
//
//		Document documenttest = documentService.findById(documentid).get();
//		assertSame(d, documenttest);
//		assertSame(pd.getDocument(), documenttest);
//		assertTrue(theProduct.getProductdocuments().contains(pd));
//
//		// Verificamos que los mocks de los repositorios cumplan su función
//		verify(documentRepositoryM).save(d);
//		verify(documentRepositoryM).findById(documentid);
//
//		// el método findById de produdctRepository es llamado 4 veces:
//		// 1. Cuando se llama desde el método test 1 del saveProductTest
//		// 2. Dentro del método editProductTest para obtener al producto
//		// 3. Dentro del método editProductTest para editar el producto
//		// 4. Dentro de este método para obtener el producto
//		verify(productRepositoryM, VerificationModeFactory.times(5)).findById(01);
//
//	}
//
//	@Test
//	@Order(6)
//	public void editDocumentTest() {
//
//		assertThrows(IllegalArgumentException.class, () -> documentService.editDocument("iddocument", null, null, null,
//				"hh", "hhh", null, null, null, null, null, null, null));
//
//		String correctFilename = "correct file name";
//		String correctextensionfile = "correct extension file";
//
//		documentService.editDocument("iddocument", null, null, correctextensionfile, correctextensionfile,
//				correctFilename, null, null, null, null, null, null, null);
//
//		Document d = documentService.findById("iddocument").get();
//
//		assertEquals(correctFilename, d.getFilename());
//		verify(documentRepositoryM, VerificationModeFactory.times(3)).findById("iddocument");
//
//	}
//
//	@Test
//	@Order(7)
//	public void saveProductVendorTest() {
//		Businessentity businessentity = new Businessentity();
//
//		businessentity.setBusinessentityid(0);
//		int bussinessEntityId = businessentity.getBusinessentityid();
//		Person person = new Person();
//		person.setBusinessentityid(bussinessEntityId);
//		businessentity.setPerson(person);
//		Employee employee = new Employee();
//		employee.setBusinessentityid(bussinessEntityId);
//		Unitmeasure um = new Unitmeasure();
//		um.setUnitmeasurecode("0");
//		Product theProduct = productService.findById(01).get();
//		um.setProducts1(new ArrayList<Product>());
//		um.addProducts1(theProduct);
//
//		Productvendor pv = new Productvendor();
//		ProductvendorPK pk = new ProductvendorPK();
//		pv.setId(pk);
//		when(productvendorRepositoryM.findById(pk)).thenReturn(Optional.of(pv));
//
//		Integer maxor = 20;
//		Integer minor = 10;
//		BigDecimal standardprice = new BigDecimal("1000");
//		pv.setUnitmeasurecode("0");
//		pk.setBusinessentityid(bussinessEntityId);
//		pv.setMinorderqty(minor);
//		pv.setMaxorderqty(maxor);
//		pv.setStandardprice(standardprice);
//
//		productvendorService.save(pv);
//
//		Productvendor pvtest = productvendorService.findById(pk).get();
//		assertNotNull(pvtest.getUnitmeasurecode());
//		assertSame(pvtest.getId().getBusinessentityid(), businessentity.getBusinessentityid());
//
//		verify(productvendorRepositoryM).save(pv);
//		verify(productvendorRepositoryM).findById(pk);
//		verify(productRepositoryM, VerificationModeFactory.times(6)).findById(01);
//		verify(productvendorRepositoryM, VerificationModeFactory.times(1)).findById(pk);
//
//	}
///*
//	@Test
//	@Order(8)
//	public void editProductvendorTest() {
//		Integer maxor = 10;
//		Integer minor = 20;
//		BigDecimal standardprice = new BigDecimal("0");
//		assertThrows(IllegalArgumentException.class, () -> productvendorService.editProductvendor(null, null, null,
//				null, maxor, minor, null, null, standardprice, null));
//
//		Businessentity businessentity = new Businessentity();
//
//		businessentity.setBusinessentityid(0);
//		int bussinessEntityId = businessentity.getBusinessentityid();
//		Person person = new Person();
//		person.setBusinessentityid(bussinessEntityId);
//		businessentity.setPerson(person);
//		Employee employee = new Employee();
//		employee.setBusinessentityid(bussinessEntityId);
//		Unitmeasure um = new Unitmeasure();
//		um.setUnitmeasurecode("0");
//		Product theProduct = productService.findById(01).get();
//		um.setProducts1(new ArrayList<Product>());
//		um.addProducts1(theProduct);
//
//		Productvendor pv = new Productvendor();
//		ProductvendorPK pk = new ProductvendorPK();
//		pv.setId(pk);
//		when(productvendorRepositoryM.findById(pk)).thenReturn(Optional.of(pv));
//
//		Integer maxorCorrect = 20;
//		Integer minorCorrect = 10;
//		BigDecimal standardpriceCorrect = new BigDecimal("10");
//		
//
//		pv.setUnitmeasurecode("0");
//		pk.setBusinessentityid(bussinessEntityId);
//		pv.setMinorderqty(minorCorrect);
//		pv.setMaxorderqty(maxorCorrect);
//		pv.setStandardprice(standardpriceCorrect);
//
//	
//		productvendorService.editProductvendor(pk, null, null, null, maxor, minor, null, null, standardpriceCorrect,
//				um.getUnitmeasurecode());
//		Productvendor pvtest = productvendorService.findById(pk).get();
//		assertEquals(pvtest.getMaxorderqty(), maxorCorrect);
//	}
//*/
//}
