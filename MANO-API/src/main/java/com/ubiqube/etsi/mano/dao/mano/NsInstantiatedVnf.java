package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.ubiqube.etsi.mano.model.nslcm.sol005.AffectedVnfChangedInfo;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedVnf extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vnfInstanceId = null;

	private String vnfdId = null;

	private String vnfProfileId = null;

	private String vnfName = null;

	/** XXX TO do. */
	@Transient
	private AffectedVnfChangedInfo changedInfo = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public String getVnfName() {
		return vnfName;
	}

	public void setVnfName(final String vnfName) {
		this.vnfName = vnfName;
	}

	public AffectedVnfChangedInfo getChangedInfo() {
		return changedInfo;
	}

	public void setChangedInfo(final AffectedVnfChangedInfo changedInfo) {
		this.changedInfo = changedInfo;
	}

}
