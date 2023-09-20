package br.com.app.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class MapperFacadeConfiguration {

	private MapperFactory mapperFactory;

	@Bean
	public MapperFacade mapperFacade() {
		mapperFactory = new DefaultMapperFactory.Builder().build();

		mapperFactory.getConverterFactory().registerConverter(new StringToDateConverter());

		return mapperFactory.getMapperFacade();
	}

}
