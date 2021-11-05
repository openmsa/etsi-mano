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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.AddPnfData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.AffinityOrAntiAffinityRule;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NestedNsInstanceData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NestedNsLocationConstraint;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ParamsForNestedNs;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ParamsForVnf;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SapData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfInstanceData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfLocationConstraint;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.WanConnectionData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Instantiate NS\&quot; operation. NOTE 1: The DF of the VNF instance shall match the VNF DF present in the associated VNF Profile. NOTE 2: The NS DF of each nested NS shall be one of the allowed flavours in the associated   NSD (as referenced in the nestedNsd attribute of the NSD of the NS to be instantiated). NOTE 3: The NSD of each referenced NSs (i.e. each nestedInstanceId) shall match the one of the nested NSD in the composite NSD. NOTE 4: When the NS is deployed over several sites, the VLs of this NS will include VNs in each site connected over the WAN. In this case, the \&quot;wanConnectionData\&quot; provides the needed information required to connect each VN to the WAN. Annex E provides additional information and guidelines about the usage of the \&quot;wanConnectionData\&quot; attribute. 
 */
@Schema(description = "This type represents request parameters for the \"Instantiate NS\" operation. NOTE 1: The DF of the VNF instance shall match the VNF DF present in the associated VNF Profile. NOTE 2: The NS DF of each nested NS shall be one of the allowed flavours in the associated   NSD (as referenced in the nestedNsd attribute of the NSD of the NS to be instantiated). NOTE 3: The NSD of each referenced NSs (i.e. each nestedInstanceId) shall match the one of the nested NSD in the composite NSD. NOTE 4: When the NS is deployed over several sites, the VLs of this NS will include VNs in each site connected over the WAN. In this case, the \"wanConnectionData\" provides the needed information required to connect each VN to the WAN. Annex E provides additional information and guidelines about the usage of the \"wanConnectionData\" attribute. ")
@Validated


public class InstantiateNsRequest   {
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

  @JsonProperty("nestedNslocationConstraints")
  @Valid
  private List<NestedNsLocationConstraint> nestedNslocationConstraints = null;

  @JsonProperty("additionalParamsForNs")
  private KeyValuePairs additionalParamsForNs = null;

  @JsonProperty("additionalParamsForNestedNs")
  @Valid
  private List<ParamsForNestedNs> additionalParamsForNestedNs = null;

  @JsonProperty("additionalParamsForVnf")
  @Valid
  private List<ParamsForVnf> additionalParamsForVnf = null;

  @JsonProperty("startTime")
  private OffsetDateTime startTime = null;

  @JsonProperty("nsInstantiationLevelId")
  private String nsInstantiationLevelId = null;

  @JsonProperty("wanConnectionData")
  @Valid
  private List<WanConnectionData> wanConnectionData = null;

  @JsonProperty("additionalAffinityOrAntiAffinityRule")
  @Valid
  private List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;

  public InstantiateNsRequest nsFlavourId(String nsFlavourId) {
    this.nsFlavourId = nsFlavourId;
    return this;
  }

  /**
   * Get nsFlavourId
   * @return nsFlavourId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsFlavourId() {
    return nsFlavourId;
  }

  public void setNsFlavourId(String nsFlavourId) {
    this.nsFlavourId = nsFlavourId;
  }

  public InstantiateNsRequest sapData(List<SapData> sapData) {
    this.sapData = sapData;
    return this;
  }

  public InstantiateNsRequest addSapDataItem(SapData sapDataItem) {
    if (this.sapData == null) {
      this.sapData = new ArrayList<>();
    }
    this.sapData.add(sapDataItem);
    return this;
  }

  /**
   * Create data concerning the SAPs of this NS. 
   * @return sapData
   **/
  @Schema(description = "Create data concerning the SAPs of this NS. ")
      @Valid
    public List<SapData> getSapData() {
    return sapData;
  }

  public void setSapData(List<SapData> sapData) {
    this.sapData = sapData;
  }

  public InstantiateNsRequest addpnfData(List<AddPnfData> addpnfData) {
    this.addpnfData = addpnfData;
    return this;
  }

  public InstantiateNsRequest addAddpnfDataItem(AddPnfData addpnfDataItem) {
    if (this.addpnfData == null) {
      this.addpnfData = new ArrayList<>();
    }
    this.addpnfData.add(addpnfDataItem);
    return this;
  }

  /**
   * Information on the PNF(s) that are part of this NS. 
   * @return addpnfData
   **/
  @Schema(description = "Information on the PNF(s) that are part of this NS. ")
      @Valid
    public List<AddPnfData> getAddpnfData() {
    return addpnfData;
  }

