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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
import com.ubiqube.etsi.mano.utils.ToStringUtil;

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
public class GrantResponse implements BaseEntity, Auditable, GrantInterface {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	/**
	 * From GrantRequest.
	 *
	 * Identifier of the VNFD that defines the VNF for which the LCM operation is to
	 * be granted.
	 */
	private String vnfdId;

	/**
	 * Identifier of the "destination" VNFD which will define the VNF after
	 * executing the "Change current VNF package" LCM operation to be granted. Shall
	 * be included if the operation changes the current VNF Package and shall be
	 * absent otherwise.
	 *
	 * @Since 3.3.1
	 */
	private String dstVnfdId;

	/**
	 * From GrantRequest.
	 *
	 * Identifier of the VNF deployment flavour of the VNFD that defines the VNF for
	 * which the LCM operation is to be granted. Shall be provided when
	 * instantiating the VNF or changing the deployment flavour of the VNF instance.
	 */
	private String flavourId;

	/**
	 * From GrantRequest.
	 *
	 * The lifecycle management operation for which granting is requested.
	 */
	private String operation;

	/**
	 * From GrantRequest.
	 *
	 * Set to true if this VNF LCM operation occurrence has been triggered by an
	 * automated procedure inside the VNFM (i.e. ScaleVnf/ScaleVnfToLevel triggered
	 * by auto-scale, or HealVnf triggered by auto-heal).
	 *
	 * Set to false otherwise.
	 */
	private boolean isAutomaticInvocation;

	/**
	 * From GrantRequest.
	 *
	 * If operation=INSTANTIATE, the identifier of the instantiation level may be
	 * provided as an alternative way to define the resources to be added. This
	 * attribute shall only be used if operation=INSTANTIATE.
	 */
	private String instantiationLevelId;

	/**
	 * From GrantRequest.
	 *
	 * Identifier of the VNF instance which this grant request is related to. Shall
	 * also be provided for VNFs that not yet exist but are planned to exist in the
	 * future, i.e. if the grant is requested for InstantiateVNF.
	 *
	 * <b>Must be string because VNFM / NFVO are differents.</b>
	 */
	private String vnfInstanceId;

	/**
	 * From GrantRequest.
	 *
	 * The identifier of the VNF lifecycle management operation occurrence
	 * associated to the GrantRequest.
	 *
	 * <b>Must be string because VNFM / NFVO are differents.</b>
	 */
	private String vnfLcmOpOccId;

	@Valid
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Set<VimConnectionInformation> vimConnections;

	/**
	 * From Grant.
	 *
	 * Identifies resource zones where the resources are approved to be allocated by
	 * the VNFM.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "grants")
	private Set<ZoneInfoEntity> zones;

	/**
	 * From Grant.
	 *
	 * Information about groups of resource zones that are related and that the NFVO
	 * has chosen to fulfil a zoneGroup constraint in the GrantVnfLifecycleOperation
	 * request. This information confirms that the NFVO has honoured the zoneGroup
	 * constraints that were passed as part of "placementConstraints" in the
	 * GrantRequest.
	 */
	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<ZoneGroupInformation> zoneGroups;

	/**
	 * Information that identifies a reservation applicable to the compute resource
	 * requirements of the corresponding grant request.
	 */
	private String computeReservationId;

	/**
	 * Information that identifies a reservation applicable to the network resource
	 * requirements of the corresponding grant request.
	 */
	private String networkReservationId;

	/**
	 * Information that identifies a reservation applicable to the storage resource
	 * requirements of the corresponding grant request.
	 */
	private String storageReservationId;

	/**
	 * From GrantRequest & Grant.
	 *
	 * List of resource definitions in the VNFD for resources to be added by the LCM
	 * operation which is related to this grant request, with one entry per
	 * resource.
	 */
	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> addResources = new LinkedHashSet<>();

	/**
	 * From GrantRequest & Grant.
	 *
	 * List of resource definitions in the VNFD for resources to be temporarily
	 * instantiated during the runtime of the LCM operation which is related to this
	 * grant request, with one entry per resource.
	 */
	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> tempResources = new LinkedHashSet<>();

	/**
	 * From GrantRequest & Grant.
	 *
	 * Provides the definitions of resources to be removed by the LCM operation
	 * which is related to this grant request, with one entry per resource.
	 */
	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> removeResources = new LinkedHashSet<>();

	/**
	 * From GrantRequest & Grant.
	 *
	 * Provides the definitions of resources to be modified by the LCM operation
	 * which is related to this grant request, with one entry per resource.
	 */
	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<GrantInformationExt> updateResources = new LinkedHashSet<>();

	/**
	 * From Grant.
	 *
	 * Information about assets for the VNF that are managed by the NFVO in the VIM,
	 * such as software images and virtualised compute resource flavours.
	 */
	@Embedded
	private GrantVimAssetsEntity vimAssets;

	/**
	 * From Grant.
	 *
	 * Information about external VLs to connect the VNF to. See note 5 and note 7.
	 * If this attribute is present according to note 5 or note 7, it need not
	 * contain those entries that are unchanged compared to the entries that were
	 * passed in the LCM operation which is related to this granting exchange.
	 */
	@Valid
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExtVirtualLinkDataEntity> extVirtualLinks = new LinkedHashSet<>();

	/**
	 * From Grant.
	 *
	 * Information about internal VLs that are managed by other entities than the
	 * VNFM.
	 */
	@Valid
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks = new LinkedHashSet<>();

	/**
	 * From GrantRequest & Grant.
	 *
	 * Additional parameters passed by the VNFM, specific to the VNF and the LCM
	 * operation.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> additionalParams = new HashMap<>();

	/**
	 * From GrantRequest.
	 *
	 * Placement constraints that the VNFM may send to the NFVO in order to
	 * influence the resource placement decision. If sent, the NFVO shall take the
	 * constraints into consideration when making resource placement decisions and
	 * shall reject the grant if they cannot be honoured.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PlacementConstraint> placementConstraints = new LinkedHashSet<>();

	/**
	 * From GrantRequest.
	 *
	 * Used by the VNFM to require that multiple resources are managed through the
	 * same VIM connection. If sent, the NFVO shall take the constraints into
	 * consideration when making VIM selection decisions and shall reject the grant
	 * if they cannot be honoured. This attribute shall be supported if VNF-related
	 * Resource Management in direct mode is applicable. The applicability and
	 * further details of this attribute for indirect mode are left for future
	 * specification.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VimConstraint> vimConstraints = new LinkedHashSet<>();

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

	public void addAddResources(final GrantInformationExt obj) {
		if (null == addResources) {
			addResources = new LinkedHashSet<>();
		}
		addResources.add(obj);
	}

	public void addRemoveResources(final GrantInformationExt obj) {
		if (null == removeResources) {
			removeResources = new LinkedHashSet<>();
		}
		removeResources.add(obj);
	}

	@Override
	public String toString() {
		return ToStringUtil.toString(this);
	}
}
