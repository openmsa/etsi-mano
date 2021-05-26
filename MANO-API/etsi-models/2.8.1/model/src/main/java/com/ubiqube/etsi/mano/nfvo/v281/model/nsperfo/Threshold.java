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
package com.ubiqube.etsi.mano.nfvo.v281.model.nsperfo;

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
 * This type represents a threshold.
 */
@ApiModel(description = "This type represents a threshold. ")
@Validated

public class Threshold {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("objectType")
	private String objectType = null;

	@JsonProperty("objectInstanceId")
	private String objectInstanceId = null;

	@JsonProperty("subObjectInstanceIds")
	@Valid
	private List<String> subObjectInstanceIds = null;

	@JsonProperty("criteria")
	private ThresholdCriteria criteria = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("_links")
	private ThresholdLinks links = null;

	public Threshold id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this threshold resource.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this threshold resource. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Threshold objectType(final String objectType) {
		this.objectType = objectType;
		return this;
	}

	/**
	 * Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.3 of ETSI GS NFV IFA 027.
	 *
	 * @return objectType
	 **/
	@ApiModelProperty(required = true, value = "Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.3 of ETSI GS NFV IFA 027. ")
	@NotNull

	@Valid

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(final String objectType) {
		this.objectType = objectType;
	}

	public Threshold objectInstanceId(final String objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
		return this;
	}

	/**
	 * Identifier of the NS instance associated with the threshold.
	 *
	 * @return objectInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the NS instance associated with the threshold. ")
	@NotNull

	public String getObjectInstanceId() {
		return objectInstanceId;
	}

	public void setObjectInstanceId(final String objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
	}

	public Threshold subObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
		return this;
	}

	public Threshold addSubObjectInstanceIdsItem(final String subObjectInstanceIdsItem) {
		if (this.subObjectInstanceIds == null) {
			this.subObjectInstanceIds = new ArrayList<>();
		}
		this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
		return this;
	}

	/**
	 * Identifiers of the sub-object instances of the measured object instance associated with the threshold. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measurement type. If this attribute is absent and a sub-object is defined n clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance.
	 *
	 * @return subObjectInstanceIds
	 **/
	@ApiModelProperty(value = "Identifiers of the sub-object instances of the measured object instance associated with the threshold. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measurement type. If this attribute is absent and a sub-object is defined n clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. ")

	public List<String> getSubObjectInstanceIds() {
		return subObjectInstanceIds;
	}

	public void setSubObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
	}

	public Threshold criteria(final ThresholdCriteria criteria) {
		this.criteria = criteria;
		return this;
	}

	/**
	 * Criteria that define this threshold.
	 *
	 * @return criteria
	 **/
	@ApiModelProperty(required = true, value = "Criteria that define this threshold. ")
	@NotNull

	@Valid

	public ThresholdCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(final ThresholdCriteria criteria) {
		this.criteria = criteria;
	}

	public Threshold callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * The URI of the endpoint to send the notification to.
	 *
	 * @return callbackUri
	 **/
	@ApiModelProperty(required = true, value = "The URI of the endpoint to send the notification to. ")
	@NotNull

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public Threshold links(final ThresholdLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public ThresholdLinks getLinks() {
		return links;
	}

	public void setLinks(final ThresholdLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final Threshold threshold = (Threshold) o;
		return Objects.equals(this.id, threshold.id) &&
				Objects.equals(this.objectType, threshold.objectType) &&
				Objects.equals(this.objectInstanceId, threshold.objectInstanceId) &&
				Objects.equals(this.subObjectInstanceIds, threshold.subObjectInstanceIds) &&
				Objects.equals(this.criteria, threshold.criteria) &&
				Objects.equals(this.callbackUri, threshold.callbackUri) &&
				Objects.equals(this.links, threshold.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, objectType, objectInstanceId, subObjectInstanceIds, criteria, callbackUri, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Threshold {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
		sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
		sb.append("    subObjectInstanceIds: ").append(toIndentedString(subObjectInstanceIds)).append("\n");
		sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
