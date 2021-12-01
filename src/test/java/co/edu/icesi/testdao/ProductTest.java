package co.edu.icesi.testdao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.dao.ProductDAO;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.repositories.ProductCategoryRepositoryInterface;
import co.edu.icesi.repositories.ProductSubcategoryRepositoryInterface;
import co.edu.icesi.repositories.UnitmeasureRepositoryInterface;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductServiceImpl;

@ContextConfiguration(classes = { PersistenceContext.class })
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductTest {

	@InjectMocks
	private static ProductService productService;

	@Mock
	private static ProductDAO productDAO;

	@Mock
	private static ProductSubcategoryRepositoryInterface productsubcategoryRepository;

	@Mock
	private static ProductCategoryRepositoryInterface productcategoryRepository;

	@Mock
	private static UnitmeasureRepositoryInterface unitmeasureRepository;

	private static Productsubcategory productsubcategory;
	private static Productcategory productcategory;
	private static Unitmeasure unitmeasure;
	private static Product productTest;

	private static Integer idProductsubcategory;
	private static Integer idProductcategory;
	private static long idUnitmeasure;
	private static Integer idProductTest;

	@BeforeAll
	public static void setUpProduct() throws ParseException {
		productService = new ProductServiceImpl(productDAO, productcategoryRepository, productsubcategoryRepository,
				unitmeasureRepository);
		productsubcategory = new Productsubcategory();
		productcategory = new Productcategory();
		unitmeasure = new Unitmeasure();

		productTest = new Product();
		productsubcategory.setProductcategory(productcategory);
		productTest.setProductsubcategory(productsubcategory);
		productTest.setProductnumber("Product number");
		productTest.setDaystomanufacture(10);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
		date1 = df.parse("23/09/2020");
		Date date2 = df.parse("23/10/2020");
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		Timestamp sellstart = new Timestamp(time1);
		Timestamp sellend = new Timestamp(time2);

		productTest.setSellstartdate(sellstart);
		productTest.setSellenddate(sellend);
		idProductTest = productTest.getProductid();

	}

	@BeforeEach
	public void init() {
		Optional<Productsubcategory> optionalProductsubcategory = Optional.of(productsubcategory);
		when(productsubcategoryRepository.findById(idProductsubcategory)).thenReturn(optionalProductsubcategory);
		when(productDAO.findById(idProductTest)).thenReturn(productTest);
		Optional<Productcategory> optionalProductcategory = Optional.of(productcategory);
		when(productcategoryRepository.findById(idProductcategory)).thenReturn(optionalProductcategory);
		Optional<Unitmeasure> optionalUnitmeasure = Optional.of(unitmeasure);
		when(unitmeasureRepository.findById(idUnitmeasure)).thenReturn(optionalUnitmeasure);
	}

	@Nested
	@DisplayName("Test of save product")
	class SaveProduct {
		@Test
		public void saveProductTest() {
			try {
				Product product = new Product();
				product.setProductsubcategory(productsubcategory);
				product.setDaystomanufacture(10);
				product.setProductnumber("productnumber");
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date1;
				date1 = df.parse("23/09/2020");
				Date date2 = df.parse("23/10/2020");
				long time1 = date1.getTime();
				long time2 = date2.getTime();
				Timestamp sellstart = new Timestamp(time1);
				Timestamp sellend = new Timestamp(time2);

				product.setSellstartdate(sellstart);
				product.setSellenddate(sellend);
				assertDoesNotThrow(() -> productService.saveCorrect(product, idProductcategory, idProductsubcategory,
						idUnitmeasure));
				verify(productDAO).save(product);
				verifyNoMoreInteractions(productDAO);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		@Test
		public void saveProductWithoutProductsubcategory() {

			try {
				Product product = new Product();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date1;
				date1 = df.parse("23/09/2020");
				Date date2 = df.parse("23/10/2020");
				long time1 = date1.getTime();
				long time2 = date2.getTime();
				Timestamp sellstart = new Timestamp(time1);
				Timestamp sellend = new Timestamp(time2);

				product.setSellstartdate(sellstart);
				product.setSellenddate(sellend);
				product.setDaystomanufacture(10);
				product.setProductnumber("productnumber");

				when(productDAO.save(product)).thenReturn(product);
				assertThrows(RuntimeException.class, () -> productService.saveCorrect(product, idProductsubcategory - 5,
						idProductcategory, idUnitmeasure));
				verify(productDAO, times(0)).save(product);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		@Test
		public void saveProductWithoutUnitmeasure() {
			try {
				Product product = new Product();
				product.setProductsubcategory(productsubcategory);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date1;
				date1 = df.parse("23/09/2020");
				Date date2 = df.parse("23/10/2020");
				long time1 = date1.getTime();
				long time2 = date2.getTime();
				Timestamp sellstart = new Timestamp(time1);
				Timestamp sellend = new Timestamp(time2);

				product.setSellstartdate(sellstart);
				product.setSellenddate(sellend);
				product.setDaystomanufacture(10);
				product.setProductnumber("productnumber");

				when(productDAO.save(product)).thenReturn(product);
				assertThrows(RuntimeException.class, () -> productService.saveCorrect(product, idProductsubcategory,
						idProductcategory, idUnitmeasure - 5));
				verify(productDAO, times(0)).save(product);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		@Test
		public void saveProductNull() {
			Product product = null;
			when(productDAO.save(product)).thenReturn(product);
			assertThrows(RuntimeException.class,
					() -> productService.saveCorrect(product, idProductsubcategory, idProductcategory, idUnitmeasure));
			verify(productDAO, times(0)).save(product);

		}

		@Test
		public void saveProductWithoutDaystomanufacture() {
			try {
				Product product = new Product();
				product.setProductsubcategory(productsubcategory);
				product.setProductnumber("productnumber");
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date1;
				date1 = df.parse("23/09/2020");
				Date date2 = df.parse("23/10/2020");
				long time1 = date1.getTime();
				long time2 = date2.getTime();
				Timestamp sellstart = new Timestamp(time1);
				Timestamp sellend = new Timestamp(time2);

				product.setSellstartdate(sellstart);
				product.setSellenddate(sellend);
				assertThrows(RuntimeException.class, () -> productService.saveCorrect(product, idProductsubcategory,
						idProductcategory, idUnitmeasure));
				verify(productDAO, times(0)).save(product);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		@Test
		public void saveProductWithoutProductnumber() {
			try {
				Product product = new Product();
				product.setProductsubcategory(productsubcategory);
				product.setDaystomanufacture(10);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date1;
				date1 = df.parse("23/09/2020");
				Date date2 = df.parse("23/10/2020");
				long time1 = date1.getTime();
				long time2 = date2.getTime();
				Timestamp sellstart = new Timestamp(time1);
				Timestamp sellend = new Timestamp(time2);

				product.setSellstartdate(sellstart);
				product.setSellenddate(sellend);
				assertThrows(RuntimeException.class, () -> productService.saveCorrect(product, idProductsubcategory,
						idProductcategory, idUnitmeasure));
				verify(productDAO, times(0)).save(product);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		@Test
		public void saveProductWithoutSellstartdate() {
			try {
				Product product = new Product();
				product.setProductsubcategory(productsubcategory);
				product.setProductnumber("productnumber");
				product.setDaystomanufacture(10);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date2 = df.parse("23/10/2020");
				long time2 = date2.getTime();
				Timestamp sellend = new Timestamp(time2);

				product.setSellenddate(sellend);
				assertThrows(RuntimeException.class, () -> productService.saveCorrect(product, idProductsubcategory,
						idProductcategory, idUnitmeasure));
				verify(productDAO, times(0)).save(product);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		@Test
		public void saveProductWithoutSellenddate() {
			try {
				Product product = new Product();
				product.setProductsubcategory(productsubcategory);
				product.setProductnumber("productnumber");
				product.setDaystomanufacture(10);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date date1;
				date1 = df.parse("23/09/2020");
				long time1 = date1.getTime();
				Timestamp sellstart = new Timestamp(time1);

				product.setSellstartdate(sellstart);
				assertThrows(RuntimeException.class, () -> productService.saveCorrect(product, idProductsubcategory,
						idProductcategory, idUnitmeasure));
				verify(productDAO, times(0)).save(product);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

	}

	@Nested
	@DisplayName("Test of edit product")
	class EditProduct {
		@Test
		public void editProductTest() {
			Product productToEdit = productService.findById(idProductTest);
			verify(productDAO).findById(idProductTest);

			productToEdit.setDaystomanufacture(11);
			productToEdit.setProductnumber("product number");

			when(productDAO.update(productToEdit)).thenReturn(productToEdit);
			assertDoesNotThrow(() -> productService.editCorrect(productToEdit, idProductsubcategory, idProductcategory,
					idUnitmeasure));
			verify(productDAO).update(productToEdit);
		}

		@Test
		public void editProductWithoutProductsubcategory() {
			Product productToEdit = productService.findById(idProductTest);
			verify(productDAO).findById(idProductTest);

			Productsubcategory ps2 = new Productsubcategory();
			ps2.setProductsubcategoryid(22);
			ps2.setProductcategory(productcategory);
			productToEdit.setProductsubcategory(ps2);

			when(productDAO.update(productToEdit)).thenReturn(productToEdit);
			assertThrows(RuntimeException.class, () -> productService.editCorrect(productToEdit,
					ps2.getProductsubcategoryid(), idProductcategory, idUnitmeasure));
			verify(productDAO, times(0)).update(productToEdit);

		}

		@Test
		public void editProductWithoutUnitmeasure() {
			Product productToEdit = productService.findById(idProductTest);
			verify(productDAO).findById(idProductTest);

			Unitmeasure um2 = new Unitmeasure();
			um2.setUnitmeasurecode(22L);

			when(productDAO.update(productToEdit)).thenReturn(productToEdit);
			assertThrows(RuntimeException.class, () -> productService.editCorrect(productToEdit, idProductsubcategory,
					idProductcategory, um2.getUnitmeasurecode()));
			verify(productDAO, times(0)).update(productToEdit);

		}
		
		

	}
}
