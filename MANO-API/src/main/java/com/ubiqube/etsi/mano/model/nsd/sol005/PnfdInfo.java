package com.ubiqube.etsi.mano.model.nsd.sol005;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.KeyValuePairs;
import com.ubiqube.etsi.mano.model.ProblemDetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a response for the query PNFD operation.
 */
@ApiModel(description = "This type represents a response for the query PNFD operation. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T16:34:13.188+01:00")

public class PnfdInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("pnfdId")
	private String pnfdId = null;

	@JsonProperty("pnfdName")
	private String pnfdName = null;

	@JsonProperty("pnfdersion")
	private String pnfdersion = null;

	@JsonProperty("pnfdProvider")
	private String pnfdProvider = null;

	@JsonProperty("pnfdInvariantId")
	private String pnfdInvariantId = null;

	@JsonProperty("pnfdOnboardingState")
	private PnfdOnboardingStateType pnfdOnboardingState = null;

	@JsonProperty("onboardingFailureDetails")
	private ProblemDetails onboardingFailureDetails = null;

	@JsonProperty("pnfdUsageState")
	private PnfdUsageStateType pnfdUsageState = null;

	@JsonProperty("userDefinedData")
	private Map<String, Object> userDefinedData = null;

	@JsonProperty("_links")
	private PnfdInfoLinks links = null;

	public PnfdInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the on-boarded individual PNF descriptor resource. This
	 * identifier is allocated by the NFVO.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the on-boarded individual PNF descriptor resource. This identifier is allocated by the NFVO. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public PnfdInfo pnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
		return this;
	}

	/**
	 * This identifier, which is managed by the PNFD designer, identifies the PNFD
	 * in a globally unique way. It is copied from the PNFD content and shall be
	 * present after the PNFD content is on-boarded.
	 *
	 * @return pnfdId
	 **/
	@ApiModelProperty(value = "This identifier, which is managed by the PNFD designer, identifies the PNFD in a globally unique way. It is copied from the PNFD content and shall be present after the PNFD content is on-boarded. ")

	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public PnfdInfo pnfdName(final String pnfdName) {
		this.pnfdName = pnfdName;
		return this;
	}

	/**
	 * Name of the on-boarded PNFD. This information is copied from the PNFD content
	 * and shall be present after the PNFD content is on-boarded.
	 *
	 * @return pnfdName
	 **/
	@ApiModelProperty(value = "Name of the on-boarded PNFD. This information is copied from the PNFD content and shall be present after the PNFD content is on-boarded. ")

	public String getPnfdName() {
		return pnfdName;
	}

	public void setPnfdName(final String pnfdName) {
		this.pnfdName = pnfdName;
	}

	public PnfdInfo pnfdersion(final String pnfdersion) {
		this.pnfdersion = pnfdersion;
		return this;
	}

	/**
	 * Get pnfdersion
	 *
	 * @return pnfdersion
	 **/
	@ApiModelProperty(value = "")

	public String getPnfdersion() {
		return pnfdersion;
	}

	public void setPnfdersion(final String pnfdersion) {
		this.pnfdersion = pnfdersion;
	}

	public PnfdInfo pnfdProvider(final String pnfdProvider) {
		this.pnfdProvider = pnfdProvider;
		return this;
	}

	/**
	 * Provider of the on-boarded PNFD. This information is copied from the PNFD
	 * content and shall be present after the PNFD content is on-boarded.
	 *
	 * @return pnfdProvider
	 **/
	@ApiModelProperty(value = "Provider of the on-boarded PNFD. This information is copied from the PNFD content and shall be present after the PNFD content is on-boarded. ")

	public String getPnfdProvider() {
		return pnfdProvider;
	}

	public void setPnfdProvider(final String pnfdProvider) {
		this.pnfdProvider = pnfdProvider;
	}

	public PnfdInfo pnfdInvariantId(final String pnfdInvariantId) {
		this.pnfdInvariantId = pnfdInvariantId;
		return this;
	}

	/**
	 * Identifies a PNFD in a version independent manner. This attribute is
	 * invariant across versions of PNFD.
	 *
	 * @return pnfdInvariantId
	 **/
	@ApiModelProperty(value = "Identifies a PNFD in a version independent manner. This attribute is invariant across versions of PNFD. ")

	public String getPnfdInvariantId() {
		return pnfdInvariantId;
	}

	public void setPnfdInvariantId(final String pnfdInvariantId) {
		this.pnfdInvariantId = pnfdInvariantId;
	}

	public PnfdInfo pnfdOnboardingState(final PnfdOnboardingStateType pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
		return this;
	}

	/**
	 * On-boarding state of the individual PNF descriptor resource.
	 *
	 * @return pnfdOnboardingState
	 **/
	@ApiModelProperty(required = true, value = "On-boarding state of the individual PNF descriptor resource. ")
	@NotNull

	@Valid

	public PnfdOnboardingStateType getPnfdOnboardingState() {
		return pnfdOnboardingState;
	}

	public void setPnfdOnboardingState(final PnfdOnboardingStateType pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
	}

	public PnfdInfo onboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
		return this;
	}

	/**
	 * Failure details of current on-boarding procedure. See clause 6.3 of ETSI GS
	 * NFV-SOL 013 for the details of \"ProblemDetails\" structure. It shall be
	 * present when the pnfdOnboardingState attribute is CREATED and the uploading
	 * or processing fails in the NFVO.
	 *
	 * @return onboardingFailureDetails
	 **/
	@ApiModelProperty(value = "Failure details of current on-boarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 for the details of \"ProblemDetails\" structure. It shall be present when the pnfdOnboardingState attribute is CREATED and the uploading or processing fails in the NFVO. ")

	@Valid

	public ProblemDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public PnfdInfo pnfdUsageState(final PnfdUsageStateType pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
		return this;
	}

	/**
	 * Usage state of the individual PNF descriptor resource.
	 *
	 * @return pnfdUsageState
	 **/
	@ApiModelProperty(required = true, value = "Usage state of the individual PNF descriptor resource. ")
	@NotNull

	@Valid

	public PnfdUsageStateType getPnfdUsageState() {
		return pnfdUsageState;
	}

	public void setPnfdUsageState(final PnfdUsageStateType pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
	}

	public PnfdInfo userDefinedData(final KeyValuePairs userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * User defined data for the individual PNF descriptor resource. This attribute
	 * can be modified with the PATCH method.
	 *
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "User defined data for the individual PNF descriptor resource. This attribute can be modified with the PATCH method. ")

	@Valid

	public Map<String, Object> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, Object> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public PnfdInfo links(final PnfdInfoLinks links) {
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

	public PnfdInfoLinks getLinks() {
		return links;
	}

	public void setLinks(final PnfdInfoLinks links) {
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
		final PnfdInfo pnfdInfo = (PnfdInfo) o;
		return Objects.equals(this.id, pnfdInfo.id) &&
				Objects.equals(this.pnfdId, pnfdInfo.pnfdId) &&
				Objects.equals(this.pnfdName, pnfdInfo.pnfdName) &&
				Objects.equals(this.pnfdersion, pnfdInfo.pnfdersion) &&
				Objects.equals(this.pnfdProvider, pnfdInfo.pnfdProvider) &&
				Objects.equals(this.pnfdInvariantId, pnfdInfo.pnfdInvariantId) &&
				Objects.equals(this.pnfdOnboardingState, pnfdInfo.pnfdOnboardingState) &&
				Objects.equals(this.onboardingFailureDetails, pnfdInfo.onboardingFailureDetails) &&
				Objects.equals(this.pnfdUsageState, pnfdInfo.pnfdUsageState) &&
				Objects.equals(this.userDefinedData, pnfdInfo.userDefinedData) &&
				Objects.equals(this.links, pnfdInfo.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pnfdId, pnfdName, pnfdersion, pnfdProvider, pnfdInvariantId, pnfdOnboardingState, onboardingFailureDetails, pnfdUsageState, userDefinedData, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfdInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
		sb.append("    pnfdName: ").append(toIndentedString(pnfdName)).append("\n");
		sb.append("    pnfdersion: ").append(toIndentedString(pnfdersion)).append("\n");
		sb.append("    pnfdProvider: ").append(toIndentedString(pnfdProvider)).append("\n");
		sb.append("    pnfdInvariantId: ").append(toIndentedString(pnfdInvariantId)).append("\n");
		sb.append("    pnfdOnboardingState: ").append(toIndentedString(pnfdOnboardingState)).append("\n");
		sb.append("    onboardingFailureDetails: ").append(toIndentedString(onboardingFailureDetails)).append("\n");
		sb.append("    pnfdUsageState: ").append(toIndentedString(pnfdUsageState)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
