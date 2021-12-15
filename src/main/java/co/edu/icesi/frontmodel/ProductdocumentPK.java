package co.edu.icesi.frontmodel;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the productdocument database table.
 * 
 */
public class ProductdocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer productid;

	private Long documentnode;

	public ProductdocumentPK() {
	}
	public Integer getProductid() {
		return this.productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Long getDocumentnode() {
		return this.documentnode;
	}
	public void setDocumentnode(Long documentnode) {
		this.documentnode = documentnode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductdocumentPK)) {
			return false;
		}
		ProductdocumentPK castOther = (ProductdocumentPK)other;
		return 
			this.productid.equals(castOther.productid)
			&& this.documentnode.equals(castOther.documentnode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productid.hashCode();
		hash = hash * prime + this.documentnode.hashCode();
		
		return hash;
	}
}