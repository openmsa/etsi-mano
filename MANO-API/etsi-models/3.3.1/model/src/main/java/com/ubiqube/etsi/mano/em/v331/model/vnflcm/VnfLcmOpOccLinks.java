package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
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

  @JsonProperty("vnfSnapshot")
  private Link vnfSnapshot = null;

  public VnfLcmOpOccLinks self(Link self) {
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

  public VnfLcmOpOccLinks vnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Get vnfInstance
   * @return vnfInstance
   **/
  @Schema(required = true, description = "")
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
   * Get grant
   * @return grant
   **/
  @Schema(description = "")
  
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
   * Get cancel
   * @return cancel
   **/
  @Schema(description = "")
  
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
   * Get retry
   * @return retry
   **/
  @Schema(description = "")
  
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
   * Get rollback
   * @return rollback
   **/
  @Schema(description = "")
  
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
   * Get fail
   * @return fail
   **/
  @Schema(description = "")
  
    @Valid
    public Link getFail() {
    return fail;
  }

  public void setFail(Link fail) {
    this.fail = fail;
  }

  public VnfLcmOpOccLinks vnfSnapshot(Link vnfSnapshot) {
    this.vnfSnapshot = vnfSnapshot;
    return this;
  }

  /**
   * Get vnfSnapshot
   * @return vnfSnapshot
   **/
  @Schema(description = "")
  
    @Valid
    public Link getVnfSnapshot() {
    return vnfSnapshot;
  }

  public void setVnfSnapshot(Link vnfSnapshot) {
    this.vnfSnapshot = vnfSnapshot;
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
        Objects.equals(this.fail, vnfLcmOpOccLinks.fail) &&
        Objects.equals(this.vnfSnapshot, vnfLcmOpOccLinks.vnfSnapshot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, vnfInstance, grant, cancel, retry, rollback, fail, vnfSnapshot);
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
    sb.append("    vnfSnapshot: ").append(toIndentedString(vnfSnapshot)).append("\n");
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
