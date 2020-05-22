package com.ubiqube.etsi.mano.dao.mano;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditListener.class)
public class VnfInstantiatedBase implements Auditable, BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	/*
	 * Vnf Compute.
	 */
	private UUID vduId = null;

	/**
	 * Point to a VnfInstantiated row.
	 */
	private UUID removedInstantiated;

	/**
	 * Grant Info
	 */
	private String reservationId;

	/**
	 * Grant Info
	 */
	private String resourceProviderId = null;

	/**
	 * Grant Info
	 */
	private String zoneId = null;

	/**
	 * Grant Info
	 */
	private String resourceGroupId = null;

	/**
	 * VIM Resources.
	 */
	@ManyToOne(cascade = CascadeType.DETACH)
	private VimConnectionInformation vimConnectionInformation;

	private String resourceId = null;

	private String vimLevelResourceType = null;

	@Enumerated(EnumType.STRING)
	private InstantiationStatusType status = InstantiationStatusType.NOT_STARTED;

	@Enumerated(EnumType.STRING)
	private ChangeType changeType;

	private Date startTime;

	private Date endTime;

	@ManyToOne
	private VnfCompute vnfCompute;

	@ManyToOne
	private VnfLcmOpOccs vnfLcmOpOccs;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	@Embedded
	private Audit audit;

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

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(final String reservationId) {
		this.reservationId = reservationId;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public void setResourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public String getVimLevelResourceType() {
		return vimLevelResourceType;
	}

	public void setVimLevelResourceType(final String vimLevelResourceType) {
		this.vimLevelResourceType = vimLevelResourceType;
	}

	public InstantiationStatusType getStatus() {
		return status;
	}

	public void setStatus(final InstantiationStatusType status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public VnfCompute getVnfCompute() {
		return vnfCompute;
	}

	public void setVnfCompute(final VnfCompute vnfCompute) {
		this.vnfCompute = vnfCompute;
	}

	public VnfLcmOpOccs getVnfLcmOpOccs() {
		return vnfLcmOpOccs;
	}

	public void setVnfLcmOpOccs(final VnfLcmOpOccs vnfLcmOpOccs) {
		this.vnfLcmOpOccs = vnfLcmOpOccs;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeType changeType) {
		this.changeType = changeType;
	}

	public UUID getRemovedInstantiated() {
		return removedInstantiated;
	}

	public void setRemovedInstantiated(final UUID removedInstantiated) {
		this.removedInstantiated = removedInstantiated;
	}

	public VduInstantiationLevel getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final VduInstantiationLevel instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public final Map<String, String> getMetadata() {
		return metadata;
	}

	public final void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

}
