package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.search.annotations.FieldBridge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.KeyValuePairs;
import com.ubiqube.etsi.mano.model.nslcm.ChangeTypeEnum;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

public class LcmAffectedVnfc {
	@Id
	private final String id = null;
	// Probably a vdu instance.
	private final String vduId = null;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private final ChangeTypeEnum changeType = null;

	private final VimResource computeResource = null;

	@JsonProperty("metadata")
	private final KeyValuePairs metadata = null;
// Those are also instances.
	private final List<String> affectedVnfcCpIds = null;

	private final List<String> addedStorageResourceIds = null;

	private final List<String> removedStorageResourceIds = null;

}
