package com.ubiqube.etsi.mano.vnfm.service;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantInterface;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.HttpGateway;
import com.ubiqube.etsi.mano.service.ServerService;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmGrantManagementImpl implements GrantManagement {
	private static final Pattern UUID_REGEXP = Pattern.compile("(?<uuid>[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12})$");
	private final MapperFacade mapper;
	private final ServerService serverService;

	public VnfmGrantManagementImpl(final MapperFacade mapper, final ServerService serverService) {
		this.mapper = mapper;
		this.serverService = serverService;
	}

	@Override
	public GrantResponse get(final UUID grantId) {
		final ServerAdapter server = serverService.findNearestServer();
		final Map<String, Object> uriVariables = Map.of("grantId", grantId);
		final URI uri = server.getUriFor(ApiVersionType.SOL003_GRANT, "grants/{grantId}", uriVariables);
		final HttpGateway httpGateway = server.httpGateway();
		final ResponseEntity<?> resp = server.rest().getWithReturn(uri, httpGateway.getGrantResponse());
		GrantResponse grants = new GrantResponse();
		if (resp.getStatusCodeValue() == 202) {
			grants.setId(grantId);
			grants.setAvailable(Boolean.FALSE);
		} else {
			grants = mapper.map(resp.getBody(), GrantResponse.class);
		}
		return grants;
	}

	@Override
	public GrantResponse post(final GrantInterface grant) {
		final ServerAdapter server = serverService.findNearestServer();
		final URI uri = server.getUriFor(ApiVersionType.SOL003_GRANT, "grants/", Map.of());
		final Object manoGrant = mapper.map(grant, server.httpGateway().getGrantRequest());
		server.httpGateway().makeGrantLinks(manoGrant);
		final ResponseEntity<?> resp = server.rest().postWithReturn(uri, manoGrant, server.httpGateway().getGrantResponse());
		if (resp.getStatusCodeValue() == 201) {
			return handleLocation(resp);
		}
		return mapper.map(resp.getBody(), GrantResponse.class);
	}

	private static GrantResponse handleLocation(final ResponseEntity<?> resp) {
		final Optional<List<String>> loc = Optional.ofNullable(resp.getHeaders().get("Location"));
		if (loc.isPresent()) {
			final Matcher m = UUID_REGEXP.matcher(loc.get().get(0));
			m.find();
			final String uuid = m.group("uuid");
			final GrantResponse grants = new GrantResponse();
			grants.setId(UUID.fromString(uuid));
			grants.setAvailable(Boolean.FALSE);
			return grants;
		}
		throw new GenericException("Grant post received a ACCEPTED response with no Location header");
	}
}
