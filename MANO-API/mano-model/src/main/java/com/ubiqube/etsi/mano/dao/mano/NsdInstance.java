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
package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.ubiqube.etsi.mano.dao.mano.nfvo.NsVnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;

@Entity
@Indexed
public class NsdInstance implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@FullTextField
	private String nsInstanceName = null;

	@FullTextField
	private String nsInstanceDescription = null;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsdPackage nsdInfo = null;

	@FullTextField
	private String flavourId = null;

	private String nsInstantiationLevelId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<NsVnfInstance> vnfInstance = null;

	// XXX Add pnfInfo
	// XXX Add virtualLinkInfo
	// XXX Add vnffgInfo
	// XXX Add sapInfo

	@OneToMany
	private List<NsdInstance> nestedNsInstance = null;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private InstantiationState nsState = null;

	// XXX Add nsScaleStatus

	// XXX Add additionalAffinityOrAntiAffinityRule

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<NestedNsInstanceData> nestedNsInstanceData;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstanceData> vnfInstanceData;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nsInstance")
	private Set<NsBlueprint> blueprint;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getNsInstanceName() {
		return nsInstanceName;
	}

	public void setNsInstanceName(final String nsInstanceName) {
		this.nsInstanceName = nsInstanceName;
	}

	public String getNsInstanceDescription() {
		return nsInstanceDescription;
	}

	public void setNsInstanceDescription(final String nsInstanceDescription) {
		this.nsInstanceDescription = nsInstanceDescription;
	}

	public NsdPackage getNsdInfo() {
		return nsdInfo;
	}

	public void setNsdInfo(final NsdPackage nsdInfoId) {
		this.nsdInfo = nsdInfoId;
	}

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public List<NsVnfInstance> getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final List<NsVnfInstance> vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public List<NsdInstance> getNestedNsInstance() {
		return nestedNsInstance;
	}

	public void setNestedNsInstance(final List<NsdInstance> nestedNsInstanceId) {
		this.nestedNsInstance = nestedNsInstanceId;
	}

	public InstantiationState getNsState() {
		return nsState;
	}

	public void setNsState(final InstantiationState nsState) {
		this.nsState = nsState;
	}

	public String getNsInstantiationLevelId() {
		return nsInstantiationLevelId;
	}

	public void setNsInstantiationLevelId(final String nsInstantiationLevelId) {
		this.nsInstantiationLevelId = nsInstantiationLevelId;
	}

	public Set<NestedNsInstanceData> getNestedNsInstanceData() {
		return nestedNsInstanceData;
	}

	public void setNestedNsInstanceData(final Set<NestedNsInstanceData> nestedNsInstanceData) {
		this.nestedNsInstanceData = nestedNsInstanceData;
	}

	public Set<VnfInstanceData> getVnfInstanceData() {
		return vnfInstanceData;
	}

	public void setVnfInstanceData(final Set<VnfInstanceData> vnfInstanceData) {
		this.vnfInstanceData = vnfInstanceData;
	}

	public Set<NsBlueprint> getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(final Set<NsBlueprint> blueprint) {
		this.blueprint = blueprint;
	}

	public void addNestedNsInstance(final NsdInstance nsIn) {
		if (null == nestedNsInstance) {
			nestedNsInstance = new ArrayList<>();
		}
		nestedNsInstance.add(nsIn);
	}

}
