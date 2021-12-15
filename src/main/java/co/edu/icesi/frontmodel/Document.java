package co.edu.icesi.frontmodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.icesi.model.Add;

/**
 * The persistent class for the document database table.
 *
 */

public class Document implements Serializable {
	private static final long serialVersionUID = 1L;


	private long documentnode;

	private Integer changenumber;

	private byte[] document;

	private String documentsummary;

	@NotNull(groups=Add.class, message="file extension shouldn't be empty")
	@Size(min=3, groups= Add.class)
	private String fileextension;

	@NotNull(groups=Add.class, message="file name shouldn't be empty")
	@Size(min=4, groups= Add.class)
	private String filename;

	private String folderflag;

	private Timestamp modifieddate;

	private Integer owner;

	private String revision;

	private Integer rowguid;

	private Integer status;

	private String title;
	
	@Transient
	private Product product;

	// bi-directional many-to-one association to Productdocument
	@JsonIgnore
	@OneToMany(mappedBy = "document", cascade=CascadeType.REMOVE)
	private List<Productdocument> productdocuments;

	public Document() {
	}

	public Productdocument addProductdocument(Productdocument productdocument) {
		getProductdocuments().add(productdocument);
		productdocument.setDocument(this);

		return productdocument;
	}

	public Integer getChangenumber() {
		return this.changenumber;
	}

	public byte[] getDocument() {
		return this.document;
	}

	public Long getDocumentnode() {
		return this.documentnode;
	}

	public String getDocumentsummary() {
		return this.documentsummary;
	}

	public String getFileextension() {
		return this.fileextension;
	}

	public String getFilename() {
		return this.filename;
	}

	public String getFolderflag() {
		return this.folderflag;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getOwner() {
		return this.owner;
	}

	public List<Productdocument> getProductdocuments() {
		return this.productdocuments;
	}

	public String getRevision() {
		return this.revision;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public String getTitle() {
		return this.title;
	}

	public Productdocument removeProductdocument(Productdocument productdocument) {
		getProductdocuments().remove(productdocument);
		productdocument.setDocument(null);

		return productdocument;
	}

	public void setChangenumber(Integer changenumber) {
		this.changenumber = changenumber;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public void setDocumentnode(Long documentnode) {
		this.documentnode = documentnode;
	}

	public void setDocumentsummary(String documentsummary) {
		this.documentsummary = documentsummary;
	}

	public void setFileextension(String fileextension) {
		this.fileextension = fileextension;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFolderflag(String folderflag) {
		this.folderflag = folderflag;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public void setProductdocuments(List<Productdocument> productdocuments) {
		this.productdocuments = productdocuments;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}