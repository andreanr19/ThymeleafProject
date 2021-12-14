package co.edu.icesi.frontmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.icesi.model.Add;
import co.edu.icesi.model.Billofmaterial;
import co.edu.icesi.model.Productcosthistory;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.Productinventory;
import co.edu.icesi.model.Productlistpricehistory;
import co.edu.icesi.model.Productmodel;
import co.edu.icesi.model.Productproductphoto;
import co.edu.icesi.model.Productreview;
import co.edu.icesi.frontmodel.Productsubcategory;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.frontmodel.Unitmeasure;
import co.edu.icesi.model.Workorder;

/**
 * The persistent class for the product database table.
 *
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer productid;

	private String class_;

	private String color;

	@NotNull(groups=Add.class)
	@Min(value = 1, message = "days to manufacture should be greather than 0", groups = Add.class)
	private Integer daystomanufacture;

	private Timestamp discontinueddate;

	private String finishedgoodsflag;

	private BigDecimal listprice;

	private String makeflag;

	private Timestamp modifieddate;

	@NotNull(message="name shouldn't be null", groups=Add.class)
	private String name;

	private String productline;

	@NotNull(groups=Add.class)
	@Min(value = 1, message = "product number should be greather than 0", groups = Add.class)
	private String productnumber;

	private Integer reorderpoint;

	private Integer rowguid;

	private Integer safetystocklevel;

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Timestamp sellenddate;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp sellstartdate;

	private String size;

	private BigDecimal standardcost;

	private String style;

	private BigDecimal weight;

	// bi-directional many-to-one association to Billofmaterial
	@JsonIgnore
	private List<Billofmaterial> billofmaterials1;

	// bi-directional many-to-one association to Billofmaterial
	@JsonIgnore
	private List<Billofmaterial> billofmaterials2;

	// bi-directional many-to-one association to Productmodel

	private Productmodel productmodel;

	@NotNull(groups = Add.class)
	// bi-directional many-to-one association to Productsubcategory

	private Productsubcategory productsubcategory;

	@NotNull(groups = Add.class)
	// bi-directional many-to-one association to Unitmeasure
	@ManyToOne
	private Unitmeasure unitmeasure1;

	// bi-directional many-to-one association to Unitmeasure
	@ManyToOne
	@JoinColumn(name = "weightunitmeasurecode")
	private Unitmeasure unitmeasure2;

	// bi-directional many-to-one association to Productcosthistory
	@JsonIgnore
	private List<Productcosthistory> productcosthistories;

	// bi-directional many-to-one association to Productdocument
	@JsonIgnore
	private List<Productdocument> productdocuments;

	// bi-directional many-to-one association to Productinventory
	@JsonIgnore
	private List<Productinventory> productinventories;

	// bi-directional many-to-one association to Productlistpricehistory
	@JsonIgnore
	private List<Productlistpricehistory> productlistpricehistories;

	// bi-directional many-to-one association to Productproductphoto
	@JsonIgnore
	private List<Productproductphoto> productproductphotos;

	// bi-directional many-to-one association to Productreview
	@JsonIgnore
	private List<Productreview> productreviews;

	// bi-directional many-to-one association to Transactionhistory
	@JsonIgnore
	private List<Transactionhistory> transactionhistories;

	// bi-directional many-to-one association to Workorder
	@JsonIgnore
	private List<Workorder> workorders;

	public Product() {
		setTransactionhistories(new ArrayList<>());
		setProductdocuments(new ArrayList<>());

	}


	public List<Billofmaterial> getBillofmaterials1() {
		return this.billofmaterials1;
	}

	public List<Billofmaterial> getBillofmaterials2() {
		return this.billofmaterials2;
	}

	public String getClass_() {
		return this.class_;
	}

	public String getColor() {
		return this.color;
	}

	public Integer getDaystomanufacture() {
		return this.daystomanufacture;
	}

	public Timestamp getDiscontinueddate() {
		return this.discontinueddate;
	}

	public String getFinishedgoodsflag() {
		return this.finishedgoodsflag;
	}

	public BigDecimal getListprice() {
		return this.listprice;
	}

	public String getMakeflag() {
		return this.makeflag;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public List<Productcosthistory> getProductcosthistories() {
		return this.productcosthistories;
	}

	public List<Productdocument> getProductdocuments() {
		return this.productdocuments;
	}

	public Integer getProductid() {
		return this.productid;
	}

	public List<Productinventory> getProductinventories() {
		return this.productinventories;
	}

	public String getProductline() {
		return this.productline;
	}

	public List<Productlistpricehistory> getProductlistpricehistories() {
		return this.productlistpricehistories;
	}

	public Productmodel getProductmodel() {
		return this.productmodel;
	}

	public String getProductnumber() {
		return this.productnumber;
	}

	public List<Productproductphoto> getProductproductphotos() {
		return this.productproductphotos;
	}

	public List<Productreview> getProductreviews() {
		return this.productreviews;
	}

	public Productsubcategory getProductsubcategory() {
		return this.productsubcategory;
	}

	public Integer getReorderpoint() {
		return this.reorderpoint;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Integer getSafetystocklevel() {
		return this.safetystocklevel;
	}

	public Timestamp getSellenddate() {
		return this.sellenddate;
	}

	public Timestamp getSellstartdate() {
		return this.sellstartdate;
	}

	public String getSize() {
		return this.size;
	}

	public BigDecimal getStandardcost() {
		return this.standardcost;
	}

	public String getStyle() {
		return this.style;
	}

	public List<Transactionhistory> getTransactionhistories() {
		return this.transactionhistories;
	}

	public Unitmeasure getUnitmeasure1() {
		return this.unitmeasure1;
	}

	public Unitmeasure getUnitmeasure2() {
		return this.unitmeasure2;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public List<Workorder> getWorkorders() {
		return this.workorders;
	}

	public Billofmaterial removeBillofmaterials1(Billofmaterial billofmaterials1) {
		getBillofmaterials1().remove(billofmaterials1);
		billofmaterials1.setProduct1(null);

		return billofmaterials1;
	}

	public Billofmaterial removeBillofmaterials2(Billofmaterial billofmaterials2) {
		getBillofmaterials2().remove(billofmaterials2);
		billofmaterials2.setProduct2(null);

		return billofmaterials2;
	}

	public Productcosthistory removeProductcosthistory(Productcosthistory productcosthistory) {
		getProductcosthistories().remove(productcosthistory);
		productcosthistory.setProduct(null);

		return productcosthistory;
	}

	public Productdocument removeProductdocument(Productdocument productdocument) {
		getProductdocuments().remove(productdocument);
		productdocument.setProduct(null);

		return productdocument;
	}

	public Productinventory removeProductinventory(Productinventory productinventory) {
		getProductinventories().remove(productinventory);
		productinventory.setProduct(null);

		return productinventory;
	}

	public Productlistpricehistory removeProductlistpricehistory(Productlistpricehistory productlistpricehistory) {
		getProductlistpricehistories().remove(productlistpricehistory);
		productlistpricehistory.setProduct(null);

		return productlistpricehistory;
	}

	public Productproductphoto removeProductproductphoto(Productproductphoto productproductphoto) {
		getProductproductphotos().remove(productproductphoto);
		productproductphoto.setProduct(null);

		return productproductphoto;
	}

	public Productreview removeProductreview(Productreview productreview) {
		getProductreviews().remove(productreview);
		productreview.setProduct(null);

		return productreview;
	}

	public Transactionhistory removeTransactionhistory(Transactionhistory transactionhistory) {
		getTransactionhistories().remove(transactionhistory);
		transactionhistory.setProduct(null);

		return transactionhistory;
	}

	public Workorder removeWorkorder(Workorder workorder) {
		getWorkorders().remove(workorder);
		workorder.setProduct(null);

		return workorder;
	}

	public void setBillofmaterials1(List<Billofmaterial> billofmaterials1) {
		this.billofmaterials1 = billofmaterials1;
	}

	public void setBillofmaterials2(List<Billofmaterial> billofmaterials2) {
		this.billofmaterials2 = billofmaterials2;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setDaystomanufacture(Integer daystomanufacture) {
		this.daystomanufacture = daystomanufacture;
	}

	public void setDiscontinueddate(Timestamp discontinueddate) {
		this.discontinueddate = discontinueddate;
	}

	public void setFinishedgoodsflag(String finishedgoodsflag) {
		this.finishedgoodsflag = finishedgoodsflag;
	}

	public void setListprice(BigDecimal listprice) {
		this.listprice = listprice;
	}

	public void setMakeflag(String makeflag) {
		this.makeflag = makeflag;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductcosthistories(List<Productcosthistory> productcosthistories) {
		this.productcosthistories = productcosthistories;
	}

	public void setProductdocuments(List<Productdocument> productdocuments) {
		this.productdocuments = productdocuments;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public void setProductinventories(List<Productinventory> productinventories) {
		this.productinventories = productinventories;
	}

	public void setProductline(String productline) {
		this.productline = productline;
	}

	public void setProductlistpricehistories(List<Productlistpricehistory> productlistpricehistories) {
		this.productlistpricehistories = productlistpricehistories;
	}

	public void setProductmodel(Productmodel productmodel) {
		this.productmodel = productmodel;
	}

	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}

	public void setProductproductphotos(List<Productproductphoto> productproductphotos) {
		this.productproductphotos = productproductphotos;
	}

	public void setProductreviews(List<Productreview> productreviews) {
		this.productreviews = productreviews;
	}

	public void setProductsubcategory(Productsubcategory productsubcategory) {
		this.productsubcategory = productsubcategory;
	}

	public void setReorderpoint(Integer reorderpoint) {
		this.reorderpoint = reorderpoint;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSafetystocklevel(Integer safetystocklevel) {
		this.safetystocklevel = safetystocklevel;
	}

	public void setSellenddate(Timestamp sellenddate) {
		this.sellenddate = sellenddate;
	}

	public void setSellstartdate(Timestamp sellstartdate) {
		this.sellstartdate = sellstartdate;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setStandardcost(BigDecimal standardcost) {
		this.standardcost = standardcost;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setTransactionhistories(List<Transactionhistory> transactionhistories) {
		this.transactionhistories = transactionhistories;
	}

	public void setUnitmeasure1(Unitmeasure unitmeasure1) {
		this.unitmeasure1 = unitmeasure1;
	}

	public void setUnitmeasure2(Unitmeasure unitmeasure2) {
		this.unitmeasure2 = unitmeasure2;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public void setWorkorders(List<Workorder> workorders) {
		this.workorders = workorders;
	}

}