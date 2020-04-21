package com.ubiqube.etsi.mano.service.pkg;

import com.ubiqube.parser.tosca.scalar.Time;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class TimeConverter extends BidirectionalConverter<Time, Long> {

	@Override
	public Long convertTo(final Time source, final Type<Long> destinationType, final MappingContext mappingContext) {
		return source.getValue().longValue();
	}

	@Override
	public Time convertFrom(final Long source, final Type<Time> destinationType, final MappingContext mappingContext) {
		return new Time(source);
	}

}
