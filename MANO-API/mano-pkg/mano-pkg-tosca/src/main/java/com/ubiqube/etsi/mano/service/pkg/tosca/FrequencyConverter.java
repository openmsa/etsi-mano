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
package com.ubiqube.etsi.mano.service.pkg.tosca;

import com.ubiqube.parser.tosca.scalar.Frequency;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class FrequencyConverter extends BidirectionalConverter<Frequency, Double> {

	@Override
	public Double convertTo(final Frequency source, final Type<Double> destinationType, final MappingContext mappingContext) {
		return source.getValue();
	}

	@Override
	public Frequency convertFrom(final Double source, final Type<Frequency> destinationType, final MappingContext mappingContext) {
		return new Frequency(source + " mhz");
	}

}
