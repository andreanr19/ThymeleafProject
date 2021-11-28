package co.edu.icesi.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dao.IDocumentDAO;
import co.edu.icesi.dao.IProductDAO;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;
import co.edu.icesi.repositories.DocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductRepositoryInterface;

@Service
public class DocumentServiceImpl implements DocumentService {

	// private DocumentRepositoryInterface documentRepository;
	private IDocumentDAO documentDAO;

	private IProductDAO productDAO;
	// private ProductRepositoryInterface productRepository;

	private ProductDocumentRepositoryInterface productdocumentRepository;

	@Autowired
	public DocumentServiceImpl(ProductDocumentRepositoryInterface productdocumentRepository, IDocumentDAO documentDAO,
			IProductDAO productDAO) {

		this.productDAO = productDAO;
		this.productdocumentRepository = productdocumentRepository;
		this.documentDAO = documentDAO;
	}

	public <S extends Document> S save(S document) {
		// documentRepository.save(document);
		documentDAO.save(document);
		return document;
	}
	@Transactional
	public void saveCorrect(Document document, Integer productId) {
		Product product = productDAO.findById(productId);
		if (document == null) {
			throw new RuntimeException();
		} else if (product == null) {
			throw new RuntimeException();
		} else if ((document.getFilename().length() < 4)) {
			throw new IllegalArgumentException("Invalid argument");

		} else if ((document.getFileextension().length() < 3)) {
			throw new IllegalArgumentException("Invalid argument");

		} else {

			document.setProduct(product);
//			long documentnodereal = document.getDocumentnode();
//			Document documentreference = document;
			System.out.println("******" + document);
			document = documentDAO.save(document);
			System.out.println("-----" + document);
			ProductdocumentPK pdk = new ProductdocumentPK();
			Productdocument pd = new Productdocument();
			pdk.setDocumentnode(document.getDocumentnode());
			System.out.println("sdajdabjkh " + document.getDocumentnode());
			pdk.setProductid(product.getProductid());
			pd.setId(pdk);
			pd.setDocument(document);

			pd.setProduct(product); // transient

			productdocumentRepository.save(pd);

		}
	}

	public <S extends Document> Iterable<S> saveAll(Iterable<S> documents) {
		for (Document doc : documents) {
			save(doc);
		}
		return documents;
	}

	public Document findById(Long id) {
		// return documentRepository.findById(id);
		return documentDAO.findById(id);
	}

//	public boolean existsById(Long id) {
//		return documentRepository.existsById(id);
//	}

	public Iterable<Document> findAll() {
		return documentDAO.findAll();
	}

//	public Iterable<Document> findAllById(Iterable<Long> theids) {
//		return documentRepository.findAllById(theids);
//	}
//
//	public long count() {
//		return documentRepository.count();
//	}
//
//	public void deleteAll() {
//		documentRepository.deleteAll();
//	}

	public void delete(Document doc) {
		documentDAO.delete(doc);
	}

//	public void deleteAll(Iterable<? extends Document> docs) {
//		documentRepository.deleteAll(docs);
//	}
//
//	public void deleteById(Long id) {
//		documentRepository.deleteById(id);
//	}

	@Transactional
	public void editCorrect(Document document, Integer productId) {
		Product product = productDAO.findById(productId);

		if (document == null) {
			throw new RuntimeException();
		} else {
			Document d = documentDAO.findById(document.getDocumentnode());

			if ((document.getFilename().length() < 4)) {
				throw new IllegalArgumentException("Invalid argument");

			} else if ((document.getFileextension().length() < 3)) {
				throw new IllegalArgumentException("Invalid argument");

			} else if (d == null) {
				throw new RuntimeException();
			} else {
				Document docentity = d;

				docentity.setFileextension(document.getFileextension());
				docentity.setFilename(document.getFilename());
				long time = System.currentTimeMillis();
				Timestamp actualdate = new Timestamp(time);

//				docentity.getProductdocuments().get(docentity.getProductdocuments().size() - 1)
//						.setProduct(product.get());
				docentity = documentDAO.update(docentity);
				docentity.setModifieddate(actualdate);
				System.out.println(docentity.getModifieddate());
				ProductdocumentPK pdk = new ProductdocumentPK();
				Productdocument pd = new Productdocument();
				pdk.setDocumentnode(docentity.getDocumentnode());
				pdk.setProductid(product.getProductid());
				pd.setId(pdk);
				pd.setDocument(docentity);
				pd.setProduct(product);
//				document.addProductdocument(pd);
//				product.get().addProductdocument(pd);
				productdocumentRepository.save(pd);

			}
		}
	}
}
