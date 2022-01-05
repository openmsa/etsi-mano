/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.mapper;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class UuidConverter extends BidirectionalConverter<String, UUID> {

	private static final Logger LOG = LoggerFactory.getLogger(UuidConverter.class);

	@Override
	public UUID convertTo(final String source, final Type<UUID> dt, final MappingContext mappingContext) {
		try {
			return UUID.fromString(source);
		} catch (final RuntimeException e) {
			LOG.error("Just break here.");
			throw e;
		}
	}

	@Override
	public String convertFrom(final UUID source, final Type<String> dt, final MappingContext mappingContext) {
		return source.toString();
	}

}
