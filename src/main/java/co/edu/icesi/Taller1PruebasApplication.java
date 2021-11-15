package co.edu.icesi;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.model.Businessentity;
import co.edu.icesi.model.Document;
import co.edu.icesi.model.Product;
import co.edu.icesi.model.Productcategory;
import co.edu.icesi.model.Productdocument;
import co.edu.icesi.model.ProductdocumentPK;
import co.edu.icesi.model.Productsubcategory;
import co.edu.icesi.model.Productvendor;
import co.edu.icesi.model.Transactionhistory;
import co.edu.icesi.model.Unitmeasure;
import co.edu.icesi.model.Vendor;
import co.edu.icesi.repositories.ProductVendorRepositoryInterface;
import co.edu.icesi.services.BusinessEntityService;
import co.edu.icesi.services.BusinessEntityServiceImpl;
import co.edu.icesi.services.DocumentService;
import co.edu.icesi.services.DocumentServiceImpl;
import co.edu.icesi.services.ProductCategoryService;
import co.edu.icesi.services.ProductCategoryServiceImpl;
import co.edu.icesi.services.ProductService;
import co.edu.icesi.services.ProductServiceImpl;
import co.edu.icesi.services.ProductSubcategoryService;
import co.edu.icesi.services.ProductSubcategoryServiceImpl;
import co.edu.icesi.services.ProductVendorService;
import co.edu.icesi.services.ProductVendorServiceImpl;
import co.edu.icesi.services.ProductdocumentService;
import co.edu.icesi.services.ProductdocumentServiceImpl;
import co.edu.icesi.services.TransactionHistoryService;
import co.edu.icesi.services.TransactionHistoryServiceImpl;
import co.edu.icesi.services.UnitMeasureService;
import co.edu.icesi.services.UnitMeasureServiceImpl;
import co.edu.icesi.services.UserService;
import co.edu.icesi.services.UserServiceImpl;
import co.edu.icesi.services.VendorService;
import co.edu.icesi.services.VendorServiceImpl;
import co.edu.icesi.users.UserEntity;
import co.edu.icesi.users.Usertype;

@SpringBootApplication
@ComponentScan(basePackages = { "co.edu.icesi" })
public class Taller1PruebasApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext c = SpringApplication.run(Taller1PruebasApplication.class, args);

		UserService u = c.getBean(UserServiceImpl.class);
		UserEntity user1 = new UserEntity();
		user1.setUsername("charles777");
		user1.setPassword("{noop}password1");

		user1.setType(Usertype.ADMINISTRATOR);

		u.save(user1);

		UserEntity user2 = new UserEntity();
		user2.setUsername("Andrea");
		user2.setPassword("{noop}password2");

		user2.setType(Usertype.OPERATOR);
		u.save(user2);

		// Init product subcategory
		ProductSubcategoryService ps = c.getBean(ProductSubcategoryServiceImpl.class);
		Productsubcategory ps1 = new Productsubcategory();
		ps1.setName("Transport");
		ps1 = ps.save(ps1);

		// Init product category
		ProductCategoryService pc = c.getBean(ProductCategoryServiceImpl.class);
		Productcategory pc1 = new Productcategory();
		pc1.setName("Transport");
		pc1 = pc.save(pc1);

		// Init unit measure
		UnitMeasureService um = c.getBean(UnitMeasureServiceImpl.class);
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("Kilometers");
		um1 = um.save(um1);

		// Init product1
		Product product1 = new Product();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
		Date date2;
		try {
			date1 = df.parse("23/09/2020");
			date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time1);
			Timestamp sellend = new Timestamp(time2);
			product1.setSellstartdate(sellstart);
			product1.setSellenddate(sellend);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		product1.setClass_("Bus");
		product1.setName("Bus");
		product1.setProductnumber("2");
		product1.setDaystomanufacture(4);

		// Init product2

		Product product2 = new Product();
		product2.setClass_("Car");
		product2.setName("Car");
		product2.setColor("Red");
		product2.setProductnumber("2");
		product2.setDaystomanufacture(4);
		try {
			date1 = df.parse("23/09/2020");
			date2 = df.parse("23/10/2020");
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			Timestamp sellstart = new Timestamp(time1);
			Timestamp sellend = new Timestamp(time2);
			product2.setSellstartdate(sellstart);
			product2.setSellenddate(sellend);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ProductService p = c.getBean(ProductServiceImpl.class);
		p.saveCorrect(product1, pc1.getProductcategoryid(), ps1.getProductsubcategoryid(), um1.getUnitmeasurecode());
		p.saveCorrect(product2, pc1.getProductcategoryid(), ps1.getProductsubcategoryid(), um1.getUnitmeasurecode());

		// Init transaction history
		Transactionhistory th1 = new Transactionhistory();
		th1.setProduct(product1);
		th1.setQuantity(10);
		th1.setActualcost(new BigDecimal("5"));

		TransactionHistoryService ths = c.getBean(TransactionHistoryServiceImpl.class);
		ths.saveCorrect(th1, product1.getProductid());

		// Init Document

		DocumentService ds = c.getBean(DocumentServiceImpl.class);
		Document d1 = new Document();
		d1.setFilename("Andrea!!");
		d1.setFileextension("Morgan!");
		ds.saveCorrect(d1, product1.getProductid());

		// Init businessentity
		BusinessEntityService be = c.getBean(BusinessEntityServiceImpl.class);
		Businessentity b1 = new Businessentity();
		be.save(b1);
		Businessentity b2 = new Businessentity();
		be.save(b2);
		Businessentity b3 = new Businessentity();
		be.save(b3);

		// init vendor
		VendorService vs = c.getBean(VendorServiceImpl.class);
		Vendor v1 = new Vendor();
		v1.setName("Snow");
		v1.setBusinessentityid(b1.getBusinessentityid());
		vs.save(v1);
		Vendor v2 = new Vendor();
		v2.setName("Max");
		v2.setBusinessentityid(b2.getBusinessentityid());
		vs.save(v2);
		Vendor v3 = new Vendor();
		v3.setName("Lucy");
		v3.setBusinessentityid(b3.getBusinessentityid());
		vs.save(v3);

		// Init Product Vendor
		ProductVendorService pvs = c.getBean(ProductVendorServiceImpl.class);
		Productvendor pv1 = new Productvendor();
		pv1.setId(1);
		pv1.setMaxorderqty(1000);
		pv1.setMinorderqty(500);
		pv1.setStandardprice(new BigDecimal("1000"));
		pvs.save(pv1, um1.getUnitmeasurecode(), product1.getProductid(), v1.getBusinessentityid());

		Iterable<Productvendor> prv = pvs.findAll();
		System.out.println(prv.iterator().hasNext());

	}

}
