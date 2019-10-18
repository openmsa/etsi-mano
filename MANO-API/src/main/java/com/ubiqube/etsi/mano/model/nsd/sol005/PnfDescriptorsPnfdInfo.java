package com.ubiqube.etsi.mano.model.nsd.sol005;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * \"This type represents a response for the query PNFD operation.\"
 **/
@ApiModel(description = "\"This type represents a response for the query PNFD operation.\" ")
public class PnfDescriptorsPnfdInfo {

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String id = null;

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String pnfdId = null;

	@ApiModelProperty(value = "Name of the on-boarded PNFD. This information is copied from the PNFD content and shall be present after the PNFD content is on-boarded. ")
	/**
	 * Name of the on-boarded PNFD. This information is copied from the PNFD content
	 * and shall be present after the PNFD content is on-boarded.
	 **/
	private String pnfdName = null;

	@ApiModelProperty(value = "A Version. ")
	/**
	 * A Version.
	 **/
	private String pnfdersion = null;

	@ApiModelProperty(value = "\"Provider of the on-boarded PNFD. This information is copied from the PNFD content and shall be present after the PNFD content is on-boarded.\" ")
	/**
	 * \"Provider of the on-boarded PNFD. This information is copied from the PNFD
	 * content and shall be present after the PNFD content is on-boarded.\"
	 **/
	private String pnfdProvider = null;

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String pnfdInvariantId = null;

	@XmlType(name = "PnfdOnboardingStateEnum")
	@XmlEnum(String.class)
	public enum PnfdOnboardingStateEnum {

		@XmlEnumValue("CREATED")
		CREATED(String.valueOf("CREATED")), @XmlEnumValue("UPLOADING")
		UPLOADING(String.valueOf("UPLOADING")), @XmlEnumValue("PROCESSING")
		PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("ONBOARDING")
		ONBOARDING(String.valueOf("ONBOARDING"));

		private final String value;

		PnfdOnboardingStateEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static PnfdOnboardingStateEnum fromValue(final String v) {
			for (final PnfdOnboardingStateEnum b : PnfdOnboardingStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "The enumeration PnfdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.6-1 of GS-NFV SOL005. It indicates the on-boarding state of the individual PNF descriptor resource. CREATED = The PNF descriptor resource is created.  UPLOADING = The associated PNFD content is being uploaded. PROCESSING = The associated PNFD content is being processed, e.g. validation. ONBOARDED = The associated PNFD content is on-boarded. ")
	/**
	 * The enumeration PnfdOnboardingStateType shall comply with the provisions
	 * defined in Table 5.5.4.6-1 of GS-NFV SOL005. It indicates the on-boarding
	 * state of the individual PNF descriptor resource. CREATED = The PNF descriptor
	 * resource is created. UPLOADING = The associated PNFD content is being
	 * uploaded. PROCESSING = The associated PNFD content is being processed, e.g.
	 * validation. ONBOARDED = The associated PNFD content is on-boarded.
	 **/
	private PnfdOnboardingStateEnum pnfdOnboardingState = null;

	@ApiModelProperty(value = "")
	@Valid
	private NsDescriptorsNsdInfoOnboardingFailureDetails onboardingFailureDetails = null;

	@XmlType(name = "PnfdUsageStateEnum")
	@XmlEnum(String.class)
	public enum PnfdUsageStateEnum {

		@XmlEnumValue("IN_USE")
		IN_USE(String.valueOf("IN_USE")), @XmlEnumValue("NOT_IN_USE")
		NOT_IN_USE(String.valueOf("NOT_IN_USE"));

		private final String value;

		PnfdUsageStateEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static PnfdUsageStateEnum fromValue(final String v) {
			for (final PnfdUsageStateEnum b : PnfdUsageStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "\"The enumeration PnfdUsageStateType shall comply with the provisions defined in Table 5.5.4.7-1 of GS NFV-SOL005. It indicates the usage state of the resource.IN-USE = The resource is in use.NOT_IN_USE = The resource is not-in-use.\" ")
	/**
	 * \"The enumeration PnfdUsageStateType shall comply with> the provisions
	 * defined in Table 5.5.4.7-1 of GS NFV-SOL005. It indicates the usage state of
	 * the resource.IN-USE = The resource is in use.NOT_IN_USE = The resource is
	 * not-in-use.\"
	 **/
	private PnfdUsageStateEnum pnfdUsageState = null;

	@ApiModelProperty(value = "User defined data for the individual PNF descriptor resource. This attribute can be modified with the PATCH method. ")
	/**
	 * User defined data for the individual PNF descriptor resource. This attribute
	 * can be modified with the PATCH method.
	 **/
	private Map<String, Object> userDefinedData = null;

	@ApiModelProperty(required = true, value = "")
	@Valid
	private PnfDescriptorsPnfdInfoLinks links = null;

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return id
	 **/
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public PnfDescriptorsPnfdInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return pnfdId
	 **/
	@JsonProperty("pnfdId")
	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public PnfDescriptorsPnfdInfo pnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
		return this;
	}

	/**
	 * Name of the on-boarded PNFD. This information is copied from the PNFD content
	 * and shall be present after the PNFD content is on-boarded.
	 *
	 * @return pnfdName
	 **/
	@JsonProperty("pnfdName")
	public String getPnfdName() {
		return pnfdName;
	}

	public void setPnfdName(final String pnfdName) {
		this.pnfdName = pnfdName;
	}

	public PnfDescriptorsPnfdInfo pnfdName(final String pnfdName) {
		this.pnfdName = pnfdName;
		return this;
	}

	/**
	 * A Version.
	 *
	 * @return pnfdersion
	 **/
	@JsonProperty("pnfdersion")
	public String getPnfdersion() {
		return pnfdersion;
	}

	public void setPnfdersion(final String pnfdersion) {
		this.pnfdersion = pnfdersion;
	}

	public PnfDescriptorsPnfdInfo pnfdersion(final String pnfdersion) {
		this.pnfdersion = pnfdersion;
		return this;
	}

	/**
	 * \&quot;Provider of the on-boarded PNFD. This information is copied from the
	 * PNFD content and shall be present after the PNFD content is
	 * on-boarded.\&quot;
	 *
	 * @return pnfdProvider
	 **/
	@JsonProperty("pnfdProvider")
	public String getPnfdProvider() {
		return pnfdProvider;
	}

	public void setPnfdProvider(final String pnfdProvider) {
		this.pnfdProvider = pnfdProvider;
	}

	public PnfDescriptorsPnfdInfo pnfdProvider(final String pnfdProvider) {
		this.pnfdProvider = pnfdProvider;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return pnfdInvariantId
	 **/
	@JsonProperty("pnfdInvariantId")
	public String getPnfdInvariantId() {
		return pnfdInvariantId;
	}

	public void setPnfdInvariantId(final String pnfdInvariantId) {
		this.pnfdInvariantId = pnfdInvariantId;
	}

	public PnfDescriptorsPnfdInfo pnfdInvariantId(final String pnfdInvariantId) {
		this.pnfdInvariantId = pnfdInvariantId;
		return this;
	}

	/**
	 * The enumeration PnfdOnboardingStateType shall comply with the provisions
	 * defined in Table 5.5.4.6-1 of GS-NFV SOL005. It indicates the on-boarding
	 * state of the individual PNF descriptor resource. CREATED &#x3D; The PNF
	 * descriptor resource is created. UPLOADING &#x3D; The associated PNFD content
	 * is being uploaded. PROCESSING &#x3D; The associated PNFD content is being
	 * processed, e.g. validation. ONBOARDED &#x3D; The associated PNFD content is
	 * on-boarded.
	 *
	 * @return pnfdOnboardingState
	 **/
	@JsonProperty("pnfdOnboardingState")
	@NotNull
	public String getPnfdOnboardingState() {
		if (pnfdOnboardingState == null) {
			return null;
		}
		return pnfdOnboardingState.value();
	}

	public void setPnfdOnboardingState(final PnfdOnboardingStateEnum pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
	}

	public PnfDescriptorsPnfdInfo pnfdOnboardingState(final PnfdOnboardingStateEnum pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
		return this;
	}

	/**
	 * Get onboardingFailureDetails
	 *
	 * @return onboardingFailureDetails
	 **/
	@JsonProperty("onboardingFailureDetails")
	public NsDescriptorsNsdInfoOnboardingFailureDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final NsDescriptorsNsdInfoOnboardingFailureDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public PnfDescriptorsPnfdInfo onboardingFailureDetails(final NsDescriptorsNsdInfoOnboardingFailureDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
		return this;
	}

	/**
	 * \&quot;The enumeration PnfdUsageStateType shall comply with the provisions
	 * defined in Table 5.5.4.7-1 of GS NFV-SOL005. It indicates the usage state of
	 * the resource.IN-USE &#x3D; The resource is in use.NOT_IN_USE &#x3D; The
	 * resource is not-in-use.\&quot;
	 *
	 * @return pnfdUsageState
	 **/
	@JsonProperty("pnfdUsageState")
	@NotNull
	public String getPnfdUsageState() {
		if (pnfdUsageState == null) {
			return null;
		}
		return pnfdUsageState.value();
	}

	public void setPnfdUsageState(final PnfdUsageStateEnum pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
	}

	public PnfDescriptorsPnfdInfo pnfdUsageState(final PnfdUsageStateEnum pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
		return this;
	}

	/**
	 * User defined data for the individual PNF descriptor resource. This attribute
	 * can be modified with the PATCH method.
	 *
	 * @return userDefinedData
	 **/
	@JsonProperty("userDefinedData")
	public Map<String, Object> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, Object> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public PnfDescriptorsPnfdInfo userDefinedData(final Map<String, Object> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@JsonProperty("_links")
	@NotNull
	public PnfDescriptorsPnfdInfoLinks getLinks() {
		return links;
	}

	public void setLinks(final PnfDescriptorsPnfdInfoLinks links) {
		this.links = links;
	}

	public PnfDescriptorsPnfdInfo links(final PnfDescriptorsPnfdInfoLinks links) {
		this.links = links;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfDescriptorsPnfdInfo {\n");

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
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
