package com.ubiqube.etsi.mano.vnfm.v351.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents resource definition information related to a snapshot resource.  It shall comply with the provisions defined in table 9.5.3.11-1. NOTE 1: If present, the value of the \&quot;vduId\&quot; (for a related VDU) in the \&quot;VnfcResourceInfo\&quot;          referred by the \&quot;vnfcInfoId\&quot; of the \&quot;VnfcSnapshotInfo\&quot; shall match the value of the          \&quot;vduId\&quot; in the resource definition that is signalled in the granting request. NOTE 2: For snapshot resource definitions extracted from a VNF snapshot package, only the          \&quot;vnfcSnapshotId\&quot; and \&quot;storageSnapshotId\&quot; (in case of a storage type of resource)          are applicable. If the snapshot resource definition is generated as part of a VNF          snapshot created by the VNFM (that is, not extracted from a VNF snapshot package),          the \&quot;snapshotResource\&quot; is applicable. This is a similar specification as the one          defined with the \&quot;vduId\&quot;, \&quot;resourceTemplateId\&quot; and \&quot;resource\&quot; attributes provided in          the ResourceDefinition, but in this case applicable to resources that are defined from          VNF snapshots instead of VNFD. 
 */
@Schema(description = "This type represents resource definition information related to a snapshot resource.  It shall comply with the provisions defined in table 9.5.3.11-1. NOTE 1: If present, the value of the \"vduId\" (for a related VDU) in the \"VnfcResourceInfo\"          referred by the \"vnfcInfoId\" of the \"VnfcSnapshotInfo\" shall match the value of the          \"vduId\" in the resource definition that is signalled in the granting request. NOTE 2: For snapshot resource definitions extracted from a VNF snapshot package, only the          \"vnfcSnapshotId\" and \"storageSnapshotId\" (in case of a storage type of resource)          are applicable. If the snapshot resource definition is generated as part of a VNF          snapshot created by the VNFM (that is, not extracted from a VNF snapshot package),          the \"snapshotResource\" is applicable. This is a similar specification as the one          defined with the \"vduId\", \"resourceTemplateId\" and \"resource\" attributes provided in          the ResourceDefinition, but in this case applicable to resources that are defined from          VNF snapshots instead of VNFD. ")
@Validated


public class SnapshotResourceDefinition   {
  @JsonProperty("vnfSnapshotId")
  private String vnfSnapshotId = null;

  @JsonProperty("vnfcSnapshotId")
  private String vnfcSnapshotId = null;

  @JsonProperty("storageSnapshotId")
  private String storageSnapshotId = null;

  @JsonProperty("snapshotResource")
  private ResourceHandle snapshotResource = null;

  public SnapshotResourceDefinition vnfSnapshotId(String vnfSnapshotId) {
    this.vnfSnapshotId = vnfSnapshotId;
    return this;
  }

  /**
   * Get vnfSnapshotId
   * @return vnfSnapshotId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfSnapshotId() {
    return vnfSnapshotId;
  }

  public void setVnfSnapshotId(String vnfSnapshotId) {
    this.vnfSnapshotId = vnfSnapshotId;
  }

  public SnapshotResourceDefinition vnfcSnapshotId(String vnfcSnapshotId) {
    this.vnfcSnapshotId = vnfcSnapshotId;
    return this;
  }

  /**
   * Get vnfcSnapshotId
   * @return vnfcSnapshotId
   **/
  @Schema(description = "")
  
    public String getVnfcSnapshotId() {
    return vnfcSnapshotId;
  }

  public void setVnfcSnapshotId(String vnfcSnapshotId) {
    this.vnfcSnapshotId = vnfcSnapshotId;
  }

  public SnapshotResourceDefinition storageSnapshotId(String storageSnapshotId) {
    this.storageSnapshotId = storageSnapshotId;
    return this;
  }

  /**
   * Get storageSnapshotId
   * @return storageSnapshotId
   **/
  @Schema(description = "")
  
    public String getStorageSnapshotId() {
    return storageSnapshotId;
  }

  public void setStorageSnapshotId(String storageSnapshotId) {
    this.storageSnapshotId = storageSnapshotId;
  }

  public SnapshotResourceDefinition snapshotResource(ResourceHandle snapshotResource) {
    this.snapshotResource = snapshotResource;
    return this;
  }

  /**
   * Get snapshotResource
   * @return snapshotResource
   **/
  @Schema(description = "")
  
    @Valid
    public ResourceHandle getSnapshotResource() {
    return snapshotResource;
  }

  public void setSnapshotResource(ResourceHandle snapshotResource) {
    this.snapshotResource = snapshotResource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SnapshotResourceDefinition snapshotResourceDefinition = (SnapshotResourceDefinition) o;
    return Objects.equals(this.vnfSnapshotId, snapshotResourceDefinition.vnfSnapshotId) &&
        Objects.equals(this.vnfcSnapshotId, snapshotResourceDefinition.vnfcSnapshotId) &&
        Objects.equals(this.storageSnapshotId, snapshotResourceDefinition.storageSnapshotId) &&
        Objects.equals(this.snapshotResource, snapshotResourceDefinition.snapshotResource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotId, vnfcSnapshotId, storageSnapshotId, snapshotResource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SnapshotResourceDefinition {\n");
    
    sb.append("    vnfSnapshotId: ").append(toIndentedString(vnfSnapshotId)).append("\n");
    sb.append("    vnfcSnapshotId: ").append(toIndentedString(vnfcSnapshotId)).append("\n");
    sb.append("    storageSnapshotId: ").append(toIndentedString(storageSnapshotId)).append("\n");
    sb.append("    snapshotResource: ").append(toIndentedString(snapshotResource)).append("\n");
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
