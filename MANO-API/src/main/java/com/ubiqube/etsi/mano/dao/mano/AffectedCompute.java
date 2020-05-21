package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.FieldBridge;

import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
public class AffectedCompute implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private UUID vduId = null;

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private ChangeType changeType = null;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private VnfInstantiedCompute vnfInstantiedCompute;

	@ManyToOne
	private VnfCompute vnfCompute;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata = new HashMap<>();

	/**
	 * XXX Should be computed.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> affectedVnfcCpIds = null;

	/**
	 * XXX Should be computed.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> addedStorageResourceIds = null;

	/**
	 * XXX Should be computed. This is a vien of current
	 * VnfStorage.changeType==Removed.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> removedStorageResourceIds = null;

	@ManyToOne
	private VnfLcmOpOccs vnfLcmOpOccs;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public UUID getVduId() {
		return vduId;
	}

	public void setVduId(final UUID vduId) {
		this.vduId = vduId;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeType changeType) {
		this.changeType = changeType;
	}

	public VnfInstantiedCompute getVnfInstantiedCompute() {
		return vnfInstantiedCompute;
	}

	public void setVnfInstantiedCompute(final VnfInstantiedCompute vnfInstantiedCompute) {
		this.vnfInstantiedCompute = vnfInstantiedCompute;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Set<String> getAffectedVnfcCpIds() {
		return affectedVnfcCpIds;
	}

	public void setAffectedVnfcCpIds(final Set<String> affectedVnfcCpIds) {
		this.affectedVnfcCpIds = affectedVnfcCpIds;
	}

	public Set<String> getAddedStorageResourceIds() {
		return addedStorageResourceIds;
	}

	public void setAddedStorageResourceIds(final Set<String> addedStorageResourceIds) {
		this.addedStorageResourceIds = addedStorageResourceIds;
	}

	public Set<String> getRemovedStorageResourceIds() {
		return removedStorageResourceIds;
	}

	public void setRemovedStorageResourceIds(final Set<String> removedStorageResourceIds) {
		this.removedStorageResourceIds = removedStorageResourceIds;
	}

	public void addAddedStorageResourceIds(final String y) {
		if (null == addedStorageResourceIds) {
			addedStorageResourceIds = new HashSet<>();
		}
		addedStorageResourceIds.add(y);
	}

	public VnfCompute getVnfCompute() {
		return vnfCompute;
	}

	public void setVnfCompute(final VnfCompute vnfCompute) {
		this.vnfCompute = vnfCompute;
	}

	public VduInstantiationLevel getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final VduInstantiationLevel instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public VnfLcmOpOccs getVnfLcmOpOccs() {
		return vnfLcmOpOccs;
	}

	public void setVnfLcmOpOccs(final VnfLcmOpOccs vnfLcmOpOccs) {
		this.vnfLcmOpOccs = vnfLcmOpOccs;
	}

}
