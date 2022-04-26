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
package com.ubiqube.etsi.mano.vnfm.fc.vnflcm;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;

public interface VnfInstanceGenericFrontController {

	ResponseEntity<Void> terminate(@NotNull UUID vnfInstanceId, CancelModeTypeEnum cancelMode, Integer timeout, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<Void> scaleToLevel(@NotNull UUID vnfInstanceId, U body, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<Void> scale(@NotNull UUID vnfInstanceId, U body, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<Void> snapshot(@NotNull UUID vnfInstanceId, U body);

	<V> ResponseEntity<V> modify(@NotNull UUID vnfInstanceId, String body, String ifMatch, Function<VnfInstance, String> getSelfLink);

	<U> ResponseEntity<Void> operate(@NotNull UUID vnfInstanceId, U body, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<Void> instantiate(@NotNull UUID vnfInstanceId, U body, Function<VnfBlueprint, String> getSelfLink);

	ResponseEntity<Void> heal(@NotNull UUID vnfInstanceId, String cause, Map<String, String> hashMap);

	<U> ResponseEntity<U> findById(@NotNull UUID vnfInstanceId, Class<U> clazz, Consumer<U> makeLink, String instanceSelfLink);

	ResponseEntity<Void> deleteById(@NotNull UUID vnfInstanceId);

	<U> ResponseEntity<Void> createSnapshot(@NotNull UUID vnfInstanceId, U object, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<Void> changeVnfPkg(@NotNull UUID vnfInstanceId, U object, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<Void> changeFlavour(@NotNull UUID vnfInstanceId, U object, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<Void> changeExtConn(@NotNull UUID vnfInstanceId, U object, Function<VnfBlueprint, String> getSelfLink);

	<U> ResponseEntity<U> create(@NotNull String vnfdId, String vnfInstanceName, String vnfInstanceDescription, Class<U> clazz, Consumer<U> makeLink, String selfLink);

	<U> ResponseEntity<String> search(MultiValueMap<String, String> requestParams, Class<U> clazz, String nextpageOpaqueMarker, Consumer<U> makeLink);

}