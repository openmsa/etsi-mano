package com.ubiqube.etsi.mano.mapper;

import java.util.UUID;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class UuidConverter extends BidirectionalConverter<String, UUID> {

	@Override
	public UUID convertTo(final String source, final Type<UUID> _destinationType, final MappingContext mappingContext) {
		return UUID.fromString(source);
	}

	@Override
	public String convertFrom(final UUID source, final Type<String> _destinationType, final MappingContext mappingContext) {
		return source.toString();
	}

}
