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
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.ubiqube.etsi.mano.dao.mano.pkg.VirtualCpu;

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
@Indexed
public class VnfCompute implements ToscaEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	private UUID id;

	private String toscaId;

	@FullTextField
	private String toscaName;

	private String state;

	@FullTextField
	private String name;

	@FullTextField
	private String description;

	@Column(length = 9000)
	@GenericField
	private String cloudInit;
	private String sourcePath;
	private String destinationPath;

	@Embedded
	private VirtualCpu virtualCpu = new VirtualCpu();

	@GenericField
	private long virtualMemorySize;

	@GenericField
	private long diskSize;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SoftwareImage softwareImage;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> storages;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> networks;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<MonitoringParams> monitoringParameters;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vnfCompute")
	private Set<VduInstantiationLevel> instantiationLevel;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<PlacementGroup> placementGroup;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> affinityRule;
	/**
	 * Initial delta.
	 */
	private Integer initialNumberOfInstance;

	@Embedded
	private Audit audit;

	@Embedded
	private VduProfile vduProfile = new VduProfile();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> securityGroup;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vnfCompute")
	private Set<VnfComputeAspectDelta> scalingAspectDeltas;

	public void addScalingAspectDeltas(final VnfComputeAspectDelta scalingDelta) {
		if (null == scalingAspectDeltas) {
			scalingAspectDeltas = new LinkedHashSet<>();
		}
		scalingDelta.setVnfCompute(this);
		scalingAspectDeltas.add(scalingDelta);
	}

	public void addVduInstantiationLevel(final VduInstantiationLevel _vduInstantiationLevel) {
		if (null == instantiationLevel) {
			instantiationLevel = new LinkedHashSet<>();
		}
		instantiationLevel.add(_vduInstantiationLevel);
	}

	public void addSecurityGroups(final String securityGroupName) {
		if (null == securityGroup) {
			securityGroup = new LinkedHashSet<>();
		}
		securityGroup.add(securityGroupName);
	}

	public void addAffinity(final String toscaName2) {
		if (null == affinityRule) {
			affinityRule = new LinkedHashSet<>();
		}
		affinityRule.add(toscaName2);
	}

}
