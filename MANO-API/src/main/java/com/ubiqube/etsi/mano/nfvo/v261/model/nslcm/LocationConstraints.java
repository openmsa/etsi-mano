package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

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
 * This type represents location constraints for a VNF to be instantiated. The
 * location constraints shall be presented as a country code, optionally
 * followed by a civic address based on the format defined by IETF RFC 4776
 * [13].
 */
@ApiModel(description = "This type represents location constraints for a VNF to be instantiated. The location constraints shall be presented as a country code, optionally followed by a civic address based on the format defined by IETF RFC 4776 [13]. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class LocationConstraints {
	@JsonProperty("countryCode")
	private String countryCode = null;

	@JsonProperty("civicAddressElement")
	@Valid
	private List<LocationConstraintsCivicAddressElement> civicAddressElement = null;

	public LocationConstraints countryCode(final String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	/**
	 * The two-letter ISO 3166 [29] country code in capital letters.
	 * 
	 * @return countryCode
	 **/
	@ApiModelProperty(required = true, value = "The two-letter ISO 3166 [29] country code in capital letters. ")
	@NotNull

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public LocationConstraints civicAddressElement(final List<LocationConstraintsCivicAddressElement> civicAddressElement) {
		this.civicAddressElement = civicAddressElement;
		return this;
	}

	public LocationConstraints addCivicAddressElementItem(final LocationConstraintsCivicAddressElement civicAddressElementItem) {
		if (this.civicAddressElement == null) {
			this.civicAddressElement = new ArrayList<>();
		}
		this.civicAddressElement.add(civicAddressElementItem);
		return this;
	}

	/**
	 * Zero or more elements comprising the civic address.
	 * 
	 * @return civicAddressElement
	 **/
	@ApiModelProperty(value = "Zero or more elements comprising the civic address. ")

	@Valid

	public List<LocationConstraintsCivicAddressElement> getCivicAddressElement() {
		return civicAddressElement;
	}

	public void setCivicAddressElement(final List<LocationConstraintsCivicAddressElement> civicAddressElement) {
		this.civicAddressElement = civicAddressElement;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final LocationConstraints locationConstraints = (LocationConstraints) o;
		return Objects.equals(this.countryCode, locationConstraints.countryCode) &&
				Objects.equals(this.civicAddressElement, locationConstraints.civicAddressElement);
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryCode, civicAddressElement);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LocationConstraints {\n");

		sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
		sb.append("    civicAddressElement: ").append(toIndentedString(civicAddressElement)).append("\n");
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
