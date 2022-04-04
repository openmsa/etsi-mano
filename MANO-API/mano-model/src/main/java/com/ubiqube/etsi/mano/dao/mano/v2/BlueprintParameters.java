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

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.ubiqube.etsi.mano.dao.mano.ExtCpInfo;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfMonitoringParameter;
import com.ubiqube.etsi.mano.dao.mano.VnfcResourceInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsHeal;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScale;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScaleInfo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 *         <p>
 *         <b>NOTE 1</b>: Modifying the value of this attribute shall not be
 *         performed when conflicts exist between the previous and the newly
 *         referred VNF package, i.e. when the new VNFD is changed with respect
 *         to the previous VNFD in other aspects than merely referencing to
 *         other VNF software images. In order to avoid misalignment of the
 *         VnfInstance with the current VNF's on-boarded VNF Package, the values
 *         of attributes in the VnfInstance that have corresponding attributes
 *         in the VNFD shall be kept in sync with the values in the VNFD.
 *         </p>
 *         <p>
 *         <b>NOTE 2</b>: ETSI GS NFV-SOL 001 [i.4] specifies the structure and
 *         format of the VNFD based on TOSCA specifications.
 *         </p>
 *         <p>
 *         NOTE 3: VNF configurable properties are sometimes also referred to as
 *         configuration parameters applicable to a VNF. Some of these are set
 *         prior to instantiation and cannot be modified if the VNF is
 *         instantiated, some are set prior to instantiation (are part of
 *         initial configuration) and can be modified later, and others can be
 *         set only after instantiation. The applicability of certain
 *         configuration may depend on the VNF and the required operation of the
 *         VNF at a certain point in time.
 *         </p>
 *         <p>
 *         NOTE 4: Upon creation of the VnfInstance structure, the VNFM shall
 *         create and initialize all child attributes of
 *         "vnfConfigurableProperties", "metadata" and "extensions" that were
 *         declared in the VNFD with a defined initial value. The defined
 *         initial values can be declared in the VNFD, and/or, in case of
 *         "metadata", obtained from the "CreateVnfRequest" structure. Child
 *         attributes of "vnfConfigurableProperties", "metadata" and
 *         "extensions" that have no defined initial value shall not be created,
 *         in order to be consistent with the semantics of the JSON Merge Patch
 *         method (see IETF RFC 7396 [5]) that interprets null values as
 *         deletion request.
 *         </p>
 *         <p>
 *         NOTE 5: It is possible to have several ExtManagedVirtualLinkInfo for
 *         the same VNF internal VL in case of a multi- site VNF spanning
 *         several VIMs. The set of ExtManagedVirtualLinkInfo corresponding to
 *         the same VNF internal VL shall indicate so by referencing to the same
 *         VnfVirtualLinkDesc and externally-managed multi- site VL instance
 *         (refer to clause 5.5.3.3).
 *         </p>
 *         <p>
 *         NOTE 6: Even though externally-managed internal VLs are also used for
 *         VNF-internal connectivity, they shall not be listed in the
 *         "vnfVirtualLinkResourceInfo" attribute as this would be redundant.
 *         </p>
 * 
 */
@Getter
@Setter
@Embeddable
public class BlueprintParameters implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	String instantiationLevelId;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ScaleInfo> scaleStatus;

	/**
	 * Scale status of the VNF, one entry per aspect. Represents for every scaling
	 * aspect how "big" the VNF has been scaled w.r.t. that aspect. This attribute
	 * shall be present if the VNF supports scaling. See clause B.2 for an
	 * explanation of VNF scaling.
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<NsScaleInfo> nsScaleStatus;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ScaleInfo> nsStepStatus;

	/**
	 * Information about the externally-managed internal VLs of the VNF instance.
	 * See note 5 and note 6.
	 */
	@Valid
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks;

	private Integer numberOfSteps;

	@Enumerated(EnumType.STRING)
	private ScaleTypeEnum scaleType;

	private String aspectId;

	/**
	 * Identifier of the VNF deployment flavour applied to this VNF instance.
	 *
	 */
	private String flavourId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsScale nsScale;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsHeal nsHeal;

	/**
	 * State of the VNF instance.
	 */
	@Enumerated(EnumType.STRING)
	private OperationalStateType state;

	/**
	 * Information about localization language of the VNF (includes e.g. strings in
	 * the VNFD). The localization languages supported by a VNF can be declared in
	 * the VNFD, and localization language selection can take place at instantiation
	 * time. The value shall comply with the format defined in IETF RFC 5646 [3].
	 *
	 */
	private String localizationLanguage;

	/**
	 * Active monitoring parameters.
	 */
	@Transient
	private Set<VnfMonitoringParameter> vnfMonitoringParameter;

	/**
	 * Information about the virtualised storage resources used as storage for the
	 * VNF instance.
	 */
	@Transient
	private Set<VirtualStorageResourceInfo> virtualStorageResourceInfo;

	/**
	 * Information about the external VLs the VNF instance is connected to.
	 */
	@Transient
	private Set<ExtVirtualLinkInfoEntity> extVirtualLinkInfo;

	/**
	 * Information about the external CPs exposed by the VNF instance. When trunking
	 * is enabled, the list of entries includes both, external CPs corresponding to
	 * parent ports of a trunk, and external CPs associated to sub-ports of a trunk.
	 */
	@Transient
	private Set<ExtCpInfo> extCpInfo;

	/**
	 * Information about the virtualised compute and storage resources used by the
	 * VNFCs of the VNF instance.
	 */
	@Transient
	private Set<VnfcResourceInfoEntity> vnfcResourceInfo;

	/**
	 * Information about the virtualised network resources used by the VLs of the
	 * VNF instance. See note 6.
	 */
	@Transient
	private Set<VirtualLinkInfo> virtualLinkResourceInfo;
}
