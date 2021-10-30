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
package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.VnfcInfoModifications;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications for an \&quot;Individual VNF instance\&quot; resource, i.e. modifications to a resource representation based on the \&quot;VnfInstance\&quot; data type. The attributes of \&quot;VnfInstance\&quot; that can be modified according to the provisions in clause 5.5.2.2 are included in the \&quot;VnfInfoModificationRequest\&quot; data type. The \&quot;VnfInfoModificationRequest\&quot; data type shall comply with the provisions defined in table 5.5.2.12-1. 
 */
@Schema(description = "This type represents attribute modifications for an \"Individual VNF instance\" resource, i.e. modifications to a resource representation based on the \"VnfInstance\" data type. The attributes of \"VnfInstance\" that can be modified according to the provisions in clause 5.5.2.2 are included in the \"VnfInfoModificationRequest\" data type. The \"VnfInfoModificationRequest\" data type shall comply with the provisions defined in table 5.5.2.12-1. ")
@Validated


public class VnfInfoModificationRequest   {
  @JsonProperty("vnfInstanceName")
  private String vnfInstanceName = null;

  @JsonProperty("vnfInstanceDescription")
  private String vnfInstanceDescription = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  @JsonProperty("vnfcInfoModifications")
  @Valid
  private List<VnfcInfoModifications> vnfcInfoModifications = null;

  @JsonProperty("vnfcInfoModificationsDeleteIds")
  @Valid
  private List<String> vnfcInfoModificationsDeleteIds = null;

  public VnfInfoModificationRequest vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * New value of the \"vnfInstanceName\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. 
   * @return vnfInstanceName
   **/
  @Schema(description = "New value of the \"vnfInstanceName\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. ")
  
    public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public VnfInfoModificationRequest vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * New value of the \"vnfInstanceDescription\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. 
   * @return vnfInstanceDescription
   **/
  @Schema(description = "New value of the \"vnfInstanceDescription\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. ")
  
    public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public VnfInfoModificationRequest vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfInfoModificationRequest vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Get vnfConfigurableProperties
   * @return vnfConfigurableProperties
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public VnfInfoModificationRequest metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public VnfInfoModificationRequest extensions(KeyValuePairs extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Get extensions
   * @return extensions
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }

  public VnfInfoModificationRequest vnfcInfoModifications(List<VnfcInfoModifications> vnfcInfoModifications) {
    this.vnfcInfoModifications = vnfcInfoModifications;
    return this;
  }

  public VnfInfoModificationRequest addVnfcInfoModificationsItem(VnfcInfoModifications vnfcInfoModificationsItem) {
    if (this.vnfcInfoModifications == null) {
      this.vnfcInfoModifications = new ArrayList<>();
    }
    this.vnfcInfoModifications.add(vnfcInfoModificationsItem);
    return this;
  }

  /**
   * Modifications of certain entries in the \"vnfcInfo\" attribute array in the \"instantiatedVnfInfo\" attribute of \"VnfInstance\".\" to be used as \"newList\" as defined below this table. 
   * @return vnfcInfoModifications
   **/
  @Schema(description = "Modifications of certain entries in the \"vnfcInfo\" attribute array in the \"instantiatedVnfInfo\" attribute of \"VnfInstance\".\" to be used as \"newList\" as defined below this table. ")
      @Valid
    public List<VnfcInfoModifications> getVnfcInfoModifications() {
    return vnfcInfoModifications;
  }

  public void setVnfcInfoModifications(List<VnfcInfoModifications> vnfcInfoModifications) {
    this.vnfcInfoModifications = vnfcInfoModifications;
  }

  public VnfInfoModificationRequest vnfcInfoModificationsDeleteIds(List<String> vnfcInfoModificationsDeleteIds) {
    this.vnfcInfoModificationsDeleteIds = vnfcInfoModificationsDeleteIds;
    return this;
  }

  public VnfInfoModificationRequest addVnfcInfoModificationsDeleteIdsItem(String vnfcInfoModificationsDeleteIdsItem) {
    if (this.vnfcInfoModificationsDeleteIds == null) {
      this.vnfcInfoModificationsDeleteIds = new ArrayList<>();
    }
    this.vnfcInfoModificationsDeleteIds.add(vnfcInfoModificationsDeleteIdsItem);
    return this;
  }