  public void setAddpnfData(List<AddPnfData> addpnfData) {
    this.addpnfData = addpnfData;
  }

  public InstantiateNsRequest vnfInstanceData(List<VnfInstanceData> vnfInstanceData) {
    this.vnfInstanceData = vnfInstanceData;
    return this;
  }

  public InstantiateNsRequest addVnfInstanceDataItem(VnfInstanceData vnfInstanceDataItem) {
    if (this.vnfInstanceData == null) {
      this.vnfInstanceData = new ArrayList<>();
    }
    this.vnfInstanceData.add(vnfInstanceDataItem);
    return this;
  }

  /**
   * Specify an existing VNF instance to be used in the NS. If needed, the VNF Profile to be used for this VNF instance is also provided. See note 1. 
   * @return vnfInstanceData
   **/
  @Schema(description = "Specify an existing VNF instance to be used in the NS. If needed, the VNF Profile to be used for this VNF instance is also provided. See note 1. ")
      @Valid
    public List<VnfInstanceData> getVnfInstanceData() {
    return vnfInstanceData;
  }

  public void setVnfInstanceData(List<VnfInstanceData> vnfInstanceData) {
    this.vnfInstanceData = vnfInstanceData;
  }

  public InstantiateNsRequest nestedNsInstanceData(List<NestedNsInstanceData> nestedNsInstanceData) {
    this.nestedNsInstanceData = nestedNsInstanceData;
    return this;
  }

  public InstantiateNsRequest addNestedNsInstanceDataItem(NestedNsInstanceData nestedNsInstanceDataItem) {
    if (this.nestedNsInstanceData == null) {
      this.nestedNsInstanceData = new ArrayList<>();
    }
    this.nestedNsInstanceData.add(nestedNsInstanceDataItem);
    return this;
  }

  /**
   * Specify an existing NS instance to be used as a nested NS within the NS. If needed, the NS Profile to be used for this nested NS  instance is also provided. See note 2 and note 3. 
   * @return nestedNsInstanceData
   **/
  @Schema(description = "Specify an existing NS instance to be used as a nested NS within the NS. If needed, the NS Profile to be used for this nested NS  instance is also provided. See note 2 and note 3. ")
      @Valid
    public List<NestedNsInstanceData> getNestedNsInstanceData() {
    return nestedNsInstanceData;
  }

  public void setNestedNsInstanceData(List<NestedNsInstanceData> nestedNsInstanceData) {
    this.nestedNsInstanceData = nestedNsInstanceData;
  }

