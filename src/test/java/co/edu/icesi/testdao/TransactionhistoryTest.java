package co.edu.icesi.testdao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.PersistenceContext;

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
import co.edu.icesi.dao.TransactionhistoryDAO;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.repositories.ProductRepositoryInterface;
import co.edu.icesi.services.TransactionHistoryService;
import co.edu.icesi.services.TransactionHistoryServiceImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = { PersistenceContext.class })
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransactionhistoryTest {

	@InjectMocks
	private static TransactionHistoryService transactionhistoryService;

	@Mock
	private static TransactionhistoryDAO transactionhistoryDAO;

	@Mock
	private static ProductDAO productDAO;

	private static Product product;
	private static Transactionhistory transactionhistoryTest;

	private static Integer idProduct;
	private static Integer idTransactionhistoryTest;

	@BeforeAll
	public static void setUp() {
		transactionhistoryService = new TransactionHistoryServiceImpl(productDAO, transactionhistoryDAO);
		product = new Product();
		idProduct = product.getProductid();

		transactionhistoryTest = new Transactionhistory();
		transactionhistoryTest.setProduct(product);
		transactionhistoryTest.setActualcost(new BigDecimal("10"));
		transactionhistoryTest.setReferenceorderid(1);
		transactionhistoryTest.setQuantity(15);

		idTransactionhistoryTest = transactionhistoryTest.getTransactionid();
	}

	@BeforeEach
	public void init() {

		when(productDAO.findById(idProduct)).thenReturn(product);
		when(transactionhistoryDAO.findById(idTransactionhistoryTest)).thenReturn(transactionhistoryTest);

	}

	@Nested
	@DisplayName("Test of save transactionhistory method")
	class SaveTransactionhistory {

		@Test
		public void saveTransactionhistoryTest() {
			Transactionhistory th = new Transactionhistory();
			th.setActualcost(new BigDecimal("10"));
			th.setReferenceorderid(1);
			th.setQuantity(15);

			when(transactionhistoryDAO.save(th)).thenReturn(th);
			assertDoesNotThrow(() -> transactionhistoryService.saveCorrect(th, idProduct));

			verify(transactionhistoryDAO).save(th);
			verifyNoMoreInteractions(transactionhistoryDAO);
		}

		@Test
		public void saveNullTransactionhistoryTest() {
			Transactionhistory th = null;
			when(transactionhistoryDAO.save(th)).thenReturn(th);
			assertThrows(RuntimeException.class, () -> transactionhistoryService.saveCorrect(th, idProduct));

		}

		@Test
		public void saveTransactionhistoryWithoutActualCost() {
			Transactionhistory th = new Transactionhistory();
			th.setReferenceorderid(1);
			th.setQuantity(15);

			when(transactionhistoryDAO.save(th)).thenReturn(th);
			assertThrows(RuntimeException.class, () -> transactionhistoryService.saveCorrect(th, idProduct));

		}

		@Test
		public void saveTransactionhistoryWithoutQuantity() {
			Transactionhistory th = new Transactionhistory();
			th.setActualcost(new BigDecimal("10"));
			th.setReferenceorderid(1);

			when(transactionhistoryDAO.save(th)).thenReturn(th);
			assertThrows(RuntimeException.class, () -> transactionhistoryService.saveCorrect(th, idProduct));

		}

	}

	@Nested
	@DisplayName("Test of edit transaction history")
	class EditTransactionhistory {
		@Test
		public void editTransactionhistoryTest() {
			Transactionhistory thToEdit = transactionhistoryService.findById(idTransactionhistoryTest);
			verify(transactionhistoryDAO).findById(idTransactionhistoryTest);

			Product p = new Product();
			p.setProductid(2);
			Optional<Product> optionalProduct = Optional.of(p);
			when(productDAO.findById(2)).thenReturn(p);

			thToEdit.setProduct(p);
			when(transactionhistoryDAO.update(thToEdit)).thenReturn(thToEdit);
			assertDoesNotThrow(() -> transactionhistoryService.editCorrect(thToEdit, p.getProductid()));
			verify(transactionhistoryDAO).update(thToEdit);
		}

		@Test
		public void editTransactionhistoryNull() {
			assertThrows(RuntimeException.class, () -> transactionhistoryService.editCorrect(null, idProduct));

		}

		@Test
		public void editTransactionhistoryWithoutProductTest() {
			Transactionhistory thToEdit = transactionhistoryService.findById(idTransactionhistoryTest);
			verify(transactionhistoryDAO).findById(idTransactionhistoryTest);

			Product p = new Product();
			p.setProductid(2);

			thToEdit.setProduct(p);
			when(transactionhistoryDAO.update(thToEdit)).thenReturn(thToEdit);
			assertThrows(RuntimeException.class,
					() -> transactionhistoryService.editCorrect(thToEdit, p.getProductid()));
			verify(transactionhistoryDAO, times(0)).update(thToEdit);
		}
	}

}
