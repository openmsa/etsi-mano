package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Same as CpProtocolInfo.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
public class CpProtocolDataEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	private LayerProtocolType layerProtocol = null;

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

	public LayerProtocolType getLayerProtocol() {
		return layerProtocol;
	}

	public void setLayerProtocol(final LayerProtocolType layerProtocol) {
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
