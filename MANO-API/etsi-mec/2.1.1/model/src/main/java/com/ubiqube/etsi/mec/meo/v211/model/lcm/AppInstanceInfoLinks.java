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
public class AppInstanceInfoLinks   {
  @JsonProperty("self")
  private LinkType self = null;

  @JsonProperty("instantiate")
  private LinkType instantiate = null;

  @JsonProperty("terminate")
  private LinkType terminate = null;

  @JsonProperty("operate")
  private LinkType operate = null;

  public AppInstanceInfoLinks self(LinkType self) {
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

  public AppInstanceInfoLinks instantiate(LinkType instantiate) {
    this.instantiate = instantiate;
    return this;
  }

  /**
   * Get instantiate
   * @return instantiate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public LinkType getInstantiate() {
    return instantiate;
  }

  public void setInstantiate(LinkType instantiate) {
    this.instantiate = instantiate;
  }

  public AppInstanceInfoLinks terminate(LinkType terminate) {
    this.terminate = terminate;
    return this;
  }

  /**
   * Get terminate
   * @return terminate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public LinkType getTerminate() {
    return terminate;
  }

  public void setTerminate(LinkType terminate) {
    this.terminate = terminate;
  }

  public AppInstanceInfoLinks operate(LinkType operate) {
    this.operate = operate;
    return this;
  }

  /**
   * Get operate
   * @return operate
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public LinkType getOperate() {
    return operate;
  }

  public void setOperate(LinkType operate) {
    this.operate = operate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppInstanceInfoLinks appInstanceInfoLinks = (AppInstanceInfoLinks) o;
    return Objects.equals(this.self, appInstanceInfoLinks.self) &&
        Objects.equals(this.instantiate, appInstanceInfoLinks.instantiate) &&
        Objects.equals(this.terminate, appInstanceInfoLinks.terminate) &&
        Objects.equals(this.operate, appInstanceInfoLinks.operate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, instantiate, terminate, operate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstanceInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    instantiate: ").append(toIndentedString(instantiate)).append("\n");
    sb.append("    terminate: ").append(toIndentedString(terminate)).append("\n");
    sb.append("    operate: ").append(toIndentedString(operate)).append("\n");
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
