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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type describes the additional affinity or anti-affinity rule applicable between the VNF instances to be instantiated in the NS instantiation operation request or between the VNF instances to be instantiated in the NS instantiation operation request and the existing VNF instances.. 
 */
@ApiModel(description = "This type describes the additional affinity or anti-affinity rule applicable between the VNF instances to be instantiated in the NS instantiation operation request or between the VNF instances to be instantiated in the NS instantiation operation request and the existing VNF instances.. ")
@Validated
public class AffinityOrAntiAffinityRule   {
  @JsonProperty("vnfdId")
  @Valid
  private List<String> vnfdId = null;

  @JsonProperty("vnfProfileId")
  @Valid
  private List<String> vnfProfileId = null;

  @JsonProperty("vnfInstanceId")
  @Valid
  private List<String> vnfInstanceId = null;

  /**
   * The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY. 
   */
  public enum AffinityOrAntiAffiintyEnum {
    AFFINITY("AFFINITY"),
    
    ANTI_AFFINITY("ANTI_AFFINITY");

    private String value;

    AffinityOrAntiAffiintyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AffinityOrAntiAffiintyEnum fromValue(String text) {
      for (AffinityOrAntiAffiintyEnum b : AffinityOrAntiAffiintyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("affinityOrAntiAffiinty")
  private AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty = null;

  /**
   * Specifies the scope of the rule where the placement constraint applies. Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE. 
   */
  public enum ScopeEnum {
    NFVI_POP("NFVI_POP"),
    
    ZONE("ZONE"),
    
    ZONE_GROUP("ZONE_GROUP"),
    
    NFVI_NODE("NFVI_NODE");

    private String value;

    ScopeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ScopeEnum fromValue(String text) {
      for (ScopeEnum b : ScopeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("scope")
  private ScopeEnum scope = null;

  public AffinityOrAntiAffinityRule vnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  public AffinityOrAntiAffinityRule addVnfdIdItem(String vnfdIdItem) {
    if (this.vnfdId == null) {
      this.vnfdId = new ArrayList<>();
    }
    this.vnfdId.add(vnfdIdItem);
    return this;
  }

  /**
   * Reference to a VNFD. When the VNFD which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VNFD presents is not necessary as a part of the NS to be instantiated. 
   * @return vnfdId
  **/
  @ApiModelProperty(value = "Reference to a VNFD. When the VNFD which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VNFD presents is not necessary as a part of the NS to be instantiated. ")
  
    public List<String> getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
  }

  public AffinityOrAntiAffinityRule vnfProfileId(List<String> vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
    return this;
  }

  public AffinityOrAntiAffinityRule addVnfProfileIdItem(String vnfProfileIdItem) {
    if (this.vnfProfileId == null) {
      this.vnfProfileId = new ArrayList<>();
    }
    this.vnfProfileId.add(vnfProfileIdItem);
    return this;
  }

  /**
   * Reference to a vnfProfile defined in the NSD. At least one VnfProfile which is used to instantiate VNF for the NS to be instantiated as the subject of the affinity or anti-affinity rule shall be present. When the VnfProfile which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VnfProfile presents is not necessary as a part of the NS to be instantiated. 
   * @return vnfProfileId
  **/
  @ApiModelProperty(value = "Reference to a vnfProfile defined in the NSD. At least one VnfProfile which is used to instantiate VNF for the NS to be instantiated as the subject of the affinity or anti-affinity rule shall be present. When the VnfProfile which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VnfProfile presents is not necessary as a part of the NS to be instantiated. ")
  
    public List<String> getVnfProfileId() {
    return vnfProfileId;
  }

  public void setVnfProfileId(List<String> vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
  }

  public AffinityOrAntiAffinityRule vnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  public AffinityOrAntiAffinityRule addVnfInstanceIdItem(String vnfInstanceIdItem) {
    if (this.vnfInstanceId == null) {
      this.vnfInstanceId = new ArrayList<>();
    }
    this.vnfInstanceId.add(vnfInstanceIdItem);
    return this;
  }

  /**
   * Reference to the existing VNF instance as the subject of the affinity or anti-affinity rule. The existing VNF instance is not necessary as a part of the NS to be instantiated. 
   * @return vnfInstanceId
  **/
  @ApiModelProperty(value = "Reference to the existing VNF instance as the subject of the affinity or anti-affinity rule. The existing VNF instance is not necessary as a part of the NS to be instantiated. ")
  
    public List<String> getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public AffinityOrAntiAffinityRule affinityOrAntiAffiinty(AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty) {
    this.affinityOrAntiAffiinty = affinityOrAntiAffiinty;
    return this;
  }

  /**
   * The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY. 
   * @return affinityOrAntiAffiinty
  **/
  @ApiModelProperty(required = true, value = "The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY. ")
      @NotNull

    public AffinityOrAntiAffiintyEnum getAffinityOrAntiAffiinty() {
    return affinityOrAntiAffiinty;
  }

  public void setAffinityOrAntiAffiinty(AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty) {
    this.affinityOrAntiAffiinty = affinityOrAntiAffiinty;
  }

  public AffinityOrAntiAffinityRule scope(ScopeEnum scope) {
    this.scope = scope;
    return this;
  }

  /**
   * Specifies the scope of the rule where the placement constraint applies. Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE. 
   * @return scope
  **/
  @ApiModelProperty(required = true, value = "Specifies the scope of the rule where the placement constraint applies. Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE. ")
      @NotNull

    public ScopeEnum getScope() {
    return scope;
  }

  public void setScope(ScopeEnum scope) {
    this.scope = scope;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffinityOrAntiAffinityRule affinityOrAntiAffinityRule = (AffinityOrAntiAffinityRule) o;
    return Objects.equals(this.vnfdId, affinityOrAntiAffinityRule.vnfdId) &&
        Objects.equals(this.vnfProfileId, affinityOrAntiAffinityRule.vnfProfileId) &&
        Objects.equals(this.vnfInstanceId, affinityOrAntiAffinityRule.vnfInstanceId) &&
        Objects.equals(this.affinityOrAntiAffiinty, affinityOrAntiAffinityRule.affinityOrAntiAffiinty) &&
        Objects.equals(this.scope, affinityOrAntiAffinityRule.scope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfdId, vnfProfileId, vnfInstanceId, affinityOrAntiAffiinty, scope);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffinityOrAntiAffinityRule {\n");
    
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    affinityOrAntiAffiinty: ").append(toIndentedString(affinityOrAntiAffiinty)).append("\n");
    sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
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