  /**
   * List of identifiers entries to be deleted from the 'vnfcInfoModifications\" attribute array to be used as \"deleteIdList\" as defined below this table. The following provisions shall apply when modifying an attribute that is an array of objects of type \"VnfcInfo\" by supplying an array of objects of type \"VnfcInfoModifications\". Assumptions:   1) \"oldList\" is the \"VnfcInfo\" array to be modified and \"newList\" is the \"VnfcInfoModifications\"       array that contains the changes.   2) \"oldEntry\" is an entry in \"oldList\" and \"newEntry\" is an entry in \"newList\".   3) A \"newEntry\" has a \"corresponding entry\" if there exists an \"oldEntry\" that has the same content       of the \"id\" attribute as the \"newEntry\"; a \"newEntry\" has no corresponding entry if no such       \"oldEntry\" exists.   4) In any array of \"VnfcInfo\" resp. \"VnfcInfoModifications\" structures, the content of \"id\" is unique       (i.e. there are no two entries with the same content of \"id\"). Provisions:   1) For each \"newEntry\" in \"newList\" that has no corresponding entry in \"oldList\", the \"oldList\" array       shall be modified by adding that \"newEntry\".   2) For each \"newEntry\" in \"newList\" that has a corresponding \"oldEntry\" in \"oldList\",       the value of \"oldEntry\" shall be updated with the content of \"newEntry\" as specified       for the data type of \"newEntry (refer to clause 5.5.3.24 for the data type \"VnfcInfoModifications\"). 
   * @return vnfcInfoModificationsDeleteIds
   **/
  @Schema(description = "List of identifiers entries to be deleted from the 'vnfcInfoModifications\" attribute array to be used as \"deleteIdList\" as defined below this table. The following provisions shall apply when modifying an attribute that is an array of objects of type \"VnfcInfo\" by supplying an array of objects of type \"VnfcInfoModifications\". Assumptions:   1) \"oldList\" is the \"VnfcInfo\" array to be modified and \"newList\" is the \"VnfcInfoModifications\"       array that contains the changes.   2) \"oldEntry\" is an entry in \"oldList\" and \"newEntry\" is an entry in \"newList\".   3) A \"newEntry\" has a \"corresponding entry\" if there exists an \"oldEntry\" that has the same content       of the \"id\" attribute as the \"newEntry\"; a \"newEntry\" has no corresponding entry if no such       \"oldEntry\" exists.   4) In any array of \"VnfcInfo\" resp. \"VnfcInfoModifications\" structures, the content of \"id\" is unique       (i.e. there are no two entries with the same content of \"id\"). Provisions:   1) For each \"newEntry\" in \"newList\" that has no corresponding entry in \"oldList\", the \"oldList\" array       shall be modified by adding that \"newEntry\".   2) For each \"newEntry\" in \"newList\" that has a corresponding \"oldEntry\" in \"oldList\",       the value of \"oldEntry\" shall be updated with the content of \"newEntry\" as specified       for the data type of \"newEntry (refer to clause 5.5.3.24 for the data type \"VnfcInfoModifications\"). ")
  
    public List<String> getVnfcInfoModificationsDeleteIds() {
    return vnfcInfoModificationsDeleteIds;
  }

  public void setVnfcInfoModificationsDeleteIds(List<String> vnfcInfoModificationsDeleteIds) {
    this.vnfcInfoModificationsDeleteIds = vnfcInfoModificationsDeleteIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInfoModificationRequest vnfInfoModificationRequest = (VnfInfoModificationRequest) o;
    return Objects.equals(this.vnfInstanceName, vnfInfoModificationRequest.vnfInstanceName) &&
        Objects.equals(this.vnfInstanceDescription, vnfInfoModificationRequest.vnfInstanceDescription) &&
        Objects.equals(this.vnfdId, vnfInfoModificationRequest.vnfdId) &&
        Objects.equals(this.vnfConfigurableProperties, vnfInfoModificationRequest.vnfConfigurableProperties) &&
        Objects.equals(this.metadata, vnfInfoModificationRequest.metadata) &&
        Objects.equals(this.extensions, vnfInfoModificationRequest.extensions) &&
        Objects.equals(this.vnfcInfoModifications, vnfInfoModificationRequest.vnfcInfoModifications) &&
        Objects.equals(this.vnfcInfoModificationsDeleteIds, vnfInfoModificationRequest.vnfcInfoModificationsDeleteIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceName, vnfInstanceDescription, vnfdId, vnfConfigurableProperties, metadata, extensions, vnfcInfoModifications, vnfcInfoModificationsDeleteIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInfoModificationRequest {\n");
    
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    vnfcInfoModifications: ").append(toIndentedString(vnfcInfoModifications)).append("\n");
    sb.append("    vnfcInfoModificationsDeleteIds: ").append(toIndentedString(vnfcInfoModificationsDeleteIds)).append("\n");
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
