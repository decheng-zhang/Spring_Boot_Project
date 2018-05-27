package com.lindedin.learning.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;

import com.lindedin.learning.converter.ReservationEntityToReservationResponseConverter;
import com.lindedin.learning.converter.ReservationRequestToReservationEntityConverter;
import com.lindedin.learning.converter.RoomEntityToReservationResponseConverter;

@Configuration
public class ConversionConfig {

	
	
	@Bean
	public ConversionService conversionService() {
		
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		return bean.getObject();
		
	}
	

	private Set<Converter> getConverters(){
		Set<Converter> converters=new HashSet<Converter>();
		
		
		
		converters.add(new RoomEntityToReservationResponseConverter());
		converters.add(new ReservationRequestToReservationEntityConverter());
		converters.add(new ReservationEntityToReservationResponseConverter());
		
		return converters;
	}
	

}
