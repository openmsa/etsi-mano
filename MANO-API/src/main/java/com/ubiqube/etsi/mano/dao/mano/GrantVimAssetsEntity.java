package com.ubiqube.etsi.mano.dao.mano;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.validation.Valid;

@Embeddable
public class GrantVimAssetsEntity {
	@ElementCollection
	private List<VimComputeResourceFlavourEntity> computeResourceFlavours = new ArrayList<>();

	@Valid
	@ElementCollection
	private List<VimSoftwareImageEntity> softwareImages = new ArrayList<>();

	public List<VimComputeResourceFlavourEntity> getComputeResourceFlavours() {
		return computeResourceFlavours;
	}

	public void setComputeResourceFlavours(final List<VimComputeResourceFlavourEntity> computeResourceFlavours) {
		this.computeResourceFlavours = computeResourceFlavours;
	}

	public List<VimSoftwareImageEntity> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(final List<VimSoftwareImageEntity> softwareImages) {
		this.softwareImages = softwareImages;
	}

}
