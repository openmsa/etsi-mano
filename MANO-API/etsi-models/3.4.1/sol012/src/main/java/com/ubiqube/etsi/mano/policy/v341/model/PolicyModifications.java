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
import com.ubiqube.etsi.mano.policy.v341.model.ActivationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the parameters for modifying a policy. It shall comply with the provisions defined in table 5.6.2.4-1 
 */
@Schema(description = "This type represents the parameters for modifying a policy. It shall comply with the provisions defined in table 5.6.2.4-1 ")
@Validated


public class PolicyModifications   {
  @JsonProperty("activationStatus")
  private ActivationStatus activationStatus = null;

  @JsonProperty("selectedVersion")
  private String selectedVersion = null;

  @JsonProperty("addAssociations")
  @Valid
  private List<String> addAssociations = null;

  @JsonProperty("removeAssociations")
  @Valid
  private List<String> removeAssociations = null;

  @JsonProperty("removeAllAssociations")
  private Boolean removeAllAssociations = null;

  public PolicyModifications activationStatus(ActivationStatus activationStatus) {
    this.activationStatus = activationStatus;
    return this;
  }

  /**
   * Get activationStatus
   * @return activationStatus
   **/
  @Schema(description = "")
  
    @Valid
    public ActivationStatus getActivationStatus() {
    return activationStatus;
  }

  public void setActivationStatus(ActivationStatus activationStatus) {
    this.activationStatus = activationStatus;
  }

  public PolicyModifications selectedVersion(String selectedVersion) {
    this.selectedVersion = selectedVersion;
    return this;
  }

  /**
   * Get selectedVersion
   * @return selectedVersion
   **/
  @Schema(description = "")
  
    public String getSelectedVersion() {
    return selectedVersion;
  }

  public void setSelectedVersion(String selectedVersion) {
    this.selectedVersion = selectedVersion;
  }

  public PolicyModifications addAssociations(List<String> addAssociations) {
    this.addAssociations = addAssociations;
    return this;
  }

  public PolicyModifications addAddAssociationsItem(String addAssociationsItem) {
    if (this.addAssociations == null) {
      this.addAssociations = new ArrayList<>();
    }
    this.addAssociations.add(addAssociationsItem);
    return this;
  }

  /**
   * Identifiers of entities to be added to the “associations” attribute in the “Policy” data structure which represents the policy. The API producer shall ignore the identifiers that already exist in the \"associations\" attribute in the “Policy” data structure. Shall be absent when the association feature is not applicable for the PF. 
   * @return addAssociations
   **/
  @Schema(description = "Identifiers of entities to be added to the “associations” attribute in the “Policy” data structure which represents the policy. The API producer shall ignore the identifiers that already exist in the \"associations\" attribute in the “Policy” data structure. Shall be absent when the association feature is not applicable for the PF. ")
  
    public List<String> getAddAssociations() {
    return addAssociations;
  }

  public void setAddAssociations(List<String> addAssociations) {
    this.addAssociations = addAssociations;
  }

  public PolicyModifications removeAssociations(List<String> removeAssociations) {
    this.removeAssociations = removeAssociations;
    return this;
  }

  public PolicyModifications addRemoveAssociationsItem(String removeAssociationsItem) {
    if (this.removeAssociations == null) {
      this.removeAssociations = new ArrayList<>();
    }
    this.removeAssociations.add(removeAssociationsItem);
    return this;
  }

  /**
   * Identifiers of entities to be removed from the “associations” attribute in the “Policy” data structure which represents the policy. The API producer shall ignore the identifiers that do not exist in the \"associations\" attribute in the “Policy” data structure. Shall be absent when the association feature is not applicable for the PF. 
   * @return removeAssociations
   **/
  @Schema(description = "Identifiers of entities to be removed from the “associations” attribute in the “Policy” data structure which represents the policy. The API producer shall ignore the identifiers that do not exist in the \"associations\" attribute in the “Policy” data structure. Shall be absent when the association feature is not applicable for the PF. ")
  
    public List<String> getRemoveAssociations() {
    return removeAssociations;
  }

  public void setRemoveAssociations(List<String> removeAssociations) {
    this.removeAssociations = removeAssociations;
  }

  public PolicyModifications removeAllAssociations(Boolean removeAllAssociations) {
    this.removeAllAssociations = removeAllAssociations;
    return this;
  }

  /**
   * Shall be set to TRUE if the policy is no longer associated to any specific entities managed by the PF. Shall be absent when the association feature is not applicable for the PF. If \"removeAllAssociations\" is set to TRUE, neither \"addAssociations\" nor “removeAssociations” attributes shall be present. Once all associations have been removed, how the PF determines the scope of applicability of the policy is outside the scope of the present document. 
   * @return removeAllAssociations
   **/
  @Schema(description = "Shall be set to TRUE if the policy is no longer associated to any specific entities managed by the PF. Shall be absent when the association feature is not applicable for the PF. If \"removeAllAssociations\" is set to TRUE, neither \"addAssociations\" nor “removeAssociations” attributes shall be present. Once all associations have been removed, how the PF determines the scope of applicability of the policy is outside the scope of the present document. ")
  
    public Boolean isRemoveAllAssociations() {
    return removeAllAssociations;
  }

  public void setRemoveAllAssociations(Boolean removeAllAssociations) {
    this.removeAllAssociations = removeAllAssociations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolicyModifications policyModifications = (PolicyModifications) o;
    return Objects.equals(this.activationStatus, policyModifications.activationStatus) &&
        Objects.equals(this.selectedVersion, policyModifications.selectedVersion) &&
        Objects.equals(this.addAssociations, policyModifications.addAssociations) &&
        Objects.equals(this.removeAssociations, policyModifications.removeAssociations) &&
        Objects.equals(this.removeAllAssociations, policyModifications.removeAllAssociations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activationStatus, selectedVersion, addAssociations, removeAssociations, removeAllAssociations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolicyModifications {\n");
    
    sb.append("    activationStatus: ").append(toIndentedString(activationStatus)).append("\n");
    sb.append("    selectedVersion: ").append(toIndentedString(selectedVersion)).append("\n");
    sb.append("    addAssociations: ").append(toIndentedString(addAssociations)).append("\n");
    sb.append("    removeAssociations: ").append(toIndentedString(removeAssociations)).append("\n");
    sb.append("    removeAllAssociations: ").append(toIndentedString(removeAllAssociations)).append("\n");
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
