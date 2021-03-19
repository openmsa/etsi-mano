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
package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PkgmNotificationsFilterVnfProductsFromProviders1
 */
@Validated
public class PkgmNotificationsFilterVnfProductsFromProviders1 {
	@JsonProperty("vnfProvider")
	private String vnfProvider = null;

	@JsonProperty("vnfProductsFromProviders")
	@Valid
	private List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders = null;

	@JsonProperty("vnfdId")
	@Valid
	private List<String> vnfdId = null;

	@JsonProperty("vnfPkgId")
	@Valid
	private List<String> vnfPkgId = null;

	@JsonProperty("operationalState")
	@Valid
	private List<PackageOperationalStateType> operationalState = null;

	@JsonProperty("usageState")
	@Valid
	private List<PackageUsageStateType> usageState = null;

	@JsonProperty("vnfmInfo")
	@Valid
	private List<String> vnfmInfo = null;

	public PkgmNotificationsFilterVnfProductsFromProviders1 vnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	/**
	 * Name of the VNFprovider to match.
	 *
	 * @return vnfProvider
	 **/
	@ApiModelProperty(required = true, value = "Name of the VNFprovider to match. ")
	@NotNull

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 vnfProductsFromProviders(final List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders) {
		this.vnfProductsFromProviders = vnfProductsFromProviders;
		return this;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 addVnfProductsFromProvidersItem(final PkgmNotificationsFilterVnfProductsFromProviders vnfProductsFromProvidersItem) {
		if (this.vnfProductsFromProviders == null) {
			this.vnfProductsFromProviders = new ArrayList<>();
		}
		this.vnfProductsFromProviders.add(vnfProductsFromProvidersItem);
		return this;
	}

	/**
	 * If present, match VNF packages that contain VNF products with certain product
	 * names, from one particular provider.
	 *
	 * @return vnfProductsFromProviders
	 **/
	@ApiModelProperty(value = "If present, match VNF packages that contain VNF products with certain product names, from one particular provider. ")
	@Valid
	public List<PkgmNotificationsFilterVnfProductsFromProviders> getVnfProductsFromProviders() {
		return vnfProductsFromProviders;
	}

	public void setVnfProductsFromProviders(final List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders) {
		this.vnfProductsFromProviders = vnfProductsFromProviders;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 vnfdId(final List<String> vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 addVnfdIdItem(final String vnfdIdItem) {
		if (this.vnfdId == null) {
			this.vnfdId = new ArrayList<>();
		}
		this.vnfdId.add(vnfdIdItem);
		return this;
	}

	/**
	 * Match VNF packages with a VNFD identifier listed in the attribute.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(value = "Match VNF packages with a VNFD identifier listed in the attribute. ")

	public List<String> getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final List<String> vnfdId) {
		this.vnfdId = vnfdId;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 vnfPkgId(final List<String> vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
		return this;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 addVnfPkgIdItem(final String vnfPkgIdItem) {
		if (this.vnfPkgId == null) {
			this.vnfPkgId = new ArrayList<>();
		}
		this.vnfPkgId.add(vnfPkgIdItem);
		return this;
	}

	/**
	 * Match VNF packages with a package identifier listed in the attribute. May be
	 * present if the \"notificationTypes\" attribute contains the value
	 * \"VnfPackageChangeNotification\", and shall be absent otherwise.
	 *
	 * @return vnfPkgId
	 **/
	@ApiModelProperty(value = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")

	public List<String> getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(final List<String> vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 operationalState(final List<PackageOperationalStateType> operationalState) {
		this.operationalState = operationalState;
		return this;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 addOperationalStateItem(final PackageOperationalStateType operationalStateItem) {
		if (this.operationalState == null) {
			this.operationalState = new ArrayList<>();
		}
		this.operationalState.add(operationalStateItem);
		return this;
	}

	/**
	 * Match VNF packages with a package identifier listed in the attribute. May be
	 * present if the \"notificationTypes\" attribute contains the value
	 * \"VnfPackageChangeNotification\", and shall be absent otherwise.
	 *
	 * @return operationalState
	 **/
	@ApiModelProperty(value = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")
	@Valid
	public List<PackageOperationalStateType> getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(final List<PackageOperationalStateType> operationalState) {
		this.operationalState = operationalState;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 usageState(final List<PackageUsageStateType> usageState) {
		this.usageState = usageState;
		return this;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 addUsageStateItem(final PackageUsageStateType usageStateItem) {
		if (this.usageState == null) {
			this.usageState = new ArrayList<>();
		}
		this.usageState.add(usageStateItem);
		return this;
	}

	/**
	 * Match particular usage state of the on-boarded VNF package. May be present if
	 * the \"notificationTypes\" attribute contains the value
	 * \"VnfPackageChangeNotification\", and shall be absent otherwise.
	 *
	 * @return usageState
	 **/
	@ApiModelProperty(value = "Match particular usage state of the on-boarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")
	@Valid
	public List<PackageUsageStateType> getUsageState() {
		return usageState;
	}

	public void setUsageState(final List<PackageUsageStateType> usageState) {
		this.usageState = usageState;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 vnfmInfo(final List<String> vnfmInfo) {
		this.vnfmInfo = vnfmInfo;
		return this;
	}

	public PkgmNotificationsFilterVnfProductsFromProviders1 addVnfmInfoItem(final String vnfmInfoItem) {
		if (this.vnfmInfo == null) {
			this.vnfmInfo = new ArrayList<>();
		}
		this.vnfmInfo.add(vnfmInfoItem);
		return this;
	}

	/**
	 * Match strings that specify VNFMs compatible with the VNF. See Table
	 * 9.5.2.5-1.
	 *
	 * @return vnfmInfo
	 **/
	@ApiModelProperty(value = "Match strings that specify VNFMs compatible with the VNF. See Table 9.5.2.5-1. ")

	public List<String> getVnfmInfo() {
		return vnfmInfo;
	}

	public void setVnfmInfo(final List<String> vnfmInfo) {
		this.vnfmInfo = vnfmInfo;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PkgmNotificationsFilterVnfProductsFromProviders1 pkgmNotificationsFilterVnfProductsFromProviders1 = (PkgmNotificationsFilterVnfProductsFromProviders1) o;
		return Objects.equals(this.vnfProvider, pkgmNotificationsFilterVnfProductsFromProviders1.vnfProvider) &&
				Objects.equals(this.vnfProductsFromProviders, pkgmNotificationsFilterVnfProductsFromProviders1.vnfProductsFromProviders) &&
				Objects.equals(this.vnfdId, pkgmNotificationsFilterVnfProductsFromProviders1.vnfdId) &&
				Objects.equals(this.vnfPkgId, pkgmNotificationsFilterVnfProductsFromProviders1.vnfPkgId) &&
				Objects.equals(this.operationalState, pkgmNotificationsFilterVnfProductsFromProviders1.operationalState) &&
				Objects.equals(this.usageState, pkgmNotificationsFilterVnfProductsFromProviders1.usageState) &&
				Objects.equals(this.vnfmInfo, pkgmNotificationsFilterVnfProductsFromProviders1.vnfmInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProvider, vnfProductsFromProviders, vnfdId, vnfPkgId, operationalState, usageState, vnfmInfo);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PkgmNotificationsFilterVnfProductsFromProviders1 {\n");

		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProductsFromProviders: ").append(toIndentedString(vnfProductsFromProviders)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
		sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
		sb.append("    usageState: ").append(toIndentedString(usageState)).append("\n");
		sb.append("    vnfmInfo: ").append(toIndentedString(vnfmInfo)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
