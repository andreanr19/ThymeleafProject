package co.edu.icesi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.model.Businessentity;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.model.Vendor;
import co.edu.icesi.repositories.BusinessEntityRepositoryInterface;
import co.edu.icesi.repositories.DocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductVendorRepositoryInterface;
import co.edu.icesi.repositories.TransactionHistoryRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
import co.edu.icesi.repositories.VendorRepositoryInterface;
import co.edu.icesi.services.BusinessEntityServiceImpl;
import co.edu.icesi.services.DocumentServiceImpl;
import co.edu.icesi.services.ProductCategoryServiceImpl;
import co.edu.icesi.services.ProductServiceImpl;
import co.edu.icesi.services.ProductSubcategoryServiceImpl;
import co.edu.icesi.services.ProductVendorServiceImpl;
import co.edu.icesi.services.ProductdocumentServiceImpl;
import co.edu.icesi.services.TransactionHistoryServiceImpl;
import co.edu.icesi.services.UnitMeasureServiceImpl;
import co.edu.icesi.services.VendorServiceImpl;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller1PruebasApplication.class)
class Taller1PruebasApplicationTests {

	// Mocks punto A
	@Mock
	private ProductRepositoryInterface productRepositoryM;
	@InjectMocks
	private ProductServiceImpl productService;
	@Mock
	private ProductSubcategoryRepositoryInterface productSubCategoryRepositoryM;

	@InjectMocks
	private ProductSubcategoryServiceImpl productSubcategoryService;
	@Mock
	private UnitmeasureRepositoryInterface unitmeasureRepositoryM;
	@InjectMocks
	private UnitMeasureServiceImpl unitmeasureService;
	@Mock
	private ProductCategoryRepositoryInterface productCategoryRepositoryM;
	@InjectMocks
	private ProductCategoryServiceImpl productCategoryService;

	// MOCKS PUNTO B
	@Mock
	private TransactionHistoryRepositoryInterface transactionHistoryRepositoryM;
	@InjectMocks
	private TransactionHistoryServiceImpl transactionHistoryService;

	// MOCKS PUNTO C
	@Mock
	private DocumentRepositoryInterface documentRepositoryM;
	@Mock
	private ProductDocumentRepositoryInterface productDocumentRepositoryM;
	@InjectMocks
	private DocumentServiceImpl documentService;
	@InjectMocks
	private ProductdocumentServiceImpl productDocumentService;

	@Mock
	private VendorRepositoryInterface vendorRepositoryM;
	@Mock
	private BusinessEntityRepositoryInterface busineesEntityRepositoryM;
	@InjectMocks
	private VendorServiceImpl vendorService;
	@InjectMocks
	private BusinessEntityServiceImpl businessEntityService;
	@Mock
	private ProductVendorRepositoryInterface productvendorRepositoryM;
	@InjectMocks
	private ProductVendorServiceImpl productvendorService;

	@Autowired
	public Taller1PruebasApplicationTests(ProductServiceImpl productService,
			ProductSubcategoryServiceImpl productSubcategoryService, ProductCategoryServiceImpl productCategoryService,
			TransactionHistoryServiceImpl transactionHistoryService, DocumentServiceImpl documentService,
			ProductdocumentServiceImpl productDocumentService, ProductVendorServiceImpl productvendorService,
			UnitMeasureServiceImpl unitmeasureService, VendorServiceImpl vendorService,
			BusinessEntityServiceImpl businessEntityService) {

		this.productService = productService;
		this.productSubcategoryService = productSubcategoryService;
		this.productCategoryService = productCategoryService;
		this.transactionHistoryService = transactionHistoryService;
		this.documentService = documentService;
		this.productDocumentService = productDocumentService;
		this.productvendorService = productvendorService;
		this.unitmeasureService = unitmeasureService;
		this.vendorService = vendorService;
		this.businessEntityService = businessEntityService;

	}