  public InstantiateNsRequest locationConstraints(List<VnfLocationConstraint> locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  public InstantiateNsRequest addLocationConstraintsItem(VnfLocationConstraint locationConstraintsItem) {
    if (this.locationConstraints == null) {
      this.locationConstraints = new ArrayList<>();
    }
    this.locationConstraints.add(locationConstraintsItem);
    return this;
  }

  /**
   * Defines the location constraints for the VNF to be instantiated as part of the NS instantiation. An example can be a constraint for the VNF to be in a specific geographic location.. 
   * @return locationConstraints
   **/
  @Schema(description = "Defines the location constraints for the VNF to be instantiated as part of the NS instantiation. An example can be a constraint for the VNF to be in a specific geographic location.. ")
      @Valid
    public List<VnfLocationConstraint> getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(List<VnfLocationConstraint> locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  public InstantiateNsRequest nestedNslocationConstraints(List<NestedNsLocationConstraint> nestedNslocationConstraints) {
    this.nestedNslocationConstraints = nestedNslocationConstraints;
    return this;
  }

  public InstantiateNsRequest addNestedNslocationConstraintsItem(NestedNsLocationConstraint nestedNslocationConstraintsItem) {
    if (this.nestedNslocationConstraints == null) {
      this.nestedNslocationConstraints = new ArrayList<>();
    }
    this.nestedNslocationConstraints.add(nestedNslocationConstraintsItem);
    return this;
  }

  /**
   * Defines the location constraints for the nested NS to be instantiated as part of the NS instantiation. An example can be a constraint for the nested NS to be in a specific geographic location. 
   * @return nestedNslocationConstraints
   **/
  @Schema(description = "Defines the location constraints for the nested NS to be instantiated as part of the NS instantiation. An example can be a constraint for the nested NS to be in a specific geographic location. ")
      @Valid
    public List<NestedNsLocationConstraint> getNestedNslocationConstraints() {
    return nestedNslocationConstraints;
  }

  public void setNestedNslocationConstraints(List<NestedNsLocationConstraint> nestedNslocationConstraints) {
    this.nestedNslocationConstraints = nestedNslocationConstraints;
  }

  public InstantiateNsRequest additionalParamsForNs(KeyValuePairs additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
    return this;
  }

  /**
   * Get additionalParamsForNs
   * @return additionalParamsForNs
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParamsForNs() {
    return additionalParamsForNs;
  }

  public void setAdditionalParamsForNs(KeyValuePairs additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
  }

  public InstantiateNsRequest additionalParamsForNestedNs(List<ParamsForNestedNs> additionalParamsForNestedNs) {
    this.additionalParamsForNestedNs = additionalParamsForNestedNs;
    return this;
  }

  public InstantiateNsRequest addAdditionalParamsForNestedNsItem(ParamsForNestedNs additionalParamsForNestedNsItem) {
    if (this.additionalParamsForNestedNs == null) {
      this.additionalParamsForNestedNs = new ArrayList<>();
    }
    this.additionalParamsForNestedNs.add(additionalParamsForNestedNsItem);
    return this;
  }

  /**
   * Allows the OSS/BSS to provide additional parameter(s) per nested NS instance (as opposed to the composite NS level, which is covered in additionalParamsForNs, and as opposed to the VNF level, which is covered in additionalParamsForVnf). This is for nested NS instances that are to be created by the NFVO as part of the NS instantiation and not for existing nested NS instances that are referenced for reuse. 
   * @return additionalParamsForNestedNs
   **/
  @Schema(description = "Allows the OSS/BSS to provide additional parameter(s) per nested NS instance (as opposed to the composite NS level, which is covered in additionalParamsForNs, and as opposed to the VNF level, which is covered in additionalParamsForVnf). This is for nested NS instances that are to be created by the NFVO as part of the NS instantiation and not for existing nested NS instances that are referenced for reuse. ")
      @Valid
    public List<ParamsForNestedNs> getAdditionalParamsForNestedNs() {
    return additionalParamsForNestedNs;
  }

  public void setAdditionalParamsForNestedNs(List<ParamsForNestedNs> additionalParamsForNestedNs) {
    this.additionalParamsForNestedNs = additionalParamsForNestedNs;
  }

  public InstantiateNsRequest additionalParamsForVnf(List<ParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
    return this;
  }

  public InstantiateNsRequest addAdditionalParamsForVnfItem(ParamsForVnf additionalParamsForVnfItem) {
    if (this.additionalParamsForVnf == null) {
      this.additionalParamsForVnf = new ArrayList<>();
    }
    this.additionalParamsForVnf.add(additionalParamsForVnfItem);
    return this;
  }

  /**
   * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the composite NS level,  which is covered in additionalParamsForNs and as opposed  to the nested NS level, which is covered in  additionalParamsForNestedNs). This is for VNFs that are to be created by the NFVO as part of the NS instantiation  and not for existing VNF that are referenced for reuse. 
   * @return additionalParamsForVnf
   **/
  @Schema(description = "Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the composite NS level,  which is covered in additionalParamsForNs and as opposed  to the nested NS level, which is covered in  additionalParamsForNestedNs). This is for VNFs that are to be created by the NFVO as part of the NS instantiation  and not for existing VNF that are referenced for reuse. ")
      @Valid
    public List<ParamsForVnf> getAdditionalParamsForVnf() {
    return additionalParamsForVnf;
  }

  public void setAdditionalParamsForVnf(List<ParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
  }

  public InstantiateNsRequest startTime(OffsetDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public InstantiateNsRequest nsInstantiationLevelId(String nsInstantiationLevelId) {
    this.nsInstantiationLevelId = nsInstantiationLevelId;
    return this;
  }

  /**
   * Get nsInstantiationLevelId
   * @return nsInstantiationLevelId
   **/
  @Schema(description = "")
  
    public String getNsInstantiationLevelId() {
    return nsInstantiationLevelId;
  }

  public void setNsInstantiationLevelId(String nsInstantiationLevelId) {
    this.nsInstantiationLevelId = nsInstantiationLevelId;
  }

  public InstantiateNsRequest wanConnectionData(List<WanConnectionData> wanConnectionData) {
    this.wanConnectionData = wanConnectionData;
    return this;
  }

  public InstantiateNsRequest addWanConnectionDataItem(WanConnectionData wanConnectionDataItem) {
    if (this.wanConnectionData == null) {
      this.wanConnectionData = new ArrayList<>();
    }
    this.wanConnectionData.add(wanConnectionDataItem);
    return this;
  }

  /**
   * Information for connecting VNs to the WAN when VLs are deployed across a WAN. See note 4. 
   * @return wanConnectionData
   **/
  @Schema(description = "Information for connecting VNs to the WAN when VLs are deployed across a WAN. See note 4. ")
      @Valid
    public List<WanConnectionData> getWanConnectionData() {
    return wanConnectionData;
  }

  public void setWanConnectionData(List<WanConnectionData> wanConnectionData) {
    this.wanConnectionData = wanConnectionData;
  }

  public InstantiateNsRequest additionalAffinityOrAntiAffinityRule(List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
    this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
    return this;
  }

  public InstantiateNsRequest addAdditionalAffinityOrAntiAffinityRuleItem(AffinityOrAntiAffinityRule additionalAffinityOrAntiAffinityRuleItem) {
    if (this.additionalAffinityOrAntiAffinityRule == null) {
      this.additionalAffinityOrAntiAffinityRule = new ArrayList<>();
    }
    this.additionalAffinityOrAntiAffinityRule.add(additionalAffinityOrAntiAffinityRuleItem);
    return this;
  }

  /**
   * Specifies additional affinity or anti-affinity constraint for the VNF instances to be instantiated as part of the NS instantiation. Shall not conflict with rules already specified in the NSD. 
   * @return additionalAffinityOrAntiAffinityRule
   **/
  @Schema(description = "Specifies additional affinity or anti-affinity constraint for the VNF instances to be instantiated as part of the NS instantiation. Shall not conflict with rules already specified in the NSD. ")
      @Valid
    public List<AffinityOrAntiAffinityRule> getAdditionalAffinityOrAntiAffinityRule() {
    return additionalAffinityOrAntiAffinityRule;
  }

  public void setAdditionalAffinityOrAntiAffinityRule(List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
    this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstantiateNsRequest instantiateNsRequest = (InstantiateNsRequest) o;
    return Objects.equals(this.nsFlavourId, instantiateNsRequest.nsFlavourId) &&
        Objects.equals(this.sapData, instantiateNsRequest.sapData) &&
        Objects.equals(this.addpnfData, instantiateNsRequest.addpnfData) &&
        Objects.equals(this.vnfInstanceData, instantiateNsRequest.vnfInstanceData) &&
        Objects.equals(this.nestedNsInstanceData, instantiateNsRequest.nestedNsInstanceData) &&
        Objects.equals(this.locationConstraints, instantiateNsRequest.locationConstraints) &&
        Objects.equals(this.nestedNslocationConstraints, instantiateNsRequest.nestedNslocationConstraints) &&
        Objects.equals(this.additionalParamsForNs, instantiateNsRequest.additionalParamsForNs) &&
        Objects.equals(this.additionalParamsForNestedNs, instantiateNsRequest.additionalParamsForNestedNs) &&
        Objects.equals(this.additionalParamsForVnf, instantiateNsRequest.additionalParamsForVnf) &&
        Objects.equals(this.startTime, instantiateNsRequest.startTime) &&
        Objects.equals(this.nsInstantiationLevelId, instantiateNsRequest.nsInstantiationLevelId) &&
        Objects.equals(this.wanConnectionData, instantiateNsRequest.wanConnectionData) &&
        Objects.equals(this.additionalAffinityOrAntiAffinityRule, instantiateNsRequest.additionalAffinityOrAntiAffinityRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsFlavourId, sapData, addpnfData, vnfInstanceData, nestedNsInstanceData, locationConstraints, nestedNslocationConstraints, additionalParamsForNs, additionalParamsForNestedNs, additionalParamsForVnf, startTime, nsInstantiationLevelId, wanConnectionData, additionalAffinityOrAntiAffinityRule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstantiateNsRequest {\n");
    
    sb.append("    nsFlavourId: ").append(toIndentedString(nsFlavourId)).append("\n");
    sb.append("    sapData: ").append(toIndentedString(sapData)).append("\n");
    sb.append("    addpnfData: ").append(toIndentedString(addpnfData)).append("\n");
    sb.append("    vnfInstanceData: ").append(toIndentedString(vnfInstanceData)).append("\n");
    sb.append("    nestedNsInstanceData: ").append(toIndentedString(nestedNsInstanceData)).append("\n");
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
    sb.append("    nestedNslocationConstraints: ").append(toIndentedString(nestedNslocationConstraints)).append("\n");
    sb.append("    additionalParamsForNs: ").append(toIndentedString(additionalParamsForNs)).append("\n");
    sb.append("    additionalParamsForNestedNs: ").append(toIndentedString(additionalParamsForNestedNs)).append("\n");
    sb.append("    additionalParamsForVnf: ").append(toIndentedString(additionalParamsForVnf)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    nsInstantiationLevelId: ").append(toIndentedString(nsInstantiationLevelId)).append("\n");
    sb.append("    wanConnectionData: ").append(toIndentedString(wanConnectionData)).append("\n");
    sb.append("    additionalAffinityOrAntiAffinityRule: ").append(toIndentedString(additionalAffinityOrAntiAffinityRule)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
