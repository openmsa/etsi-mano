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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ExtVirtualLinkData;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type specifies the information needed to change the current VNF package
 * for a VNF instance. Clause B.3 of the ETSI GS NFV-IFA 007 illustrates the
 * variants of changes to the current VNF Package and information flow
 * procedures. This operation encompasses the following scenarios: * Changes of
 * the VNF virtualised resources, such as requirements, composition and
 * structure between the VNF versions, without changing the VNF software
 * version. * Changes of both the VNF software version and the VNF virtualised
 * resources. This case includes replacing the VNF software version by means of
 * virtualised resources management, such as terminating the current virtualized
 * resource instances running the current software version and instantiating new
 * virtualized resource instances with the destination VNF software version. The
 * new virtualized resource instances may have the same characteristics as the
 * current virtualized resource instances. * Changes related to the VNFD, such
 * as correction of bugs in the VNFD, changes in the naming scheme of VNFD
 * components (e.g. name of the VDU, vduId), and adding/removing descriptors of
 * VNF Package changes (VnfPackageChangeInfo). NOTE: For software updates that
 * are executed by functional entities outside NFV-MANO and that require
 * synchronization of the information held by the NFV-MANO entities with a new
 * VNF package that reflects the same changes, an alternative procedure using
 * the PATCH method on the \&quot;Individual VNF instance\&quot; resource has
 * been defined, as illustrated in annex B.2 of ETSI GS NFV-IFA 007 [19]. This
 * procedure assumes certain restrictions on the characteristics of the new VNF
 * package, as defined in note 1 in table 5.5.2.2-1 of ETSI GS NFV-SOL 003. This
 * type shall comply with the provisions defined in Table 6.5.3.54-1.
 */
@Schema(description = "This type specifies the information needed to change the current VNF package for a VNF instance. Clause B.3 of the ETSI GS NFV-IFA 007 illustrates the variants of changes to the current VNF Package and information flow procedures. This operation encompasses the following scenarios: * Changes of the VNF virtualised resources, such as requirements, composition and structure between the VNF versions,   without changing the VNF software version. * Changes of both the VNF software version and the VNF virtualised resources. This case includes replacing the VNF   software version by means of virtualised resources management, such as terminating the current virtualized resource   instances running the current software version and instantiating new virtualized resource instances with the   destination VNF software version. The new virtualized resource instances may have the same characteristics as the   current virtualized resource instances. * Changes related to the VNFD, such as correction of bugs in the VNFD, changes in the naming scheme of VNFD components   (e.g. name of the VDU, vduId), and adding/removing descriptors of VNF Package changes (VnfPackageChangeInfo). NOTE: For software updates that are executed by functional entities outside NFV-MANO and that require synchronization       of the information held by the NFV-MANO entities with a new VNF package that reflects the same changes, an       alternative procedure using the PATCH method on the \"Individual VNF instance\" resource has been defined, as       illustrated in annex B.2 of ETSI GS NFV-IFA 007 [19]. This procedure assumes certain restrictions on the       characteristics of the new VNF package, as defined in note 1 in table 5.5.2.2-1 of ETSI GS NFV-SOL 003. This type shall comply with the provisions defined in Table 6.5.3.54-1. ")
@Validated

public class ChangeVnfPackageData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("extVirtualLinks")
	@Valid
	private List<ExtVirtualLinkData> extVirtualLinks = null;

	@JsonProperty("extManagedVirtualLinks")
	@Valid
	private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	@JsonProperty("extensions")
	private Map<String, String> extensions = null;

	@JsonProperty("vnfConfigurableProperties")
	private Map<String, String> vnfConfigurableProperties = null;

	public ChangeVnfPackageData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public ChangeVnfPackageData vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get vnfdId
	 *
	 * @return vnfdId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public ChangeVnfPackageData extVirtualLinks(final List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
		return this;
	}

	public ChangeVnfPackageData addExtVirtualLinksItem(final ExtVirtualLinkData extVirtualLinksItem) {
		if (this.extVirtualLinks == null) {
			this.extVirtualLinks = new ArrayList<>();
		}
		this.extVirtualLinks.add(extVirtualLinksItem);
		return this;
	}

	/**
	 * Information about external VLs to connect the VNF to. Entries in the list
	 * that are unchanged need not be supplied as part of this request.
	 *
	 * @return extVirtualLinks
	 **/
	@Schema(description = "Information about external VLs to connect the VNF to. Entries in the list that are unchanged need not be supplied as part of this request. ")
	@Valid
	public List<ExtVirtualLinkData> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(final List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	public ChangeVnfPackageData extManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
		return this;
	}

	public ChangeVnfPackageData addExtManagedVirtualLinksItem(final ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
		if (this.extManagedVirtualLinks == null) {
			this.extManagedVirtualLinks = new ArrayList<>();
		}
		this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
		return this;
	}

	/**
	 * Information about internal VLs that are managed by the NFVO.
	 *
	 * @return extManagedVirtualLinks
	 **/
	@Schema(description = "Information about internal VLs that are managed by the NFVO. ")
	@Valid
	public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public ChangeVnfPackageData additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public ChangeVnfPackageData extensions(final Map<String, String> extensions) {
		this.extensions = extensions;
		return this;
	}

	/**
	 * Get extensions
	 *
	 * @return extensions
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getExtensions() {
		return extensions;
	}

	public void setExtensions(final Map<String, String> extensions) {
		this.extensions = extensions;
	}

	public ChangeVnfPackageData vnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	/**
	 * Get vnfConfigurableProperties
	 *
	 * @return vnfConfigurableProperties
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public void setVnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ChangeVnfPackageData changeVnfPackageData = (ChangeVnfPackageData) o;
		return Objects.equals(this.vnfInstanceId, changeVnfPackageData.vnfInstanceId) &&
				Objects.equals(this.vnfdId, changeVnfPackageData.vnfdId) &&
				Objects.equals(this.extVirtualLinks, changeVnfPackageData.extVirtualLinks) &&
				Objects.equals(this.extManagedVirtualLinks, changeVnfPackageData.extManagedVirtualLinks) &&
				Objects.equals(this.additionalParams, changeVnfPackageData.additionalParams) &&
				Objects.equals(this.extensions, changeVnfPackageData.extensions) &&
				Objects.equals(this.vnfConfigurableProperties, changeVnfPackageData.vnfConfigurableProperties);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, vnfdId, extVirtualLinks, extManagedVirtualLinks, additionalParams, extensions, vnfConfigurableProperties);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ChangeVnfPackageData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
		sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
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
