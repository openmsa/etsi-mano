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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.util.HashSet;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mec.pkg.FeatureDependency;
import com.ubiqube.etsi.mano.dao.mec.pkg.LatencyDescriptor;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
public class AppPkgDto {

	private String appDId;

	private String appSoftwareVersion;

	private String appDVersion;

	private String appDescription;

	private String appInfoName;

	private LatencyDescriptor appLatency;

	private String appName;

	private String appProvider;

	private String appSoftVersion;

	private String changeAppInstanceStateOpConfig;

	private final Set<String> mecVersion = new HashSet<>();

	private String terminateAppInstanceOpConfig;

	private VnfCompute virtualComputeDescriptor;

	private Set<FeatureDependency> appFeatureOptional;

	private Set<FeatureDependency> appFeatureRequired;
}
