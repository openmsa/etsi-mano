package com.ubiqube.etsi.mano.config;

import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.mapper.UuidConverter;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class OrikaConfiguration implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(final MapperFactory orikaMapperFactory) {
		final ConverterFactory converterFactory = orikaMapperFactory.getConverterFactory();
		converterFactory.registerConverter(new UuidConverter());
	}

}
