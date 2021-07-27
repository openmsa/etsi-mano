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
package com.ubiqube.etsi.mano.policy.v341.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for creating a policy. It shall comply with the provisions defined in table 5.6.2.2-1. 
 */
@Schema(description = "This type represents request parameters for creating a policy. It shall comply with the provisions defined in table 5.6.2.2-1. ")
@Validated


public class CreatePolicyRequest   {
  @JsonProperty("designer")
  private String designer = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("pfId")
  private String pfId = null;

  @JsonProperty("associations")
  @Valid
  private List<String> associations = null;

  public CreatePolicyRequest designer(String designer) {
    this.designer = designer;
    return this;
  }

  /**
   * Human readable name of the designer of the policy. 
   * @return designer
   **/
  @Schema(required = true, description = "Human readable name of the designer of the policy. ")
      @NotNull

    public String getDesigner() {
    return designer;
  }

  public void setDesigner(String designer) {
    this.designer = designer;
  }

  public CreatePolicyRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human readable name of the policy. 
   * @return name
   **/
  @Schema(required = true, description = "Human readable name of the policy. ")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreatePolicyRequest pfId(String pfId) {
    this.pfId = pfId;
    return this;
  }

  /**
   * Get pfId
   * @return pfId
   **/
  @Schema(description = "")
  
    public String getPfId() {
    return pfId;
  }

  public void setPfId(String pfId) {
    this.pfId = pfId;
  }

  public CreatePolicyRequest associations(List<String> associations) {
    this.associations = associations;
    return this;
  }

  public CreatePolicyRequest addAssociationsItem(String associationsItem) {
    if (this.associations == null) {
      this.associations = new ArrayList<>();
    }
    this.associations.add(associationsItem);
    return this;
  }

  /**
   * Initial value of the “associations” attribute in the “Policy” data structure which represents the policy. Shall be absent when the association feature is not applicable for the PF. The associations refer to identifiers of entities that the PF manages. E.g., if the PF is a VNFM, the policy can associate to VNF instances; if the PF is NFVO, the policy can associate to an NS instances. How the PF determines the scope of applicability of the policy when this attribute is absent is outside the scope of the present document. 
   * @return associations
   **/
  @Schema(description = "Initial value of the “associations” attribute in the “Policy” data structure which represents the policy. Shall be absent when the association feature is not applicable for the PF. The associations refer to identifiers of entities that the PF manages. E.g., if the PF is a VNFM, the policy can associate to VNF instances; if the PF is NFVO, the policy can associate to an NS instances. How the PF determines the scope of applicability of the policy when this attribute is absent is outside the scope of the present document. ")
  
    public List<String> getAssociations() {
    return associations;
  }

  public void setAssociations(List<String> associations) {
    this.associations = associations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatePolicyRequest createPolicyRequest = (CreatePolicyRequest) o;
    return Objects.equals(this.designer, createPolicyRequest.designer) &&
        Objects.equals(this.name, createPolicyRequest.name) &&
        Objects.equals(this.pfId, createPolicyRequest.pfId) &&
        Objects.equals(this.associations, createPolicyRequest.associations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(designer, name, pfId, associations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePolicyRequest {\n");
    
    sb.append("    designer: ").append(toIndentedString(designer)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    pfId: ").append(toIndentedString(pfId)).append("\n");
    sb.append("    associations: ").append(toIndentedString(associations)).append("\n");
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
