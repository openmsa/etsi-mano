package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.LinkType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource.
 */
@ApiModel(description = "Links to resources related to this resource.")
@Validated
public class AppPkgInfoLinks   {
  @JsonProperty("self")
  private LinkType self = null;

  @JsonProperty("appD")
  private LinkType appD = null;

  @JsonProperty("appPkgContent")
  private LinkType appPkgContent = null;

  public AppPkgInfoLinks self(LinkType self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getSelf() {
    return self;
  }

  public void setSelf(LinkType self) {
    this.self = self;
  }

  public AppPkgInfoLinks appD(LinkType appD) {
    this.appD = appD;
    return this;
  }

  /**
   * Get appD
   * @return appD
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getAppD() {
    return appD;
  }

  public void setAppD(LinkType appD) {
    this.appD = appD;
  }

  public AppPkgInfoLinks appPkgContent(LinkType appPkgContent) {
    this.appPkgContent = appPkgContent;
    return this;
  }

  /**
   * Get appPkgContent
   * @return appPkgContent
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getAppPkgContent() {
    return appPkgContent;
  }

  public void setAppPkgContent(LinkType appPkgContent) {
    this.appPkgContent = appPkgContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppPkgInfoLinks appPkgInfoLinks = (AppPkgInfoLinks) o;
    return Objects.equals(this.self, appPkgInfoLinks.self) &&
        Objects.equals(this.appD, appPkgInfoLinks.appD) &&
        Objects.equals(this.appPkgContent, appPkgInfoLinks.appPkgContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, appD, appPkgContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    appD: ").append(toIndentedString(appD)).append("\n");
    sb.append("    appPkgContent: ").append(toIndentedString(appPkgContent)).append("\n");
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
