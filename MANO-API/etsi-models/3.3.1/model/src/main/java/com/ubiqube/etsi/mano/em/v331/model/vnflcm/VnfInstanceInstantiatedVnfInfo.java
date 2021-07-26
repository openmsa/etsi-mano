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
package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ExtManagedVirtualLinkInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ExtVirtualLinkInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.MonitoringParameter;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ScaleInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfExtCpInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfOperationalStateType;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfVirtualLinkResourceInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfcInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfcResourceInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information specific to an instantiated VNF instance. This attribute shall be present if the instantiateState attribute value is INSTANTIATED. 
 */
@Schema(description = "Information specific to an instantiated VNF instance. This attribute shall be present if the instantiateState attribute value is INSTANTIATED. ")
@Validated


public class VnfInstanceInstantiatedVnfInfo   {
  @JsonProperty("flavourId")
  private String flavourId = null;

  @JsonProperty("vnfState")
  private VnfOperationalStateType vnfState = null;

  @JsonProperty("scaleStatus")
  @Valid
  private List<ScaleInfo> scaleStatus = null;

  @JsonProperty("maxScaleLevels")
  @Valid
  private List<ScaleInfo> maxScaleLevels = null;

  @JsonProperty("extCpInfo")
  @Valid
  private List<VnfExtCpInfo> extCpInfo = new ArrayList<>();

  @JsonProperty("extVirtualLinkInfo")
  @Valid
  private List<ExtVirtualLinkInfo> extVirtualLinkInfo = null;

  @JsonProperty("extManagedVirtualLinkInfo")
  @Valid
  private List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo = null;

  @JsonProperty("monitoringParameters")
  @Valid
  private List<MonitoringParameter> monitoringParameters = null;

  @JsonProperty("localizationLanguage")
  private String localizationLanguage = null;

  @JsonProperty("vnfcResourceInfo")
  @Valid
  private List<VnfcResourceInfo> vnfcResourceInfo = null;

  @JsonProperty("vnfVirtualLinkResourceInfo")
  @Valid
  private List<VnfVirtualLinkResourceInfo> vnfVirtualLinkResourceInfo = null;

  @JsonProperty("vnfVirtualStorageResourceInfo")
  @Valid
  private List<VirtualStorageResourceInfo> vnfVirtualStorageResourceInfo = null;

  @JsonProperty("vnfcInfo")
  @Valid
  private List<VnfcInfo> vnfcInfo = null;

  public VnfInstanceInstantiatedVnfInfo flavourId(String flavourId) {
    this.flavourId = flavourId;
    return this;
  }

  /**
   * Get flavourId
   * @return flavourId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getFlavourId() {
    return flavourId;
  }

  public void setFlavourId(String flavourId) {
    this.flavourId = flavourId;
  }

  public VnfInstanceInstantiatedVnfInfo vnfState(VnfOperationalStateType vnfState) {
    this.vnfState = vnfState;
    return this;
  }

  /**
   * Get vnfState
   * @return vnfState
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public VnfOperationalStateType getVnfState() {
    return vnfState;
  }

  public void setVnfState(VnfOperationalStateType vnfState) {
    this.vnfState = vnfState;
  }

  public VnfInstanceInstantiatedVnfInfo scaleStatus(List<ScaleInfo> scaleStatus) {
    this.scaleStatus = scaleStatus;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addScaleStatusItem(ScaleInfo scaleStatusItem) {
    if (this.scaleStatus == null) {
      this.scaleStatus = new ArrayList<>();
    }
    this.scaleStatus.add(scaleStatusItem);
    return this;
  }

  /**
   * Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \"big\" the VNF has been scaled w.r.t. that aspect. 
   * @return scaleStatus
   **/
  @Schema(description = "Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \"big\" the VNF has been scaled w.r.t. that aspect. ")
      @Valid
    public List<ScaleInfo> getScaleStatus() {
    return scaleStatus;
  }

  public void setScaleStatus(List<ScaleInfo> scaleStatus) {
    this.scaleStatus = scaleStatus;
  }

