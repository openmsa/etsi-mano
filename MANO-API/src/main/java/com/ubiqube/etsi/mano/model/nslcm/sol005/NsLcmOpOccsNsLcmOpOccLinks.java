package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Links to resources related to this resource. 
 **/
@ApiModel(description="Links to resources related to this resource. ")
public class NsLcmOpOccsNsLcmOpOccLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf self = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf nsInstance = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf cancel = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf retry = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf rollback = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf _continue = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf fail = null;
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

  public NsLcmOpOccsNsLcmOpOccLinks self(NsInstancesNsInstanceLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Get nsInstance
   * @return nsInstance
  **/
  @JsonProperty("nsInstance")
  @NotNull
  public NsInstancesNsInstanceLinksSelf getNsInstance() {
    return nsInstance;
  }

  public void setNsInstance(NsInstancesNsInstanceLinksSelf nsInstance) {
    this.nsInstance = nsInstance;
  }

  public NsLcmOpOccsNsLcmOpOccLinks nsInstance(NsInstancesNsInstanceLinksSelf nsInstance) {
    this.nsInstance = nsInstance;
    return this;
  }

 /**
   * Get cancel
   * @return cancel
  **/
  @JsonProperty("cancel")
  public NsInstancesNsInstanceLinksSelf getCancel() {
    return cancel;
  }

  public void setCancel(NsInstancesNsInstanceLinksSelf cancel) {
    this.cancel = cancel;
  }

  public NsLcmOpOccsNsLcmOpOccLinks cancel(NsInstancesNsInstanceLinksSelf cancel) {
    this.cancel = cancel;
    return this;
  }

 /**
   * Get retry
   * @return retry
  **/
  @JsonProperty("retry")
  public NsInstancesNsInstanceLinksSelf getRetry() {
    return retry;
  }

  public void setRetry(NsInstancesNsInstanceLinksSelf retry) {
    this.retry = retry;
  }

  public NsLcmOpOccsNsLcmOpOccLinks retry(NsInstancesNsInstanceLinksSelf retry) {
    this.retry = retry;
    return this;
  }

 /**
   * Get rollback
   * @return rollback
  **/
  @JsonProperty("rollback")
  public NsInstancesNsInstanceLinksSelf getRollback() {
    return rollback;
  }

  public void setRollback(NsInstancesNsInstanceLinksSelf rollback) {
    this.rollback = rollback;
  }

  public NsLcmOpOccsNsLcmOpOccLinks rollback(NsInstancesNsInstanceLinksSelf rollback) {
    this.rollback = rollback;
    return this;
  }

 /**
   * Get _continue
   * @return _continue
  **/
  @JsonProperty("continue")
  public NsInstancesNsInstanceLinksSelf getContinue() {
    return _continue;
  }

  public void setContinue(NsInstancesNsInstanceLinksSelf _continue) {
    this._continue = _continue;
  }

  public NsLcmOpOccsNsLcmOpOccLinks _continue(NsInstancesNsInstanceLinksSelf _continue) {
    this._continue = _continue;
    return this;
  }

 /**
   * Get fail
   * @return fail
  **/
  @JsonProperty("fail")
  public NsInstancesNsInstanceLinksSelf getFail() {
    return fail;
  }

  public void setFail(NsInstancesNsInstanceLinksSelf fail) {
    this.fail = fail;
  }

  public NsLcmOpOccsNsLcmOpOccLinks fail(NsInstancesNsInstanceLinksSelf fail) {
    this.fail = fail;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    nsInstance: ").append(toIndentedString(nsInstance)).append("\n");
    sb.append("    cancel: ").append(toIndentedString(cancel)).append("\n");
    sb.append("    retry: ").append(toIndentedString(retry)).append("\n");
    sb.append("    rollback: ").append(toIndentedString(rollback)).append("\n");
    sb.append("    _continue: ").append(toIndentedString(_continue)).append("\n");
    sb.append("    fail: ").append(toIndentedString(fail)).append("\n");
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

