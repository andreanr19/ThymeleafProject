package co.edu.icesi.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.businessdelegate.BusinessDelegate;
import co.edu.icesi.frontmodel.Productsubcategory;
import lombok.extern.log4j.Log4j2;

@WritingConverter
@Component
@Log4j2
public class StringToProductsubcategoryConverter implements Converter<String, Productsubcategory>{
	
	@Autowired
	private BusinessDelegate bd;
	
	@Override
	public Productsubcategory convert(String productsubcategoryid) {
		log.info("hjffkfadlsjkljkjkjkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
		return bd.findByProductsubcategoryId(Integer.parseInt(productsubcategoryid));
	}

}
