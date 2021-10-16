package com.ubiqube.etsi.mano.dao.mano.config;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Servers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private AuthentificationInformations authentification;

	private String url;

	private boolean ignoreSsl;

	@Lob
	private String tlsCert;

	private String version;

	@Enumerated(EnumType.STRING)
	private PlanStatusType serverStatus;

	private FailureDetails failureDetails;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RemoteSubscription> remoteSubscriptions;
}
