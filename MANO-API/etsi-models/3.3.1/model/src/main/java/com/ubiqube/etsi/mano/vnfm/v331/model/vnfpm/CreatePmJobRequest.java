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
package com.ubiqube.etsi.mano.vnfm.v331.model.vnfpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v331.model.SubscriptionAuthentication;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a request to create a PM job.
 */
@Schema(description = "This type represents a request to create a PM job. ")
@Validated

public class CreatePmJobRequest {
	@JsonProperty("objectType")
	private String objectType = null;

	@JsonProperty("objectInstanceIds")
	@Valid
	private List<String> objectInstanceIds = new ArrayList<>();

	@JsonProperty("subObjectInstanceIds")
	@Valid
	private List<String> subObjectInstanceIds = null;

	@JsonProperty("criteria")
	private PmJobCriteria criteria = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("authentication")
	private SubscriptionAuthentication authentication = null;

	public CreatePmJobRequest objectType(final String objectType) {
		this.objectType = objectType;
		return this;
	}

	/**
	 * Type of the measured object. The applicable measured object type for a
	 * measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027.
	 *
	 * @return objectType
	 **/
	@Schema(required = true, description = "Type of the measured object. The applicable measured object type for a measurement  is defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
	@NotNull

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(final String objectType) {
		this.objectType = objectType;
	}

	public CreatePmJobRequest objectInstanceIds(final List<String> objectInstanceIds) {
		this.objectInstanceIds = objectInstanceIds;
		return this;
	}

	public CreatePmJobRequest addObjectInstanceIdsItem(final String objectInstanceIdsItem) {
		this.objectInstanceIds.add(objectInstanceIdsItem);
		return this;
	}

	/**
	 * Identifiers of the measured object instances for which performance
	 * information is requested to be collected.
	 *
	 * @return objectInstanceIds
	 **/
	@Schema(required = true, description = "Identifiers of the measured object instances for which performance information is requested to be collected. ")
	@NotNull

	public List<String> getObjectInstanceIds() {
		return objectInstanceIds;
	}

	public void setObjectInstanceIds(final List<String> objectInstanceIds) {
		this.objectInstanceIds = objectInstanceIds;
	}

	public CreatePmJobRequest subObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
		return this;
	}

	public CreatePmJobRequest addSubObjectInstanceIdsItem(final String subObjectInstanceIdsItem) {
		if (this.subObjectInstanceIds == null) {
			this.subObjectInstanceIds = new ArrayList<>();
		}
		this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
		return this;
	}

	/**
	 * Identifiers of the sub-object instances of the measured object instance for
	 * which performance information is requested to be collected. May be present if
	 * a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027for the related
	 * measured object type. If this attribute is present, the cardinality of the
	 * \"objectInstanceIds\" attribute shall be 1. If this attribute is absent and a
	 * sub-object is defined in clause 6.2 of ETSI GS NFV IFA 027 for the related
	 * measured object type, measurements will be taken for all sub-object instances
	 * of the measured object instance.
	 *
	 * @return subObjectInstanceIds
	 **/
	@Schema(description = "Identifiers of the sub-object instances of the measured object instance for which performance information is requested to be collected. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027for the related measured object type. If this attribute is present, the cardinality of the \"objectInstanceIds\" attribute shall be 1. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV IFA 027 for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance. ")

	public List<String> getSubObjectInstanceIds() {
		return subObjectInstanceIds;
	}

	public void setSubObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
	}

	public CreatePmJobRequest criteria(final PmJobCriteria criteria) {
		this.criteria = criteria;
		return this;
	}

	/**
	 * Get criteria
	 *
	 * @return criteria
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public PmJobCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(final PmJobCriteria criteria) {
		this.criteria = criteria;
	}

	public CreatePmJobRequest callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * Get callbackUri
	 *
	 * @return callbackUri
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public CreatePmJobRequest authentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
		return this;
	}

	/**
	 * Get authentication
	 *
	 * @return authentication
	 **/
	@Schema(description = "")

	@Valid
	public SubscriptionAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CreatePmJobRequest createPmJobRequest = (CreatePmJobRequest) o;
		return Objects.equals(this.objectType, createPmJobRequest.objectType) &&
				Objects.equals(this.objectInstanceIds, createPmJobRequest.objectInstanceIds) &&
				Objects.equals(this.subObjectInstanceIds, createPmJobRequest.subObjectInstanceIds) &&
				Objects.equals(this.criteria, createPmJobRequest.criteria) &&
				Objects.equals(this.callbackUri, createPmJobRequest.callbackUri) &&
				Objects.equals(this.authentication, createPmJobRequest.authentication);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectType, objectInstanceIds, subObjectInstanceIds, criteria, callbackUri, authentication);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreatePmJobRequest {\n");

		sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
		sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
		sb.append("    subObjectInstanceIds: ").append(toIndentedString(subObjectInstanceIds)).append("\n");
		sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
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
