package com.ubiqube.etsi.mano.service.event;

public class ProviderData {

	private String vnfProvider;

	private String vnfProductName;

	private String vnfSoftwareVersion;

	private String vnfVersion;

	private String flavorId;

	private String vnfdVersion;

	private String vnfdId;

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public String getVnfVersion() {
		return vnfVersion;
	}

	public void setVnfVersion(final String vnfVersion) {
		this.vnfVersion = vnfVersion;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

}
