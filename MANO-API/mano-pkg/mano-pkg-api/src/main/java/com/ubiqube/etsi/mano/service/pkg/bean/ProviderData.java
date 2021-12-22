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
package com.ubiqube.etsi.mano.service.pkg.bean;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.MonitoringParams;
import com.ubiqube.etsi.mano.dao.mano.pkg.VnfProfile;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class ProviderData {

	private String vnfProvider;

	private String vnfProductName;
	/**
	 * Human readable description of the VNF Product
	 */
	private String productInfoDescription;

	private String vnfSoftwareVersion;

	private String vnfVersion;

	private String flavorId;
	/**
	 * Human readable description of the DF
	 */
	@NotNull
	private String flavourDescription;

	private String vnfdVersion;

	private String vnfdId;

	private String descriptorId;

	private String descriptorVersion;

	private Set<MonitoringParams> monitoringParameters;

	private Set<String> vnfmInfo;

	private VnfProfile vnfProfile;

	/**
	 * Default localization language that is instantiated if no information about
	 * selected localization language is available
	 */
	private String defaultLocalizationLanguage;
	/**
	 * Information about localization languages of the VNF
	 */
	private Set<String> localizationLanguages;
	/**
	 * Scale status of the VNF, one entry per aspect. Represents for every scaling
	 * aspect how "big" the VNF has been scaled w.r.t. that aspect.
	 */
	@NotNull
	private Set<ScaleInfo> scaleStatus;

	private String virtualLinkReq;
}
