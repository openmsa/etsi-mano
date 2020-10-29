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
package com.ubiqube.etsi.mano.common.v261.model.lcmgrant;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type contains a mapping between a software image definition the VNFD and
 * the corresponding software image managed by the NFVO in the VIM which is
 * needed during compute resource instantiation.
 */
@ApiModel(description = "This type contains a mapping between a software image definition the VNFD and the corresponding software image managed by the NFVO in the VIM which is needed during compute resource instantiation. ")
@Validated
public class VimSoftwareImage {
	@JsonProperty("vimConnectionId")
	private String vimConnectionId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("vnfdSoftwareImageId")
	private String vnfdSoftwareImageId = null;

	@JsonProperty("vimSoftwareImageId")
	private String vimSoftwareImageId = null;

	public VimSoftwareImage vimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	/**
	 * Identifier of the VIM connection to access the software image referenced in
	 * this structure. The applicable \"VimConnectionInfo\" structure, which is
	 * referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\"
	 * attribute of the \"VnfInstance\" structure. This attribute shall only be
	 * supported and present if VNF-related resource management in direct mode is
	 * applicable.
	 *
	 * @return vimConnectionId
	 **/
	@ApiModelProperty(value = "Identifier of the VIM connection to access the software image referenced in this structure. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. ")

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public VimSoftwareImage resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Identifies the entity responsible for the management of the virtualised
	 * resource. This attribute shall only be supported and present if VNF-related
	 * resource management in indirect mode is applicable. The identification scheme
	 * is outside the scope of the present document.
	 *
	 * @return resourceProviderId
	 **/
	@ApiModelProperty(value = "Identifies the entity responsible for the management of the virtualised resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public VimSoftwareImage vnfdSoftwareImageId(final String vnfdSoftwareImageId) {
		this.vnfdSoftwareImageId = vnfdSoftwareImageId;
		return this;
	}

	/**
	 * Identifier which references the software image descriptor in the VNFD.
	 *
	 * @return vnfdSoftwareImageId
	 **/
	@ApiModelProperty(required = true, value = "Identifier which references the software image descriptor in the VNFD. ")
	@NotNull

	public String getVnfdSoftwareImageId() {
		return vnfdSoftwareImageId;
	}

	public void setVnfdSoftwareImageId(final String vnfdSoftwareImageId) {
		this.vnfdSoftwareImageId = vnfdSoftwareImageId;
	}

	public VimSoftwareImage vimSoftwareImageId(final String vimSoftwareImageId) {
		this.vimSoftwareImageId = vimSoftwareImageId;
		return this;
	}

	/**
	 * Identifier of the software image in the resource management layer (i.e. VIM).
	 *
	 * @return vimSoftwareImageId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the software image in the resource management layer (i.e. VIM). ")
	@NotNull

	public String getVimSoftwareImageId() {
		return vimSoftwareImageId;
	}

	public void setVimSoftwareImageId(final String vimSoftwareImageId) {
		this.vimSoftwareImageId = vimSoftwareImageId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VimSoftwareImage vimSoftwareImage = (VimSoftwareImage) o;
		return Objects.equals(this.vimConnectionId, vimSoftwareImage.vimConnectionId) &&
				Objects.equals(this.resourceProviderId, vimSoftwareImage.resourceProviderId) &&
				Objects.equals(this.vnfdSoftwareImageId, vimSoftwareImage.vnfdSoftwareImageId) &&
				Objects.equals(this.vimSoftwareImageId, vimSoftwareImage.vimSoftwareImageId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vimConnectionId, resourceProviderId, vnfdSoftwareImageId, vimSoftwareImageId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VimSoftwareImage {\n");

		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    vnfdSoftwareImageId: ").append(toIndentedString(vnfdSoftwareImageId)).append("\n");
		sb.append("    vimSoftwareImageId: ").append(toIndentedString(vimSoftwareImageId)).append("\n");
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