  public VnfInstanceInstantiatedVnfInfo maxScaleLevels(List<ScaleInfo> maxScaleLevels) {
    this.maxScaleLevels = maxScaleLevels;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addMaxScaleLevelsItem(ScaleInfo maxScaleLevelsItem) {
    if (this.maxScaleLevels == null) {
      this.maxScaleLevels = new ArrayList<>();
    }
    this.maxScaleLevels.add(maxScaleLevelsItem);
    return this;
  }

  /**
   * Maximum allowed scale levels of the VNF, one entry per aspect. This attribute shall be present if the VNF supports scaling. 
   * @return maxScaleLevels
   **/
  @Schema(description = "Maximum allowed scale levels of the VNF, one entry per aspect. This attribute shall be present if the VNF supports scaling. ")
      @Valid
    public List<ScaleInfo> getMaxScaleLevels() {
    return maxScaleLevels;
  }

  public void setMaxScaleLevels(List<ScaleInfo> maxScaleLevels) {
    this.maxScaleLevels = maxScaleLevels;
  }

  public VnfInstanceInstantiatedVnfInfo extCpInfo(List<VnfExtCpInfo> extCpInfo) {
    this.extCpInfo = extCpInfo;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addExtCpInfoItem(VnfExtCpInfo extCpInfoItem) {
    this.extCpInfo.add(extCpInfoItem);
    return this;
  }

  /**
   * Information about the external CPs exposed by the VNF instance. When trunking is enabled, the list of entries includes both, external CPs corresponding to parent ports of a trunk, and external CPs associated to sub-ports of a trunk. 
   * @return extCpInfo
   **/
  @Schema(required = true, description = "Information about the external CPs exposed by the VNF instance. When trunking is enabled, the list of entries includes both, external CPs corresponding to parent ports of a trunk, and external CPs associated to sub-ports of a trunk. ")
      @NotNull
    @Valid
  @Size(min=1)   public List<VnfExtCpInfo> getExtCpInfo() {
    return extCpInfo;
  }

  public void setExtCpInfo(List<VnfExtCpInfo> extCpInfo) {
    this.extCpInfo = extCpInfo;
  }

  public VnfInstanceInstantiatedVnfInfo extVirtualLinkInfo(List<ExtVirtualLinkInfo> extVirtualLinkInfo) {
    this.extVirtualLinkInfo = extVirtualLinkInfo;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addExtVirtualLinkInfoItem(ExtVirtualLinkInfo extVirtualLinkInfoItem) {
    if (this.extVirtualLinkInfo == null) {
      this.extVirtualLinkInfo = new ArrayList<>();
    }
    this.extVirtualLinkInfo.add(extVirtualLinkInfoItem);
    return this;
  }

  /**
   * Information about the external VLs the VNF instance is connected to. 
   * @return extVirtualLinkInfo
   **/
  @Schema(description = "Information about the external VLs the VNF instance is connected to. ")
      @Valid
    public List<ExtVirtualLinkInfo> getExtVirtualLinkInfo() {
    return extVirtualLinkInfo;
  }

  public void setExtVirtualLinkInfo(List<ExtVirtualLinkInfo> extVirtualLinkInfo) {
    this.extVirtualLinkInfo = extVirtualLinkInfo;
  }

  public VnfInstanceInstantiatedVnfInfo extManagedVirtualLinkInfo(List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
    this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addExtManagedVirtualLinkInfoItem(ExtManagedVirtualLinkInfo extManagedVirtualLinkInfoItem) {
    if (this.extManagedVirtualLinkInfo == null) {
      this.extManagedVirtualLinkInfo = new ArrayList<>();
    }
    this.extManagedVirtualLinkInfo.add(extManagedVirtualLinkInfoItem);
    return this;
  }

  /**
   * External virtual links the VNF instance is connected to. It is possible to have several ExtManagedVirtualLinkInfo for the same VNF internal VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkInfo corresponding to the same VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 5.5.3.5). 
   * @return extManagedVirtualLinkInfo
   **/
  @Schema(description = "External virtual links the VNF instance is connected to. It is possible to have several ExtManagedVirtualLinkInfo for the same VNF internal VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkInfo corresponding to the same VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 5.5.3.5). ")
      @Valid
    public List<ExtManagedVirtualLinkInfo> getExtManagedVirtualLinkInfo() {
    return extManagedVirtualLinkInfo;
  }

  public void setExtManagedVirtualLinkInfo(List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
    this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
  }

  public VnfInstanceInstantiatedVnfInfo monitoringParameters(List<MonitoringParameter> monitoringParameters) {
    this.monitoringParameters = monitoringParameters;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addMonitoringParametersItem(MonitoringParameter monitoringParametersItem) {
    if (this.monitoringParameters == null) {
      this.monitoringParameters = new ArrayList<>();
    }
    this.monitoringParameters.add(monitoringParametersItem);
    return this;
  }

  /**
   * Active monitoring parameters. 
   * @return monitoringParameters
   **/
  @Schema(description = "Active monitoring parameters. ")
      @Valid
    public List<MonitoringParameter> getMonitoringParameters() {
    return monitoringParameters;
  }

  public void setMonitoringParameters(List<MonitoringParameter> monitoringParameters) {
    this.monitoringParameters = monitoringParameters;
  }

  public VnfInstanceInstantiatedVnfInfo localizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
    return this;
  }

  /**
   * Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646. 
   * @return localizationLanguage
   **/
  @Schema(description = "Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646. ")
  
    public String getLocalizationLanguage() {
    return localizationLanguage;
  }

  public void setLocalizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
  }

  public VnfInstanceInstantiatedVnfInfo vnfcResourceInfo(List<VnfcResourceInfo> vnfcResourceInfo) {
    this.vnfcResourceInfo = vnfcResourceInfo;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addVnfcResourceInfoItem(VnfcResourceInfo vnfcResourceInfoItem) {
    if (this.vnfcResourceInfo == null) {
      this.vnfcResourceInfo = new ArrayList<>();
    }
    this.vnfcResourceInfo.add(vnfcResourceInfoItem);
    return this;
  }

  /**
   * Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance. 
   * @return vnfcResourceInfo
   **/
  @Schema(description = "Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance. ")
      @Valid
    public List<VnfcResourceInfo> getVnfcResourceInfo() {
    return vnfcResourceInfo;
  }

  public void setVnfcResourceInfo(List<VnfcResourceInfo> vnfcResourceInfo) {
    this.vnfcResourceInfo = vnfcResourceInfo;
  }

  public VnfInstanceInstantiatedVnfInfo vnfVirtualLinkResourceInfo(List<VnfVirtualLinkResourceInfo> vnfVirtualLinkResourceInfo) {
    this.vnfVirtualLinkResourceInfo = vnfVirtualLinkResourceInfo;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addVnfVirtualLinkResourceInfoItem(VnfVirtualLinkResourceInfo vnfVirtualLinkResourceInfoItem) {
    if (this.vnfVirtualLinkResourceInfo == null) {
      this.vnfVirtualLinkResourceInfo = new ArrayList<>();
    }
    this.vnfVirtualLinkResourceInfo.add(vnfVirtualLinkResourceInfoItem);
    return this;
  }

  /**
   * Information about the virtualised network resources used by the VLs of the VNF instance. 
   * @return vnfVirtualLinkResourceInfo
   **/
  @Schema(description = "Information about the virtualised network resources used by the VLs of the VNF instance. ")
      @Valid
    public List<VnfVirtualLinkResourceInfo> getVnfVirtualLinkResourceInfo() {
    return vnfVirtualLinkResourceInfo;
  }

  public void setVnfVirtualLinkResourceInfo(List<VnfVirtualLinkResourceInfo> vnfVirtualLinkResourceInfo) {
    this.vnfVirtualLinkResourceInfo = vnfVirtualLinkResourceInfo;
  }

  public VnfInstanceInstantiatedVnfInfo vnfVirtualStorageResourceInfo(List<VirtualStorageResourceInfo> vnfVirtualStorageResourceInfo) {
    this.vnfVirtualStorageResourceInfo = vnfVirtualStorageResourceInfo;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addVnfVirtualStorageResourceInfoItem(VirtualStorageResourceInfo vnfVirtualStorageResourceInfoItem) {
    if (this.vnfVirtualStorageResourceInfo == null) {
      this.vnfVirtualStorageResourceInfo = new ArrayList<>();
    }
    this.vnfVirtualStorageResourceInfo.add(vnfVirtualStorageResourceInfoItem);
    return this;
  }

  /**
   * Information on the virtualised storage resource(s) used as storage for the VNF instance. 
   * @return vnfVirtualStorageResourceInfo
   **/
  @Schema(description = "Information on the virtualised storage resource(s) used as storage for the VNF instance. ")
      @Valid
    public List<VirtualStorageResourceInfo> getVnfVirtualStorageResourceInfo() {
    return vnfVirtualStorageResourceInfo;
  }

  public void setVnfVirtualStorageResourceInfo(List<VirtualStorageResourceInfo> vnfVirtualStorageResourceInfo) {
    this.vnfVirtualStorageResourceInfo = vnfVirtualStorageResourceInfo;
  }

  public VnfInstanceInstantiatedVnfInfo vnfcInfo(List<VnfcInfo> vnfcInfo) {
    this.vnfcInfo = vnfcInfo;
    return this;
  }

  public VnfInstanceInstantiatedVnfInfo addVnfcInfoItem(VnfcInfo vnfcInfoItem) {
    if (this.vnfcInfo == null) {
      this.vnfcInfo = new ArrayList<>();
    }
    this.vnfcInfo.add(vnfcInfoItem);
    return this;
  }

  /**
   * Information about the VNFC instances. 
   * @return vnfcInfo
   **/
  @Schema(description = "Information about the VNFC instances. ")
      @Valid
    public List<VnfcInfo> getVnfcInfo() {
    return vnfcInfo;
  }

  public void setVnfcInfo(List<VnfcInfo> vnfcInfo) {
    this.vnfcInfo = vnfcInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstanceInstantiatedVnfInfo vnfInstanceInstantiatedVnfInfo = (VnfInstanceInstantiatedVnfInfo) o;
    return Objects.equals(this.flavourId, vnfInstanceInstantiatedVnfInfo.flavourId) &&
        Objects.equals(this.vnfState, vnfInstanceInstantiatedVnfInfo.vnfState) &&
        Objects.equals(this.scaleStatus, vnfInstanceInstantiatedVnfInfo.scaleStatus) &&
        Objects.equals(this.maxScaleLevels, vnfInstanceInstantiatedVnfInfo.maxScaleLevels) &&
        Objects.equals(this.extCpInfo, vnfInstanceInstantiatedVnfInfo.extCpInfo) &&
        Objects.equals(this.extVirtualLinkInfo, vnfInstanceInstantiatedVnfInfo.extVirtualLinkInfo) &&
        Objects.equals(this.extManagedVirtualLinkInfo, vnfInstanceInstantiatedVnfInfo.extManagedVirtualLinkInfo) &&
        Objects.equals(this.monitoringParameters, vnfInstanceInstantiatedVnfInfo.monitoringParameters) &&
        Objects.equals(this.localizationLanguage, vnfInstanceInstantiatedVnfInfo.localizationLanguage) &&
        Objects.equals(this.vnfcResourceInfo, vnfInstanceInstantiatedVnfInfo.vnfcResourceInfo) &&
        Objects.equals(this.vnfVirtualLinkResourceInfo, vnfInstanceInstantiatedVnfInfo.vnfVirtualLinkResourceInfo) &&
        Objects.equals(this.vnfVirtualStorageResourceInfo, vnfInstanceInstantiatedVnfInfo.vnfVirtualStorageResourceInfo) &&
        Objects.equals(this.vnfcInfo, vnfInstanceInstantiatedVnfInfo.vnfcInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flavourId, vnfState, scaleStatus, maxScaleLevels, extCpInfo, extVirtualLinkInfo, extManagedVirtualLinkInfo, monitoringParameters, localizationLanguage, vnfcResourceInfo, vnfVirtualLinkResourceInfo, vnfVirtualStorageResourceInfo, vnfcInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstanceInstantiatedVnfInfo {\n");
    
    sb.append("    flavourId: ").append(toIndentedString(flavourId)).append("\n");
    sb.append("    vnfState: ").append(toIndentedString(vnfState)).append("\n");
    sb.append("    scaleStatus: ").append(toIndentedString(scaleStatus)).append("\n");
    sb.append("    maxScaleLevels: ").append(toIndentedString(maxScaleLevels)).append("\n");
    sb.append("    extCpInfo: ").append(toIndentedString(extCpInfo)).append("\n");
    sb.append("    extVirtualLinkInfo: ").append(toIndentedString(extVirtualLinkInfo)).append("\n");
    sb.append("    extManagedVirtualLinkInfo: ").append(toIndentedString(extManagedVirtualLinkInfo)).append("\n");
    sb.append("    monitoringParameters: ").append(toIndentedString(monitoringParameters)).append("\n");
    sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
    sb.append("    vnfcResourceInfo: ").append(toIndentedString(vnfcResourceInfo)).append("\n");
    sb.append("    vnfVirtualLinkResourceInfo: ").append(toIndentedString(vnfVirtualLinkResourceInfo)).append("\n");
    sb.append("    vnfVirtualStorageResourceInfo: ").append(toIndentedString(vnfVirtualStorageResourceInfo)).append("\n");
    sb.append("    vnfcInfo: ").append(toIndentedString(vnfcInfo)).append("\n");
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
