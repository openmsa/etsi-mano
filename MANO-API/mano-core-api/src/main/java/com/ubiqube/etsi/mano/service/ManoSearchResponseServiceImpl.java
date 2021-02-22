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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.BadRequestException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.JsonBeanProperty;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;
import com.ubiqube.etsi.mano.json.MapperForView;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ManoSearchResponseServiceImpl implements ManoSearchResponseService {
	private final MapperFacade mapper;

	private final Set<String> officialParameters;

	private final JsonBeanUtil jsonBeanUtil;

	public ManoSearchResponseServiceImpl(final MapperFacade _mapper, final JsonBeanUtil _jsonBeanUtil) {
		mapper = _mapper;
		jsonBeanUtil = _jsonBeanUtil;
		officialParameters = new HashSet<>(Arrays.asList("fields", "exclude_fields", "exclude_default", "filter", "all_fields"));
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> parameters, final Class<?> clazz, @Nullable final String excludeDefaults, final Set<String> mandatoryFields, final List<?> list, final Class<U> target, final Consumer<U> makeLink) {
		final MultiValueMap<String, String> params = (parameters == null) ? new LinkedMultiValueMap<>() : parameters;
		checkParameters(params);
		final List<String> fields = params.get("fields");
		final List<String> excludeFields = params.get("excluse_fields");
		final boolean haveDefaultFields = params.containsKey("exclude_default");
		final boolean allFields = params.containsKey("all_fields");
		final List<U> vnfPkginfos = list.stream()
				.map(x -> mapper.map(x, target))
				.collect(Collectors.toList());
		vnfPkginfos.forEach(makeLink);

		final Set<String> fieldsSet = getFields(fields, mandatoryFields);
		checkAllFields(fieldsSet, clazz);

		Set<String> excluded = getExcludedFields(excludeFields);
		if (haveDefaultFields || (excluded.isEmpty() && fieldsSet.isEmpty() && !allFields)) {
			excluded = applyDefault(excludeDefaults);
		}
		final ObjectMapper mapperForQuery = MapperForView.getMapperForView(excluded, fieldsSet);

		String resp = null;
		try {
			resp = mapperForQuery.writeValueAsString(vnfPkginfos);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.ok(resp);

	}

	private void checkAllFields(final Set<String> fieldsSet, @NotNull final Class<?> clazz) {
		final Map<String, JsonBeanProperty> res = jsonBeanUtil.getPropertiesFromClass(clazz);
		fieldsSet.forEach(x -> {
			if (!res.containsKey(x) && !innerClass(x, clazz)) {
				throw new BadRequestException(x + " is not a valid field.");
			}
		});
	}

	private static boolean innerClass(final String x, final Class<?> clazz) {
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			final PropertyDescriptor[] propDescs = beanInfo.getPropertyDescriptors();
			return Arrays.stream(propDescs).anyMatch(y -> y.getName().equals(x));
		} catch (final IntrospectionException e) {
			throw new GenericException(e);
		}
	}

	private void checkParameters(@NotNull final MultiValueMap<String, String> parameters) {
		final HashSet<String> section = new HashSet<>(parameters.keySet());
		section.removeAll(officialParameters);
		if (!section.isEmpty()) {
			throw new BadRequestException("Bad query parameters: " + parameters);
		}
	}

	private static Set<String> applyDefault(final String excludeDefaults) {
		if (null == excludeDefaults) {
			return new HashSet<>();
		}
		return Arrays.stream(excludeDefaults.split(",")).collect(Collectors.toSet());
	}

	@NotNull
	private static Set<String> getExcludedFields(final List<String> excludeFields) {
		final Set<String> fieldsSet = new HashSet<>();
		if (null == excludeFields) {
			return fieldsSet;
		}
		return excludeFields.stream().flatMap(x -> Arrays.stream(x.split(","))).collect(Collectors.toSet());
	}

	@NotNull
	private static Set<String> getFields(final List<String> fields, final Set<String> mandatoryFields) {
		if (null == fields) {
			return new HashSet<>();
		}
		final Set<String> allFields = fields.stream().flatMap(x -> Arrays.stream(x.split(","))).collect(Collectors.toSet());
		allFields.addAll(mandatoryFields);
		return allFields;
	}

}
