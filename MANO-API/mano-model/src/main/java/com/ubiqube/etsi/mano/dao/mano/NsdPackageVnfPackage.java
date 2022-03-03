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
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.nsd.ForwarderMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScalingLevelMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScalingStepMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NsdPackageVnfPackage implements Levelable<VnfScalingStepMapping, VnfScalingLevelMapping>, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne(cascade = CascadeType.DETACH)
	private VnfPackage vnfPackage;

	@ManyToOne
	private NsdPackage nsdPackage;

	private String toscaName;

	private String toscaId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VnfScalingStepMapping> stepMapping;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<VnfScalingLevelMapping> levelMapping;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderColumn
	private Set<ListKeyPair> virtualLinks;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ForwarderMapping> forwardMapping;

	public void addStepMapping(final VnfScalingStepMapping scaling) {
		if (null == stepMapping) {
			stepMapping = new LinkedHashSet<>();
		}
		stepMapping.add(scaling);
	}

	public void addLevelMapping(final VnfScalingLevelMapping mapping) {
		if (null == levelMapping) {
			levelMapping = new LinkedHashSet<>();
		}
		levelMapping.add(mapping);
	}

	public void addVirtualLink(final String name) {
		if (null == virtualLinks) {
			virtualLinks = new LinkedHashSet<>();
		}
		virtualLinks.add(new ListKeyPair(name, virtualLinks.size()));
	}

	public void addForwardMapping(final ForwarderMapping forwarderMapping) {
		if (null == forwardMapping) {
			forwardMapping = new LinkedHashSet<>();
		}
		forwardMapping.add(forwarderMapping);
	}
}
