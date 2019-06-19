package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents subscription filter criteria to match VNF instances.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents subscription filter criteria to match VNF instances. ")

public class InlineResponse2002FilterVnfInstanceSubscriptionFilter {

	private @Valid List<String> vnfdIds = new ArrayList<String>();
	private @Valid List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders = new ArrayList<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders>();
	private @Valid List<String> vnfInstanceIds = new ArrayList<String>();
	private @Valid List<String> vnfInstanceNames = new ArrayList<String>();

	/**
	 * If present, match VNF instances that were created based on a VNFD identified
	 * by one of the vnfdId values listed in this attribute. The attributes
	 * \&quot;vnfdIds\&quot; and \&quot;vnfProductsFromProviders\&quot; are
	 * alternatives to reference to VNF instances that are based on certain VNFDs in
	 * a filter. They should not be used both in the same filter instance, but one
	 * alternative should be chosen.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilter vnfdIds(List<String> vnfdIds) {
		this.vnfdIds = vnfdIds;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	@JsonProperty("vnfdIds")
	public List<String> getVnfdIds() {
		return vnfdIds;
	}

	public void setVnfdIds(List<String> vnfdIds) {
		this.vnfdIds = vnfdIds;
	}

	/**
	 * If present, match VNF instances that belong to VNF products from certain
	 * providers. The attributes \&quot;vnfdIds\&quot; and
	 * \&quot;vnfProductsFromProviders\&quot; are alternatives to reference to VNF
	 * instances that are based on certain VNFDs in a filter. They should not be
	 * used both in the same filter instance, but one alternative should be chosen.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilter vnfProductsFromProviders(List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders) {
		this.vnfProductsFromProviders = vnfProductsFromProviders;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances that belong to VNF products from certain providers. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	@JsonProperty("vnfProductsFromProviders")
	public List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders> getVnfProductsFromProviders() {
		return vnfProductsFromProviders;
	}

	public void setVnfProductsFromProviders(List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders) {
		this.vnfProductsFromProviders = vnfProductsFromProviders;
	}

	/**
	 * If present, match VNF instances with an instance identifier listed in this
	 * attribute. The attributes \&quot;vnfInstanceIds\&quot; and
	 * \&quot;vnfInstanceNames\&quot; are alternatives to reference to particular
	 * VNF Instances in a filter. They should not be used both in the same filter
	 * instance, but one alternative should be chosen.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilter vnfInstanceIds(List<String> vnfInstanceIds) {
		this.vnfInstanceIds = vnfInstanceIds;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances with an instance identifier listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	@JsonProperty("vnfInstanceIds")
	public List<String> getVnfInstanceIds() {
		return vnfInstanceIds;
	}

	public void setVnfInstanceIds(List<String> vnfInstanceIds) {
		this.vnfInstanceIds = vnfInstanceIds;
	}

	/**
	 * If present, match VNF instances with a VNF Instance Name listed in this
	 * attribute. The attributes \&quot;vnfInstanceIds\&quot; and
	 * \&quot;vnfInstanceNames\&quot; are alternatives to reference to particular
	 * VNF Instances in a filter. They should not be used both in the same filter
	 * instance, but one alternative should be chosen.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilter vnfInstanceNames(List<String> vnfInstanceNames) {
		this.vnfInstanceNames = vnfInstanceNames;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances with a VNF Instance Name listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	@JsonProperty("vnfInstanceNames")
	public List<String> getVnfInstanceNames() {
		return vnfInstanceNames;
	}

	public void setVnfInstanceNames(List<String> vnfInstanceNames) {
		this.vnfInstanceNames = vnfInstanceNames;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2002FilterVnfInstanceSubscriptionFilter inlineResponse2002FilterVnfInstanceSubscriptionFilter = (InlineResponse2002FilterVnfInstanceSubscriptionFilter) o;
		return Objects.equals(vnfdIds, inlineResponse2002FilterVnfInstanceSubscriptionFilter.vnfdIds) &&
				Objects.equals(vnfProductsFromProviders, inlineResponse2002FilterVnfInstanceSubscriptionFilter.vnfProductsFromProviders) &&
				Objects.equals(vnfInstanceIds, inlineResponse2002FilterVnfInstanceSubscriptionFilter.vnfInstanceIds) &&
				Objects.equals(vnfInstanceNames, inlineResponse2002FilterVnfInstanceSubscriptionFilter.vnfInstanceNames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfdIds, vnfProductsFromProviders, vnfInstanceIds, vnfInstanceNames);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2002FilterVnfInstanceSubscriptionFilter {\n");

		sb.append("    vnfdIds: ").append(toIndentedString(vnfdIds)).append("\n");
		sb.append("    vnfProductsFromProviders: ").append(toIndentedString(vnfProductsFromProviders)).append("\n");
		sb.append("    vnfInstanceIds: ").append(toIndentedString(vnfInstanceIds)).append("\n");
		sb.append("    vnfInstanceNames: ").append(toIndentedString(vnfInstanceNames)).append("\n");
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
