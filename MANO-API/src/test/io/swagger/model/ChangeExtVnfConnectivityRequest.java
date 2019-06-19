package io.swagger.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents request parameters for the  \&quot;Change external VNF connectivity\&quot; operation to modify the external connectivity of a VNF instance.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents request parameters for the  \"Change external VNF connectivity\" operation to modify the external connectivity of a VNF instance. ")

public class ChangeExtVnfConnectivityRequest {

	private @Valid List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks = new ArrayList<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks>();
	private @Valid Object additionalParams = null;

	/**
	 * Information about external VLs to change (e.g. connect the VNF to).
	 **/
	public ChangeExtVnfConnectivityRequest extVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
		return this;
	}

	@ApiModelProperty(required = true, value = "Information about external VLs to change (e.g. connect the VNF to). ")
	@JsonProperty("extVirtualLinks")
	@NotNull
	public List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(List<VnfInstancesvnfInstanceIdinstantiateExtVirtualLinks> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public ChangeExtVnfConnectivityRequest additionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("additionalParams")
	public Object getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ChangeExtVnfConnectivityRequest {\n");

		sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