	@BeforeAll
	public void setUp() {
		Productcategory pc = new Productcategory();
		when(productCategoryRepositoryM.findById(01)).thenReturn(Optional.of(pc));
		Productsubcategory psc = new Productsubcategory();
		when(productSubCategoryRepositoryM.findById(01)).thenReturn(Optional.of(psc));
		Unitmeasure um = new Unitmeasure();
		when(unitmeasureRepositoryM.findById(01L)).thenReturn(Optional.of(um));
		Product p = new Product();
		when(productRepositoryM.findById(01)).thenReturn(Optional.of(p));
		Transactionhistory th = new Transactionhistory();
		when(transactionHistoryRepositoryM.findById(01)).thenReturn(Optional.of(th));
		Document d = new Document();
		when(documentRepositoryM.findById(01L)).thenReturn(Optional.of(d));
		Productvendor pd = new Productvendor();
		when(productvendorRepositoryM.findById(01)).thenReturn(Optional.of(pd));
		Vendor v = new Vendor();
		when(vendorRepositoryM.findById(01)).thenReturn(Optional.of(v));
		Businessentity b = new Businessentity();
		when(busineesEntityRepositoryM.findById(01)).thenReturn(Optional.of(b));
		when(documentRepositoryM.save(d)).thenReturn(d);

	}

	// Punto 5A: Test para el servicio de guardar un producto.
	@Test
	@Order(1)
	public void saveProductTest() {
		setUp();
		Product p = new Product();
		when(productRepositoryM.findById(01)).thenReturn(Optional.of(p));
		Productcategory pc = productCategoryService.findById(01).get();
		Productsubcategory psc = productSubcategoryService.findById(01).get();
		p.setProductnumber("Number of product");
		p.setDaystomanufacture(10);

		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = df.parse("23/09/2020");
			Date date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time1);
			Timestamp sellend = new Timestamp(time2);
			p.setSellstartdate(sellstart);
			p.setSellenddate(sellend);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		productService.saveCorrect(p, 01, 01, 01L); // guardo s product a través del servicio

		Product theProduct = productService.findById(01); // me devuelve a p

		assertFalse(theProduct.getProductnumber().isEmpty());
		assertTrue(theProduct.getDaystomanufacture() > 0);
		assertTrue(theProduct.getSellstartdate().before(theProduct.getSellenddate()));
		assertEquals(theProduct.getProductsubcategory(), psc);
		assertEquals(theProduct.getProductsubcategory().getProductcategory(), pc);

