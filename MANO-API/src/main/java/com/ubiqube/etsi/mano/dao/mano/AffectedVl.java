package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class AffectedVl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id = null;

	private final String virtualLinkDescId = null;

	private final ChangeType changeType = null;

	@Embedded
	private final ResourceHandleEntity networkResource = null;

	@Transient
	private final Map<String, String> metadata = new HashMap<>();

}
