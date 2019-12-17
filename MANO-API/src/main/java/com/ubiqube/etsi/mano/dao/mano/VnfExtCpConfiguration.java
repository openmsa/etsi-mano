package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class VnfExtCpConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	private String cpInstanceId = null;

	private String linkPortId = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vnfExtCpConfiguration")
	private List<CpProtocolDataEntity> cpProtocolData = null;

	@OneToOne
	private VnfExtCpDataEntity vnfExtCpDataEntity;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	public String getLinkPortId() {
		return linkPortId;
	}

	public void setLinkPortId(final String linkPortId) {
		this.linkPortId = linkPortId;
	}

	public List<CpProtocolDataEntity> getCpProtocolData() {
		return cpProtocolData;
	}

	public void setCpProtocolData(final List<CpProtocolDataEntity> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
	}

	public VnfExtCpDataEntity getVnfExtCpDataEntity() {
		return vnfExtCpDataEntity;
	}

	public void setVnfExtCpDataEntity(final VnfExtCpDataEntity vnfExtCpDataEntity) {
		this.vnfExtCpDataEntity = vnfExtCpDataEntity;
	}

}
