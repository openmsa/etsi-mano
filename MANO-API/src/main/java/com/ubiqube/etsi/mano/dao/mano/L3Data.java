package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

@Embeddable
public class L3Data implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private boolean dhcpEnabled;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<IpPool> ipAllocationPools;

	private String ipv6AddressMode;

	private String ipVersion;

	private String l3Name;

	private String cidr;

	private String gatewayIp;

	public boolean isDhcpEnabled() {
		return dhcpEnabled;
	}

	public void setDhcpEnabled(final boolean dhcpEnabled) {
		this.dhcpEnabled = dhcpEnabled;
	}

	public List<IpPool> getIpAllocationPools() {
		return ipAllocationPools;
	}

	public void setIpAllocationPools(final List<IpPool> ipAllocationPools) {
		this.ipAllocationPools = ipAllocationPools;
	}

	public String getIpv6AddressMode() {
		return ipv6AddressMode;
	}

	public void setIpv6AddressMode(final String ipv6AddressMode) {
		this.ipv6AddressMode = ipv6AddressMode;
	}

	public String getIpVersion() {
		return ipVersion;
	}

	public void setIpVersion(final String ipVersion) {
		this.ipVersion = ipVersion;
	}

	public String getL3Name() {
		return l3Name;
	}

	public void setL3Name(final String l3Name) {
		this.l3Name = l3Name;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(final String cidr) {
		this.cidr = cidr;
	}

	public String getGatewayIp() {
		return gatewayIp;
	}

	public void setGatewayIp(final String gatewayIp) {
		this.gatewayIp = gatewayIp;
	}

}
