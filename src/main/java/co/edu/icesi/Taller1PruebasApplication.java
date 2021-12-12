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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories
public class Taller1PruebasApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext c = SpringApplication.run(Taller1PruebasApplication.class, args);

		

	}

}
