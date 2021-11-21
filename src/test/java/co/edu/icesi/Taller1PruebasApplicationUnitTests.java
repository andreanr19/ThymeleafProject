package co.edu.icesi;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import java.util.ArrayList;
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

import co.edu.icesi.model.Employee;
import co.edu.icesi.model.Businessentity;
import co.edu.icesi.model.Person;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.ProductvendorPK;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.repositories.DocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductVendorRepositoryInterface;
import co.edu.icesi.repositories.TransactionHistoryRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
import co.edu.icesi.services.DocumentServiceImpl;
import co.edu.icesi.services.ProductCategoryServiceImpl;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductServiceImpl;
import co.edu.icesi.services.ProductSubcategoryServiceImpl;
import co.edu.icesi.services.ProductVendorServiceImpl;
import co.edu.icesi.services.ProductdocumentServiceImpl;
import co.edu.icesi.services.TransactionHistoryServiceImpl;

//@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = Taller1PruebasApplication.class)
public class Taller1PruebasApplicationUnitTests {

	@Mock
	private static ProductRepositoryInterface productRepository;

	@Mock
	private static ProductSubcategoryRepositoryInterface productsubcategoryRepository;

	@Mock
	private static ProductCategoryRepositoryInterface productcategoryRepository;

	@Mock
	private static UnitmeasureRepositoryInterface unitmeasureRepository;

	@InjectMocks
	private static ProductService productService;

	private Optional<Productsubcategory> productsubcategory;

	private Optional<Unitmeasure> unitmeasure;

	private Optional<Productcategory> productcategory;

	private Product product;

	@BeforeAll
	public static void setUp() {
		productService = new ProductServiceImpl(productRepository, productcategoryRepository,
				productsubcategoryRepository, unitmeasureRepository);
	}

	private void validProductsubcategory() {
		Productsubcategory ps = new Productsubcategory();
		ps.setProductsubcategoryid(1);
		ps.setName("Car");
		this.productsubcategory = Optional.of(ps);
	}

	private void validCategory() {
		Productcategory pc = new Productcategory();
		pc.setProductcategoryid(1);
		pc.setName("Transportation");
		this.productcategory = Optional.of(pc);
	}

	private void validUnitmeasure() {
		Unitmeasure um = new Unitmeasure();
		um.setUnitmeasurecode(1);
		um.setName("kilometers");
		this.unitmeasure = Optional.of(um);
	}

	private void productWithoutNumberOfProduct() {
		this.validProductsubcategory();
		this.validCategory();
		this.validUnitmeasure();

		this.product = new Product();
		product.setProductid(1);
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
			product.setSellstartdate(sellstart);
			product.setSellenddate(sellend);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		product.setClass_("Bus");
		product.setName("Bus");
		product.setDaystomanufacture(4);
	}

