package co.edu.icesi.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.businessdelegate.BusinessDelegate;
import co.edu.icesi.frontmodel.Unitmeasure;

@WritingConverter
@Component
public class StringToUnitmeasureConverter implements Converter<String, Unitmeasure>{

	@Autowired 
	private BusinessDelegate bd;
	@Override
	public Unitmeasure convert(String unitmeasurecode) {
		return bd.findByUnitmeasureId(Long.parseLong(unitmeasurecode));
	}
	


}
