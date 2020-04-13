package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

@Embeddable
public class L3Data {
	private boolean dhcpEnabled;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<IpPool> ipAllocationPools;

	private String ipv6AddressMode;

	private String ipVersion;

	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
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
