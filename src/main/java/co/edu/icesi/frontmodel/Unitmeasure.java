package co.edu.icesi.frontmodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.icesi.model.Billofmaterial;

/**
 * The persistent class for the unitmeasure database table.
 *
 */

public class Unitmeasure implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long unitmeasurecode;

	private Timestamp modifieddate;

	private String name;

	// bi-directional many-to-one association to Billofmaterial
	@JsonIgnore
	private List<Billofmaterial> billofmaterials;

	// bi-directional many-to-one association to Product
	@JsonIgnore
	private List<Product> products1;

	// bi-directional many-to-one association to Product
	@JsonIgnore
	private List<Product> products2;

	public Unitmeasure() {
	}

	

	public Product addProducts1(Product products1) {
		getProducts1().add(products1);
		products1.setUnitmeasure1(this);

		return products1;
	}

	public Product addProducts2(Product products2) {
		getProducts2().add(products2);
		products2.setUnitmeasure2(this);

		return products2;
	}

	public List<Billofmaterial> getBillofmaterials() {
		return this.billofmaterials;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public List<Product> getProducts1() {
		return this.products1;
	}

	public List<Product> getProducts2() {
		return this.products2;
	}

	public long getUnitmeasurecode() {
		return this.unitmeasurecode;
	}

	public Billofmaterial removeBillofmaterial(Billofmaterial billofmaterial) {
		getBillofmaterials().remove(billofmaterial);
		billofmaterial.setUnitmeasure(null);

		return billofmaterial;
	}

	public Product removeProducts1(Product products1) {
		getProducts1().remove(products1);
		products1.setUnitmeasure1(null);

		return products1;
	}

	public Product removeProducts2(Product products2) {
		getProducts2().remove(products2);
		products2.setUnitmeasure2(null);

		return products2;
	}

	public void setBillofmaterials(List<Billofmaterial> billofmaterials) {
		this.billofmaterials = billofmaterials;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProducts1(List<Product> products1) {
		this.products1 = products1;
	}

	public void setProducts2(List<Product> products2) {
		this.products2 = products2;
	}

	public void setUnitmeasurecode(long unitmeasurecode) {
		this.unitmeasurecode = unitmeasurecode;
	}

}