package co.edu.icesi.testdao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
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

import co.edu.icesi.Taller1PruebasApplication;
import co.edu.icesi.model.Businessentity;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.model.Vendor;
import co.edu.icesi.repositories.DocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
import co.edu.icesi.repositories.TransactionHistoryRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
import co.edu.icesi.services.BusinessEntityServiceImpl;
import co.edu.icesi.services.DocumentServiceImpl;
import co.edu.icesi.services.ProductCategoryService;
import co.edu.icesi.services.ProductCategoryServiceImpl;
import co.edu.icesi.services.ProductServiceImpl;
import co.edu.icesi.services.ProductSubcategoryService;
import co.edu.icesi.services.ProductSubcategoryServiceImpl;
import co.edu.icesi.services.ProductVendorServiceImpl;
import co.edu.icesi.services.ProductdocumentServiceImpl;
import co.edu.icesi.services.TransactionHistoryServiceImpl;
import co.edu.icesi.services.UnitMeasureService;
import co.edu.icesi.services.UnitMeasureServiceImpl;
import co.edu.icesi.services.VendorServiceImpl;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller1PruebasApplication.class)
class IntegratedTestsWithDAOS {

	private ProductServiceImpl productService;
	private Product productEntity;

	private ProductSubcategoryServiceImpl productSubcategoryService;
	private Productsubcategory productsubcategoryEntity;

	private ProductCategoryServiceImpl productCategoryService;
	private Productcategory productcategoryEntity;

	private TransactionHistoryServiceImpl transactionHistoryService;
	private Transactionhistory transactionHistoryEntity;

	private DocumentServiceImpl documentService;
	private Document documentEntity;

	private UnitMeasureServiceImpl unitmeasureService;
	private Unitmeasure unitmeasureEntity;

	private ProductVendorServiceImpl productVendorService;
	private Productvendor productvendorEntity;
	private BusinessEntityServiceImpl businessEntityService;
	private Businessentity businessEntity;
	private VendorServiceImpl vendorService;
	private Vendor vendorEntity;

	@Autowired
	public IntegratedTestsWithDAOS(ProductServiceImpl productService,
			ProductSubcategoryServiceImpl productSubcategoryService, ProductCategoryServiceImpl productCategoryService,
			TransactionHistoryServiceImpl transactionHistoryService, DocumentServiceImpl documentService,
			UnitMeasureServiceImpl unitmeasureService, ProductVendorServiceImpl productVendorService,
			BusinessEntityServiceImpl businessEntityService, VendorServiceImpl vendorService) {

		this.productService = productService;
		this.productSubcategoryService = productSubcategoryService;
		this.productCategoryService = productCategoryService;
		this.transactionHistoryService = transactionHistoryService;
		this.documentService = documentService;
		this.unitmeasureService = unitmeasureService;
		this.productVendorService = productVendorService;
		this.businessEntityService = businessEntityService;
		this.vendorService = vendorService;

	}

	@BeforeAll
	public void setupProductService() {
		productsubcategoryEntity = new Productsubcategory();
		productsubcategoryEntity.setName("Transport");
		productsubcategoryEntity = productSubcategoryService.save(productsubcategoryEntity);

		productcategoryEntity = new Productcategory();
		productcategoryEntity.setName("Transport");
		productcategoryEntity = productCategoryService.save(productcategoryEntity);

		unitmeasureEntity = new Unitmeasure();
		unitmeasureEntity.setName("Kilometers");
		unitmeasureEntity = unitmeasureService.save(unitmeasureEntity);
	}

	@BeforeAll
	public void setUpTransactionhistory() {
		transactionHistoryEntity = new Transactionhistory();
	}

	@BeforeAll
	public void setUpDocument() {
		documentEntity = new Document();

	}

	@BeforeAll
	public void setUpProductVendor() {
		businessEntity = new Businessentity();
		businessEntityService.save(businessEntity);
		vendorEntity = new Vendor();
		vendorEntity.setName("Snow");
		vendorEntity.setBusinessentityid(businessEntity.getBusinessentityid());
		vendorService.save(vendorEntity);

	}

