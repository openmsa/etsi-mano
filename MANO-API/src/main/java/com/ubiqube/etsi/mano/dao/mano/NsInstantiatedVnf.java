package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@ManyToOne(cascade = CascadeType.ALL)
	private VnfInstance vnfInstance = null;

	@ManyToOne(cascade = CascadeType.DETACH)
	private VnfPackage vnfd = null;

	private String vnfProfileId = null;

	private String vnfName = null;

	private String instanceDescription;

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

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public VnfPackage getVnfd() {
		return vnfd;
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

	public void setVnfInstance(final VnfInstance vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public void setVnfd(final VnfPackage vnfd) {
		this.vnfd = vnfd;
	}

	public String getInstanceDescription() {
		return instanceDescription;
	}

	public void setInstanceDescription(final String instanceDescription) {
		this.instanceDescription = instanceDescription;
	}

}
