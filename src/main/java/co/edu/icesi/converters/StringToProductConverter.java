package co.edu.icesi.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.businessdelegate.BusinessDelegate;
import co.edu.icesi.frontmodel.Product;
import lombok.extern.log4j.Log4j2;

@WritingConverter
@Component
@Log4j2
public class StringToProductConverter implements Converter<String, Product>{
	
	@Autowired
	private BusinessDelegate bd;
	
	@Override
	public Product convert(String productid) {
		log.info("hdjhsjhsjhshhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhkjkdsh");
		return bd.findProductById(Integer.parseInt(productid));
	}

}
