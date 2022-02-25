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
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.utils.ToStringIgnore;
import com.ubiqube.etsi.mano.utils.ToStringUtil;

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
public class ExtManagedVirtualLinkDataEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vnfVirtualLinkDescId = null;

	// 3.3.1
	private String vnfdId;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String resourceId = null;

	private String vimLevelResourceType;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<LinkPortInfo> vnfLinkPorts;

	// 3.3.1
	private String extManagedMultisiteVirtualLinkId;

	@ToStringIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private GrantResponse grants;

	@ToStringIgnore
	@ManyToOne
	private VnfInstance vnfInstance;

	@Override
	public String toString() {
		return ToStringUtil.toString(this);
	}
}
