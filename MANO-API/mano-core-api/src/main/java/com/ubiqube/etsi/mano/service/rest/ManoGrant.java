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
package com.ubiqube.etsi.mano.service.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.dao.mano.GrantInterface;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.HttpGateway;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManoGrant {

	private static final Logger LOG = LoggerFactory.getLogger(ManoGrant.class);
	private static final Pattern UUID_REGEXP = Pattern.compile("(?<uuid>[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})$");

	private final ManoClient client;
	private UUID id;

	public ManoGrant(final ManoClient manoClient) {
		this.client = manoClient;
		client.setFragment("grants");
		manoClient.setQueryType(ApiVersionType.SOL003_GRANT);
	}

	public ManoGrant(final ManoClient manoClient, final UUID id) {
		this.client = manoClient;
		client.setObjectId(id);
		client.setFragment("grants/{id}");
		manoClient.setQueryType(ApiVersionType.SOL003_GRANT);
		this.id = id;
	}

	public GrantResponse find() {
		final ResponseEntity<?> resp = client.createQuery()
				.setWireOutClass(HttpGateway::getGrantResponse)
				.getRaw();
		return buildResponse(resp, id);
	}

	public GrantResponse create(final GrantInterface grant) {
		final Function<HttpGateway, Object> request = (final HttpGateway httpGateway) -> httpGateway.createGrantRequest(grant);
		final ResponseEntity<?> resp = client.createQuery(request)
				.setWireInClass(HttpGateway::getGrantRequest)
				.setWireOutClass(HttpGateway::getGrantResponse)
				.setOutClass(GrantResponse.class)
				.postRaw();
		GrantResponse respCreate = handleLocation(resp);
		final ManoGrant manoId = new ManoGrant(client, respCreate.getId());
		while (Boolean.FALSE.equals(respCreate.getAvailable())) {
			try {
				Thread.sleep(1000);
			} catch (final InterruptedException e) {
				LOG.debug("", e);
				Thread.currentThread().interrupt();
			}
			respCreate = manoId.find();
		}
		return respCreate;
	}

	private static GrantResponse handleLocation(final ResponseEntity<?> resp) {
		final Optional<List<String>> loc = Optional.ofNullable(resp.getHeaders().get("Location"));
		if (!loc.isPresent()) {
			throw new GenericException("Grant post received a ACCEPTED response with no Location header");
		}
		final Matcher m = UUID_REGEXP.matcher(loc.get().get(0));
		m.find();
		final String uuid = m.group("uuid");
		final GrantResponse grants = new GrantResponse();
		grants.setId(UUID.fromString(uuid));
		grants.setAvailable(Boolean.FALSE);
		return grants;
	}

	private GrantResponse buildResponse(final ResponseEntity<?> resp, final UUID grantId) {
		GrantResponse grants = new GrantResponse();
		if (resp.getStatusCodeValue() == 202) {
			grants.setId(grantId);
			grants.setAvailable(Boolean.FALSE);
		} else {
			grants = client.getMapper().map(resp.getBody(), GrantResponse.class);
			grants.setAvailable(true);
		}
		return grants;
	}

}
