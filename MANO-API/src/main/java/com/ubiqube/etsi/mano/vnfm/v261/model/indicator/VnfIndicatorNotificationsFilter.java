package com.ubiqube.etsi.mano.vnfm.v261.model.indicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.vnfm.v261.model.VnfInstanceSubscriptionFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription filter related to notifications about VNF
 * indicator value changes. At a particular nesting level in the filter
 * structure, the following applies: All attributes shall match in order for the
 * filter to match (logical \&quot;and\&quot; between different filter
 * attributes). If an attribute is an array, the attribute shall match if at
 * least one of the values in the array matches (logical \&quot;or\&quot;
 * between the values of one filter attribute).
 */
@ApiModel(description = "This type represents a subscription filter related to notifications about VNF indicator value changes. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T15:58:19.438+02:00")

public class VnfIndicatorNotificationsFilter {
	@JsonProperty("vnfInstanceSubscriptionFilter")
	private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = null;

	@JsonProperty("indicatorIds")
	@Valid
	private List<String> indicatorIds = null;

	public VnfIndicatorNotificationsFilter vnfInstanceSubscriptionFilter(final VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
		return this;
	}

	/**
	 * Filter criteria to select VNF instances about which to notify.
	 *
	 * @return vnfInstanceSubscriptionFilter
	 **/
	@ApiModelProperty(value = "Filter criteria to select VNF instances about which to notify. ")

	@Valid

	public VnfInstanceSubscriptionFilter getVnfInstanceSubscriptionFilter() {
		return vnfInstanceSubscriptionFilter;
	}

	public void setVnfInstanceSubscriptionFilter(final VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
	}

	public VnfIndicatorNotificationsFilter indicatorIds(final List<String> indicatorIds) {
		this.indicatorIds = indicatorIds;
		return this;
	}

	public VnfIndicatorNotificationsFilter addIndicatorIdsItem(final String indicatorIdsItem) {
		if (this.indicatorIds == null) {
			this.indicatorIds = new ArrayList<>();
		}
		this.indicatorIds.add(indicatorIdsItem);
		return this;
	}

	/**
	 * Match particular VNF indicator identifiers.
	 *
	 * @return indicatorIds
	 **/
	@ApiModelProperty(value = "Match particular VNF indicator identifiers. ")

	public List<String> getIndicatorIds() {
		return indicatorIds;
	}

	public void setIndicatorIds(final List<String> indicatorIds) {
		this.indicatorIds = indicatorIds;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfIndicatorNotificationsFilter vnfIndicatorNotificationsFilter = (VnfIndicatorNotificationsFilter) o;
		return Objects.equals(this.vnfInstanceSubscriptionFilter, vnfIndicatorNotificationsFilter.vnfInstanceSubscriptionFilter) &&
				Objects.equals(this.indicatorIds, vnfIndicatorNotificationsFilter.indicatorIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceSubscriptionFilter, indicatorIds);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfIndicatorNotificationsFilter {\n");

		sb.append("    vnfInstanceSubscriptionFilter: ").append(toIndentedString(vnfInstanceSubscriptionFilter)).append("\n");
		sb.append("    indicatorIds: ").append(toIndentedString(indicatorIds)).append("\n");
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
