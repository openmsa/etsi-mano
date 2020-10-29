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

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an identifier of the CP or SAP instance. It shall comply
 * with the provisions defined in Table 6.5.3.56-1.
 */
@ApiModel(description = "This type represents an identifier of the CP or SAP instance.  It shall comply with the provisions defined in Table 6.5.3.56-1. ")
@Validated


public class NsCpHandle {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfExtCpInstanceId")
	private String vnfExtCpInstanceId = null;

	@JsonProperty("pnfInfoId")
	private String pnfInfoId = null;

	@JsonProperty("pnfExtCpInstanceId")
	private String pnfExtCpInstanceId = null;

	@JsonProperty("nsInstanceId")
	private String nsInstanceId = null;

	@JsonProperty("nsSapInstanceId")
	private String nsSapInstanceId = null;

	public NsCpHandle vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Identifier of the VNF instance associated to the CP instance. This attribute
	 * shall be present if the CP instance is VNF external CP.
	 * 
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(value = "Identifier of the VNF instance associated to the CP instance. This attribute shall be present if the CP instance is VNF external CP. ")

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public NsCpHandle vnfExtCpInstanceId(final String vnfExtCpInstanceId) {
		this.vnfExtCpInstanceId = vnfExtCpInstanceId;
		return this;
	}

	/**
	 * Identifier of the VNF external CP instance in the scope of the VNF instance.
	 * This attribute shall be present if the CP instance is VNF external CP. See
	 * notes 1 and 4.
	 * 
	 * @return vnfExtCpInstanceId
	 **/
	@ApiModelProperty(value = "Identifier of the VNF external CP instance in the scope of the VNF instance. This attribute shall be present if the CP instance is VNF external CP. See notes 1 and 4. ")

	public String getVnfExtCpInstanceId() {
		return vnfExtCpInstanceId;
	}

	public void setVnfExtCpInstanceId(final String vnfExtCpInstanceId) {
		this.vnfExtCpInstanceId = vnfExtCpInstanceId;
	}

	public NsCpHandle pnfInfoId(final String pnfInfoId) {
		this.pnfInfoId = pnfInfoId;
		return this;
	}

	/**
	 * Identifier of the PNF instance associated to the CP instance. This attribute
	 * shall be present if the CP instance is PNF external CP. See notes 2 and 4.
	 * 
	 * @return pnfInfoId
	 **/
	@ApiModelProperty(value = "Identifier of the PNF instance associated to the CP instance. This attribute shall be present if the CP instance is PNF external CP. See notes 2 and 4.           ")

	public String getPnfInfoId() {
		return pnfInfoId;
	}

	public void setPnfInfoId(final String pnfInfoId) {
		this.pnfInfoId = pnfInfoId;
	}

	public NsCpHandle pnfExtCpInstanceId(final String pnfExtCpInstanceId) {
		this.pnfExtCpInstanceId = pnfExtCpInstanceId;
		return this;
	}

	/**
	 * Identifier of the PNF external CP instance in the scope of the PNF. This
	 * attribute shall be present if the CP instance is PNF external CP. See notes 2
	 * and 4.
	 * 
	 * @return pnfExtCpInstanceId
	 **/
	@ApiModelProperty(value = "Identifier of the PNF external CP instance in the scope of the PNF. This attribute shall be present if the CP instance is PNF external CP. See notes 2 and 4. ")

	public String getPnfExtCpInstanceId() {
		return pnfExtCpInstanceId;
	}

	public void setPnfExtCpInstanceId(final String pnfExtCpInstanceId) {
		this.pnfExtCpInstanceId = pnfExtCpInstanceId;
	}

	public NsCpHandle nsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
		return this;
	}

	/**
	 * Identifier of the NS instance associated to the SAP instance. This attribute
	 * shall be present if the CP instance is NS SAP. See notes 3 and 4.
	 * 
	 * @return nsInstanceId
	 **/
	@ApiModelProperty(value = "Identifier of the NS instance associated to the SAP instance. This attribute shall be present if the CP instance is NS SAP. See notes 3 and 4.            ")

	public String getNsInstanceId() {
		return nsInstanceId;
	}

	public void setNsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
	}

	public NsCpHandle nsSapInstanceId(final String nsSapInstanceId) {
		this.nsSapInstanceId = nsSapInstanceId;
		return this;
	}

	/**
	 * Identifier of the SAP instance in the scope of the NS instance. This
	 * attribute shall be present if the CP instance is NS SAP. See notes 3 and 4.
	 * 
	 * @return nsSapInstanceId
	 **/
	@ApiModelProperty(value = "Identifier of the SAP instance in the scope of the NS instance. This attribute shall be present if the CP instance is NS SAP. See notes 3 and 4.            ")

	public String getNsSapInstanceId() {
		return nsSapInstanceId;
	}

	public void setNsSapInstanceId(final String nsSapInstanceId) {
		this.nsSapInstanceId = nsSapInstanceId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsCpHandle nsCpHandle = (NsCpHandle) o;
		return Objects.equals(this.vnfInstanceId, nsCpHandle.vnfInstanceId) &&
				Objects.equals(this.vnfExtCpInstanceId, nsCpHandle.vnfExtCpInstanceId) &&
				Objects.equals(this.pnfInfoId, nsCpHandle.pnfInfoId) &&
				Objects.equals(this.pnfExtCpInstanceId, nsCpHandle.pnfExtCpInstanceId) &&
				Objects.equals(this.nsInstanceId, nsCpHandle.nsInstanceId) &&
				Objects.equals(this.nsSapInstanceId, nsCpHandle.nsSapInstanceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, vnfExtCpInstanceId, pnfInfoId, pnfExtCpInstanceId, nsInstanceId, nsSapInstanceId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsCpHandle {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfExtCpInstanceId: ").append(toIndentedString(vnfExtCpInstanceId)).append("\n");
		sb.append("    pnfInfoId: ").append(toIndentedString(pnfInfoId)).append("\n");
		sb.append("    pnfExtCpInstanceId: ").append(toIndentedString(pnfExtCpInstanceId)).append("\n");
		sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
		sb.append("    nsSapInstanceId: ").append(toIndentedString(nsSapInstanceId)).append("\n");
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
