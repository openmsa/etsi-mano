package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

public class NsAddressData implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String addressType;

	/** L2. */
	private Boolean macAddressAssignment;

	/** L3. */
	private Integer numberOfIpAddress;
	private Boolean ipAddressAssignment;
	private String ipAddressType;
	private Boolean floatingIpActivated;

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(final String addressType) {
		this.addressType = addressType;
	}

	public Boolean getMacAddressAssignment() {
		return macAddressAssignment;
	}

	public void setMacAddressAssignment(final Boolean macAddressAssignment) {
		this.macAddressAssignment = macAddressAssignment;
	}

	public Integer getNumberOfIpAddress() {
		return numberOfIpAddress;
	}

	public void setNumberOfIpAddress(final Integer numberOfIpAddress) {
		this.numberOfIpAddress = numberOfIpAddress;
	}

	public Boolean getIpAddressAssignment() {
		return ipAddressAssignment;
	}

	public void setIpAddressAssignment(final Boolean ipAddressAssignment) {
		this.ipAddressAssignment = ipAddressAssignment;
	}

	public String getIpAddressType() {
		return ipAddressType;
	}

	public void setIpAddressType(final String ipAddressType) {
		this.ipAddressType = ipAddressType;
	}

	public Boolean getFloatingIpActivated() {
		return floatingIpActivated;
	}

	public void setFloatingIpActivated(final Boolean floatingIpActivated) {
		this.floatingIpActivated = floatingIpActivated;
	}

}
