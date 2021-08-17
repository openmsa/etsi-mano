/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.dao.mano.v2;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.BlueZoneGroupInformation;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.OperateChanges;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.ZoneInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.vnfi.ChangeExtVnfConnRequest;

@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class VnfBlueprint extends AbstractBlueprint<VnfTask, VnfInstance> implements Blueprint<VnfTask, VnfInstance> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	@JoinColumn
	private VnfInstance vnfInstance;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfTask> tasks = new HashSet<>();

	@Valid
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private Set<VimConnectionInformation> vimConnections = null;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "grants")
	private Set<ZoneInfoEntity> zones = null;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BlueZoneGroupInformation> zoneGroups = null;

	@FullTextField
	private String grantsRequestId;

	@Embedded
	@IndexedEmbedded
	private BlueprintParameters parameters = new BlueprintParameters();

	@Embedded
	@IndexedEmbedded
	private OperateChanges operateChanges = new OperateChanges();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ExtVirtualLinkDataEntity> extVirtualLinks;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ChangeExtVnfConnRequest changeExtVnfConnRequest;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public String getGrantsRequestId() {
		return grantsRequestId;
	}

	@Override
	public void setGrantsRequestId(final String grantsRequestId) {
		this.grantsRequestId = grantsRequestId;
	}

	@Override
	public Set<VimConnectionInformation> getVimConnections() {
		return vimConnections;
	}

	@Override
	public void setVimConnections(final Set<VimConnectionInformation> vimConnections) {
		this.vimConnections = vimConnections;
	}

	public Set<ZoneInfoEntity> getZones() {
		return zones;
	}

	@Override
	public void setZones(final Set<ZoneInfoEntity> zones) {
		this.zones = zones;
	}

	public Set<BlueZoneGroupInformation> getZoneGroups() {
		return zoneGroups;
	}

	@Override
	public void setZoneGroups(final Set<BlueZoneGroupInformation> zoneGroups) {
		this.zoneGroups = zoneGroups;
	}

	@Override
	public BlueprintParameters getParameters() {
		return parameters;
	}

	public void setParameters(final BlueprintParameters parameters) {
		this.parameters = parameters;
	}

	public OperateChanges getOperateChanges() {
		return operateChanges;
	}

	public void setOperateChanges(final OperateChanges operateChanges) {
		this.operateChanges = operateChanges;
	}

	@Override
	public Set<VnfTask> getTasks() {
		return tasks;
	}

	@Override
	public void addTask(final VnfTask task) {
		if (null == tasks) {
			tasks = new HashSet<>();
		}
		tasks.add(task);
	}

	@Override
	public void setTasks(final Set<VnfTask> _tasks) {
		tasks = _tasks;
	}

	public Set<ExtManagedVirtualLinkDataEntity> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	@Override
	public void setExtManagedVirtualLinks(final Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	@Override
	public VnfInstance getInstance() {
		return vnfInstance;
	}

	public Set<ExtVirtualLinkDataEntity> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(final Set<ExtVirtualLinkDataEntity> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	public ChangeExtVnfConnRequest getChangeExtVnfConnRequest() {
		return changeExtVnfConnRequest;
	}

	public void setChangeExtVnfConnRequest(final ChangeExtVnfConnRequest changeExtVnfConnRequest) {
		this.changeExtVnfConnRequest = changeExtVnfConnRequest;
	}

	@Override
	public void addVimConnection(final VimConnectionInformation vimConnection) {
		if (this.vimConnections == null) {
			this.vimConnections = new LinkedHashSet<>();
		}
		this.vimConnections.add(vimConnection);
	}

}
