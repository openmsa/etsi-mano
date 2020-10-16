package com.ubiqube.etsi.mano.model;

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

public class VnfInstantiate {
	private String flavourId = null;

	private String instantiationLevelId = null;

	private List<VimConnectionInformation> vimConnectionInfo = null;

	private List<ExternalManagedVirtualLink> extManagedVirtualLinks = null;

	private String localizationLanguage = null;

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public List<VimConnectionInformation> getVimConnectionInfo() {
		return vimConnectionInfo;
	}

	public void setVimConnectionInfo(final List<VimConnectionInformation> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
	}

	public List<ExternalManagedVirtualLink> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final List<ExternalManagedVirtualLink> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(final String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

}
