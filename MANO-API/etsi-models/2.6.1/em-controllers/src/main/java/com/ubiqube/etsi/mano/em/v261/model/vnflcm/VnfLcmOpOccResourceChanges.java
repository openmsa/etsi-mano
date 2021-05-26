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
package com.ubiqube.etsi.mano.em.v261.model.vnflcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This attribute contains information about the cumulative changes to
 * virtualised resources that were performed so far by the LCM operation since
 * its start, if applicable.
 */
@ApiModel(description = "This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable. ")
@Validated
public class VnfLcmOpOccResourceChanges {
	@JsonProperty("affectedVnfcs")
	@Valid
	private List<AffectedVnfc> affectedVnfcs = null;

	@JsonProperty("affectedVirtualLinks")
	@Valid
	private List<AffectedVirtualLink> affectedVirtualLinks = null;

	@JsonProperty("affectedVirtualStorages")
	@Valid
	private List<AffectedVirtualStorage> affectedVirtualStorages = null;

	public VnfLcmOpOccResourceChanges affectedVnfcs(final List<AffectedVnfc> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
		return this;
	}

	public VnfLcmOpOccResourceChanges addAffectedVnfcsItem(final AffectedVnfc affectedVnfcsItem) {
		if (this.affectedVnfcs == null) {
			this.affectedVnfcs = new ArrayList<>();
		}
		this.affectedVnfcs.add(affectedVnfcsItem);
		return this;
	}

	/**
	 * Information about VNFC instances that were affected during the lifecycle
	 * operation. This allows the NFVO to obtain the information contained in the
	 * latest \"result\" notification if it has not received it due to an error or a
	 * wrongly configured subscription filter.
	 *
	 * @return affectedVnfcs
	 **/
	@ApiModelProperty(value = "Information about VNFC instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	@Valid
	public List<AffectedVnfc> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(final List<AffectedVnfc> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public VnfLcmOpOccResourceChanges affectedVirtualLinks(final List<AffectedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
		return this;
	}

	public VnfLcmOpOccResourceChanges addAffectedVirtualLinksItem(final AffectedVirtualLink affectedVirtualLinksItem) {
		if (this.affectedVirtualLinks == null) {
			this.affectedVirtualLinks = new ArrayList<>();
		}
		this.affectedVirtualLinks.add(affectedVirtualLinksItem);
		return this;
	}

	/**
	 * Information about VL instances that were affected during the lifecycle
	 * operation. This allows the NFVO to obtain the information contained in the
	 * latest \"result\" notification if it has not received it due to an error or a
	 * wrongly configured subscription filter.
	 *
	 * @return affectedVirtualLinks
	 **/
	@ApiModelProperty(value = "Information about VL instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	@Valid
	public List<AffectedVirtualLink> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(final List<AffectedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	public VnfLcmOpOccResourceChanges affectedVirtualStorages(final List<AffectedVirtualStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
		return this;
	}

	public VnfLcmOpOccResourceChanges addAffectedVirtualStoragesItem(final AffectedVirtualStorage affectedVirtualStoragesItem) {
		if (this.affectedVirtualStorages == null) {
			this.affectedVirtualStorages = new ArrayList<>();
		}
		this.affectedVirtualStorages.add(affectedVirtualStoragesItem);
		return this;
	}

	/**
	 * Information about virtualised storage instances that were affected during the
	 * lifecycle operation. This allows the NFVO to obtain the information contained
	 * in the latest \"result\" notification if it has not received it due to an
	 * error or a wrongly configured subscription filter.
	 *
	 * @return affectedVirtualStorages
	 **/
	@ApiModelProperty(value = "Information about virtualised storage instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	@Valid
	public List<AffectedVirtualStorage> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(final List<AffectedVirtualStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfLcmOpOccResourceChanges vnfLcmOpOccResourceChanges = (VnfLcmOpOccResourceChanges) o;
		return Objects.equals(this.affectedVnfcs, vnfLcmOpOccResourceChanges.affectedVnfcs) &&
				Objects.equals(this.affectedVirtualLinks, vnfLcmOpOccResourceChanges.affectedVirtualLinks) &&
				Objects.equals(this.affectedVirtualStorages, vnfLcmOpOccResourceChanges.affectedVirtualStorages);
	}

	@Override
	public int hashCode() {
		return Objects.hash(affectedVnfcs, affectedVirtualLinks, affectedVirtualStorages);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfLcmOpOccResourceChanges {\n");

		sb.append("    affectedVnfcs: ").append(toIndentedString(affectedVnfcs)).append("\n");
		sb.append("    affectedVirtualLinks: ").append(toIndentedString(affectedVirtualLinks)).append("\n");
		sb.append("    affectedVirtualStorages: ").append(toIndentedString(affectedVirtualStorages)).append("\n");
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
