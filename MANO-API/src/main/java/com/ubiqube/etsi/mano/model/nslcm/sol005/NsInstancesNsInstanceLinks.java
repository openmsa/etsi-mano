package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * Links to resources related to this resource.
 **/
@ApiModel(description="Links to resources related to this resource.")
public class NsInstancesNsInstanceLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf self = null;

  @ApiModelProperty(value = "Links to resources related to this notification. ")
  @Valid
 /**
   * Links to resources related to this notification. 
  **/
  private List<NsInstancesNsInstanceLinksSelf> nestedNsInstances = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf instantiate = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf terminate = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf update = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf scale = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf heal = null;
 /**
   * Get self
   * @return self
  **/
  @JsonProperty("self")
  @NotNull
  public NsInstancesNsInstanceLinksSelf getSelf() {
    return self;
  }

  public void setSelf(NsInstancesNsInstanceLinksSelf self) {
    this.self = self;
  }

  public NsInstancesNsInstanceLinks self(NsInstancesNsInstanceLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Links to resources related to this notification. 
   * @return nestedNsInstances
  **/
  @JsonProperty("nestedNsInstances")
  public List<NsInstancesNsInstanceLinksSelf> getNestedNsInstances() {
    return nestedNsInstances;
  }

  public void setNestedNsInstances(List<NsInstancesNsInstanceLinksSelf> nestedNsInstances) {
    this.nestedNsInstances = nestedNsInstances;
  }

  public NsInstancesNsInstanceLinks nestedNsInstances(List<NsInstancesNsInstanceLinksSelf> nestedNsInstances) {
    this.nestedNsInstances = nestedNsInstances;
    return this;
  }

  public NsInstancesNsInstanceLinks addNestedNsInstancesItem(NsInstancesNsInstanceLinksSelf nestedNsInstancesItem) {
    this.nestedNsInstances.add(nestedNsInstancesItem);
    return this;
  }

 /**
   * Get instantiate
   * @return instantiate
  **/
  @JsonProperty("instantiate")
  public NsInstancesNsInstanceLinksSelf getInstantiate() {
    return instantiate;
  }

  public void setInstantiate(NsInstancesNsInstanceLinksSelf instantiate) {
    this.instantiate = instantiate;
  }

  public NsInstancesNsInstanceLinks instantiate(NsInstancesNsInstanceLinksSelf instantiate) {
    this.instantiate = instantiate;
    return this;
  }

 /**
   * Get terminate
   * @return terminate
  **/
  @JsonProperty("terminate")
  public NsInstancesNsInstanceLinksSelf getTerminate() {
    return terminate;
  }

  public void setTerminate(NsInstancesNsInstanceLinksSelf terminate) {
    this.terminate = terminate;
  }

  public NsInstancesNsInstanceLinks terminate(NsInstancesNsInstanceLinksSelf terminate) {
    this.terminate = terminate;
    return this;
  }

 /**
   * Get update
   * @return update
  **/
  @JsonProperty("update")
  public NsInstancesNsInstanceLinksSelf getUpdate() {
    return update;
  }

  public void setUpdate(NsInstancesNsInstanceLinksSelf update) {
    this.update = update;
  }

  public NsInstancesNsInstanceLinks update(NsInstancesNsInstanceLinksSelf update) {
    this.update = update;
    return this;
  }

 /**
   * Get scale
   * @return scale
  **/
  @JsonProperty("scale")
  public NsInstancesNsInstanceLinksSelf getScale() {
    return scale;
  }

  public void setScale(NsInstancesNsInstanceLinksSelf scale) {
    this.scale = scale;
  }

  public NsInstancesNsInstanceLinks scale(NsInstancesNsInstanceLinksSelf scale) {
    this.scale = scale;
    return this;
  }

 /**
   * Get heal
   * @return heal
  **/
  @JsonProperty("heal")
  public NsInstancesNsInstanceLinksSelf getHeal() {
    return heal;
  }

  public void setHeal(NsInstancesNsInstanceLinksSelf heal) {
    this.heal = heal;
  }

  public NsInstancesNsInstanceLinks heal(NsInstancesNsInstanceLinksSelf heal) {
    this.heal = heal;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    nestedNsInstances: ").append(toIndentedString(nestedNsInstances)).append("\n");
    sb.append("    instantiate: ").append(toIndentedString(instantiate)).append("\n");
    sb.append("    terminate: ").append(toIndentedString(terminate)).append("\n");
    sb.append("    update: ").append(toIndentedString(update)).append("\n");
    sb.append("    scale: ").append(toIndentedString(scale)).append("\n");
    sb.append("    heal: ").append(toIndentedString(heal)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

