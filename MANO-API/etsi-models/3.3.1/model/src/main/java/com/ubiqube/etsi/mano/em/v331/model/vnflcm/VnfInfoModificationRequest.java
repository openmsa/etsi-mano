package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfcInfoModifications;
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
   * List of identifiers entries to be deleted from the 'vnfcInfoModifications\" attribute array to be used as \"deleteIdList\" as defined below this table. 
   * @return vnfcInfoModificationsDeleteIds
   **/
  @Schema(description = "List of identifiers entries to be deleted from the 'vnfcInfoModifications\" attribute array to be used as \"deleteIdList\" as defined below this table. ")
  
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
