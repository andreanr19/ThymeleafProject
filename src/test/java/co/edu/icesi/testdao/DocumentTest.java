package co.edu.icesi.testdao;

import static org.mockito.Mockito.when;

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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import co.edu.icesi.dao.DocumentDAO;
import co.edu.icesi.dao.ProductDAO;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
import co.edu.icesi.services.DocumentService;
import co.edu.icesi.services.DocumentServiceImpl;

@ContextConfiguration(classes = { PersistenceContext.class })
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DocumentTest {

	@InjectMocks
	private static DocumentService documentService;

	@Mock
	private static DocumentDAO documentDAO;
	@Mock
	private static ProductDAO productDAO;
	@Mock
	private static ProductDocumentRepositoryInterface productDocumentRepository;

	private static Product product;
	private static Document documentTest;
	private static Productdocument productdocument;

	private static Integer idProduct;
	private static long idDocumentTest;

	@BeforeAll
	public static void setUp() {
		documentService = new DocumentServiceImpl(productDocumentRepository, documentDAO, productDAO);
		product = new Product();
		productdocument = new Productdocument();
		idProduct = product.getProductid();

		documentTest = new Document();
		documentTest.setProduct(product);

		idDocumentTest = documentTest.getDocumentnode();
	}

	@BeforeEach
	public void init() {
		when(productDAO.findById(idProduct)).thenReturn(product);
		when(documentDAO.findById(idDocumentTest)).thenReturn(documentTest);
		when(productDocumentRepository.save(productdocument)).thenReturn(productdocument);
	}

	@Nested
	@DisplayName("Test of save Document")
	class SaveDocumentMethod {
		@Test
		public void saveDocumentTest() {
			Document doc = new Document();
			doc.setFileextension("hellohello");
			doc.setFilename("hellohello");
			when(documentDAO.save(doc)).thenReturn(doc);
			assertDoesNotThrow(() -> documentService.saveCorrect(doc, idProduct));

			verify(documentDAO).save(doc);
			verifyNoMoreInteractions(documentDAO);
		}

		@Test
		public void saveNullDocumentTest() {
			Document doc = null;
			when(documentDAO.save(doc)).thenReturn(doc);
			assertThrows(RuntimeException.class, () -> documentService.saveCorrect(doc, idProduct));
		}

		@Test
		public void saveDocumentWithoutProductTest() {
			Document doc = new Document();
			doc.setFileextension("hellohello");
			doc.setFilename("hellohello");
			when(documentDAO.save(doc)).thenReturn(doc);
			assertThrows(RuntimeException.class, () -> documentService.saveCorrect(doc, idProduct - 5));
			verify(documentDAO, times(0)).save(doc);
		}

		@Test
		public void saveDocumentWithoutFileName() {
			Document doc = new Document();
			doc.setFileextension("hellohello");
			when(documentDAO.save(doc)).thenReturn(doc);
			assertThrows(RuntimeException.class, () -> documentService.saveCorrect(doc, idProduct));

		}

		@Test
		public void saveDocumentWithoutFileExtension() {
			Document doc = new Document();
			doc.setFilename("hellohello");
			when(documentDAO.save(doc)).thenReturn(doc);
			assertThrows(RuntimeException.class, () -> documentService.saveCorrect(doc, idProduct));

		}
	}

	@Nested
	@DisplayName("Test of edit Document")
	class EditDocumentMethods {
		@Test
		public void editDocumentTest() {
			Document documentToEdit = documentService.findById(idDocumentTest);
			when(documentDAO.findById(idDocumentTest)).thenReturn(documentToEdit);
			when(documentDAO.save(documentToEdit)).thenReturn(documentToEdit);
			verify(documentDAO).findById(idDocumentTest);

			Product product = new Product();
			product.setProductid(3);
			when(productDAO.findById(3)).thenReturn(product);

			documentToEdit.setProduct(product);
			documentToEdit.setFileextension("we see the darkness over light");
			documentToEdit.setFilename("No one saw the blood on my hands");
			when(documentDAO.update(documentToEdit)).thenReturn(documentToEdit);
			assertDoesNotThrow(() -> documentService.editCorrect(documentToEdit, product.getProductid()));
			verify(documentDAO).update(documentToEdit);
		}

		@Test
		public void editDocumentNullTest() {
			Document documentNull = null;
			assertThrows(RuntimeException.class, ()-> documentService.editCorrect(documentNull, idProduct));
		}
		
		@Test
		public void editDocumentWithoutProductTest() {
			Document documentToEdit = documentService.findById(idDocumentTest);
			when(documentDAO.findById(idDocumentTest)).thenReturn(documentToEdit);
			when(documentDAO.save(documentToEdit)).thenReturn(documentToEdit);
			verify(documentDAO).findById(idDocumentTest);
			documentToEdit.setProduct(product);
			documentToEdit.setFileextension("we see the darkness over light");
			documentToEdit.setFilename("No one saw the blood on my hands");
			assertThrows(RuntimeException.class, ()-> documentService.editCorrect(documentToEdit, idProduct-5));
			verify(documentDAO, times(0)).update(documentToEdit);

		}
	}

}
