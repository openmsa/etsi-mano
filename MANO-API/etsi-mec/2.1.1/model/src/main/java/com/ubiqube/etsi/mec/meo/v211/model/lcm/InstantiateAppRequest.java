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
package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.LocationConstraints;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.MECHostInformation;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.VimConnectionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InstantiateAppRequest
 */
@Validated
public class InstantiateAppRequest   {
  @JsonProperty("locationConstraints")
  private LocationConstraints locationConstraints = null;

  @JsonProperty("selectedMECHostInfo")
  @Valid
  private List<MECHostInformation> selectedMECHostInfo = new ArrayList<>();

  @JsonProperty("vimConnectionInfo")
  @Valid
  private List<VimConnectionInfo> vimConnectionInfo = null;

  @JsonProperty("virtualComputeDescriptor")
  private String virtualComputeDescriptor = null;

  @JsonProperty("virtualStorageDescriptor")
  @Valid
  private List<String> virtualStorageDescriptor = null;

  public InstantiateAppRequest locationConstraints(LocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  /**
   * Get locationConstraints
   * @return locationConstraints
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public LocationConstraints getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(LocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  public InstantiateAppRequest selectedMECHostInfo(List<MECHostInformation> selectedMECHostInfo) {
    this.selectedMECHostInfo = selectedMECHostInfo;
    return this;
  }

  public InstantiateAppRequest addSelectedMECHostInfoItem(MECHostInformation selectedMECHostInfoItem) {
    this.selectedMECHostInfo.add(selectedMECHostInfoItem);
    return this;
  }

  /**
   * Describes the information of selected host for the application instance. See note 2.
   * @return selectedMECHostInfo
  **/
  @ApiModelProperty(required = true, value = "Describes the information of selected host for the application instance. See note 2.")
      @NotNull
    @Valid
  @Size(min=1)   public List<MECHostInformation> getSelectedMECHostInfo() {
    return selectedMECHostInfo;
  }

  public void setSelectedMECHostInfo(List<MECHostInformation> selectedMECHostInfo) {
    this.selectedMECHostInfo = selectedMECHostInfo;
  }

  public InstantiateAppRequest vimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public InstantiateAppRequest addVimConnectionInfoItem(VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new ArrayList<>();
    }
    this.vimConnectionInfo.add(vimConnectionInfoItem);
    return this;
  }

  /**
   * Information about VIM connections to be used for managing the resources for the application instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if application-related resource management in direct mode is applicable. See note 2.
   * @return vimConnectionInfo
  **/
  @ApiModelProperty(value = "Information about VIM connections to be used for managing the resources for the application instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if application-related resource management in direct mode is applicable. See note 2.")
      @Valid
    public List<VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public InstantiateAppRequest virtualComputeDescriptor(String virtualComputeDescriptor) {
    this.virtualComputeDescriptor = virtualComputeDescriptor;
    return this;
  }

  /**
   * Get virtualComputeDescriptor
   * @return virtualComputeDescriptor
  **/
  @ApiModelProperty(value = "")
  
    public String getVirtualComputeDescriptor() {
    return virtualComputeDescriptor;
  }

  public void setVirtualComputeDescriptor(String virtualComputeDescriptor) {
    this.virtualComputeDescriptor = virtualComputeDescriptor;
  }

  public InstantiateAppRequest virtualStorageDescriptor(List<String> virtualStorageDescriptor) {
    this.virtualStorageDescriptor = virtualStorageDescriptor;
    return this;
  }

  public InstantiateAppRequest addVirtualStorageDescriptorItem(String virtualStorageDescriptorItem) {
    if (this.virtualStorageDescriptor == null) {
      this.virtualStorageDescriptor = new ArrayList<>();
    }
    this.virtualStorageDescriptor.add(virtualStorageDescriptorItem);
    return this;
  }

  /**
   * Defines descriptors of virtual storage resources to be used by the application instance to be created. See note 1.
   * @return virtualStorageDescriptor
  **/
  @ApiModelProperty(value = "Defines descriptors of virtual storage resources to be used by the application instance to be created. See note 1.")
  
    public List<String> getVirtualStorageDescriptor() {
    return virtualStorageDescriptor;
  }

  public void setVirtualStorageDescriptor(List<String> virtualStorageDescriptor) {
    this.virtualStorageDescriptor = virtualStorageDescriptor;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstantiateAppRequest instantiateAppRequest = (InstantiateAppRequest) o;
    return Objects.equals(this.locationConstraints, instantiateAppRequest.locationConstraints) &&
        Objects.equals(this.selectedMECHostInfo, instantiateAppRequest.selectedMECHostInfo) &&
        Objects.equals(this.vimConnectionInfo, instantiateAppRequest.vimConnectionInfo) &&
        Objects.equals(this.virtualComputeDescriptor, instantiateAppRequest.virtualComputeDescriptor) &&
        Objects.equals(this.virtualStorageDescriptor, instantiateAppRequest.virtualStorageDescriptor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locationConstraints, selectedMECHostInfo, vimConnectionInfo, virtualComputeDescriptor, virtualStorageDescriptor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstantiateAppRequest {\n");
    
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
    sb.append("    selectedMECHostInfo: ").append(toIndentedString(selectedMECHostInfo)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    virtualComputeDescriptor: ").append(toIndentedString(virtualComputeDescriptor)).append("\n");
    sb.append("    virtualStorageDescriptor: ").append(toIndentedString(virtualStorageDescriptor)).append("\n");
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
