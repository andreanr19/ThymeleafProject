package co.edu.icesi.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.businessdelegate.BusinessDelegate;
import co.edu.icesi.frontmodel.Productcategory;
import lombok.extern.log4j.Log4j2;

@WritingConverter
@Component
@Log4j2
public class StringToProductcategoryConverter implements Converter<String,Productcategory>{

	@Autowired
	private BusinessDelegate bd;
	
	@Override
	public Productcategory convert(String productcategoryid) {
		log.info("sjdkcdljfdkljkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		return bd.findByProductcategoryId(Integer.parseInt(productcategoryid));
	}
}
