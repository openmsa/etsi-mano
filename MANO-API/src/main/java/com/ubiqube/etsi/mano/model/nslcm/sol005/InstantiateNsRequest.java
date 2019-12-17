package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.KeyValuePairs;

import io.swagger.annotations.ApiModelProperty;

/**
 * InstantiateNsRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class InstantiateNsRequest {
	@JsonProperty("nsFlavourId")
	private String nsFlavourId = null;

	@JsonProperty("sapData")
	@Valid
	private List<SapData> sapData = null;

	@JsonProperty("addpnfData")
	@Valid
	private List<AddPnfData> addpnfData = null;

	@JsonProperty("vnfInstanceData")
	@Valid
	private List<VnfInstanceData> vnfInstanceData = null;

	@JsonProperty("nestedNsInstanceData")
	@Valid
	private List<NestedNsInstanceData> nestedNsInstanceData = null;

	@JsonProperty("locationConstraints")
	@Valid
	private List<VnfLocationConstraint> locationConstraints = null;

	@JsonProperty("additionalParamsForNs")
	private KeyValuePairs additionalParamsForNs = null;

	@JsonProperty("additionalParamForNestedNs")
	@Valid
	private List<ParamsForNestedNs> additionalParamForNestedNs = null;

	@JsonProperty("additionalParamsForVnf")
	@Valid
	private List<ParamsForVnf> additionalParamsForVnf = null;

	@JsonProperty("startTime")
	private OffsetDateTime startTime = null;

	@JsonProperty("nsInstantiationLevelId")
	private String nsInstantiationLevelId = null;

	@JsonProperty("additionalAffinityOrAntiAffinityRule")
	@Valid
	private List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;

	public InstantiateNsRequest nsFlavourId(final String nsFlavourId) {
		this.nsFlavourId = nsFlavourId;
		return this;
	}

	/**
	 * Identifier of the NS deployment flavor to be instantiated.
	 *
	 * @return nsFlavourId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the NS deployment flavor to be instantiated. ")
	@NotNull

	public String getNsFlavourId() {
		return nsFlavourId;
	}

	public void setNsFlavourId(final String nsFlavourId) {
		this.nsFlavourId = nsFlavourId;
	}

	public InstantiateNsRequest sapData(final List<SapData> sapData) {
		this.sapData = sapData;
		return this;
	}

	public InstantiateNsRequest addSapDataItem(final SapData sapDataItem) {
		if (this.sapData == null) {
			this.sapData = new ArrayList<>();
		}
		this.sapData.add(sapDataItem);
		return this;
	}

	/**
	 * Create data concerning the SAPs of this NS.
	 *
	 * @return sapData
	 **/
	@ApiModelProperty(value = "Create data concerning the SAPs of this NS. ")

	@Valid

	public List<SapData> getSapData() {
		return sapData;
	}

	public void setSapData(final List<SapData> sapData) {
		this.sapData = sapData;
	}

	public InstantiateNsRequest addpnfData(final List<AddPnfData> addpnfData) {
		this.addpnfData = addpnfData;
		return this;
	}

	public InstantiateNsRequest addAddpnfDataItem(final AddPnfData addpnfDataItem) {
		if (this.addpnfData == null) {
			this.addpnfData = new ArrayList<>();
		}
		this.addpnfData.add(addpnfDataItem);
		return this;
	}

	/**
	 * Information on the PNF(s) that are part of this NS.
	 *
	 * @return addpnfData
	 **/
	@ApiModelProperty(value = "Information on the PNF(s) that are part of this NS. ")

	@Valid

	public List<AddPnfData> getAddpnfData() {
		return addpnfData;
	}

	public void setAddpnfData(final List<AddPnfData> addpnfData) {
		this.addpnfData = addpnfData;
	}

	public InstantiateNsRequest vnfInstanceData(final List<VnfInstanceData> vnfInstanceData) {
		this.vnfInstanceData = vnfInstanceData;
		return this;
	}

	public InstantiateNsRequest addVnfInstanceDataItem(final VnfInstanceData vnfInstanceDataItem) {
		if (this.vnfInstanceData == null) {
			this.vnfInstanceData = new ArrayList<>();
		}
		this.vnfInstanceData.add(vnfInstanceDataItem);
		return this;
	}

	/**
	 * Specify an existing VNF instance to be used in the NS. If needed, the VNF
	 * Profile to be used for this VNF instance is also provided. The DF of the VNF
	 * instance shall match the VNF DF present in the associated VNF Profile.
	 *
	 * @return vnfInstanceData
	 **/
	@ApiModelProperty(value = "Specify an existing VNF instance to be used in the NS. If needed, the VNF Profile to be used for this VNF instance is also provided. The DF of the VNF instance shall match the VNF DF  present in the associated VNF Profile. ")

	@Valid

	public List<VnfInstanceData> getVnfInstanceData() {
		return vnfInstanceData;
	}

	public void setVnfInstanceData(final List<VnfInstanceData> vnfInstanceData) {
		this.vnfInstanceData = vnfInstanceData;
	}

	public InstantiateNsRequest nestedNsInstanceData(final List<NestedNsInstanceData> nestedNsInstanceData) {
		this.nestedNsInstanceData = nestedNsInstanceData;
		return this;
	}

	public InstantiateNsRequest addNestedNsInstanceDataItem(final NestedNsInstanceData nestedNsInstanceDataItem) {
		if (this.nestedNsInstanceData == null) {
			this.nestedNsInstanceData = new ArrayList<>();
		}
		this.nestedNsInstanceData.add(nestedNsInstanceDataItem);
		return this;
	}

	/**
	 * Specify an existing NS instance to be used as a nested NS within the NS. If
	 * needed, the NS Profile to be used for this nested NS instance is also
	 * provided. NOTE 2: The NS DF of each nested NS shall be one of the allowed
	 * flavours in the associated NSD (as referenced in the nestedNsd attribute of
	 * the NSD of the NS to be instantiated). NOTE 3: The NSD of each referenced NSs
	 * (i.e. each nestedInstanceId) shall match the one of the nested NSD in the
	 * composite NSD.
	 *
	 * @return nestedNsInstanceData
	 **/
	@ApiModelProperty(value = "Specify an existing NS instance to be used as a nested NS within the NS. If needed, the NS Profile to be used for this nested NS  instance is also provided. NOTE 2: The NS DF of each nested NS shall be one of the  allowed flavours in the associated NSD (as referenced in the nestedNsd attribute of the NSD of the NS to be instantiated). NOTE 3: The NSD of each referenced NSs (i.e. each  nestedInstanceId) shall match the one of the nested NSD in  the composite NSD. ")

	@Valid

	public List<NestedNsInstanceData> getNestedNsInstanceData() {
		return nestedNsInstanceData;
	}

	public void setNestedNsInstanceData(final List<NestedNsInstanceData> nestedNsInstanceData) {
		this.nestedNsInstanceData = nestedNsInstanceData;
	}

	public InstantiateNsRequest locationConstraints(final List<VnfLocationConstraint> locationConstraints) {
		this.locationConstraints = locationConstraints;
		return this;
	}

	public InstantiateNsRequest addLocationConstraintsItem(final VnfLocationConstraint locationConstraintsItem) {
		if (this.locationConstraints == null) {
			this.locationConstraints = new ArrayList<>();
		}
		this.locationConstraints.add(locationConstraintsItem);
		return this;
	}

	/**
	 * Defines the location constraints for the VNF to be instantiated as part of
	 * the NS instantiation. An example can be a constraint for the VNF to be in a
	 * specific geographic location..
	 *
	 * @return locationConstraints
	 **/
	@ApiModelProperty(value = "Defines the location constraints for the VNF to be instantiated as part of the NS instantiation. An example can be a constraint for the VNF to be in a specific geographic location.. ")

	@Valid

	public List<VnfLocationConstraint> getLocationConstraints() {
		return locationConstraints;
	}

	public void setLocationConstraints(final List<VnfLocationConstraint> locationConstraints) {
		this.locationConstraints = locationConstraints;
	}

	public InstantiateNsRequest additionalParamsForNs(final KeyValuePairs additionalParamsForNs) {
		this.additionalParamsForNs = additionalParamsForNs;
		return this;
	}

	/**
	 * Allows the OSS/BSS to provide additional parameter(s) at the composite NS
	 * level (as opposed to the VNF level, which is covered in
	 * additionalParamsForVnf), and as opposed to the nested NS level, which is
	 * covered in additionalParamForNestedNs.
	 *
	 * @return additionalParamsForNs
	 **/
	@ApiModelProperty(value = "Allows the OSS/BSS to provide additional parameter(s) at the composite NS level (as opposed to the VNF level,  which is covered in additionalParamsForVnf), and as  opposed to the nested NS level, which is covered in additionalParamForNestedNs. ")

	@Valid

	public KeyValuePairs getAdditionalParamsForNs() {
		return additionalParamsForNs;
	}

	public void setAdditionalParamsForNs(final KeyValuePairs additionalParamsForNs) {
		this.additionalParamsForNs = additionalParamsForNs;
	}

	public InstantiateNsRequest additionalParamForNestedNs(final List<ParamsForNestedNs> additionalParamForNestedNs) {
		this.additionalParamForNestedNs = additionalParamForNestedNs;
		return this;
	}

	public InstantiateNsRequest addAdditionalParamForNestedNsItem(final ParamsForNestedNs additionalParamForNestedNsItem) {
		if (this.additionalParamForNestedNs == null) {
			this.additionalParamForNestedNs = new ArrayList<>();
		}
		this.additionalParamForNestedNs.add(additionalParamForNestedNsItem);
		return this;
	}

	/**
	 * Allows the OSS/BSS to provide additional parameter(s) per nested NS instance
	 * (as opposed to the composite NS level, which is covered in
	 * additionalParamForNs, and as opposed to the VNF level, which is covered in
	 * additionalParamForVnf). This is for nested NS instances that are to be
	 * created by the NFVO as part of the NS instantiation and not for existing
	 * nested NS instances that are referenced for reuse.
	 *
	 * @return additionalParamForNestedNs
	 **/
	@ApiModelProperty(value = "Allows the OSS/BSS to provide additional parameter(s) per nested NS instance (as opposed to the composite NS level, which is covered in additionalParamForNs, and as opposed to the VNF level, which is covered in additionalParamForVnf). This is for nested NS instances that are to be created by the NFVO as part of the NS instantiation and not for existing nested NS instances that are referenced for reuse. ")

	@Valid

	public List<ParamsForNestedNs> getAdditionalParamForNestedNs() {
		return additionalParamForNestedNs;
	}

	public void setAdditionalParamForNestedNs(final List<ParamsForNestedNs> additionalParamForNestedNs) {
		this.additionalParamForNestedNs = additionalParamForNestedNs;
	}

	public InstantiateNsRequest additionalParamsForVnf(final List<ParamsForVnf> additionalParamsForVnf) {
		this.additionalParamsForVnf = additionalParamsForVnf;
		return this;
	}

	public InstantiateNsRequest addAdditionalParamsForVnfItem(final ParamsForVnf additionalParamsForVnfItem) {
		if (this.additionalParamsForVnf == null) {
			this.additionalParamsForVnf = new ArrayList<>();
		}
		this.additionalParamsForVnf.add(additionalParamsForVnfItem);
		return this;
	}

	/**
	 * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as
	 * opposed to the composite NS level, which is covered in additionalParamsForNs
	 * and as opposed to the nested NS level, which is covered in
	 * additionalParamForNestedNs). This is for VNFs that are to be created by the
	 * NFVO as part of the NS instantiation and not for existing VNF that are
	 * referenced for reuse.
	 *
	 * @return additionalParamsForVnf
	 **/
	@ApiModelProperty(value = "Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the composite NS level,  which is covered in additionalParamsForNs and as opposed  to the nested NS level, which is covered in  additionalParamForNestedNs). This is for VNFs that are  to be created by the NFVO as part of the NS instantiation  and not for existing VNF that are referenced for reuse. ")

	@Valid

	public List<ParamsForVnf> getAdditionalParamsForVnf() {
		return additionalParamsForVnf;
	}

	public void setAdditionalParamsForVnf(final List<ParamsForVnf> additionalParamsForVnf) {
		this.additionalParamsForVnf = additionalParamsForVnf;
	}

	public InstantiateNsRequest startTime(final OffsetDateTime startTime) {
		this.startTime = startTime;
		return this;
	}

	/**
	 * Timestamp indicating the earliest time to instantiate the NS. Cardinality
	 * \"0\" indicates the NS instantiation takes place immediately.
	 *
	 * @return startTime
	 **/
	@ApiModelProperty(value = "Timestamp indicating the earliest time to instantiate the NS.  Cardinality \"0\" indicates the NS instantiation takes place immediately. ")

	@Valid

	public OffsetDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(final OffsetDateTime startTime) {
		this.startTime = startTime;
	}

	public InstantiateNsRequest nsInstantiationLevelId(final String nsInstantiationLevelId) {
		this.nsInstantiationLevelId = nsInstantiationLevelId;
		return this;
	}

	/**
	 * Identifies one of the NS instantiation levels declared in the DF applicable
	 * to this NS instance. If not present, the default NS instantiation level as
	 * declared in the NSD shall be used.
	 *
	 * @return nsInstantiationLevelId
	 **/
	@ApiModelProperty(value = "Identifies one of the NS instantiation levels declared in the DF applicable to this NS instance. If not present, the default NS instantiation level as declared in the NSD shall be used. ")

	public String getNsInstantiationLevelId() {
		return nsInstantiationLevelId;
	}

	public void setNsInstantiationLevelId(final String nsInstantiationLevelId) {
		this.nsInstantiationLevelId = nsInstantiationLevelId;
	}

	public InstantiateNsRequest additionalAffinityOrAntiAffinityRule(final List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
		return this;
	}

	public InstantiateNsRequest addAdditionalAffinityOrAntiAffinityRuleItem(final AffinityOrAntiAffinityRule additionalAffinityOrAntiAffinityRuleItem) {
		if (this.additionalAffinityOrAntiAffinityRule == null) {
			this.additionalAffinityOrAntiAffinityRule = new ArrayList<>();
		}
		this.additionalAffinityOrAntiAffinityRule.add(additionalAffinityOrAntiAffinityRuleItem);
		return this;
	}

	/**
	 * Specifies additional affinity or anti-affinity constraint for the VNF
	 * instances to be instantiated as part of the NS instantiation. Shall not
	 * conflict with rules already specified in the NSD.
	 *
	 * @return additionalAffinityOrAntiAffinityRule
	 **/
	@ApiModelProperty(value = "Specifies additional affinity or anti-affinity constraint for the VNF instances to be instantiated as part of the NS instantiation. Shall not conflict with rules already specified in the NSD. ")

	@Valid

	public List<AffinityOrAntiAffinityRule> getAdditionalAffinityOrAntiAffinityRule() {
		return additionalAffinityOrAntiAffinityRule;
	}

	public void setAdditionalAffinityOrAntiAffinityRule(final List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InstantiateNsRequest instantiateNsRequest = (InstantiateNsRequest) o;
		return Objects.equals(this.nsFlavourId, instantiateNsRequest.nsFlavourId) &&
				Objects.equals(this.sapData, instantiateNsRequest.sapData) &&
				Objects.equals(this.addpnfData, instantiateNsRequest.addpnfData) &&
				Objects.equals(this.vnfInstanceData, instantiateNsRequest.vnfInstanceData) &&
				Objects.equals(this.nestedNsInstanceData, instantiateNsRequest.nestedNsInstanceData) &&
				Objects.equals(this.locationConstraints, instantiateNsRequest.locationConstraints) &&
				Objects.equals(this.additionalParamsForNs, instantiateNsRequest.additionalParamsForNs) &&
				Objects.equals(this.additionalParamForNestedNs, instantiateNsRequest.additionalParamForNestedNs) &&
				Objects.equals(this.additionalParamsForVnf, instantiateNsRequest.additionalParamsForVnf) &&
				Objects.equals(this.startTime, instantiateNsRequest.startTime) &&
				Objects.equals(this.nsInstantiationLevelId, instantiateNsRequest.nsInstantiationLevelId) &&
				Objects.equals(this.additionalAffinityOrAntiAffinityRule, instantiateNsRequest.additionalAffinityOrAntiAffinityRule);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsFlavourId, sapData, addpnfData, vnfInstanceData, nestedNsInstanceData, locationConstraints, additionalParamsForNs, additionalParamForNestedNs, additionalParamsForVnf, startTime, nsInstantiationLevelId, additionalAffinityOrAntiAffinityRule);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InstantiateNsRequest {\n");

		sb.append("    nsFlavourId: ").append(toIndentedString(nsFlavourId)).append("\n");
		sb.append("    sapData: ").append(toIndentedString(sapData)).append("\n");
		sb.append("    addpnfData: ").append(toIndentedString(addpnfData)).append("\n");
		sb.append("    vnfInstanceData: ").append(toIndentedString(vnfInstanceData)).append("\n");
		sb.append("    nestedNsInstanceData: ").append(toIndentedString(nestedNsInstanceData)).append("\n");
		sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
		sb.append("    additionalParamsForNs: ").append(toIndentedString(additionalParamsForNs)).append("\n");
		sb.append("    additionalParamForNestedNs: ").append(toIndentedString(additionalParamForNestedNs)).append("\n");
		sb.append("    additionalParamsForVnf: ").append(toIndentedString(additionalParamsForVnf)).append("\n");
		sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
		sb.append("    nsInstantiationLevelId: ").append(toIndentedString(nsInstantiationLevelId)).append("\n");
		sb.append("    additionalAffinityOrAntiAffinityRule: ").append(toIndentedString(additionalAffinityOrAntiAffinityRule)).append("\n");
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
