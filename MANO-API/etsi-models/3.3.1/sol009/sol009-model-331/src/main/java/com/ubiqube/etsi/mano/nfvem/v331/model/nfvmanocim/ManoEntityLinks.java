package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.Link;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@ApiModel(description = "Links to resources related to this resource. ")
@Validated
public class ManoEntityLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("manoServiceInterfaces")
  private Link manoServiceInterfaces = null;

  @JsonProperty("peerEntities")
  private Link peerEntities = null;

  @JsonProperty("changeState")
  private Link changeState = null;

  @JsonProperty("changeStateOpOccs")
  private Link changeStateOpOccs = null;

  public ManoEntityLinks self(Link self) {
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
    public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public ManoEntityLinks manoServiceInterfaces(Link manoServiceInterfaces) {
    this.manoServiceInterfaces = manoServiceInterfaces;
    return this;
  }

  /**
   * Get manoServiceInterfaces
   * @return manoServiceInterfaces
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Link getManoServiceInterfaces() {
    return manoServiceInterfaces;
  }

  public void setManoServiceInterfaces(Link manoServiceInterfaces) {
    this.manoServiceInterfaces = manoServiceInterfaces;
  }

  public ManoEntityLinks peerEntities(Link peerEntities) {
    this.peerEntities = peerEntities;
    return this;
  }

  /**
   * Get peerEntities
   * @return peerEntities
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Link getPeerEntities() {
    return peerEntities;
  }

  public void setPeerEntities(Link peerEntities) {
    this.peerEntities = peerEntities;
  }

  public ManoEntityLinks changeState(Link changeState) {
    this.changeState = changeState;
    return this;
  }

  /**
   * Get changeState
   * @return changeState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Link getChangeState() {
    return changeState;
  }

  public void setChangeState(Link changeState) {
    this.changeState = changeState;
  }

  public ManoEntityLinks changeStateOpOccs(Link changeStateOpOccs) {
    this.changeStateOpOccs = changeStateOpOccs;
    return this;
  }

  /**
   * Get changeStateOpOccs
   * @return changeStateOpOccs
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Link getChangeStateOpOccs() {
    return changeStateOpOccs;
  }

  public void setChangeStateOpOccs(Link changeStateOpOccs) {
    this.changeStateOpOccs = changeStateOpOccs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoEntityLinks manoEntityLinks = (ManoEntityLinks) o;
    return Objects.equals(this.self, manoEntityLinks.self) &&
        Objects.equals(this.manoServiceInterfaces, manoEntityLinks.manoServiceInterfaces) &&
        Objects.equals(this.peerEntities, manoEntityLinks.peerEntities) &&
        Objects.equals(this.changeState, manoEntityLinks.changeState) &&
        Objects.equals(this.changeStateOpOccs, manoEntityLinks.changeStateOpOccs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, manoServiceInterfaces, peerEntities, changeState, changeStateOpOccs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoEntityLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    manoServiceInterfaces: ").append(toIndentedString(manoServiceInterfaces)).append("\n");
    sb.append("    peerEntities: ").append(toIndentedString(peerEntities)).append("\n");
    sb.append("    changeState: ").append(toIndentedString(changeState)).append("\n");
    sb.append("    changeStateOpOccs: ").append(toIndentedString(changeStateOpOccs)).append("\n");
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
