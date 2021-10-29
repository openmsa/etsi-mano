package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class VnfInstanceLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("indicators")
  private Link indicators = null;

  @JsonProperty("instantiate")
  private Link instantiate = null;

  @JsonProperty("terminate")
  private Link terminate = null;

  @JsonProperty("scale")
  private Link scale = null;

  @JsonProperty("scaleToLevel")
  private Link scaleToLevel = null;

  @JsonProperty("changeFlavour")
  private Link changeFlavour = null;

  @JsonProperty("heal")
  private Link heal = null;

  @JsonProperty("operate")
  private Link operate = null;

  @JsonProperty("changeExtConn")
  private Link changeExtConn = null;

  @JsonProperty("createSnapshot")
  private Link createSnapshot = null;

  @JsonProperty("revertToSnapshot")
  private Link revertToSnapshot = null;

  public VnfInstanceLinks self(Link self) {
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

  public VnfInstanceLinks indicators(Link indicators) {
    this.indicators = indicators;
    return this;
  }

  /**
   * Get indicators
   * @return indicators
   **/
  @Schema(description = "")
  
    @Valid
    public Link getIndicators() {
    return indicators;
  }

  public void setIndicators(Link indicators) {
    this.indicators = indicators;
  }

  public VnfInstanceLinks instantiate(Link instantiate) {
    this.instantiate = instantiate;
    return this;
  }

  /**
   * Get instantiate
   * @return instantiate
   **/
  @Schema(description = "")
  
    @Valid
    public Link getInstantiate() {
    return instantiate;
  }

  public void setInstantiate(Link instantiate) {
    this.instantiate = instantiate;
  }

  public VnfInstanceLinks terminate(Link terminate) {
    this.terminate = terminate;
    return this;
  }

  /**
   * Get terminate
   * @return terminate
   **/
  @Schema(description = "")
  
    @Valid
    public Link getTerminate() {
    return terminate;
  }

  public void setTerminate(Link terminate) {
    this.terminate = terminate;
  }

  public VnfInstanceLinks scale(Link scale) {
    this.scale = scale;
    return this;
  }

  /**
   * Get scale
   * @return scale
   **/
  @Schema(description = "")
  
    @Valid
    public Link getScale() {
    return scale;
  }

  public void setScale(Link scale) {
    this.scale = scale;
  }

  public VnfInstanceLinks scaleToLevel(Link scaleToLevel) {
    this.scaleToLevel = scaleToLevel;
    return this;
  }

  /**
   * Get scaleToLevel
   * @return scaleToLevel
   **/
  @Schema(description = "")
  
    @Valid
    public Link getScaleToLevel() {
    return scaleToLevel;
  }

  public void setScaleToLevel(Link scaleToLevel) {
    this.scaleToLevel = scaleToLevel;
  }

  public VnfInstanceLinks changeFlavour(Link changeFlavour) {
    this.changeFlavour = changeFlavour;
    return this;
  }

  /**
   * Get changeFlavour
   * @return changeFlavour
   **/
  @Schema(description = "")
  
    @Valid
    public Link getChangeFlavour() {
    return changeFlavour;
  }

  public void setChangeFlavour(Link changeFlavour) {
    this.changeFlavour = changeFlavour;
  }

  public VnfInstanceLinks heal(Link heal) {
    this.heal = heal;
    return this;
  }

  /**
   * Get heal
   * @return heal
   **/
  @Schema(description = "")
  
    @Valid
    public Link getHeal() {
    return heal;
  }

  public void setHeal(Link heal) {
    this.heal = heal;
  }

  public VnfInstanceLinks operate(Link operate) {
    this.operate = operate;
    return this;
  }

  /**
   * Get operate
   * @return operate
   **/
  @Schema(description = "")
  
    @Valid
    public Link getOperate() {
    return operate;
  }

  public void setOperate(Link operate) {
    this.operate = operate;
  }

  public VnfInstanceLinks changeExtConn(Link changeExtConn) {
    this.changeExtConn = changeExtConn;
    return this;
  }

  /**
   * Get changeExtConn
   * @return changeExtConn
   **/
  @Schema(description = "")
  
    @Valid
    public Link getChangeExtConn() {
    return changeExtConn;
  }

  public void setChangeExtConn(Link changeExtConn) {
    this.changeExtConn = changeExtConn;
  }

  public VnfInstanceLinks createSnapshot(Link createSnapshot) {
    this.createSnapshot = createSnapshot;
    return this;
  }

  /**
   * Get createSnapshot
   * @return createSnapshot
   **/
  @Schema(description = "")
  
    @Valid
    public Link getCreateSnapshot() {
    return createSnapshot;
  }

  public void setCreateSnapshot(Link createSnapshot) {
    this.createSnapshot = createSnapshot;
  }

  public VnfInstanceLinks revertToSnapshot(Link revertToSnapshot) {
    this.revertToSnapshot = revertToSnapshot;
    return this;
  }

  /**
   * Get revertToSnapshot
   * @return revertToSnapshot
   **/
  @Schema(description = "")
  
    @Valid
    public Link getRevertToSnapshot() {
    return revertToSnapshot;
  }

  public void setRevertToSnapshot(Link revertToSnapshot) {
    this.revertToSnapshot = revertToSnapshot;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstanceLinks vnfInstanceLinks = (VnfInstanceLinks) o;
    return Objects.equals(this.self, vnfInstanceLinks.self) &&
        Objects.equals(this.indicators, vnfInstanceLinks.indicators) &&
        Objects.equals(this.instantiate, vnfInstanceLinks.instantiate) &&
        Objects.equals(this.terminate, vnfInstanceLinks.terminate) &&
        Objects.equals(this.scale, vnfInstanceLinks.scale) &&
        Objects.equals(this.scaleToLevel, vnfInstanceLinks.scaleToLevel) &&
        Objects.equals(this.changeFlavour, vnfInstanceLinks.changeFlavour) &&
        Objects.equals(this.heal, vnfInstanceLinks.heal) &&
        Objects.equals(this.operate, vnfInstanceLinks.operate) &&
        Objects.equals(this.changeExtConn, vnfInstanceLinks.changeExtConn) &&
        Objects.equals(this.createSnapshot, vnfInstanceLinks.createSnapshot) &&
        Objects.equals(this.revertToSnapshot, vnfInstanceLinks.revertToSnapshot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, indicators, instantiate, terminate, scale, scaleToLevel, changeFlavour, heal, operate, changeExtConn, createSnapshot, revertToSnapshot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstanceLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    indicators: ").append(toIndentedString(indicators)).append("\n");
    sb.append("    instantiate: ").append(toIndentedString(instantiate)).append("\n");
    sb.append("    terminate: ").append(toIndentedString(terminate)).append("\n");
    sb.append("    scale: ").append(toIndentedString(scale)).append("\n");
    sb.append("    scaleToLevel: ").append(toIndentedString(scaleToLevel)).append("\n");
    sb.append("    changeFlavour: ").append(toIndentedString(changeFlavour)).append("\n");
    sb.append("    heal: ").append(toIndentedString(heal)).append("\n");
    sb.append("    operate: ").append(toIndentedString(operate)).append("\n");
    sb.append("    changeExtConn: ").append(toIndentedString(changeExtConn)).append("\n");
    sb.append("    createSnapshot: ").append(toIndentedString(createSnapshot)).append("\n");
    sb.append("    revertToSnapshot: ").append(toIndentedString(revertToSnapshot)).append("\n");
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
