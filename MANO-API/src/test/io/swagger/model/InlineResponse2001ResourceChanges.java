package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable. ")

public class InlineResponse2001ResourceChanges {

	private @Valid List<InlineResponse2001ResourceChangesAffectedVnfcs> affectedVnfcs = new ArrayList<InlineResponse2001ResourceChangesAffectedVnfcs>();
	private @Valid List<InlineResponse2001ResourceChangesAffectedVirtualLinks> affectedVirtualLinks = new ArrayList<InlineResponse2001ResourceChangesAffectedVirtualLinks>();
	private @Valid List<InlineResponse2001ResourceChangesAffectedVirtualStorages> affectedVirtualStorages = new ArrayList<InlineResponse2001ResourceChangesAffectedVirtualStorages>();

	/**
	 * Information about VNFC instances that were affected during the lifecycle
	 * operation. This allows the NFVO to obtain the information contained in the
	 * latest \&quot;result\&quot; notification if it has not received it due to an
	 * error or a wrongly configured subscription filter.
	 **/
	public InlineResponse2001ResourceChanges affectedVnfcs(List<InlineResponse2001ResourceChangesAffectedVnfcs> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
		return this;
	}

	@ApiModelProperty(value = "Information about VNFC instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	@JsonProperty("affectedVnfcs")
	public List<InlineResponse2001ResourceChangesAffectedVnfcs> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(List<InlineResponse2001ResourceChangesAffectedVnfcs> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	/**
	 * Information about VL instances that were affected during the lifecycle
	 * operation. This allows the NFVO to obtain the information contained in the
	 * latest \&quot;result\&quot; notification if it has not received it due to an
	 * error or a wrongly configured subscription filter.
	 **/
	public InlineResponse2001ResourceChanges affectedVirtualLinks(List<InlineResponse2001ResourceChangesAffectedVirtualLinks> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
		return this;
	}

	@ApiModelProperty(value = "Information about VL instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	@JsonProperty("affectedVirtualLinks")
	public List<InlineResponse2001ResourceChangesAffectedVirtualLinks> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(List<InlineResponse2001ResourceChangesAffectedVirtualLinks> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	/**
	 * Information about virtualised storage instances that were affected during the
	 * lifecycle operation. This allows the NFVO to obtain the information contained
	 * in the latest \&quot;result\&quot; notification if it has not received it due
	 * to an error or a wrongly configured subscription filter.
	 **/
	public InlineResponse2001ResourceChanges affectedVirtualStorages(List<InlineResponse2001ResourceChangesAffectedVirtualStorages> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
		return this;
	}

	@ApiModelProperty(value = "Information about virtualised storage instances that were affected during the lifecycle operation. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	@JsonProperty("affectedVirtualStorages")
	public List<InlineResponse2001ResourceChangesAffectedVirtualStorages> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(List<InlineResponse2001ResourceChangesAffectedVirtualStorages> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2001ResourceChanges inlineResponse2001ResourceChanges = (InlineResponse2001ResourceChanges) o;
		return Objects.equals(affectedVnfcs, inlineResponse2001ResourceChanges.affectedVnfcs) &&
				Objects.equals(affectedVirtualLinks, inlineResponse2001ResourceChanges.affectedVirtualLinks) &&
				Objects.equals(affectedVirtualStorages, inlineResponse2001ResourceChanges.affectedVirtualStorages);
	}

	@Override
	public int hashCode() {
		return Objects.hash(affectedVnfcs, affectedVirtualLinks, affectedVirtualStorages);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2001ResourceChanges {\n");

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
