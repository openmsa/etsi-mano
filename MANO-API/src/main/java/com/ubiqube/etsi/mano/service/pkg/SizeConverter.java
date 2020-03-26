package com.ubiqube.etsi.mano.service.pkg;

import com.ubiqube.parser.tosca.scalar.Size;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class SizeConverter extends BidirectionalConverter<Size, Long> {

	@Override
	public Long convertTo(final Size source, final Type<Long> destinationType, final MappingContext mappingContext) {
		return Long.valueOf(source.getValue().longValue());
	}

	@Override
	public Size convertFrom(final Long source, final Type<Size> destinationType, final MappingContext mappingContext) {
		return new Size(source);
	}
}