		verify(productRepositoryM).save(p);
		verify(productRepositoryM).findById(01);

	}

	// Punto 5A: Test para el servicio de editar un producto
	@Test
	@Order(2)
	public void editProduct() {

		// verifico que cuando se edite un producto con un argumento inválido, salte una
		// excepción
		try {
			Product theProduct = new Product();
			String productNumber = "";
			Integer daysToManufacture = 0;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date1;
			date1 = df.parse("23/09/2020");
			Date date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time2);
			Timestamp sellend = new Timestamp(time1);

			theProduct.setProductnumber(productNumber);
			theProduct.setDaystomanufacture(daysToManufacture);
			theProduct.setSellstartdate(sellstart);
			theProduct.setSellenddate(sellend);
			assertThrows(RuntimeException.class, () -> productService.editCorrect(theProduct, 01, 01, 01L));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// Verifico que cuando edite un producto con todos los argumentos válidos, se
		// edite realmente
		try {
			setUp();
			Product theProduct = new Product();
			theProduct.setProductid(01);
			when(productRepositoryM.findById(01)).thenReturn(Optional.of(theProduct));
			String productNumber = "correct product number";
			Integer daysToManufacture = 3;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date1;
			date1 = df.parse("23/09/2020");
			Date date2 = df.parse("23/10/2021");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time1);
			Timestamp sellend = new Timestamp(time2);
			theProduct.setProductnumber(productNumber);
			theProduct.setDaystomanufacture(daysToManufacture);
			theProduct.setSellstartdate(sellstart);
			theProduct.setSellenddate(sellend);

			productService.editCorrect(theProduct, 01, 01, 01L);

			Product theProductEntity = productService.findById(01);

			assertEquals(productNumber, theProductEntity.getProductnumber());
			assertEquals(daysToManufacture, theProductEntity.getDaystomanufacture());
			assertEquals(sellstart, theProductEntity.getSellstartdate());
			assertEquals(sellend, theProductEntity.getSellenddate());

			// el método findById de productRepository es llamado 2 veces:
			// 1. Dentro de este método para obtener al producto
			// 2. Dentro de este método para editar el producto
			verify(productRepositoryM, VerificationModeFactory.times(2)).findById(01);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	// PUNTO 5B: Test para el servicio de guardar una historia de transacciones

	@Test
	@Order(3)
	public void saveTransactionHistoryTest() {

		setUp();
		Product theProduct = productService.findById(01);
		Transactionhistory th = new Transactionhistory();
		when(transactionHistoryRepositoryM.findById(01)).thenReturn(Optional.of(th));

		th.setQuantity(12);
		th.setActualcost(new BigDecimal("20"));
		transactionHistoryService.saveCorrect(th, 01);

		// Verificamos que se hayan asignado correctamente los objetos a través de los
		// servicios y mocks
		Transactionhistory thtest = transactionHistoryService.findById(01);
		assertSame(th, thtest);
		assertSame(theProduct, thtest.getProduct());
		// Verificamos que los mocks de los repositorios cumplan su función
		verify(transactionHistoryRepositoryM).save(th);
		verify(transactionHistoryRepositoryM).findById(01);

		/*
		 * el método findById de produdctRepository es llamado 4 veces: 1. Cuando se
		 * llama desde el método test 1 del saveProductTest 2. Dentro del método
		 * editProductTest para obtener al producto 3. Dentro del método editProductTest
		 * para editar el producto 4. Dentro de este método para obtener el producto
		 */
		verify(productRepositoryM, VerificationModeFactory.times(2)).findById(01);

	}

	@Test
	@Order(4)
	public void editTransactionHistoryTest() {

		setUp();
		Transactionhistory th = transactionHistoryService.findById(01);
		Product p = productService.findById(01);
		p.setProductid(01);
		th.setTransactionid(01);
		
		th.setQuantity(0);
		assertThrows(IllegalArgumentException.class, () -> transactionHistoryService.editCorrect(th, 01));

		th.setQuantity(10);
		th.setActualcost(new BigDecimal("10"));

		transactionHistoryService.editCorrect(th, 01);
		verify(transactionHistoryRepositoryM, VerificationModeFactory.times(3)).findById(01);

	}

	@Test
	@Order(5)
	public void saveDocumentTest() {
		setUp();
		Document d = documentService.findById(01L);
		String fileName = "This is the file name";
		String fileExtensionTest = "This is a test";
		d.setFilename(fileName);
		d.setFileextension(fileExtensionTest);

		documentService.saveCorrect(d, 01);

		Document documenttest = documentService.findById(01L);
		assertSame(d, documenttest);

		// Verificamos que los mocks de los repositorios cumplan su función
		verify(documentRepositoryM).save(d);
		verify(productRepositoryM, VerificationModeFactory.times(1)).findById(01);

	}

	@Test
	@Order(6)
	public void editDocumentTest() {

		setUp();
		Document d = documentRepositoryM.findById(01L).get();
		Product p = productService.findById(01);

		p.setProductid(01);
		d.setDocumentnode(01L);
		when(productRepositoryM.findById(01)).thenReturn(Optional.of(p));
		when(documentRepositoryM.findById(01L)).thenReturn(Optional.of(d));
		when(documentRepositoryM.save(d)).thenReturn(d);

		String correctFilename = "correct file name";
		String correctextensionfile = "correct extension file";
		d.setFileextension(correctextensionfile);
		d.setFilename(correctFilename);
		documentService.editCorrect(d, 01);

		assertEquals(correctFilename, d.getFilename());
		verify(documentRepositoryM, VerificationModeFactory.times(2)).findById(01L);

	}

	@Test
	@Order(7)
	public void saveProductVendorTest() {
		setUp();

		Productvendor pv = productvendorService.findById(01).get();
		pv.setMinorderqty(10);
		pv.setMaxorderqty(20);
		pv.setStandardprice(new BigDecimal("30"));
		productvendorService.save(pv, 01L, 01, 01);

		verify(productvendorRepositoryM).save(pv);
		verify(productvendorRepositoryM).findById(01);
		verify(productRepositoryM, VerificationModeFactory.times(1)).findById(01);

	}
	
}
