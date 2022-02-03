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

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Getter
@Indexed
@Setter
public class PnfDescriptor implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@FullTextField
	private String pnfdId;
	@FullTextField
	private String pnfdName;
	@FullTextField
	private String pnfdersion;
	@FullTextField
	private String pnfdProvider;
	@FullTextField
	private String pnfdInvariantId;
	@Enumerated(EnumType.STRING)
	private OnboardingStateType pnfdOnboardingState;
	@Embedded
	private FailureDetails onboardingFailureDetails;
	@Enumerated(EnumType.STRING)
	private PackageUsageState pnfdUsageState;
	private String userDefinedData;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> virtualLink;

	public void addVirtualLink(final String name) {
		if (null == virtualLink) {
			virtualLink = new HashSet<>();
		}
		virtualLink.add(name);
	}

}
