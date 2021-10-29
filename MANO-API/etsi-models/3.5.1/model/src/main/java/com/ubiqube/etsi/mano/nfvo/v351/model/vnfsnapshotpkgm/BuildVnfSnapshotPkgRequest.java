package com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm;

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
 * This type represents the request parameters for building the content of a VNF snapshot package. The NFVO can obtain the VNF snapshot data through the information provided in the request  parameters. It shall comply with the provisions defined in table 11.5.2.6-1. NOTE: The \&quot;overrideImportForVnfcSnapshotIds\&quot; provides the list of VNFC snapshots for which the VNF snapshot-level snapshot resource import policy is overridden. See also examples in the present clause. 
 */
@Schema(description = "This type represents the request parameters for building the content of a VNF snapshot package. The NFVO can obtain the VNF snapshot data through the information provided in the request  parameters. It shall comply with the provisions defined in table 11.5.2.6-1. NOTE: The \"overrideImportForVnfcSnapshotIds\" provides the list of VNFC snapshots for which the VNF snapshot-level snapshot resource import policy is overridden. See also examples in the present clause. ")
@Validated


public class BuildVnfSnapshotPkgRequest   {
  @JsonProperty("vnfSnapshotInfoId")
  private String vnfSnapshotInfoId = null;

  @JsonProperty("vnfcSnapshotsInfoIds")
  @Valid
  private List<String> vnfcSnapshotsInfoIds = null;

  @JsonProperty("overrideImportForVnfcSnapshotIds")
  @Valid
  private List<String> overrideImportForVnfcSnapshotIds = null;

  @JsonProperty("importSnapshotResource")
  private Boolean importSnapshotResource = null;

  public BuildVnfSnapshotPkgRequest vnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
    return this;
  }

  /**
   * Get vnfSnapshotInfoId
   * @return vnfSnapshotInfoId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfSnapshotInfoId() {
    return vnfSnapshotInfoId;
  }

  public void setVnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
  }

  public BuildVnfSnapshotPkgRequest vnfcSnapshotsInfoIds(List<String> vnfcSnapshotsInfoIds) {
    this.vnfcSnapshotsInfoIds = vnfcSnapshotsInfoIds;
    return this;
  }

  public BuildVnfSnapshotPkgRequest addVnfcSnapshotsInfoIdsItem(String vnfcSnapshotsInfoIdsItem) {
    if (this.vnfcSnapshotsInfoIds == null) {
      this.vnfcSnapshotsInfoIds = new ArrayList<>();
    }
    this.vnfcSnapshotsInfoIds.add(vnfcSnapshotsInfoIdsItem);
    return this;
  }

  /**
   * List of identifiers of information held by the NFVO about VNFC snapshots to be packaged  into the VNF snapshot package. Shall be present when it is requested to build a \"partial”  VNF snapshot package by packaging specific VNFC snapshots of the VNF snapshot. If not present,  a \"full\" VNF snapshot package is requested to be built including all the constituents of  the VNF snapshot. 
   * @return vnfcSnapshotsInfoIds
   **/
  @Schema(description = "List of identifiers of information held by the NFVO about VNFC snapshots to be packaged  into the VNF snapshot package. Shall be present when it is requested to build a \"partial”  VNF snapshot package by packaging specific VNFC snapshots of the VNF snapshot. If not present,  a \"full\" VNF snapshot package is requested to be built including all the constituents of  the VNF snapshot. ")
  
    public List<String> getVnfcSnapshotsInfoIds() {
    return vnfcSnapshotsInfoIds;
  }

  public void setVnfcSnapshotsInfoIds(List<String> vnfcSnapshotsInfoIds) {
    this.vnfcSnapshotsInfoIds = vnfcSnapshotsInfoIds;
  }

  public BuildVnfSnapshotPkgRequest overrideImportForVnfcSnapshotIds(List<String> overrideImportForVnfcSnapshotIds) {
    this.overrideImportForVnfcSnapshotIds = overrideImportForVnfcSnapshotIds;
    return this;
  }

  public BuildVnfSnapshotPkgRequest addOverrideImportForVnfcSnapshotIdsItem(String overrideImportForVnfcSnapshotIdsItem) {
    if (this.overrideImportForVnfcSnapshotIds == null) {
      this.overrideImportForVnfcSnapshotIds = new ArrayList<>();
    }
    this.overrideImportForVnfcSnapshotIds.add(overrideImportForVnfcSnapshotIdsItem);
    return this;
  }

  /**
   * If present, it indicates the list of VNFC snapshots to which the VNF snapshot-level  import snapshot resource policy indicated by the \"importSnapshotResource\" attribute  does not apply and the opposite value shall be considered.  See note. present clause. 
   * @return overrideImportForVnfcSnapshotIds
   **/
  @Schema(description = "If present, it indicates the list of VNFC snapshots to which the VNF snapshot-level  import snapshot resource policy indicated by the \"importSnapshotResource\" attribute  does not apply and the opposite value shall be considered.  See note. present clause. ")
  
    public List<String> getOverrideImportForVnfcSnapshotIds() {
    return overrideImportForVnfcSnapshotIds;
  }

  public void setOverrideImportForVnfcSnapshotIds(List<String> overrideImportForVnfcSnapshotIds) {
    this.overrideImportForVnfcSnapshotIds = overrideImportForVnfcSnapshotIds;
  }

  public BuildVnfSnapshotPkgRequest importSnapshotResource(Boolean importSnapshotResource) {
    this.importSnapshotResource = importSnapshotResource;
    return this;
  }

  /**
   * Get importSnapshotResource
   * @return importSnapshotResource
   **/
  @Schema(description = "")
  
    public Boolean getImportSnapshotResource() {
    return importSnapshotResource;
  }

  public void setImportSnapshotResource(Boolean importSnapshotResource) {
    this.importSnapshotResource = importSnapshotResource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BuildVnfSnapshotPkgRequest buildVnfSnapshotPkgRequest = (BuildVnfSnapshotPkgRequest) o;
    return Objects.equals(this.vnfSnapshotInfoId, buildVnfSnapshotPkgRequest.vnfSnapshotInfoId) &&
        Objects.equals(this.vnfcSnapshotsInfoIds, buildVnfSnapshotPkgRequest.vnfcSnapshotsInfoIds) &&
        Objects.equals(this.overrideImportForVnfcSnapshotIds, buildVnfSnapshotPkgRequest.overrideImportForVnfcSnapshotIds) &&
        Objects.equals(this.importSnapshotResource, buildVnfSnapshotPkgRequest.importSnapshotResource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotInfoId, vnfcSnapshotsInfoIds, overrideImportForVnfcSnapshotIds, importSnapshotResource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BuildVnfSnapshotPkgRequest {\n");
    
    sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
    sb.append("    vnfcSnapshotsInfoIds: ").append(toIndentedString(vnfcSnapshotsInfoIds)).append("\n");
    sb.append("    overrideImportForVnfcSnapshotIds: ").append(toIndentedString(overrideImportForVnfcSnapshotIds)).append("\n");
    sb.append("    importSnapshotResource: ").append(toIndentedString(importSnapshotResource)).append("\n");
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
