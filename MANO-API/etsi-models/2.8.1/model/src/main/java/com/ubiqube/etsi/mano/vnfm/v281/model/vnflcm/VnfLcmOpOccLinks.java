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

public class VnfLcmOpOccLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("vnfInstance")
  private Link vnfInstance = null;

  @JsonProperty("grant")
  private Link grant = null;

  @JsonProperty("cancel")
  private Link cancel = null;

  @JsonProperty("retry")
  private Link retry = null;

  @JsonProperty("rollback")
  private Link rollback = null;

  @JsonProperty("fail")
  private Link fail = null;

  public VnfLcmOpOccLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * URI of this resource. 
   * @return self
  **/
  @ApiModelProperty(required = true, value = "URI of this resource. ")
  @NotNull

  @Valid

  public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public VnfLcmOpOccLinks vnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Link to the VNF instance that the operation applies to. 
   * @return vnfInstance
  **/
  @ApiModelProperty(required = true, value = "Link to the VNF instance that the operation applies to. ")
  @NotNull

  @Valid

  public Link getVnfInstance() {
    return vnfInstance;
  }

  public void setVnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
  }

  public VnfLcmOpOccLinks grant(Link grant) {
    this.grant = grant;
    return this;
  }

  /**
   * Link to the grant for this operation, if one exists. 
   * @return grant
  **/
  @ApiModelProperty(value = "Link to the grant for this operation, if one exists. ")

  @Valid

  public Link getGrant() {
    return grant;
  }

  public void setGrant(Link grant) {
    this.grant = grant;
  }

  public VnfLcmOpOccLinks cancel(Link cancel) {
    this.cancel = cancel;
    return this;
  }

  /**
   * Link to the task resource that represents the \"cancel\" operation for this VNF LCM operation occurrence, if cancelling is currently allowed. 
   * @return cancel
  **/
  @ApiModelProperty(value = "Link to the task resource that represents the \"cancel\" operation for this VNF LCM operation occurrence, if cancelling is currently allowed. ")

  @Valid

  public Link getCancel() {
    return cancel;
  }

  public void setCancel(Link cancel) {
    this.cancel = cancel;
  }

  public VnfLcmOpOccLinks retry(Link retry) {
    this.retry = retry;
    return this;
  }

  /**
   * Link to the task resource that represents the \"retry\" operation for this VNF LCM operation occurrence, if retrying is currently allowed. 
   * @return retry
  **/
  @ApiModelProperty(value = "Link to the task resource that represents the \"retry\" operation for this VNF LCM operation occurrence, if retrying is currently allowed. ")

  @Valid

  public Link getRetry() {
    return retry;
  }

  public void setRetry(Link retry) {
    this.retry = retry;
  }

  public VnfLcmOpOccLinks rollback(Link rollback) {
    this.rollback = rollback;
    return this;
  }

  /**
   * Link to the task resource that represents the \"rollback\" operation for this VNF LCM operation occurrence, if rolling back is currently allowed. 
   * @return rollback
  **/
  @ApiModelProperty(value = "Link to the task resource that represents the \"rollback\" operation for this VNF LCM operation occurrence, if rolling back is currently allowed. ")

  @Valid

  public Link getRollback() {
    return rollback;
  }

  public void setRollback(Link rollback) {
    this.rollback = rollback;
  }

  public VnfLcmOpOccLinks fail(Link fail) {
    this.fail = fail;
    return this;
  }

  /**
   * Link to the task resource that represents the \"fail\" operation for this VNF LCM operation occurrence, if declaring as failed is currently allowed. 
   * @return fail
  **/
  @ApiModelProperty(value = "Link to the task resource that represents the \"fail\" operation for this VNF LCM operation occurrence, if declaring as failed is currently allowed. ")

  @Valid

  public Link getFail() {
    return fail;
  }

  public void setFail(Link fail) {
    this.fail = fail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfLcmOpOccLinks vnfLcmOpOccLinks = (VnfLcmOpOccLinks) o;
    return Objects.equals(this.self, vnfLcmOpOccLinks.self) &&
        Objects.equals(this.vnfInstance, vnfLcmOpOccLinks.vnfInstance) &&
        Objects.equals(this.grant, vnfLcmOpOccLinks.grant) &&
        Objects.equals(this.cancel, vnfLcmOpOccLinks.cancel) &&
        Objects.equals(this.retry, vnfLcmOpOccLinks.retry) &&
        Objects.equals(this.rollback, vnfLcmOpOccLinks.rollback) &&
        Objects.equals(this.fail, vnfLcmOpOccLinks.fail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, vnfInstance, grant, cancel, retry, rollback, fail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfLcmOpOccLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
    sb.append("    grant: ").append(toIndentedString(grant)).append("\n");
    sb.append("    cancel: ").append(toIndentedString(cancel)).append("\n");
    sb.append("    retry: ").append(toIndentedString(retry)).append("\n");
    sb.append("    rollback: ").append(toIndentedString(rollback)).append("\n");
    sb.append("    fail: ").append(toIndentedString(fail)).append("\n");
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