	@Test
	public void testProductServiceSaveCorrectWithoutNumberOfProduct() {
		productWithoutNumberOfProduct();
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		try {
			productService.saveCorrect(this.product, 1, 1, 1L);
			fail();
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	private void productWithNumberOfProduct() {
		this.validProductsubcategory();
		this.validCategory();
		this.validUnitmeasure();

		this.product = new Product();
		product.setProductid(1);
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
			product.setSellstartdate(sellstart);
			product.setSellenddate(sellend);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		product.setClass_("Bus");
		product.setName("Bus");
		product.setDaystomanufacture(4);
		product.setProductnumber("5");
	}

	@Test
	public void testProductServiceSaveCorrectWithNumberOfProduct() {
		productWithNumberOfProduct();
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		productService.saveCorrect(this.product, 1, 1, 1L);
	
		verify(productcategoryRepository).findById((int)1);
		verify(productsubcategoryRepository).findById((int)1);
		verify(unitmeasureRepository).findById((long)1);
	}
	
	private void productWithRightStartDate() {
		this.validProductsubcategory();
		this.validCategory();
		this.validUnitmeasure();

		this.product = new Product();
		product.setProductid(1);
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
			product.setSellstartdate(sellstart);
			product.setSellenddate(sellend);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		product.setClass_("Bus");
		product.setName("Bus");
		product.setDaystomanufacture(4);
		product.setProductnumber("5");
	}

	@Test
	public void testProductServiceSaveCorrectWithRightDate() {
		productWithRightStartDate();
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		productService.saveCorrect(this.product, 1, 1, 1L);
	
		verify(productcategoryRepository).findById((int)1);
		verify(productsubcategoryRepository).findById((int)1);
		verify(unitmeasureRepository).findById((long)1);
	}
	
	private void productWithWrongStartDate() {
		this.validProductsubcategory();
		this.validCategory();
		this.validUnitmeasure();

		this.product = new Product();
		product.setProductid(1);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
		Date date2;
		try {
			date1 = df.parse("23/09/2020");
			date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time2);
			Timestamp sellend = new Timestamp(time1);
			product.setSellstartdate(sellstart);
			product.setSellenddate(sellend);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		product.setClass_("Bus");
		product.setName("Bus");
		product.setDaystomanufacture(4);
		product.setProductnumber("5");
	}
	
	@Test
	public void testProductServiceSaveCorrectWithWrongtDate() {
		productWithWrongStartDate();
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		try {
			productService.saveCorrect(this.product, 1, 1, 1L);
			fail();
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	
	}
	
	private void productWithRightDaystoManufacture() {
		this.validProductsubcategory();
		this.validCategory();
		this.validUnitmeasure();

		this.product = new Product();
		product.setProductid(1);
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
			product.setSellstartdate(sellstart);
			product.setSellenddate(sellend);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		product.setClass_("Bus");
		product.setName("Bus");
		product.setDaystomanufacture(4);
		product.setProductnumber("5");
	}
	
	@Test
	public void testProductServiceSaveCorrectWithRightDaysToManufacture() {
		productWithRightDaystoManufacture();
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		productService.saveCorrect(this.product, 1, 1, 1L);
	
		verify(productcategoryRepository).findById((int)1);
		verify(productsubcategoryRepository).findById((int)1);
		verify(unitmeasureRepository).findById((long)1);
	}
	
	private void productWithWrongDaystoManufacture() {
		this.validProductsubcategory();
		this.validCategory();
		this.validUnitmeasure();

		this.product = new Product();
		product.setProductid(1);
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
			product.setSellstartdate(sellstart);
			product.setSellenddate(sellend);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		product.setClass_("Bus");
		product.setName("Bus");
		product.setDaystomanufacture(0);
		product.setProductnumber("5");
	}
	
	@Test
	public void testProductServiceSaveCorrectWithWrongtDaystoManufacture() {
		productWithWrongDaystoManufacture();
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		try {
			productService.saveCorrect(this.product, 1, 1, 1L);
			fail();
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	
	}
	
	// EDIT
	@Test
	public void testProductServiceEditCorrectWithIncorrectParameters() {
		productWithoutNumberOfProduct();
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		try {
			productService.editCorrect(this.product, 1, 1, 1L);
			fail();
		} catch (RuntimeException e) {
			assertTrue(true);
		}
		
		productWithWrongStartDate();
		
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		try {
			productService.editCorrect(this.product, 1, 1, 1L);
			fail();
		} catch (RuntimeException e) {
			assertTrue(true);
		}
		
		productWithWrongDaystoManufacture();
		
		when(productsubcategoryRepository.findById((int) 1)).thenReturn(this.productsubcategory);
		when(productcategoryRepository.findById((int) 1)).thenReturn(this.productcategory);
		when(unitmeasureRepository.findById((long) 1)).thenReturn(this.unitmeasure);

		try {
			productService.editCorrect(this.product, 1, 1, 1L);
			fail();
		} catch (RuntimeException e) {
			assertTrue(true);
		}
		
	}
}
