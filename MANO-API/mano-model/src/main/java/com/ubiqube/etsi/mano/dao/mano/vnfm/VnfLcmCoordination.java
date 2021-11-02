package com.ubiqube.etsi.mano.dao.mano.vnfm;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
public class VnfLcmCoordination {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String coordinationActionName;

	@Enumerated(EnumType.STRING)
	private LcmCoordResultTypeEnum coordinationResult;

	private OffsetDateTime startTime;

	private OffsetDateTime endTime;

	private OffsetDateTime delay;

	private EndpointType endpointType;
}
