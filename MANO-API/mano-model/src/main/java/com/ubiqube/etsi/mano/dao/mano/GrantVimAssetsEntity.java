package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class GrantVimAssetsEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<VimComputeResourceFlavourEntity> computeResourceFlavours = new HashSet<>();

	@Valid
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<VimSoftwareImageEntity> softwareImages = new HashSet<>();

	public Set<VimComputeResourceFlavourEntity> getComputeResourceFlavours() {
		return computeResourceFlavours;
	}

	public void setComputeResourceFlavours(final Set<VimComputeResourceFlavourEntity> computeResourceFlavours) {
		this.computeResourceFlavours = computeResourceFlavours;
	}

	public Set<VimSoftwareImageEntity> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(final Set<VimSoftwareImageEntity> softwareImages) {
		this.softwareImages = softwareImages;
	}

}
