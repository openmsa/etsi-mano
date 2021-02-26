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
package com.ubiqube.etsi.mano.controller.nsd;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;

public interface NsdController {

	<U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final Consumer<U> makeLink);

	void nsDescriptorsNsdInfoIdDelete(UUID id);

	NsdPackage nsDescriptorsNsdInfoIdGet(UUID id);

	byte[] nsDescriptorsNsdInfoIdNsdContentGet(UUID id);

	void nsDescriptorsNsdInfoIdNsdContentPut(UUID id, InputStream is);

	NsdPackage nsDescriptorsNsdInfoIdPatch(UUID id, String body, @Nullable String ifMatch);

	NsdPackage nsDescriptorsPost(Map<String, String> userDefinedData);

}