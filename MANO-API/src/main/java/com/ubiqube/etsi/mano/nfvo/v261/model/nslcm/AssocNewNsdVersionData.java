package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type specifies a new NSD version that is associated to the NS instance.
 * After issuing the Update NS operation with updateType &#x3D;
 * \&quot;AssocNewNsdVersion\&quot;, the NFVO shall use the referred NSD as a
 * basis for the given NS instance. Different versions of the same NSD have same
 * nsdInvariantId, but different nsdId attributes, therefore if the
 * nsdInvariantId of the NSD version that is to be associated to this NS
 * instance is different from the one used before, the NFVO shall reject the
 * request. Only new versions of the same NSD can be associated to an existing
 * NS instance. This data type shall comply with the provisions defined in Table
 * 6.5.3.34-1.
 */
@ApiModel(description = "This type specifies a new NSD version that is associated to the NS instance. After issuing the Update NS operation with updateType = \"AssocNewNsdVersion\", the NFVO shall use the referred NSD as a basis for the given NS instance. Different versions of the same NSD have same nsdInvariantId, but different nsdId attributes, therefore if the nsdInvariantId of the NSD version that is to be associated to this NS instance is different from the one used before, the NFVO shall reject the request. Only new versions of the same NSD can be associated to an existing NS instance. This data type shall comply with the provisions defined in Table 6.5.3.34-1. ")
@Validated


public class AssocNewNsdVersionData {
	@JsonProperty("newNsdId")
	private String newNsdId = null;

	@JsonProperty("sync")
	private Boolean sync = null;

	public AssocNewNsdVersionData newNsdId(final String newNsdId) {
		this.newNsdId = newNsdId;
		return this;
	}

	/**
	 * Identifier of the new NSD version that is to be associated to the NS
	 * instance.
	 * 
	 * @return newNsdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the new NSD version that is to be associated to the NS instance. ")
	@NotNull

	public String getNewNsdId() {
		return newNsdId;
	}

	public void setNewNsdId(final String newNsdId) {
		this.newNsdId = newNsdId;
	}

	public AssocNewNsdVersionData sync(final Boolean sync) {
		this.sync = sync;
		return this;
	}

	/**
	 * Specify whether the NS instance shall be automatically synchronized to the
	 * new NSD by the NFVO (in case of true value) or the NFVO shall not do any
	 * action (in case of a false value) and wait for further guidance from OSS/BSS
	 * (i.e. waiting for OSS/BSS to issue NS lifecycle management operation to
	 * explicitly add/remove VNFs and modify information of VNF instances according
	 * to the new NSD). The synchronization to the new NSD means e.g.
	 * instantiating/adding those VNFs whose VNFD is referenced by the new NSD
	 * version but not referenced by the old one, terminating/removing those VNFs
	 * whose VNFD is referenced by the old NSD version but not referenced by the new
	 * NSD version, modifying information of VNF instances to the new applicable
	 * VNFD provided in the new NSD version. A cardinality of 0 indicates that
	 * synchronization shall not be done.
	 * 
	 * @return sync
	 **/
	@ApiModelProperty(value = "Specify whether the NS instance shall be automatically synchronized to the new NSD by the NFVO (in case of true value) or the NFVO shall not do any action (in case of a false value) and wait for further guidance from OSS/BSS (i.e. waiting for OSS/BSS to issue NS lifecycle management operation to explicitly add/remove VNFs and modify information of VNF instances according to the new NSD). The synchronization to the new NSD means e.g. instantiating/adding those VNFs whose VNFD is referenced by the new NSD version but not referenced by the old one, terminating/removing those VNFs whose VNFD is referenced by the old NSD version but not referenced by the new NSD version, modifying information of VNF instances to the new applicable VNFD provided in the new NSD version. A cardinality of 0 indicates that synchronization shall not be done. ")

	public Boolean isSync() {
		return sync;
	}

	public void setSync(final Boolean sync) {
		this.sync = sync;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AssocNewNsdVersionData assocNewNsdVersionData = (AssocNewNsdVersionData) o;
		return Objects.equals(this.newNsdId, assocNewNsdVersionData.newNsdId) &&
				Objects.equals(this.sync, assocNewNsdVersionData.sync);
	}

	@Override
	public int hashCode() {
		return Objects.hash(newNsdId, sync);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AssocNewNsdVersionData {\n");

		sb.append("    newNsdId: ").append(toIndentedString(newNsdId)).append("\n");
		sb.append("    sync: ").append(toIndentedString(sync)).append("\n");
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
