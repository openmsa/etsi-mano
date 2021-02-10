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
package com.ubiqube.etsi.mano.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ManoSearchResponseService {
	private final MapperFacade mapper;

	public ManoSearchResponseService(final MapperFacade _mapper) {
		mapper = _mapper;
	}

	public <U> ResponseEntity<String> search(final String fields, final String excludeFields, final String excludeDefaults, final Set<String> mandatoryFields, final List<?> list, final Class<U> target, final Consumer<U> makeLink) {
		final List<U> vnfPkginfos = list.stream()
				.map(x -> mapper.map(x, target))
				.collect(Collectors.toList());
		vnfPkginfos.forEach(makeLink);

		final Set<String> fieldsSet = getFields(fields, mandatoryFields);

		final Set<String> excluded = getExcludedFields(excludeFields, excludeDefaults);
		final ObjectMapper mapperForQuery = MapperForView.getMapperForView(excluded, fieldsSet);

		String resp = null;
		try {
			resp = mapperForQuery.writeValueAsString(vnfPkginfos);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.ok(resp);

	}

	private static Set<String> getExcludedFields(final String excludeFields, final String excludeDefaults) {
		final Set<String> fieldsSet = new HashSet<>();
		if (null == excludeFields) {
			return fieldsSet;
		}
		Arrays.stream(excludeFields.split(",")).forEach(fieldsSet::add);
		if (null != excludeDefaults) {
			Arrays.stream(excludeDefaults.split(",")).forEach(fieldsSet::add);
		}
		return fieldsSet;
	}

	private static Set<String> getFields(final String fields, final Set<String> mandatoryFields) {
		Set<String> fieldsSet = new HashSet<>();
		if (null != fields) {
			fieldsSet = Arrays.stream(fields.split(",")).collect(Collectors.toSet());
			fieldsSet.addAll(mandatoryFields);
		}
		return fieldsSet;
	}
}
