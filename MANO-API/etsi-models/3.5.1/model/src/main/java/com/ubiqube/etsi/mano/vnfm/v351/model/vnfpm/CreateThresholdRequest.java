package com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v351.model.SubscriptionAuthentication;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a request to create a threshold.
 */
@Schema(description = "This type represents a request to create a threshold. ")
@Validated

public class CreateThresholdRequest {
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

	@JsonProperty("authentication")
	private SubscriptionAuthentication authentication = null;

	public CreateThresholdRequest objectType(final String objectType) {
		this.objectType = objectType;
		return this;
	}

	/**
	 * Type of the measured object. The applicable measured object type for a
	 * measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027.
	 *
	 * @return objectType
	 **/
	@Schema(required = true, description = "Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
	@NotNull

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(final String objectType) {
		this.objectType = objectType;
	}

	public CreateThresholdRequest objectInstanceId(final String objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
		return this;
	}

	/**
	 * Get objectInstanceId
	 *
	 * @return objectInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getObjectInstanceId() {
		return objectInstanceId;
	}

	public void setObjectInstanceId(final String objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
	}

	public CreateThresholdRequest subObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
		return this;
	}

	public CreateThresholdRequest addSubObjectInstanceIdsItem(final String subObjectInstanceIdsItem) {
		if (this.subObjectInstanceIds == null) {
			this.subObjectInstanceIds = new ArrayList<>();
		}
		this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
		return this;
	}

	/**
	 * Identifiers of the sub-object instances of the measured object instance
	 * associated with this threshold. May be present if a sub-object is defined in
	 * clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type. If
	 * this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS
	 * NFV-IFA 027 for the measured object type, measurements will be taken for all
	 * sub-object instances of the measured object instance.
	 *
	 * @return subObjectInstanceIds
	 **/
	@Schema(description = "Identifiers of the sub-object instances of the measured object instance associated with this threshold. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 for the measured object type, measurements will be taken for all sub-object instances of the measured object instance. ")

	public List<String> getSubObjectInstanceIds() {
		return subObjectInstanceIds;
	}

	public void setSubObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
	}

	public CreateThresholdRequest criteria(final ThresholdCriteria criteria) {
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
	public ThresholdCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(final ThresholdCriteria criteria) {
		this.criteria = criteria;
	}

	public CreateThresholdRequest callbackUri(final String callbackUri) {
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

	public CreateThresholdRequest authentication(final SubscriptionAuthentication authentication) {
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
		final CreateThresholdRequest createThresholdRequest = (CreateThresholdRequest) o;
		return Objects.equals(this.objectType, createThresholdRequest.objectType) &&
				Objects.equals(this.objectInstanceId, createThresholdRequest.objectInstanceId) &&
				Objects.equals(this.subObjectInstanceIds, createThresholdRequest.subObjectInstanceIds) &&
				Objects.equals(this.criteria, createThresholdRequest.criteria) &&
				Objects.equals(this.callbackUri, createThresholdRequest.callbackUri) &&
				Objects.equals(this.authentication, createThresholdRequest.authentication);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectType, objectInstanceId, subObjectInstanceIds, criteria, callbackUri, authentication);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateThresholdRequest {\n");

		sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
		sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
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