	// Punto 5A: Test para el servicio de guardar un producto.
	@Test
	@Order(1)
	public void saveProductTest() {
		productEntity = new Product();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
		Date date2;
		try {
			date1 = df.parse("23/09/2020");
			date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time1);
			Timestamp sellend = new Timestamp(time2);
			productEntity.setSellstartdate(sellstart);
			productEntity.setSellenddate(sellend);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		productEntity.setClass_("Bus");
		productEntity.setName("Bus");
		productEntity.setProductnumber("2");
		productEntity.setDaystomanufacture(4);

		productService.saveCorrect(productEntity, productcategoryEntity.getProductcategoryid(),
				productsubcategoryEntity.getProductsubcategoryid(), unitmeasureEntity.getUnitmeasurecode());

		Product theProduct = productService.findById(productEntity.getProductid()); // me devuelve a p

		assertFalse(theProduct.getProductnumber().isEmpty());
		assertTrue(theProduct.getDaystomanufacture() > 0);
		assertTrue(theProduct.getSellstartdate().before(theProduct.getSellenddate()));

	}

	// Punto 5A: Test para el servicio de editar un producto
	@Test
	@Order(2)
	public void editProduct() {

		// verifico que cuando se edite un producto con un argumento inválido, salte una
		// excepción

		try {
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

			productEntity.setProductnumber(productNumber);
			productEntity.setDaystomanufacture(daysToManufacture);
			productEntity.setSellstartdate(sellstart);
			productEntity.setSellenddate(sellend);
			assertThrows(IllegalArgumentException.class,
					() -> productService.editCorrect(productEntity,
							productEntity.getProductsubcategory().getProductcategory().getProductcategoryid(),
							productEntity.getProductsubcategory().getProductsubcategoryid(),
							productEntity.getUnitmeasure1().getUnitmeasurecode()));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// Verifico que cuando edite un producto con todos los argumentos válidos, se
		// edite realmente
		try {
			String productNumber = "correct product number";
			Integer daysToManufacture = 3;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date date1;
			date1 = df.parse("23/09/2020");
			Date date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time1);
			Timestamp sellend = new Timestamp(time2);
			productEntity.setProductnumber(productNumber);
			productEntity.setDaystomanufacture(daysToManufacture);
			productEntity.setSellstartdate(sellstart);
			productEntity.setSellenddate(sellend);

			productService.editCorrect(productEntity,
					productEntity.getProductsubcategory().getProductcategory().getProductcategoryid(),
					productEntity.getProductsubcategory().getProductsubcategoryid(),
					productEntity.getUnitmeasure1().getUnitmeasurecode());

			Product theProduct = productService.findById(productEntity.getProductid());

			assertEquals(productNumber, theProduct.getProductnumber());
			assertEquals(daysToManufacture, theProduct.getDaystomanufacture());
			assertEquals(sellstart, theProduct.getSellstartdate());
			assertEquals(sellend, theProduct.getSellenddate());

		} catch (ParseException e) {

			e.printStackTrace();
		}

	}

	// PUNTO 5B: Test para el servicio de guardar una historia de transacciones

	@Test
	@Order(3)
	public void saveTransactionHistoryTest() {

		transactionHistoryEntity.setProduct(productEntity);
		transactionHistoryEntity.setQuantity(10);
		transactionHistoryEntity.setActualcost(new BigDecimal("5"));
		transactionHistoryService.saveCorrect(transactionHistoryEntity, productEntity.getProductid());

		Transactionhistory th = transactionHistoryService.findById(transactionHistoryEntity.getTransactionid()); // me devuelve a p

		assertTrue(th.getActualcost().compareTo(new BigDecimal("5")) == 0);
		assertTrue(th.getQuantity().equals(10));
	}

	@Test
	@Order(4)
	public void editTransactionHistoryTest() {

		// verifico que cuando edite una transacción con parametros incorrectos capture
		// una excepción
		BigDecimal actualCostWrong = new BigDecimal("0");
		int quantityWrong = 0;
		transactionHistoryEntity.setActualcost(actualCostWrong);
		transactionHistoryEntity.setQuantity(quantityWrong);
		assertThrows(IllegalArgumentException.class, () -> transactionHistoryService
				.editCorrect(transactionHistoryEntity, transactionHistoryEntity.getProduct().getProductid()));

		// verifico que cuando edite una transacción con parametros correctos se edite
		// correctamente
		BigDecimal actualCostRight = new BigDecimal("10.00");
		int quantityRight = 10;
		transactionHistoryEntity.setActualcost(actualCostRight);
		transactionHistoryEntity.setQuantity(quantityRight);
		transactionHistoryService.editCorrect(transactionHistoryEntity,
				transactionHistoryEntity.getProduct().getProductid());
		Transactionhistory th = transactionHistoryService.findById(transactionHistoryEntity.getTransactionid());

		assertEquals(th.getActualcost(), actualCostRight);
		assertEquals(th.getQuantity(), (Integer) quantityRight);
	}

