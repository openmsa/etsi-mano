/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.Link;
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

  public VnfInstanceLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * URI of this resource.
   * @return self
  **/
  @ApiModelProperty(required = true, value = "URI of this resource.")
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
   * Indicators related to this VNF instance, if applicable.
   * @return indicators
  **/
  @ApiModelProperty(value = "Indicators related to this VNF instance, if applicable.")

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
   * Link to the \"Instantiate VNF task\" resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance in NOT_INSTANTIATED state). 
   * @return instantiate
  **/
  @ApiModelProperty(value = "Link to the \"Instantiate VNF task\" resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance in NOT_INSTANTIATED state). ")

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
   * Link to the \"Terminate VNF task\" resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). 
   * @return terminate
  **/
  @ApiModelProperty(value = "Link to the \"Terminate VNF task\" resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). ")

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
   * Link to the \"Scale VNF task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). 
   * @return scale
  **/
  @ApiModelProperty(value = "Link to the \"Scale VNF task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). ")

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
   * Link to the \"Scale VNF to level task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). 
   * @return scaleToLevel
  **/
  @ApiModelProperty(value = "Link to the \"Scale VNF to level task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). ")

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
   * Link to the \"Change VNF flavour task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). 
   * @return changeFlavour
  **/
  @ApiModelProperty(value = "Link to the \"Change VNF flavour task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). ")

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
   * Link to the \"Heal VNF task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). 
   * @return heal
  **/
  @ApiModelProperty(value = "Link to the \"Heal VNF task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). ")

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
   * Link to the \"Operate VNF task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). 
   * @return operate
  **/
  @ApiModelProperty(value = "Link to the \"Operate VNF task\" resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). ")

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
   * Link to the \"Change external VNF connectivity task\" resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). 
   * @return changeExtConn
  **/
  @ApiModelProperty(value = "Link to the \"Change external VNF connectivity task\" resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state). ")

  @Valid

  public Link getChangeExtConn() {
    return changeExtConn;
  }

  public void setChangeExtConn(Link changeExtConn) {
    this.changeExtConn = changeExtConn;
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
        Objects.equals(this.changeExtConn, vnfInstanceLinks.changeExtConn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, indicators, instantiate, terminate, scale, scaleToLevel, changeFlavour, heal, operate, changeExtConn);
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

