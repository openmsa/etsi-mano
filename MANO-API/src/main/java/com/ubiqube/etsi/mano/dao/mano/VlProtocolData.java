package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VlProtocolData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String associatedLayerProtocol;

	@Embedded
	private L2Data l2ProtocolData;

	@Embedded
	private L3Data l3ProtocolData;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getAssociatedLayerProtocol() {
		return associatedLayerProtocol;
	}

	public void setAssociatedLayerProtocol(final String associatedLayerProtocol) {
		this.associatedLayerProtocol = associatedLayerProtocol;
	}

	public L2Data getL2ProtocolData() {
		return l2ProtocolData;
	}

	public void setL2ProtocolData(final L2Data l2ProtocolData) {
		this.l2ProtocolData = l2ProtocolData;
	}

	public L3Data getL3ProtocolData() {
		return l3ProtocolData;
	}

	public void setL3ProtocolData(final L3Data l3ProtocolData) {
		this.l3ProtocolData = l3ProtocolData;
	}

}
