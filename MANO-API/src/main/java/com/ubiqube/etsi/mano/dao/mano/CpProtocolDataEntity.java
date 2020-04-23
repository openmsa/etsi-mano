package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.model.CpProtocolData.LayerProtocolEnum;

/**
 * Same as CpProtocolInfo.
 * 
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
public class CpProtocolDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	private LayerProtocolEnum layerProtocol = null;

	@OneToOne
	private IpOverEthernetAddressDataEntity ipOverEthernet = null;

	@OneToOne
	private VnfExtCpConfiguration vnfExtCpConfiguration = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public LayerProtocolEnum getLayerProtocol() {
		return layerProtocol;
	}

	public void setLayerProtocol(final LayerProtocolEnum layerProtocol) {
		this.layerProtocol = layerProtocol;
	}

	public IpOverEthernetAddressDataEntity getIpOverEthernet() {
		return ipOverEthernet;
	}

	public void setIpOverEthernet(final IpOverEthernetAddressDataEntity ipOverEthernet) {
		this.ipOverEthernet = ipOverEthernet;
	}

	public VnfExtCpConfiguration getVnfExtCpConfiguration() {
		return vnfExtCpConfiguration;
	}

	public void setVnfExtCpConfiguration(final VnfExtCpConfiguration vnfExtCpConfiguration) {
		this.vnfExtCpConfiguration = vnfExtCpConfiguration;
	}

}
