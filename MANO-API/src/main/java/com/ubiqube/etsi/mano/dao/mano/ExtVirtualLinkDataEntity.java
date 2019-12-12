package com.ubiqube.etsi.mano.dao.mano;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
public class ExtVirtualLinkDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String resourceId = null;

	@Valid
	@OneToMany(mappedBy = "extVirtualLinkDataEntity")
	private List<VnfExtCpDataEntity> extCps = new ArrayList<>();

	@Valid
	@OneToMany(mappedBy = "extVirtualLinkDataEntity")
	private List<ExtLinkPortDataEntity> extLinkPorts = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public List<VnfExtCpDataEntity> getExtCps() {
		return extCps;
	}

	public void setExtCps(final List<VnfExtCpDataEntity> extCps) {
		this.extCps = extCps;
	}

	public List<ExtLinkPortDataEntity> getExtLinkPorts() {
		return extLinkPorts;
	}

	public void setExtLinkPorts(final List<ExtLinkPortDataEntity> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
	}

}
