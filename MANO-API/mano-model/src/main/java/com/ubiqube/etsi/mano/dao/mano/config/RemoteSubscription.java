package com.ubiqube.etsi.mano.dao.mano.config;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Getter
@Setter
public class RemoteSubscription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private SubscriptionType subscriptionType;

	private String remoteSubscriptionId;
}
