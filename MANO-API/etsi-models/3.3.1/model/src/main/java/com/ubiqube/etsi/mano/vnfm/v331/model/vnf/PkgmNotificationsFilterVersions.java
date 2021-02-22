package com.ubiqube.etsi.mano.vnfm.v331.model.vnf;

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
 * PkgmNotificationsFilterVersions
 */
@Validated


public class PkgmNotificationsFilterVersions   {
  @JsonProperty("vnfSoftwareVersion")
  private String vnfSoftwareVersion = null;

  @JsonProperty("vnfdVersions")
  @Valid
  private List<String> vnfdVersions = null;

  public PkgmNotificationsFilterVersions vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

  /**
   * Get vnfSoftwareVersion
   * @return vnfSoftwareVersion
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public PkgmNotificationsFilterVersions vnfdVersions(List<String> vnfdVersions) {
    this.vnfdVersions = vnfdVersions;
    return this;
  }

  public PkgmNotificationsFilterVersions addVnfdVersionsItem(String vnfdVersionsItem) {
    if (this.vnfdVersions == null) {
      this.vnfdVersions = new ArrayList<>();
    }
    this.vnfdVersions.add(vnfdVersionsItem);
    return this;
  }

  /**
   * If present, match VNF packages that contain VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. 
   * @return vnfdVersions
   **/
  @Schema(description = "If present, match VNF packages that contain VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. ")
  
    public List<String> getVnfdVersions() {
    return vnfdVersions;
  }

  public void setVnfdVersions(List<String> vnfdVersions) {
    this.vnfdVersions = vnfdVersions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PkgmNotificationsFilterVersions pkgmNotificationsFilterVersions = (PkgmNotificationsFilterVersions) o;
    return Objects.equals(this.vnfSoftwareVersion, pkgmNotificationsFilterVersions.vnfSoftwareVersion) &&
        Objects.equals(this.vnfdVersions, pkgmNotificationsFilterVersions.vnfdVersions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSoftwareVersion, vnfdVersions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmNotificationsFilterVersions {\n");
    
    sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
    sb.append("    vnfdVersions: ").append(toIndentedString(vnfdVersions)).append("\n");
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
