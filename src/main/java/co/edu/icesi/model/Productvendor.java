package co.edu.icesi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the productvendor database table.
 *
 */
@Entity
@NamedQuery(name = "Productvendor.findAll", query = "SELECT p FROM Productvendor p")
public class Productvendor implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private ProductvendorPK id;
	
	@Id
	@SequenceGenerator(name = "PRODUCTVENDOR_PRODUCTVENDORID_GENERATOR", allocationSize = 1, sequenceName = "PRODUCTVENDOR_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTVENDOR_PRODUCTVENDORID_GENERATOR")
	private Integer id;
	
	@Column(insertable=false, updatable=false)
	private Integer productid;

	@Column(insertable=false, updatable=false)
	private Integer businessentityid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	private Integer averageleadtime;

	private BigDecimal lastreceiptcost;

	private Timestamp lastreceiptdate;

	@NotNull(groups=Add.class)
	private Integer maxorderqty;

	@NotNull(groups=Add.class)
	private Integer minorderqty;

	private Timestamp modifieddate;

	private Integer onorderqty;

	@NotNull(groups=Add.class)
	@Min(value=0, groups=Add.class)
	private BigDecimal standardprice;

	@NotNull(groups=Add.class)
	private long unitmeasurecode;

	// bi-directional many-to-one association to Vendor
	@NotNull(groups=Add.class)
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Vendor vendor;

	public Productvendor() {
	}

	public Integer getAverageleadtime() {
		return this.averageleadtime;
	}
//
//	public ProductvendorPK getId() {
//		return this.id;
//	}

	public BigDecimal getLastreceiptcost() {
		return this.lastreceiptcost;
	}

	public Timestamp getLastreceiptdate() {
		return this.lastreceiptdate;
	}

	public Integer getMaxorderqty() {
		return this.maxorderqty;
	}

	public Integer getMinorderqty() {
		return this.minorderqty;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getOnorderqty() {
		return this.onorderqty;
	}

	public BigDecimal getStandardprice() {
		return this.standardprice;
	}

	public long getUnitmeasurecode() {
		return this.unitmeasurecode;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setAverageleadtime(Integer averageleadtime) {
		this.averageleadtime = averageleadtime;
	}

//	public void setId(ProductvendorPK id) {
//		this.id = id;
//	}

	public void setLastreceiptcost(BigDecimal lastreceiptcost) {
		this.lastreceiptcost = lastreceiptcost;
	}

	public void setLastreceiptdate(Timestamp lastreceiptdate) {
		this.lastreceiptdate = lastreceiptdate;
	}

	public void setMaxorderqty(Integer maxorderqty) {
		this.maxorderqty = maxorderqty;
	}

	public void setMinorderqty(Integer minorderqty) {
		this.minorderqty = minorderqty;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setOnorderqty(Integer onorderqty) {
		this.onorderqty = onorderqty;
	}

	public void setStandardprice(BigDecimal standardprice) {
		this.standardprice = standardprice;
	}

	public void setUnitmeasurecode(long unitmeasurecode) {
		this.unitmeasurecode = unitmeasurecode;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Integer getProductid() {
		return this.productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getBusinessentityid() {
		return this.businessentityid;
	}
	public void setBusinessentityid(Integer businessentityid) {
		this.businessentityid = businessentityid;
	}

}