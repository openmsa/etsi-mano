package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class VnfExtCpDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID cpdId = null;

	@Valid
	@OneToMany(mappedBy = "vnfExtCpDataEntity")
	private List<VnfExtCpConfiguration> cpConfig = null;

	@OneToOne
	private ExtVirtualLinkDataEntity extVirtualLinkDataEntity;

	public UUID getCpdId() {
		return cpdId;
	}

	public void setCpdId(final UUID cpdId) {
		this.cpdId = cpdId;
	}

	public List<VnfExtCpConfiguration> getCpConfig() {
		return cpConfig;
	}

	public void setCpConfig(final List<VnfExtCpConfiguration> cpConfig) {
		this.cpConfig = cpConfig;
	}

	public ExtVirtualLinkDataEntity getExtVirtualLinkDataEntity() {
		return extVirtualLinkDataEntity;
	}

	public void setExtVirtualLinkDataEntity(final ExtVirtualLinkDataEntity extVirtualLinkDataEntity) {
		this.extVirtualLinkDataEntity = extVirtualLinkDataEntity;
	}

}
