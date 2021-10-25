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
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.SubscriptionFrontController;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @deprecated Use {@link SubscriptionFrontController}
 */
@Deprecated
public interface SubscriptionServiceV2 {

	<U> List<U> query(MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> setLink, SubscriptionType subscriptionType);

	<U> U create(Object subscriptionRequest, Class<U> clazz, Consumer<U> setLink, final SubscriptionType type);

	void deleteById(UUID id, final SubscriptionType type);

	<U> U findById(UUID id, Class<U> clazz, final Consumer<U> setLink, final SubscriptionType type);

}
