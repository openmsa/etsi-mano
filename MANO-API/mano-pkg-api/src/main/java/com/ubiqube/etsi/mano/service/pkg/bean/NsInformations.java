package com.ubiqube.etsi.mano.service.pkg.bean;

public class NsInformations {

	private String nsdId;

	private String nsdName;

	private String nsdVersion;

	private String nsdDesigner;

	private String nsdInvariantId;

	private String instantiationLevel;

	private int minNumberOfInstance;

	private int maxNumberOfInstance;

	private String flavorId;

	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public String getNsdName() {
		return nsdName;
	}

	public void setNsdName(final String nsdName) {
		this.nsdName = nsdName;
	}

	public String getNsdVersion() {
		return nsdVersion;
	}

	public void setNsdVersion(final String nsdVersion) {
		this.nsdVersion = nsdVersion;
	}

	public String getNsdDesigner() {
		return nsdDesigner;
	}

	public void setNsdDesigner(final String nsdDesigner) {
		this.nsdDesigner = nsdDesigner;
	}

	public String getNsdInvariantId() {
		return nsdInvariantId;
	}

	public void setNsdInvariantId(final String nsdInvariantId) {
		this.nsdInvariantId = nsdInvariantId;
	}

	public String getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public int getMinNumberOfInstance() {
		return minNumberOfInstance;
	}

	public void setMinNumberOfInstance(final int minNumberOfInstance) {
		this.minNumberOfInstance = minNumberOfInstance;
	}

	public int getMaxNumberOfInstance() {
		return maxNumberOfInstance;
	}

	public void setMaxNumberOfInstance(final int maxNumberOfInstance) {
		this.maxNumberOfInstance = maxNumberOfInstance;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

}
