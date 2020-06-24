package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.KeyValuePairs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents attribute modifications for an individual NS descriptor
 * resource based on the NsdInfo data type. The attributes of NsdInfo that can
 * be modified are included in the NsdInfoModifications data type.NOTE: At least
 * one of the attributes - nsdOperationalState and userDefinedData - shall be
 * present.
 */
@ApiModel(description = "This type represents attribute modifications for an individual NS descriptor resource based on the NsdInfo data type. The attributes of NsdInfo that can be modified are included in the NsdInfoModifications data type.NOTE: At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T16:34:13.188+01:00")

public class NsdInfoModifications {
	@JsonProperty("nsdOperationalState")
	private NsdOperationalStateType nsdOperationalState = null;

	@JsonProperty("userDefinedData")
	@Valid
	private List<KeyValuePairs> userDefinedData = null;

	public NsdInfoModifications nsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
		return this;
	}

	/**
	 * Get nsdOperationalState
	 * 
	 * @return nsdOperationalState
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public NsdOperationalStateType getNsdOperationalState() {
		return nsdOperationalState;
	}

	public void setNsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
	}

	public NsdInfoModifications userDefinedData(final List<KeyValuePairs> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	public NsdInfoModifications addUserDefinedDataItem(final KeyValuePairs userDefinedDataItem) {
		if (this.userDefinedData == null) {
			this.userDefinedData = new ArrayList<>();
		}
		this.userDefinedData.add(userDefinedDataItem);
		return this;
	}

	/**
	 * Modifications of the userDefinedData attribute in NsdInfo data type. See
	 * note. If present, these modifications shall be applied according to the rules
	 * of JSON Merge PATCH (see IETF RFC 7396 [25]). NOTE- At least one of the
	 * attributes - nsdOperationalState and userDefinedData - shall be present.
	 * 
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396 [25]). NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")

	@Valid

	public List<KeyValuePairs> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final List<KeyValuePairs> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsdInfoModifications nsdInfoModifications = (NsdInfoModifications) o;
		return Objects.equals(this.nsdOperationalState, nsdInfoModifications.nsdOperationalState) &&
				Objects.equals(this.userDefinedData, nsdInfoModifications.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsdOperationalState, userDefinedData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsdInfoModifications {\n");

		sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
