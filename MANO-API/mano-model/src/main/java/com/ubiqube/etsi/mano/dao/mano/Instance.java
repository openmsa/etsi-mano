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
	private UUID id = null;

	@Embedded
	private BlueprintParameters instantiatedVnfInfo = null;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private InstantiationState instantiationState = null;

	@FullTextField
	private String vnfInstanceDescription;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata = null;

	// 3.3.1 it's a map
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	private Set<VimConnectionInformation> vimConnectionInfo = new LinkedHashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> vnfConfigurableProperties = null;

	@FullTextField
	private String vnfInstanceName = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private NsdInstance nsInstance;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> extensions = null;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vnfInstance")
	private transient Set<VnfBlueprint> blueprints;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vnfInstance")
	private transient Set<ExtVirtualLinkDataEntity> extVirtualLinks;

	// private Set<VnfInstantiatedBase> extManagedVirtualLinks;
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
