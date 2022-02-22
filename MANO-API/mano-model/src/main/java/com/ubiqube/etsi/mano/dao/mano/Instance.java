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

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class Instance implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * Information specific to an instantiated VNF instance. This attribute shall be
	 * present if the instantiateState attribute value is INSTANTIATED.
	 */
	@Embedded
	private BlueprintParameters instantiatedVnfInfo;

	/**
	 * The instantiation state of the VNF. Permitted values: NOT_INSTANTIATED: The
	 * VNF instance is terminated or not instantiated. INSTANTIATED: The VNF
	 * instance is instantiated.
	 */
	@Enumerated(EnumType.STRING)
	@FullTextField
	private InstantiationState instantiationState;

	/**
	 * Human-readable description of the VNF instance. This attribute can be
	 * modified with the PATCH method.
	 */
	@FullTextField
	private String vnfInstanceDescription;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata;

	/**
	 * Information about VIM connections to be used for managing the resources for
	 * the VNF instance. The keys of the map, each of which identifies information
	 * about a particular VIM connection, are managed by the NFVO and referenced
	 * from other data structures via the "vimConnectionId" attribute. This
	 * attribute shall only be supported and present if VNF-related resource
	 * management in direct mode is applicable. This attribute can be modified with
	 * the PATCH method.
	 *
	 */
	// 3.3.1 it's a map
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	private Set<VimConnectionInformation> vimConnectionInfo = new LinkedHashSet<>();

	/**
	 * Additional VNF-specific attributes that provide the current values of the
	 * configurable properties of the VNF instance. These attributes represent
	 * values that are stored persistently in the VnfInstance structure and that
	 * correspond to configuration parameters of the VNF instance. Modifying these
	 * attributes affects the configuration of the VNF instance either directly (if
	 * the VNF instance is in INSTANTIATED state at the time of the modification) or
	 * as part of the subsequent VNF instantiation operation (if the VNF instance is
	 * in NOT_INSTANTIATED state at the time of the modification). Configurable
	 * properties referred in these attributes are declared in the VNFD. The
	 * declaration of configurable properties in the VNFD can optionally contain the
	 * specification of initial values. See notes 2, 3 and 4. The VNFM shall reject
	 * requests to write configurable properties that are not declared in the VNFD
	 * with a "422 Unprocessable entity" error response as defined in clause 6.4 of
	 * ETSI GS NFV-SOL 013 [8]. These configurable properties include the following
	 * standard attributes, which are declared in the VNFD if auto-scaling and/or
	 * auto-healing are supported by the VNF: - isAutoscaleEnabled: If present, the
	 * VNF supports auto-scaling. If set to true, auto-scaling is currently enabled.
	 * If set to false, auto-scaling is currently disabled. - isAutohealEnabled: If
	 * present, the VNF supports auto-healing. If set to true, auto-healing is
	 * currently enabled. If set to false, auto- healing is currently disabled.
	 * These configurable properties can be initialized with default values from the
	 * VNFD (see note 4). Configurable properties can be modified with values passed
	 * in the request structures of certain LCM operations, such as the
	 * InstantiateVnfRequest structure. Further, these configurable properties can
	 * be created, modified or deleted with the PATCH method. In addition, the
	 * provisions in clause 5.7 shall apply.
	 *
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> vnfConfigurableProperties;

	@FullTextField
	private String vnfInstanceName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private NsdInstance nsInstance;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> extensions;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vnfInstance")
	private transient Set<VnfBlueprint> blueprints;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vnfInstance")
	private transient Set<ExtVirtualLinkDataEntity> extVirtualLinks;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vnfInstance")
	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks;

	@Version
	private long version;

	/**
	 * UUID of an lcm op occ.
	 */
	private UUID lockedBy;

	private Audit audit = new Audit();

	public void addVimConnectionInfo(final VimConnectionInformation x) {
		if (null == this.vimConnectionInfo) {
			this.vimConnectionInfo = new LinkedHashSet<>();
		}
		vimConnectionInfo.add(x);
	}

}
