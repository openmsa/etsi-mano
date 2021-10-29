package com.ubiqube.etsi.mano.nfvo.v351.model.nfvici;

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
 * This type represents a request to create a capacity threshold. It shall
 * comply with the provisions defined in table 10.5.2.3-1.
 */
@Schema(description = "This type represents a request to create a capacity threshold. It shall comply with the provisions defined in table 10.5.2.3-1. ")
@Validated

public class CreateCapacityThresholdRequest {
	@JsonProperty("objectInstanceId")
	private String objectInstanceId = null;

	@JsonProperty("subObjectInstanceIds")
	@Valid
	private List<String> subObjectInstanceIds = null;

	@JsonProperty("criteria")
	private CapacityThresholdCriteria criteria = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("authentication")
	private SubscriptionAuthentication authentication = null;

	public CreateCapacityThresholdRequest objectInstanceId(final String objectInstanceId) {
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

	public CreateCapacityThresholdRequest subObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
		return this;
	}

	public CreateCapacityThresholdRequest addSubObjectInstanceIdsItem(final String subObjectInstanceIdsItem) {
		if (this.subObjectInstanceIds == null) {
			this.subObjectInstanceIds = new ArrayList<>();
		}
		this.subObjectInstanceIds.add(subObjectInstanceIdsItem);
		return this;
	}

	/**
	 * Identifiers of the sub-object instances of the measured object instance
	 * associate with this capacity threshold. If this attribute is absent,
	 * measurements will be taken for all sub-object instances of the measured
	 * object instance.
	 *
	 * @return subObjectInstanceIds
	 **/
	@Schema(description = "Identifiers of the sub-object instances of the measured object instance associate with this capacity threshold. If this attribute is absent, measurements will be taken for all sub-object instances of the measured object instance. ")

	public List<String> getSubObjectInstanceIds() {
		return subObjectInstanceIds;
	}

	public void setSubObjectInstanceIds(final List<String> subObjectInstanceIds) {
		this.subObjectInstanceIds = subObjectInstanceIds;
	}

	public CreateCapacityThresholdRequest criteria(final CapacityThresholdCriteria criteria) {
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
	public CapacityThresholdCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(final CapacityThresholdCriteria criteria) {
		this.criteria = criteria;
	}

	public CreateCapacityThresholdRequest callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * Get callbackUri
	 *
	 * @return callbackUri
	 **/
	@Schema(description = "")

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public CreateCapacityThresholdRequest authentication(final SubscriptionAuthentication authentication) {
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
		final CreateCapacityThresholdRequest createCapacityThresholdRequest = (CreateCapacityThresholdRequest) o;
		return Objects.equals(this.objectInstanceId, createCapacityThresholdRequest.objectInstanceId) &&
				Objects.equals(this.subObjectInstanceIds, createCapacityThresholdRequest.subObjectInstanceIds) &&
				Objects.equals(this.criteria, createCapacityThresholdRequest.criteria) &&
				Objects.equals(this.callbackUri, createCapacityThresholdRequest.callbackUri) &&
				Objects.equals(this.authentication, createCapacityThresholdRequest.authentication);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectInstanceId, subObjectInstanceIds, criteria, callbackUri, authentication);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateCapacityThresholdRequest {\n");

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
