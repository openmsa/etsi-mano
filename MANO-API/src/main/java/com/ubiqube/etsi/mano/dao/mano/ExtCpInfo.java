package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ExtCpInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String cpdId = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CpProtocolDataEntity> cpProtocolInfo = null;

	@OneToOne
	private CpProtocolDataEntity extLinkPortId = null;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	private String associatedVnfcCpId = null;

	private String associatedVnfVirtualLinkId = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public Set<CpProtocolDataEntity> getCpProtocolInfo() {
		return cpProtocolInfo;
	}

	public void setCpProtocolInfo(final Set<CpProtocolDataEntity> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
	}

	public CpProtocolDataEntity getExtLinkPortId() {
		return extLinkPortId;
	}

	public void setExtLinkPortId(final CpProtocolDataEntity extLinkPortId) {
		this.extLinkPortId = extLinkPortId;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public String getAssociatedVnfcCpId() {
		return associatedVnfcCpId;
	}

	public void setAssociatedVnfcCpId(final String associatedVnfcCpId) {
		this.associatedVnfcCpId = associatedVnfcCpId;
	}

	public String getAssociatedVnfVirtualLinkId() {
		return associatedVnfVirtualLinkId;
	}

	public void setAssociatedVnfVirtualLinkId(final String associatedVnfVirtualLinkId) {
		this.associatedVnfVirtualLinkId = associatedVnfVirtualLinkId;
	}

}
