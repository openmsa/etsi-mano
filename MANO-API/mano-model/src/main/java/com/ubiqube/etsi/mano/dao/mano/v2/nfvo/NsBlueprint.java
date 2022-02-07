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
package com.ubiqube.etsi.mano.dao.mano.v2.nfvo;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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

import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexingDependency;

import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.BlueZoneGroupInformation;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.ZoneInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.v2.AbstractBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.vnfm.RejectedLcmCoordination;
import com.ubiqube.etsi.mano.dao.mano.vnfm.VnfLcmCoordination;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class NsBlueprint extends AbstractBlueprint<NsTask, NsdInstance> {

	/** Serial. */
	private static final long serialVersionUID = 1L;
	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	@IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
	private Set<NsTask> tasks;

	@ManyToOne
	@IndexedEmbedded
	private NsdInstance nsInstance;

	private String nsInstantiationLevelId;

	private String nsFlavourId;
	// 3.5.1
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VnfLcmCoordination> lcmCoordinations;
	// 3.5.1
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<RejectedLcmCoordination> rejectedLcmCoordinations;
	// 3.5.1
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> warnings;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Set<VimConnectionInformation> vimConnections;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BlueZoneGroupInformation> zoneGroups;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ZoneInfoEntity> zones;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks;
	@Embedded
	@IndexedEmbedded
	private BlueprintParameters parameters;

	public void setNsInstance(final NsdInstance nsInstance) {
		this.nsInstance = nsInstance;
	}

	@Override
	public void addVimConnection(final VimConnectionInformation vimConnection) {
		if (null == this.vimConnections) {
			this.vimConnections = new HashSet<>();
		}
		this.vimConnections.add(vimConnection);
	}

	@Override
	public void addTask(final NsTask task) {
		tasks.add(task);
	}

	@Override
	public void setGrantsRequestId(final String string) {
		//
	}

	@Override
	public NsdInstance getInstance() {
		return nsInstance;
	}

	@Override
	public void addExtManagedVirtualLinks(final Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks) {
		if (null == extManagedVirtualLinks) {
			this.extManagedVirtualLinks = new LinkedHashSet<>();
		}
		this.extManagedVirtualLinks.addAll(extManagedVirtualLinks);
	}

	@Override
	public void addExtVirtualLinks(final Set<ExtVirtualLinkDataEntity> extVirtualLinks) {
		// TODO Auto-generated method stub

	}
}
