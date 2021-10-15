package com.ubiqube.etsi.mano.service.event;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;

import com.ubiqube.etsi.mano.config.properties.ManoProperties;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthParamBasic;
import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.config.RemoteSubscription;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
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

	public Object registerVnfm(@NotNull final UUID objectId, @NotNull final Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object registerNfvo(@NotNull final UUID objectId, @NotNull final Map<String, Object> parameters) {
		Servers server = serversJpa.findById(objectId).orElseThrow();
		final FluxRest rest = new FluxRest(server);
		final List<RemoteSubscription> remoteSubscription = server.getRemoteSubscriptions();
		if (!isSubscribe(SubscriptionType.NSDVNF, remoteSubscription)) {
			final Subscription subscription = vnfPackageSubscribe(rest);
			final RemoteSubscription remote = reMap(subscription);
			remoteSubscription.add(remote);
			server = serversJpa.save(server);
		}
		return server;
	}

	private static RemoteSubscription reMap(final Subscription subscription) {
		return RemoteSubscription.builder()
				.remoteSubscriptionId(subscription.getId().toString())
				.subscriptionType(subscription.getSubscriptionType())
				.build();
	}

	private Subscription vnfPackageSubscribe(final FluxRest rest) {
		final Subscription subsOut = createSubscription(ApiTypesEnum.SOL003, "/vnfpkgm/v1/notification/onboarding", SubscriptionType.NSDVNF);
		final UriComponents uri = rest.uriBuilder().pathSegment("vnfpkgm/v1/subscriptions").build();
		final Class<?> clazz = httpGateway.get(0).getVnfPackageSubscriptionClass();
		final Class<?> clazzWire = httpGateway.get(0).getPkgmSubscriptionRequest();
		return postSubscription(rest, uri.toUri(), subsOut, clazzWire, clazz);
	}

	private Subscription createSubscription(final ApiTypesEnum apiType, final String url, final SubscriptionType subscriptionType) {
		final AuthentificationInformations auth = createAuthInformation();
		return Subscription.builder()
				.api(apiType)
				.authentication(auth)
				.callbackUri(manoProperties.getFrontendUrl() + url)
				.subscriptionType(subscriptionType)
				.build();
	}

	private Subscription postSubscription(final FluxRest rest, final URI uri, final Object subsOut, final Class<?> clazzWire, final Class<?> clazz) {
		final Object wire = mapper.map(subsOut, clazzWire);
		final Object res = rest.post(uri, wire, clazz);
		return mapper.map(res, Subscription.class);
	}

	private static boolean isSubscribe(final SubscriptionType subscriptionType, final List<RemoteSubscription> remoteSubscriptions) {
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
}
