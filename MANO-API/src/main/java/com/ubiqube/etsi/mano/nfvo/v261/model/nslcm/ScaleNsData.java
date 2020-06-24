package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information to scale a NS.
 */
@ApiModel(description = "This type represents the information to scale a NS. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class ScaleNsData {
	@JsonProperty("vnfInstanceToBeAdded")
	@Valid
	private List<VnfInstanceData> vnfInstanceToBeAdded = null;

	@JsonProperty("vnfInstanceToBeRemoved")
	@Valid
	private List<String> vnfInstanceToBeRemoved = null;

	@JsonProperty("scaleNsByStepsData")
	private ScaleNsByStepsData scaleNsByStepsData = null;

	@JsonProperty("scaleNsToLevelData")
	private ScaleNsToLevelData scaleNsToLevelData = null;

	@JsonProperty("additionalParamsForNs")
	private ParamsForVnf additionalParamsForNs = null;

	@JsonProperty("additionalParamsForVnf")
	@Valid
	private List<ParamsForVnf> additionalParamsForVnf = null;

	@JsonProperty("locationConstraints")
	@Valid
	private List<VnfLocationConstraint> locationConstraints = null;

	public ScaleNsData vnfInstanceToBeAdded(final List<VnfInstanceData> vnfInstanceToBeAdded) {
		this.vnfInstanceToBeAdded = vnfInstanceToBeAdded;
		return this;
	}

	public ScaleNsData addVnfInstanceToBeAddedItem(final VnfInstanceData vnfInstanceToBeAddedItem) {
		if (this.vnfInstanceToBeAdded == null) {
			this.vnfInstanceToBeAdded = new ArrayList<>();
		}
		this.vnfInstanceToBeAdded.add(vnfInstanceToBeAddedItem);
		return this;
	}

	/**
	 * An existing VNF instance to be added to the NS instance as part of the
	 * scaling operation. If needed, the VNF Profile to be used for this VNF
	 * instance may also be provided.
	 * 
	 * @return vnfInstanceToBeAdded
	 **/
	@ApiModelProperty(value = "An existing VNF instance to be added to the NS instance as part of the scaling operation. If needed, the VNF Profile to be used for this VNF instance may also be provided. ")

	@Valid

	public List<VnfInstanceData> getVnfInstanceToBeAdded() {
		return vnfInstanceToBeAdded;
	}

	public void setVnfInstanceToBeAdded(final List<VnfInstanceData> vnfInstanceToBeAdded) {
		this.vnfInstanceToBeAdded = vnfInstanceToBeAdded;
	}

	public ScaleNsData vnfInstanceToBeRemoved(final List<String> vnfInstanceToBeRemoved) {
		this.vnfInstanceToBeRemoved = vnfInstanceToBeRemoved;
		return this;
	}

	public ScaleNsData addVnfInstanceToBeRemovedItem(final String vnfInstanceToBeRemovedItem) {
		if (this.vnfInstanceToBeRemoved == null) {
			this.vnfInstanceToBeRemoved = new ArrayList<>();
		}
		this.vnfInstanceToBeRemoved.add(vnfInstanceToBeRemovedItem);
		return this;
	}

	/**
	 * The VNF instance to be removed from the NS instance as part of the scaling
	 * operation.
	 * 
	 * @return vnfInstanceToBeRemoved
	 **/
	@ApiModelProperty(value = "The VNF instance to be removed from the NS instance as part of the scaling operation. ")

	public List<String> getVnfInstanceToBeRemoved() {
		return vnfInstanceToBeRemoved;
	}

	public void setVnfInstanceToBeRemoved(final List<String> vnfInstanceToBeRemoved) {
		this.vnfInstanceToBeRemoved = vnfInstanceToBeRemoved;
	}

	public ScaleNsData scaleNsByStepsData(final ScaleNsByStepsData scaleNsByStepsData) {
		this.scaleNsByStepsData = scaleNsByStepsData;
		return this;
	}

	/**
	 * The information used to scale an NS instance by one or more scaling steps.
	 * 
	 * @return scaleNsByStepsData
	 **/
	@ApiModelProperty(value = "The information used to scale an NS instance by one or more scaling steps. ")

	@Valid

	public ScaleNsByStepsData getScaleNsByStepsData() {
		return scaleNsByStepsData;
	}

	public void setScaleNsByStepsData(final ScaleNsByStepsData scaleNsByStepsData) {
		this.scaleNsByStepsData = scaleNsByStepsData;
	}

	public ScaleNsData scaleNsToLevelData(final ScaleNsToLevelData scaleNsToLevelData) {
		this.scaleNsToLevelData = scaleNsToLevelData;
		return this;
	}

	/**
	 * The information used to scale an NS instance to a target size.
	 * 
	 * @return scaleNsToLevelData
	 **/
	@ApiModelProperty(value = "The information used to scale an NS instance to a target size. ")

	@Valid

	public ScaleNsToLevelData getScaleNsToLevelData() {
		return scaleNsToLevelData;
	}

	public void setScaleNsToLevelData(final ScaleNsToLevelData scaleNsToLevelData) {
		this.scaleNsToLevelData = scaleNsToLevelData;
	}

	public ScaleNsData additionalParamsForNs(final ParamsForVnf additionalParamsForNs) {
		this.additionalParamsForNs = additionalParamsForNs;
		return this;
	}

	/**
	 * Allows the OSS/BSS to provide additional parameter(s) at the NS level
	 * necessary for the NS scaling (as opposed to the VNF level, which is covered
	 * in additionalParamForVnf).
	 * 
	 * @return additionalParamsForNs
	 **/
	@ApiModelProperty(value = "Allows the OSS/BSS to provide additional parameter(s) at the NS level necessary for the NS scaling (as opposed to the VNF level, which is covered in additionalParamForVnf). ")

	@Valid

	public ParamsForVnf getAdditionalParamsForNs() {
		return additionalParamsForNs;
	}

	public void setAdditionalParamsForNs(final ParamsForVnf additionalParamsForNs) {
		this.additionalParamsForNs = additionalParamsForNs;
	}

	public ScaleNsData additionalParamsForVnf(final List<ParamsForVnf> additionalParamsForVnf) {
		this.additionalParamsForVnf = additionalParamsForVnf;
		return this;
	}

	public ScaleNsData addAdditionalParamsForVnfItem(final ParamsForVnf additionalParamsForVnfItem) {
		if (this.additionalParamsForVnf == null) {
			this.additionalParamsForVnf = new ArrayList<>();
		}
		this.additionalParamsForVnf.add(additionalParamsForVnfItem);
		return this;
	}

	/**
	 * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as
	 * opposed to the NS level, which is covered in additionalParamforNs). This is
	 * for VNFs that are to be created by the NFVO as part of the NS scaling and not
	 * for existing VNF that are covered by the scaleVnfData.
	 * 
	 * @return additionalParamsForVnf
	 **/
	@ApiModelProperty(value = "Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamforNs). This is for VNFs that are to be created by the NFVO as part of the NS scaling and not for existing VNF that are covered by the scaleVnfData. ")

	@Valid

	public List<ParamsForVnf> getAdditionalParamsForVnf() {
		return additionalParamsForVnf;
	}

	public void setAdditionalParamsForVnf(final List<ParamsForVnf> additionalParamsForVnf) {
		this.additionalParamsForVnf = additionalParamsForVnf;
	}

	public ScaleNsData locationConstraints(final List<VnfLocationConstraint> locationConstraints) {
		this.locationConstraints = locationConstraints;
		return this;
	}

	public ScaleNsData addLocationConstraintsItem(final VnfLocationConstraint locationConstraintsItem) {
		if (this.locationConstraints == null) {
			this.locationConstraints = new ArrayList<>();
		}
		this.locationConstraints.add(locationConstraintsItem);
		return this;
	}

	/**
	 * The location constraints for the VNF to be instantiated as part of the NS
	 * scaling. An example can be a constraint for the VNF to be in a specific
	 * geographic location.
	 * 
	 * @return locationConstraints
	 **/
	@ApiModelProperty(value = "The location constraints for the VNF to be instantiated as part of the NS scaling. An example can be a constraint for the VNF to be in a specific geographic location. ")

	@Valid

	public List<VnfLocationConstraint> getLocationConstraints() {
		return locationConstraints;
	}

	public void setLocationConstraints(final List<VnfLocationConstraint> locationConstraints) {
		this.locationConstraints = locationConstraints;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleNsData scaleNsData = (ScaleNsData) o;
		return Objects.equals(this.vnfInstanceToBeAdded, scaleNsData.vnfInstanceToBeAdded) &&
				Objects.equals(this.vnfInstanceToBeRemoved, scaleNsData.vnfInstanceToBeRemoved) &&
				Objects.equals(this.scaleNsByStepsData, scaleNsData.scaleNsByStepsData) &&
				Objects.equals(this.scaleNsToLevelData, scaleNsData.scaleNsToLevelData) &&
				Objects.equals(this.additionalParamsForNs, scaleNsData.additionalParamsForNs) &&
				Objects.equals(this.additionalParamsForVnf, scaleNsData.additionalParamsForVnf) &&
				Objects.equals(this.locationConstraints, scaleNsData.locationConstraints);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceToBeAdded, vnfInstanceToBeRemoved, scaleNsByStepsData, scaleNsToLevelData, additionalParamsForNs, additionalParamsForVnf, locationConstraints);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleNsData {\n");

		sb.append("    vnfInstanceToBeAdded: ").append(toIndentedString(vnfInstanceToBeAdded)).append("\n");
		sb.append("    vnfInstanceToBeRemoved: ").append(toIndentedString(vnfInstanceToBeRemoved)).append("\n");
		sb.append("    scaleNsByStepsData: ").append(toIndentedString(scaleNsByStepsData)).append("\n");
		sb.append("    scaleNsToLevelData: ").append(toIndentedString(scaleNsToLevelData)).append("\n");
		sb.append("    additionalParamsForNs: ").append(toIndentedString(additionalParamsForNs)).append("\n");
		sb.append("    additionalParamsForVnf: ").append(toIndentedString(additionalParamsForVnf)).append("\n");
		sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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
