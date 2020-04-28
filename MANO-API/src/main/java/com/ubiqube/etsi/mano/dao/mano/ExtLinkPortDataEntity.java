package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ExtLinkPortDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ResourceHandleEntity resourceHandle = null;

	@OneToOne
	private ExtVirtualLinkDataEntity extVirtualLinkDataEntity;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public ResourceHandleEntity getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(final ResourceHandleEntity resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	public ExtVirtualLinkDataEntity getExtVirtualLinkDataEntity() {
		return extVirtualLinkDataEntity;
	}

	public void setExtVirtualLinkDataEntity(final ExtVirtualLinkDataEntity extVirtualLinkDataEntity) {
		this.extVirtualLinkDataEntity = extVirtualLinkDataEntity;
	}

}
