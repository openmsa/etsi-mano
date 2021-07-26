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
package com.ubiqube.etsi.mano.vnfm.v331.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnffm.VnfInstanceSubscriptionFilterVnfProductsFromProviders;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents subscription filter criteria to match VNF instances. 
 */
@Schema(description = "This type represents subscription filter criteria to match VNF instances. ")
@Validated


public class VnfInstanceSubscriptionFilter  implements AnyOfVnfInstanceSubscriptionFilter {
  @JsonProperty("vnfdIds")
  @Valid
  private List<String> vnfdIds = null;

  @JsonProperty("vnfProductsFromProviders")
  @Valid
  private List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders = null;

  @JsonProperty("vnfInstanceIds")
  @Valid
  private List<String> vnfInstanceIds = null;

  @JsonProperty("vnfInstanceNames")
  @Valid
  private List<String> vnfInstanceNames = null;

  public VnfInstanceSubscriptionFilter vnfdIds(List<String> vnfdIds) {
    this.vnfdIds = vnfdIds;
    return this;
  }

  public VnfInstanceSubscriptionFilter addVnfdIdsItem(String vnfdIdsItem) {
    if (this.vnfdIds == null) {
      this.vnfdIds = new ArrayList<>();
    }
    this.vnfdIds.add(vnfdIdsItem);
    return this;
  }

  /**
   * If present, match VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
   * @return vnfdIds
   **/
  @Schema(description = "If present, match VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
  
    public List<String> getVnfdIds() {
    return vnfdIds;
  }

  public void setVnfdIds(List<String> vnfdIds) {
    this.vnfdIds = vnfdIds;
  }

  public VnfInstanceSubscriptionFilter vnfProductsFromProviders(List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders) {
    this.vnfProductsFromProviders = vnfProductsFromProviders;
    return this;
  }

  public VnfInstanceSubscriptionFilter addVnfProductsFromProvidersItem(VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProductsFromProvidersItem) {
    if (this.vnfProductsFromProviders == null) {
      this.vnfProductsFromProviders = new ArrayList<>();
    }
    this.vnfProductsFromProviders.add(vnfProductsFromProvidersItem);
    return this;
  }

  /**
   * If present, match VNF instances that belong to VNF products from certain providers. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
   * @return vnfProductsFromProviders
   **/
  @Schema(description = "If present, match VNF instances that belong to VNF products from certain providers. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
      @Valid
    public List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> getVnfProductsFromProviders() {
    return vnfProductsFromProviders;
  }

  public void setVnfProductsFromProviders(List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders) {
    this.vnfProductsFromProviders = vnfProductsFromProviders;
  }

  public VnfInstanceSubscriptionFilter vnfInstanceIds(List<String> vnfInstanceIds) {
    this.vnfInstanceIds = vnfInstanceIds;
    return this;
  }

  public VnfInstanceSubscriptionFilter addVnfInstanceIdsItem(String vnfInstanceIdsItem) {
    if (this.vnfInstanceIds == null) {
      this.vnfInstanceIds = new ArrayList<>();
    }
    this.vnfInstanceIds.add(vnfInstanceIdsItem);
    return this;
  }

  /**
   * If present, match VNF instances with an instance identifier listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
   * @return vnfInstanceIds
   **/
  @Schema(description = "If present, match VNF instances with an instance identifier listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
  
    public List<String> getVnfInstanceIds() {
    return vnfInstanceIds;
  }

  public void setVnfInstanceIds(List<String> vnfInstanceIds) {
    this.vnfInstanceIds = vnfInstanceIds;
  }

  public VnfInstanceSubscriptionFilter vnfInstanceNames(List<String> vnfInstanceNames) {
    this.vnfInstanceNames = vnfInstanceNames;
    return this;
  }

  public VnfInstanceSubscriptionFilter addVnfInstanceNamesItem(String vnfInstanceNamesItem) {
    if (this.vnfInstanceNames == null) {
      this.vnfInstanceNames = new ArrayList<>();
    }
    this.vnfInstanceNames.add(vnfInstanceNamesItem);
    return this;
  }

  /**
   * If present, match VNF instances with a VNF Instance Name listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
   * @return vnfInstanceNames
   **/
  @Schema(description = "If present, match VNF instances with a VNF Instance Name listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
  
    public List<String> getVnfInstanceNames() {
    return vnfInstanceNames;
  }

  public void setVnfInstanceNames(List<String> vnfInstanceNames) {
    this.vnfInstanceNames = vnfInstanceNames;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = (VnfInstanceSubscriptionFilter) o;
    return Objects.equals(this.vnfdIds, vnfInstanceSubscriptionFilter.vnfdIds) &&
        Objects.equals(this.vnfProductsFromProviders, vnfInstanceSubscriptionFilter.vnfProductsFromProviders) &&
        Objects.equals(this.vnfInstanceIds, vnfInstanceSubscriptionFilter.vnfInstanceIds) &&
        Objects.equals(this.vnfInstanceNames, vnfInstanceSubscriptionFilter.vnfInstanceNames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfdIds, vnfProductsFromProviders, vnfInstanceIds, vnfInstanceNames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstanceSubscriptionFilter {\n");
    
    sb.append("    vnfdIds: ").append(toIndentedString(vnfdIds)).append("\n");
    sb.append("    vnfProductsFromProviders: ").append(toIndentedString(vnfProductsFromProviders)).append("\n");
    sb.append("    vnfInstanceIds: ").append(toIndentedString(vnfInstanceIds)).append("\n");
    sb.append("    vnfInstanceNames: ").append(toIndentedString(vnfInstanceNames)).append("\n");
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
