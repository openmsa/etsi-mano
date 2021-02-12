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
package com.ubiqube.etsi.mano.json;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class MapperForView {
	private MapperForView() {
		// Nothing.
	}

	public static ObjectMapper getMapperForView(@Nullable final Set<String> exclude, @Nullable final Set<String> fields) {
		final ObjectMapper mapper = getMapperInstance();
		if ((null != exclude) && !exclude.isEmpty()) {
			final List<ViewHolder> excludeList = buildViewList(exclude);
			mapper.registerModule(new SimpleModule() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setupModule(final SetupContext context) {
					super.setupModule(context);
					context.addBeanSerializerModifier(new ExclusionSerializer(excludeList));
				}
			});
		} else if ((null != fields) && !fields.isEmpty()) {
			mapper.registerModule(new SimpleModule() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setupModule(final SetupContext context) {
					super.setupModule(context);
					context.addBeanSerializerModifier(new WantedSerializer(fields));
				}
			});
		}
		return mapper;
	}

	@Nonnull
	private static List<ViewHolder> buildViewList(@Nonnull final Set<String> exclude) {
		return exclude.stream()
				.map(ViewHolder::new)
				.collect(Collectors.toList());
	}

	private static ObjectMapper getMapperInstance() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.registerModule(new JavaTimeModule());
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
		return mapper;
	}
}
