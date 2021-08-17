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

import java.util.HashSet;
import java.util.Map;
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
import javax.persistence.OneToMany;
import javax.validation.Valid;

import com.ubiqube.etsi.mano.dao.mano.grant.PlacementConstraint;
import com.ubiqube.etsi.mano.dao.mano.grant.VimConstraint;

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
@EntityListeners(AuditListener.class)
public class GrantResponse implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Embedded
	private Audit audit;

	private String vnfdId;

	// 3.3.1
	private String dstVnfdId;

	private String flavourId;

	private String operation;

	private boolean isAutomaticInvocation;

	private String instantiationLevelId;

	// Must be string because VNFM / NFVO are differents.
	private String vnfInstanceId = null;

	// Must be string because VNFM / NFVO are differents.
	private String vnfLcmOpOccId = null;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VimConnectionInformation> vimConnections = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "grants")
	private Set<ZoneInfoEntity> zones = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<ZoneGroupInformation> zoneGroups = null;

	private String computeReservationId = null;

	private String networkReservationId = null;

	private String storageReservationId = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> addResources = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> tempResources = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> removeResources = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> updateResources = null;

	@Embedded
	private GrantVimAssetsEntity vimAssets = null;

	@Valid
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ExtVirtualLinkDataEntity> extVirtualLinks = null;

	@Valid
	@OneToMany(mappedBy = "grants", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> additionalParams = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PlacementConstraint> placementConstraints = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VimConstraint> vimConstraints = null;

	private String instanceLink;

	private String lcmLink;

	/**
	 * Flag to say if grants have been, inspected.
	 */
	private Boolean available;

	public void addExtManagedVl(final ExtManagedVirtualLinkDataEntity extVl) {
		if (null == extManagedVirtualLinks) {
			extManagedVirtualLinks = new HashSet<>();
		}
		extManagedVirtualLinks.add(extVl);
	}

	public void addZones(final ZoneInfoEntity zone) {
		if (null == zones) {
			zones = new HashSet<>();
		}
		zone.setGrants(this);
		zones.add(zone);

	}

}
