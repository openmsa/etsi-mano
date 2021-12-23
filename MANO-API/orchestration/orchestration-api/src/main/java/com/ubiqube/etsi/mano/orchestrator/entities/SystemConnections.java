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
package com.ubiqube.etsi.mano.orchestrator.entities;

import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.GeoPointBinding;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import com.ubiqube.etsi.mano.dao.mano.common.GeoPoint;

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
public class SystemConnections {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID id = null;

	@FullTextField
	@Column(unique = true)
	private final String vimId = null;

	@FullTextField
	private final String vimType = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private final Map<String, String> interfaceInfo = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private final Map<String, String> accessInfo = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private final Map<String, String> extra = null;

	@GeoPointBinding
	private GeoPoint geoloc;

}
