package co.edu.icesi.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;
import co.edu.icesi.repositories.DocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductDocumentRepositoryInterface;
import co.edu.icesi.repositories.ProductRepositoryInterface;

@Service
public class DocumentServiceImpl implements DocumentService {

	private DocumentRepositoryInterface documentRepository;

	private ProductRepositoryInterface productRepository;

	private ProductDocumentRepositoryInterface productdocumentRepository;

	@Autowired
	public DocumentServiceImpl(DocumentRepositoryInterface documentRepository,
			ProductRepositoryInterface productRepository,
			ProductDocumentRepositoryInterface productdocumentRepository) {
		this.documentRepository = documentRepository;
		this.productRepository = productRepository;
		this.productdocumentRepository = productdocumentRepository;
	}

	public <S extends Document> S save(S document) {
		documentRepository.save(document);
		return document;
	}

	public void saveCorrect(Document document, Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (document == null) {
			throw new RuntimeException();
		} else if (product.isEmpty()) {
			throw new RuntimeException();
		} else if ((document.getFilename().length() < 4)) {
			throw new IllegalArgumentException("Invalid argument");

		} else if ((document.getFileextension().length() < 3)) {
			throw new IllegalArgumentException("Invalid argument");

		} else {
			document.setProduct(product.get());
			document = documentRepository.save(document);

			ProductdocumentPK pdk = new ProductdocumentPK();
			Productdocument pd = new Productdocument();
			pdk.setDocumentnode(document.getDocumentnode());
			pdk.setProductid(product.get().getProductid());
			pd.setId(pdk);
			pd.setDocument(document);
			pd.setProduct(product.get());
			//long time = System.currentTimeMillis();
			//Timestamp actualdate = new Timestamp(time);
			//pd.setModifieddate(actualdate);
//			document.addProductdocument(pd);
//			product.get().addProductdocument(pd);
			productdocumentRepository.save(pd);

		}
	}

	public <S extends Document> Iterable<S> saveAll(Iterable<S> documents) {
		for (Document doc : documents) {
			save(doc);
		}
		return documents;
	}

	public Optional<Document> findById(Long id) {
		return documentRepository.findById(id);
	}

	public boolean existsById(Long id) {
		return documentRepository.existsById(id);
	}

	public Iterable<Document> findAll() {
		return documentRepository.findAll();
	}

	public Iterable<Document> findAllById(Iterable<Long> theids) {
		return documentRepository.findAllById(theids);
	}

	public long count() {
		return documentRepository.count();
	}

	public void deleteAll() {
		documentRepository.deleteAll();
	}

	public void delete(Document doc) {
		documentRepository.delete(doc);
	}

	public void deleteAll(Iterable<? extends Document> docs) {
		documentRepository.deleteAll(docs);
	}

	public void deleteById(Long id) {
		documentRepository.deleteById(id);
	}

	public void editDocument(Long id, Integer changenumber, byte[] document, String documentsummary,
			String fileextension, String filename, String folderflag, Timestamp modifieddate, Integer owner,
			String revision, Integer rowguid, Integer status, String title) {

		if ((filename.length() < 4) || (fileextension.length() < 3)) {
			throw new IllegalArgumentException("Invalid argument");
		}
		Document d = documentRepository.findById(id).get();
		d.setChangenumber(changenumber);
		d.setDocument(document);
		d.setDocumentsummary(documentsummary);
		d.setFileextension(fileextension);
		d.setFilename(filename);
		d.setFolderflag(folderflag);
		d.setModifieddate(modifieddate);
		d.setOwner(owner);
		d.setRevision(revision);
		d.setRowguid(rowguid);
		d.setStatus(status);
		d.setTitle(title);

		save(d);
	}

	public void editCorrect(Document document, Integer productId) {
		Optional<Product> product = productRepository.findById(productId);

		if (document == null) {
			throw new RuntimeException();
		} else {
			Optional<Document> d = documentRepository.findById(document.getDocumentnode());

			if ((document.getFilename().length() < 4)) {

			} else if ((document.getFileextension().length() < 3)) {
				throw new IllegalArgumentException("Invalid argument");

			} else if (d.isEmpty()) {
				throw new RuntimeException();
			} else {
				Document docentity = d.get();

				docentity.setFileextension(document.getFileextension());
				docentity.setFilename(document.getFilename());
				long time = System.currentTimeMillis();
				Timestamp actualdate = new Timestamp(time);
				
				docentity.getProductdocuments().get(docentity.getProductdocuments().size() - 1)
						.setProduct(product.get());
				docentity = documentRepository.save(docentity);
				docentity.setModifieddate(actualdate);
				System.out.println(docentity.getModifieddate());
				ProductdocumentPK pdk = new ProductdocumentPK();
				Productdocument pd = new Productdocument();
				pdk.setDocumentnode(docentity.getDocumentnode());
				pdk.setProductid(product.get().getProductid());
				pd.setId(pdk);
				pd.setDocument(docentity);
				pd.setProduct(product.get());
//				document.addProductdocument(pd);
//				product.get().addProductdocument(pd);
				productdocumentRepository.save(pd);

			}
		}
	}
}
