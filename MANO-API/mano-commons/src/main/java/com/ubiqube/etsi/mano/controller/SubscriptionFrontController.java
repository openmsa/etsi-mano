package com.ubiqube.etsi.mano.controller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface SubscriptionFrontController {
	<U> ResponseEntity<List<U>> search(MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLinks, SubscriptionType type);

	<U> ResponseEntity<U> create(Object subscriptionRequest, Class<U> clazz, Consumer<U> makeLinks, Function<U, String> getSelfLink, SubscriptionType type);

	ResponseEntity<Void> deleteById(@NotNull String subscriptionId, SubscriptionType type);

	<U> ResponseEntity<U> findById(String subscriptionId, Class<U> clazz, Consumer<U> makeLinks, SubscriptionType type);

}
