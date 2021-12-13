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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScalingLevelMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScalingStepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsVnfScalingStepMapping;

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
@NoArgsConstructor
@Setter
public class NsdPackageNsdPackage implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private NsdPackage parent;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsdPackage child;

	private String toscaName;

	private String toscaId;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> virtualLinks;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<NsVnfScalingStepMapping> stepMapping;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<NsScalingLevelMapping> levelMapping;

	public NsdPackageNsdPackage(final NsdPackage parent, final NsdPackage child, final String toscaName, final Set<String> virtualLinks) {
		super();
		this.parent = parent;
		this.child = child;
		this.toscaName = toscaName;
		this.virtualLinks = virtualLinks;
	}

	public void addVirtualLink(final String vl) {
		if (null == virtualLinks) {
			this.virtualLinks = new HashSet<>();
		}
		virtualLinks.add(vl);
	}

	public void addStepMapping(final NsScalingStepMapping scaling) {
		if (null == stepMapping) {
			stepMapping = new HashSet<>();
		}
		stepMapping.add(scaling);
	}

	public void addLevelMapping(final NsScalingLevelMapping mapping) {
		if (null == levelMapping) {
			levelMapping = new HashSet<>();
		}
		levelMapping.add(mapping);
	}
}
