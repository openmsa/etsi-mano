/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
/*
 * SOL003 - VNF Lifecycle Management interface
 * SOL003 - VNF Lifecycle Management interface definition  IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to. In case of discrepancies the published ETSI Group Specification takes precedence.  In clause 4.3.2 of ETSI GS NFV-SOL 003 v2.4.1, an attribute-based filtering mechanism is defined. This mechanism is currently not included in the corresponding OpenAPI design for this GS version. Changes to the attribute-based filtering mechanism are being considered in v2.5.1 of this GS for inclusion in the corresponding future ETSI NFV OpenAPI design. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis&list_id=61&product=NFV&resolution=
 *
 * OpenAPI spec version: 1.1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.ubiqube.etsi.mano.vnfm.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This attribute contains information about the cumulative changes to
 * virtualised resources that were performed so far by the LCM operation since
 * its start, if applicable.
 */
@ApiModel(description = "This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable. ")

public class VnfLcmOpOccResourceChanges {
	@JsonProperty("affectedVnfcs")
	private List<AffectedVnfc> affectedVnfcs = null;

	@JsonProperty("affectedVirtualLinks")
	private List<AffectedVirtualLink> affectedVirtualLinks = null;

	@JsonProperty("affectedVirtualStorages")
	private List<AffectedVirtualStorage> affectedVirtualStorages = null;

	public VnfLcmOpOccResourceChanges affectedVnfcs(List<AffectedVnfc> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
		return this;
	}

	public VnfLcmOpOccResourceChanges addAffectedVnfcsItem(AffectedVnfc affectedVnfcsItem) {
		if (this.affectedVnfcs == null) {
			this.affectedVnfcs = new ArrayList<AffectedVnfc>();
		}
		this.affectedVnfcs.add(affectedVnfcsItem);
		return this;
	}

	/**
	 * Information about VNFC instances that were affected during the lifecycle
	 * operation. This allows the NFVO to obtain the information contained in the
	 * latest \&quot;result\&quot; notification if it has not received it due to an
	 * error or a wrongly configured subscription filter.
	 * 
	 * @return affectedVnfcs
	 **/
	@JsonProperty("affectedVnfcs")
	@ApiModelProperty(value = "Information about VNFC instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	public List<AffectedVnfc> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(List<AffectedVnfc> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public VnfLcmOpOccResourceChanges affectedVirtualLinks(List<AffectedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
		return this;
	}

	public VnfLcmOpOccResourceChanges addAffectedVirtualLinksItem(AffectedVirtualLink affectedVirtualLinksItem) {
		if (this.affectedVirtualLinks == null) {
			this.affectedVirtualLinks = new ArrayList<AffectedVirtualLink>();
		}
		this.affectedVirtualLinks.add(affectedVirtualLinksItem);
		return this;
	}

	/**
	 * Information about VL instances that were affected during the lifecycle
	 * operation. This allows the NFVO to obtain the information contained in the
	 * latest \&quot;result\&quot; notification if it has not received it due to an
	 * error or a wrongly configured subscription filter.
	 * 
	 * @return affectedVirtualLinks
	 **/
	@JsonProperty("affectedVirtualLinks")
	@ApiModelProperty(value = "Information about VL instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	public List<AffectedVirtualLink> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(List<AffectedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	public VnfLcmOpOccResourceChanges affectedVirtualStorages(List<AffectedVirtualStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
		return this;
	}

	public VnfLcmOpOccResourceChanges addAffectedVirtualStoragesItem(AffectedVirtualStorage affectedVirtualStoragesItem) {
		if (this.affectedVirtualStorages == null) {
			this.affectedVirtualStorages = new ArrayList<AffectedVirtualStorage>();
		}
		this.affectedVirtualStorages.add(affectedVirtualStoragesItem);
		return this;
	}

	/**
	 * Information about virtualised storage instances that were affected during the
	 * lifecycle operation. This allows the NFVO to obtain the information contained
	 * in the latest \&quot;result\&quot; notification if it has not received it due
	 * to an error or a wrongly configured subscription filter.
	 * 
	 * @return affectedVirtualStorages
	 **/
	@JsonProperty("affectedVirtualStorages")
	@ApiModelProperty(value = "Information about virtualised storage instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	public List<AffectedVirtualStorage> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(List<AffectedVirtualStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
