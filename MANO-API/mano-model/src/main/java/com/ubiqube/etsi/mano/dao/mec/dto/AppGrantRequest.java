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
package com.ubiqube.etsi.mano.dao.mec.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.GrantInterface;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantInformation;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
public class AppGrantRequest implements GrantInterface {
	private UUID id = null;

	private String appInstanceId = null;

	private String appLcmOpOccId = null;

	private String appDId = null;

	private NsdChangeType operation;

	private Set<GrantInformation> addResources = new HashSet<>();

	private Set<GrantInformation> tempResources = new HashSet<>();

	private Set<GrantInformation> removeResources = new HashSet<>();

	private Set<GrantInformation> updateResources = new HashSet<>();

	private GrantVimAssetsEntity vimAssets = null;
}
