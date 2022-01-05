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
package com.ubiqube.etsi.mano.dao.mano.pkg;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Embeddable
@Getter
@Setter
public class VirtualCpu implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String virtualCpuOversubscriptionPolicy;

	@FullTextField
	private String cpuArchitecture;

	private Double virtualCpuClock;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> vduCpuRequirements;

	@GenericField
	private long numVirtualCpu;

	private String virtualCpuPinningPolicy;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> virtualCpuPinningRule;
}
