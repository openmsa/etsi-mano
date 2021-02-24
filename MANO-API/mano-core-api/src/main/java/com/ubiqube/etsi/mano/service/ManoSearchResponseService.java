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

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface ManoSearchResponseService {

	<U> ResponseEntity<String> search(@Nullable final MultiValueMap<String, String> parameters, final Class<?> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final List<?> list, final Class<U> target, final Consumer<U> makeLink);
}
