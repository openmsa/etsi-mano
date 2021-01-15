package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.LinkType;
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
public class AppInstanceLcmOpOccLinks   {
  @JsonProperty("self")
  private LinkType self = null;

  @JsonProperty("appInstance")
  private LinkType appInstance = null;

  public AppInstanceLcmOpOccLinks self(LinkType self) {
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

  public AppInstanceLcmOpOccLinks appInstance(LinkType appInstance) {
    this.appInstance = appInstance;
    return this;
  }

  /**
   * Get appInstance
   * @return appInstance
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getAppInstance() {
    return appInstance;
  }

  public void setAppInstance(LinkType appInstance) {
    this.appInstance = appInstance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppInstanceLcmOpOccLinks appInstanceLcmOpOccLinks = (AppInstanceLcmOpOccLinks) o;
    return Objects.equals(this.self, appInstanceLcmOpOccLinks.self) &&
        Objects.equals(this.appInstance, appInstanceLcmOpOccLinks.appInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, appInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstanceLcmOpOccLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    appInstance: ").append(toIndentedString(appInstance)).append("\n");
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
