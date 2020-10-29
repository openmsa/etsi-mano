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
package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type specifies the parameters used for the update of an existing VNFFG
 * instance. It shall comply with the provisions defined in Table 6.5.3.37-1.
 */
@ApiModel(description = "This type specifies the parameters used for the update of an existing VNFFG instance. It shall comply with the provisions defined in Table 6.5.3.37-1. ")
@Validated


public class UpdateVnffgData {
	@JsonProperty("vnffgInfoId")
	private String vnffgInfoId = null;

	@JsonProperty("nfp")
	@Valid
	private List<NfpData> nfp = null;

	@JsonProperty("nfpInfoId")
	@Valid
	private List<String> nfpInfoId = null;

	public UpdateVnffgData vnffgInfoId(final String vnffgInfoId) {
		this.vnffgInfoId = vnffgInfoId;
		return this;
	}

	/**
	 * Identifier of an existing VNFFG to be updated for the NS Instance.
	 * 
	 * @return vnffgInfoId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of an existing VNFFG to be updated for the NS Instance. ")
	@NotNull

	public String getVnffgInfoId() {
		return vnffgInfoId;
	}

	public void setVnffgInfoId(final String vnffgInfoId) {
		this.vnffgInfoId = vnffgInfoId;
	}

	public UpdateVnffgData nfp(final List<NfpData> nfp) {
		this.nfp = nfp;
		return this;
	}

	public UpdateVnffgData addNfpItem(final NfpData nfpItem) {
		if (this.nfp == null) {
			this.nfp = new ArrayList<>();
		}
		this.nfp.add(nfpItem);
		return this;
	}

	/**
	 * Indicate the desired new NFP(s) for a given VNFFG after the operations of
	 * addition/removal of NS components (e.g. VNFs, VLs, etc.) have been completed,
	 * or indicate the updated or newly created NFP classification and selection
	 * rule which applied to an existing NFP.
	 * 
	 * @return nfp
	 **/
	@ApiModelProperty(value = "Indicate the desired new NFP(s) for a given VNFFG after the operations of addition/removal of NS components (e.g. VNFs, VLs, etc.) have been completed, or indicate the updated or newly created NFP classification and selection rule which applied to an existing NFP. ")

	@Valid

	public List<NfpData> getNfp() {
		return nfp;
	}

	public void setNfp(final List<NfpData> nfp) {
		this.nfp = nfp;
	}

	public UpdateVnffgData nfpInfoId(final List<String> nfpInfoId) {
		this.nfpInfoId = nfpInfoId;
		return this;
	}

	public UpdateVnffgData addNfpInfoIdItem(final String nfpInfoIdItem) {
		if (this.nfpInfoId == null) {
			this.nfpInfoId = new ArrayList<>();
		}
		this.nfpInfoId.add(nfpInfoIdItem);
		return this;
	}

	/**
	 * Identifier(s) of the NFP to be deleted from a given VNFFG.
	 * 
	 * @return nfpInfoId
	 **/
	@ApiModelProperty(value = "Identifier(s) of the NFP to be deleted from a given VNFFG. ")

	public List<String> getNfpInfoId() {
		return nfpInfoId;
	}

	public void setNfpInfoId(final List<String> nfpInfoId) {
		this.nfpInfoId = nfpInfoId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final UpdateVnffgData updateVnffgData = (UpdateVnffgData) o;
		return Objects.equals(this.vnffgInfoId, updateVnffgData.vnffgInfoId) &&
				Objects.equals(this.nfp, updateVnffgData.nfp) &&
				Objects.equals(this.nfpInfoId, updateVnffgData.nfpInfoId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnffgInfoId, nfp, nfpInfoId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class UpdateVnffgData {\n");

		sb.append("    vnffgInfoId: ").append(toIndentedString(vnffgInfoId)).append("\n");
		sb.append("    nfp: ").append(toIndentedString(nfp)).append("\n");
		sb.append("    nfpInfoId: ").append(toIndentedString(nfpInfoId)).append("\n");
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
