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
package com.ubiqube.etsi.mano.service.event;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;

import com.ubiqube.etsi.mano.config.properties.ManoProperties;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthParamBasic;
import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.FilterAttributes;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersion;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.config.RemoteSubscription;
import com.ubiqube.etsi.mano.dao.mano.config.ServerType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
import com.ubiqube.etsi.mano.model.ApiVersionInformation;
import com.ubiqube.etsi.mano.service.HttpGateway;
import com.ubiqube.etsi.mano.service.rest.FluxRest;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class CommonActionController {

	private static final Logger LOG = LoggerFactory.getLogger(CommonActionController.class);
	private static final List<String> VNFM_FRAGMENT = Arrays.asList("vnflcm", "vnfpm", "vnffm", "vnfind", "vrqan", "vnfsnapshotpkgm");
	private static final List<String> NFVO_FRAGMENT = Arrays.asList("grant", "vnfpkgm", "nsd", "nslcm", "nspm", "nsfm", "nfvici", "vnfsnapshotpkgm", "lcmcoord");

	private final ServersJpa serversJpa;
	private final Environment env;
	private final List<HttpGateway> httpGateway;
	private final MapperFacade mapper;
	private final ManoProperties manoProperties;

	public CommonActionController(final ServersJpa serversJpa, final Environment env, final List<com.ubiqube.etsi.mano.service.HttpGateway> httpGateway, final MapperFacade mapper, final ManoProperties manoProperties) {
		super();
		this.serversJpa = serversJpa;
		this.env = env;
		this.httpGateway = httpGateway;
		this.mapper = mapper;
		this.manoProperties = manoProperties;
	}

	public Object registerServer(@NotNull final UUID objectId, @NotNull final Map<String, Object> parameters) {
		final Servers server = serversJpa.findById(objectId).orElseThrow(() -> new GenericException("Could not find server: " + objectId));
		if (server.getServerType() == ServerType.NFVO) {
			LOG.debug("Registrating an NFVO.");
			return register(server, this::registerNfvoEx, parameters);
		}
		LOG.debug("Registrating an VNFM.");
		return register(server, this::registerVnfmEx, parameters);
	}

	public Servers register(@NotNull final Servers server, final BiFunction<Servers, Map<String, Object>, Servers> func, @NotNull final Map<String, Object> parameters) {
		try {
			final Servers res = func.apply(server, parameters);
			res.setFailureDetails(null);
			res.setServerStatus(PlanStatusType.SUCCESS);
			return serversJpa.save(res);
		} catch (final RuntimeException e) {
			LOG.error("", e);
			server.setFailureDetails(new FailureDetails(500, e.getMessage()));
			server.setServerStatus(PlanStatusType.FAILED);
			return serversJpa.save(server);
		}
	}

	private Servers registerVnfmEx(final Servers server, final Map<String, Object> parameters) {
		extractVersions(server);
		server.setServerStatus(PlanStatusType.SUCCESS);
		return serversJpa.save(server);
	}

	private void extractVersions(final Servers server) {
		if (server.getServerType() == ServerType.VNFM) {
			extratVersion(VNFM_FRAGMENT, server);
		} else {
			extratVersion(NFVO_FRAGMENT, server);
		}
	}

	private void extratVersion(final List<String> fragments, final Servers server) {
		final Set<ApiVersion> versions = new LinkedHashSet<>();
		server.setVersions(versions);
		fragments.forEach(x -> Optional.ofNullable(getVersion(x, server)).ifPresent(versions::add));
	}

	private ApiVersion getVersion(final String fragment, final Servers server) {
		try {
			final Map<String, Object> uriVariables = Map.of("fragment", fragment);
			final FluxRest rest = new FluxRest(server);
			final UriComponents uri = rest.uriBuilder().pathSegment("{fragment}/api_versions")
					.buildAndExpand(uriVariables);
			final ApiVersionInformation res = rest.get(uri.toUri(), ApiVersionInformation.class);
			return mapper.map(res, ApiVersion.class);
		} catch (final RuntimeException e) {
			LOG.info("Error fetching " + fragment, e);
		}
		return null;
	}

	private Servers registerNfvoEx(@NotNull final Servers server, @NotNull final Map<String, Object> parameters) {
		final FluxRest rest = new FluxRest(server);
		final Set<RemoteSubscription> remoteSubscription = server.getRemoteSubscriptions();
		if (!isSubscribe(SubscriptionType.NSDVNF, remoteSubscription)) {
			addSubscription(rest, server, this::vnfPackageOnboardingSubscribe, remoteSubscription);
			addSubscription(rest, server, this::vnfPackageChangeSubscribe, remoteSubscription);
			extractEndpoint(server);
			return serversJpa.save(server);
		}
		return server;
	}

	private static void addSubscription(final FluxRest rest, final Servers server, final Function<FluxRest, Subscription> func, final Set<RemoteSubscription> remoteSubscription) {
		final Subscription subs = func.apply(rest);
		final RemoteSubscription rmt = reMap(subs, server);
		remoteSubscription.add(rmt);
	}

	private void extractEndpoint(final Servers server) {
		final String prefix = convert(server.getSubscriptionType());
		final List<ApiVersionType> arr = getEnum(prefix);
		for (final ApiVersionType element : arr) {
			final ApiVersion version = getVersion(server, element);
			server.addVersion(version);
		}
	}

	private static String convert(final SubscriptionType subscriptionType) {
		switch (subscriptionType) {
		case VNF:
			return "SOL_003";
		case NSD:
			return "SOL_005";
		default:
			throw new IllegalArgumentException("Unexpected value: " + subscriptionType);
		}
	}

	private static RemoteSubscription reMap(final Subscription subscription, final Servers server) {
		return RemoteSubscription.builder()
				.remoteSubscriptionId(subscription.getId().toString())
				.subscriptionType(server.getSubscriptionType())
				.remoteServerId(server.getId())
				.build();
	}

	private Subscription vnfPackageOnboardingSubscribe(final FluxRest rest) {
		final List<FilterAttributes> filters = List.of(FilterAttributes.of("notificationTypes[0]", "VnfPackageOnboardingNotification"));
		final Subscription subsOut = createSubscriptionWithFilter(ApiTypesEnum.SOL003, "/vnfpkgm/v1/notification/onboarding", SubscriptionType.NSDVNF, filters);
		final UriComponents uri = rest.uriBuilder().pathSegment("vnfpkgm/v1/subscriptions").build();
		final Class<?> clazz = httpGateway.get(0).getVnfPackageSubscriptionClass();
		final Class<?> clazzWire = httpGateway.get(0).getPkgmSubscriptionRequest();
		return postSubscription(rest, uri.toUri(), subsOut, clazzWire, clazz);
	}

	private Subscription vnfPackageChangeSubscribe(final FluxRest rest) {
		final List<FilterAttributes> filters = List.of(FilterAttributes.of("notificationTypes[0]", "VnfPackageChangeNotification"));
		final Subscription subsOut = createSubscriptionWithFilter(ApiTypesEnum.SOL003, "/vnfpkgm/v1/notification/change", SubscriptionType.NSDVNF, filters);
		final UriComponents uri = rest.uriBuilder().pathSegment("vnfpkgm/v1/subscriptions").build();
		final Class<?> clazz = httpGateway.get(0).getVnfPackageSubscriptionClass();
		final Class<?> clazzWire = httpGateway.get(0).getPkgmSubscriptionRequest();
		return postSubscription(rest, uri.toUri(), subsOut, clazzWire, clazz);
	}

	private Subscription createSubscriptionWithFilter(final ApiTypesEnum apiType, final String url, final SubscriptionType subscriptionType, final List<FilterAttributes> filters) {
		final AuthentificationInformations auth = createAuthInformation();
		return Subscription.builder()
				.api(apiType)
				.authentication(auth)
				.callbackUri(manoProperties.getFrontendUrl() + url)
				.subscriptionType(subscriptionType)
				.filters(filters)
				.build();
	}

	private Subscription postSubscription(final FluxRest rest, final URI uri, final Object subsOut, final Class<?> clazzWire, final Class<?> clazz) {
		final Object wire = mapper.map(subsOut, clazzWire);
		final Object res = rest.post(uri, wire, clazz);
		return mapper.map(res, Subscription.class);
	}

	private static boolean isSubscribe(final SubscriptionType subscriptionType, final Set<RemoteSubscription> remoteSubscriptions) {
		final Optional<RemoteSubscription> res = remoteSubscriptions.stream().filter(x -> x.getSubscriptionType() == subscriptionType).findFirst();
		return res.isPresent();
	}

	private AuthentificationInformations createAuthInformation() {
		final AuthentificationInformations auth = new AuthentificationInformations();
		if (null != env.getProperty("keycloak.resource")) {
			final AuthParamOauth2 oauth2 = AuthParamOauth2.builder()
					.clientId(env.getProperty("keycloak.resource"))
					.clientSecret(env.getProperty("keycloak.credentials.secret"))
					.tokenEndpoint(env.getProperty("mano.swagger-o-auth2"))
					.build();
			auth.setAuthParamOath2(oauth2);
			auth.setAuthType(List.of(AuthType.OAUTH2_CLIENT_CREDENTIALS));
		} else {
			final AuthParamBasic basic = AuthParamBasic.builder()
					.userName("nfvo")
					.password(UUID.randomUUID().toString())
					.build();
			auth.setAuthParamBasic(basic);
			auth.setAuthType(List.of(AuthType.BASIC));
		}
		return auth;
	}

	static List<ApiVersionType> getEnum(final String prefix) {
		final ApiVersionType[] arr = ApiVersionType.values();
		return Arrays.stream(arr).filter(x -> x.name().startsWith(prefix)).toList();
	}

	private ApiVersion getVersion(final Servers server, final ApiVersionType type) {
		final Map<String, Object> uriVariables = Map.of("module", type.toString());
		final FluxRest rest = new FluxRest(server);
		final UriComponents uri = rest.uriBuilder().pathSegment("{module}/api_versions")
				.buildAndExpand(uriVariables);
		final ApiVersionInformation res = rest.get(uri.toUri(), ApiVersionInformation.class);
		return mapper.map(res, ApiVersion.class);
	}

}
