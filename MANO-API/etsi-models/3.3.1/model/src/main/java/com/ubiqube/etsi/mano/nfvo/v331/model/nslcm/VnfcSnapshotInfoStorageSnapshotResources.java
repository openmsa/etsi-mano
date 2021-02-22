package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfcSnapshotInfoStorageSnapshotResources
 */
@Validated


public class VnfcSnapshotInfoStorageSnapshotResources   {
  @JsonProperty("storageResourceId")
  private String storageResourceId = null;

  @JsonProperty("storageSnapshotResources")
  private ResourceHandle storageSnapshotResources = null;

  public VnfcSnapshotInfoStorageSnapshotResources storageResourceId(String storageResourceId) {
    this.storageResourceId = storageResourceId;
    return this;
  }

  /**
   * Get storageResourceId
   * @return storageResourceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getStorageResourceId() {
    return storageResourceId;
  }

  public void setStorageResourceId(String storageResourceId) {
    this.storageResourceId = storageResourceId;
  }

  public VnfcSnapshotInfoStorageSnapshotResources storageSnapshotResources(ResourceHandle storageSnapshotResources) {
    this.storageSnapshotResources = storageSnapshotResources;
    return this;
  }

  /**
   * Get storageSnapshotResources
   * @return storageSnapshotResources
   **/
  @Schema(description = "")
  
    @Valid
    public ResourceHandle getStorageSnapshotResources() {
    return storageSnapshotResources;
  }

  public void setStorageSnapshotResources(ResourceHandle storageSnapshotResources) {
    this.storageSnapshotResources = storageSnapshotResources;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfcSnapshotInfoStorageSnapshotResources vnfcSnapshotInfoStorageSnapshotResources = (VnfcSnapshotInfoStorageSnapshotResources) o;
    return Objects.equals(this.storageResourceId, vnfcSnapshotInfoStorageSnapshotResources.storageResourceId) &&
        Objects.equals(this.storageSnapshotResources, vnfcSnapshotInfoStorageSnapshotResources.storageSnapshotResources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storageResourceId, storageSnapshotResources);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcSnapshotInfoStorageSnapshotResources {\n");
    
    sb.append("    storageResourceId: ").append(toIndentedString(storageResourceId)).append("\n");
    sb.append("    storageSnapshotResources: ").append(toIndentedString(storageSnapshotResources)).append("\n");
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
