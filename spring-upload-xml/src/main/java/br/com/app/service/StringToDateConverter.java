package br.com.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class StringToDateConverter extends CustomConverter<String, Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

	@Override
	public Date convert(String source, Type<? extends Date> destinationType, MappingContext context) {
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Erro ao converter String em Date", e);
		}
	}

}
