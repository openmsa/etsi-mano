package com.ubiqube.etsi.mano.vnfm.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class VnfPkgInfoLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("vnfd")
  private Link vnfd = null;

  @JsonProperty("packageContent")
  private Link packageContent = null;

  public VnfPkgInfoLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public VnfPkgInfoLinks vnfd(Link vnfd) {
    this.vnfd = vnfd;
    return this;
  }

  /**
   * Get vnfd
   * @return vnfd
   **/
  @Schema(description = "")
  
    @Valid
    public Link getVnfd() {
    return vnfd;
  }

  public void setVnfd(Link vnfd) {
    this.vnfd = vnfd;
  }

  public VnfPkgInfoLinks packageContent(Link packageContent) {
    this.packageContent = packageContent;
    return this;
  }

  /**
   * Get packageContent
   * @return packageContent
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getPackageContent() {
    return packageContent;
  }

  public void setPackageContent(Link packageContent) {
    this.packageContent = packageContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfPkgInfoLinks vnfPkgInfoLinks = (VnfPkgInfoLinks) o;
    return Objects.equals(this.self, vnfPkgInfoLinks.self) &&
        Objects.equals(this.vnfd, vnfPkgInfoLinks.vnfd) &&
        Objects.equals(this.packageContent, vnfPkgInfoLinks.packageContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, vnfd, packageContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPkgInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    vnfd: ").append(toIndentedString(vnfd)).append("\n");
    sb.append("    packageContent: ").append(toIndentedString(packageContent)).append("\n");
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
