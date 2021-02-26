package com.ubiqube.etsi.mano.vnfm.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.PkgmNotificationsFilterVersions;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PkgmNotificationsFilterVnfProducts
 */
@Validated


public class PkgmNotificationsFilterVnfProducts   {
  @JsonProperty("vnfProductName")
  private String vnfProductName = null;

  @JsonProperty("versions")
  @Valid
  private List<PkgmNotificationsFilterVersions> versions = null;

  public PkgmNotificationsFilterVnfProducts vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

  /**
   * Get vnfProductName
   * @return vnfProductName
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public PkgmNotificationsFilterVnfProducts versions(List<PkgmNotificationsFilterVersions> versions) {
    this.versions = versions;
    return this;
  }

  public PkgmNotificationsFilterVnfProducts addVersionsItem(PkgmNotificationsFilterVersions versionsItem) {
    if (this.versions == null) {
      this.versions = new ArrayList<>();
    }
    this.versions.add(versionsItem);
    return this;
  }

  /**
   * If present, match VNF packages that contain VNF products with certain versions and a certain product name, from one particular provider. 
   * @return versions
   **/
  @Schema(description = "If present, match VNF packages that contain VNF products with certain versions and a certain product name, from one particular provider. ")
      @Valid
    public List<PkgmNotificationsFilterVersions> getVersions() {
    return versions;
  }

  public void setVersions(List<PkgmNotificationsFilterVersions> versions) {
    this.versions = versions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PkgmNotificationsFilterVnfProducts pkgmNotificationsFilterVnfProducts = (PkgmNotificationsFilterVnfProducts) o;
    return Objects.equals(this.vnfProductName, pkgmNotificationsFilterVnfProducts.vnfProductName) &&
        Objects.equals(this.versions, pkgmNotificationsFilterVnfProducts.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfProductName, versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmNotificationsFilterVnfProducts {\n");
    
    sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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
