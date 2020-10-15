package com.ubiqube.etsi.mano.mapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * TODO: I'm pretty sure that's allready exist in Orika.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OffsetDateTimeToDateConverter extends BidirectionalConverter<OffsetDateTime, Date> {

	@Override
	public Date convertTo(final OffsetDateTime source, final Type<Date> _destinationType, final MappingContext mappingContext) {
		return Date.from(source.toInstant());
	}

	@Override
	public OffsetDateTime convertFrom(final Date source, final Type<OffsetDateTime> _destinationType, final MappingContext mappingContext) {
		return OffsetDateTime.from(source.toInstant().atOffset(ZoneOffset.UTC));
	}

}