	@Test
	@Order(5)
	public void saveDocumentTest() {
		documentEntity.setFilename("Andrea!!");
		documentEntity.setFileextension("Morgan!");
		documentService.saveCorrect(documentEntity, productEntity.getProductid());

		Document d = documentService.findById(documentEntity.getDocumentnode());
		assertEquals(d.getFilename(), documentEntity.getFilename());
		assertEquals(d.getFileextension(), documentEntity.getFileextension());
	}

	@Test
	@Order(6)
	public void editDocumentTest() {
		// verifico que cuando edite un documento con parametros incorrectos capture
		// una excepción

		String filenameIncorrect = "";
		String fileExtensionIncorrect = "";
		documentEntity.setFilename(filenameIncorrect);
		documentEntity.setFileextension(fileExtensionIncorrect);
		assertThrows(IllegalArgumentException.class,
				() -> documentService.editCorrect(documentEntity, documentEntity.getProduct().getProductid()));

		// verifico que cuando edite un documento con parametros correctos capture se
		// edite correctamente
		String filenamecorrect = "Isabel";
		String fileExtensioncorrect = "Vaquiperro";
		documentEntity.setFilename(filenamecorrect);
		documentEntity.setFileextension(fileExtensioncorrect);
		documentService.editCorrect(documentEntity, documentEntity.getProduct().getProductid());
		Document d = documentService.findById(documentEntity.getDocumentnode());

		assertEquals(d.getFileextension(), fileExtensioncorrect);
		assertEquals(d.getFilename(), filenamecorrect);
	}

	@Test
	@Order(7)
	public void saveProductVendor() {
		productvendorEntity = new Productvendor();
		productvendorEntity.setId(1);
		productvendorEntity.setMaxorderqty(1000);
		productvendorEntity.setMinorderqty(500);
		productvendorEntity.setStandardprice(new BigDecimal("1000"));
		productVendorService.save(productvendorEntity, unitmeasureEntity.getUnitmeasurecode(),
				productEntity.getProductid(), vendorEntity.getBusinessentityid());

		Productvendor pv = productVendorService.findById(productvendorEntity.getId()).get();
		assertEquals(pv.getMaxorderqty(), productvendorEntity.getMaxorderqty());
		assertEquals(pv.getMinorderqty(), productvendorEntity.getMinorderqty());

	}

	@Test
	@Order(8)
	public void editProductVendor() {
		// verifico que cuando edite un productvendor con parametros incorrectos capture
		// una excepción
		int maxorderWrong = 200;
		int minorderWrong = 500;
		BigDecimal standardPrinceWrong = new BigDecimal("0");
		productvendorEntity.setMaxorderqty(maxorderWrong);
		productvendorEntity.setMinorderqty(minorderWrong);
		productvendorEntity.setStandardprice(standardPrinceWrong);

		assertThrows(IllegalArgumentException.class,
				() -> productVendorService.edit(productvendorEntity,
						productEntity.getUnitmeasure1().getUnitmeasurecode(), productEntity.getProductid(),
						vendorEntity.getBusinessentityid()));

		// verifico que cuando edite un productvendor con parametros correctos capture
		// una excepción
		int maxorderRight = 500;
		int minorderRight = 200;
		BigDecimal standardPrinceRight = new BigDecimal("100.00");
		productvendorEntity.setMaxorderqty(maxorderRight);
		productvendorEntity.setMinorderqty(minorderRight);
		productvendorEntity.setStandardprice(standardPrinceRight);
		productVendorService.edit(productvendorEntity, productEntity.getUnitmeasure1().getUnitmeasurecode(),
				productEntity.getProductid(), vendorEntity.getBusinessentityid());
		Productvendor pv = productVendorService.findById(productvendorEntity.getId()).get();
		assertEquals(pv.getMaxorderqty(), maxorderRight);
		assertEquals(pv.getMinorderqty(), minorderRight);
		assertEquals(pv.getStandardprice(), standardPrinceRight);

	}

}