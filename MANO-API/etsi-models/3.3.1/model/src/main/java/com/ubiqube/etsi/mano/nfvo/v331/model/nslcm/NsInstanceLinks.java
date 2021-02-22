package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource.
 */
@Schema(description = "Links to resources related to this resource.")
@Validated


public class NsInstanceLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("nestedNsInstances")
  @Valid
  private List<Link> nestedNsInstances = null;

  @JsonProperty("vnfSnapshotInfos")
  @Valid
  private List<Link> vnfSnapshotInfos = null;

  @JsonProperty("instantiate")
  private Link instantiate = null;

  @JsonProperty("terminate")
  private Link terminate = null;

  @JsonProperty("update")
  private Link update = null;

  @JsonProperty("scale")
  private Link scale = null;

  @JsonProperty("heal")
  private Link heal = null;

  public NsInstanceLinks self(Link self) {
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

  public NsInstanceLinks nestedNsInstances(List<Link> nestedNsInstances) {
    this.nestedNsInstances = nestedNsInstances;
    return this;
  }

  public NsInstanceLinks addNestedNsInstancesItem(Link nestedNsInstancesItem) {
    if (this.nestedNsInstances == null) {
      this.nestedNsInstances = new ArrayList<>();
    }
    this.nestedNsInstances.add(nestedNsInstancesItem);
    return this;
  }

  /**
   * Links to resources related to this notification. 
   * @return nestedNsInstances
   **/
  @Schema(description = "Links to resources related to this notification. ")
      @Valid
    public List<Link> getNestedNsInstances() {
    return nestedNsInstances;
  }

  public void setNestedNsInstances(List<Link> nestedNsInstances) {
    this.nestedNsInstances = nestedNsInstances;
  }

  public NsInstanceLinks vnfSnapshotInfos(List<Link> vnfSnapshotInfos) {
    this.vnfSnapshotInfos = vnfSnapshotInfos;
    return this;
  }

  public NsInstanceLinks addVnfSnapshotInfosItem(Link vnfSnapshotInfosItem) {
    if (this.vnfSnapshotInfos == null) {
      this.vnfSnapshotInfos = new ArrayList<>();
    }
    this.vnfSnapshotInfos.add(vnfSnapshotInfosItem);
    return this;
  }

  /**
   * Links to the VNF snapshots associated to VNF instances which are part of this NS instance. 
   * @return vnfSnapshotInfos
   **/
  @Schema(description = "Links to the VNF snapshots associated to VNF instances which are part of this NS instance. ")
      @Valid
    public List<Link> getVnfSnapshotInfos() {
    return vnfSnapshotInfos;
  }

  public void setVnfSnapshotInfos(List<Link> vnfSnapshotInfos) {
    this.vnfSnapshotInfos = vnfSnapshotInfos;
  }

  public NsInstanceLinks instantiate(Link instantiate) {
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

  public NsInstanceLinks terminate(Link terminate) {
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

  public NsInstanceLinks update(Link update) {
    this.update = update;
    return this;
  }

  /**
   * Get update
   * @return update
   **/
  @Schema(description = "")
  
    @Valid
    public Link getUpdate() {
    return update;
  }

  public void setUpdate(Link update) {
    this.update = update;
  }

  public NsInstanceLinks scale(Link scale) {
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

  public NsInstanceLinks heal(Link heal) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsInstanceLinks nsInstanceLinks = (NsInstanceLinks) o;
    return Objects.equals(this.self, nsInstanceLinks.self) &&
        Objects.equals(this.nestedNsInstances, nsInstanceLinks.nestedNsInstances) &&
        Objects.equals(this.vnfSnapshotInfos, nsInstanceLinks.vnfSnapshotInfos) &&
        Objects.equals(this.instantiate, nsInstanceLinks.instantiate) &&
        Objects.equals(this.terminate, nsInstanceLinks.terminate) &&
        Objects.equals(this.update, nsInstanceLinks.update) &&
        Objects.equals(this.scale, nsInstanceLinks.scale) &&
        Objects.equals(this.heal, nsInstanceLinks.heal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, nestedNsInstances, vnfSnapshotInfos, instantiate, terminate, update, scale, heal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstanceLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    nestedNsInstances: ").append(toIndentedString(nestedNsInstances)).append("\n");
    sb.append("    vnfSnapshotInfos: ").append(toIndentedString(vnfSnapshotInfos)).append("\n");
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
