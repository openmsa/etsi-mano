package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Links to resources related to this resource.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Links to resources related to this resource. ")

public class InlineResponse2001Links {

	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf self = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf vnfInstance = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf grant = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf cancel = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf retry = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf rollback = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf fail = null;

	/**
	 **/
	public InlineResponse2001Links self(VnfInstancesInstantiatedVnfInfoLinksSelf self) {
		this.self = self;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("self")
	@NotNull
	public VnfInstancesInstantiatedVnfInfoLinksSelf getSelf() {
		return self;
	}

	public void setSelf(VnfInstancesInstantiatedVnfInfoLinksSelf self) {
		this.self = self;
	}

	/**
	 **/
	public InlineResponse2001Links vnfInstance(VnfInstancesInstantiatedVnfInfoLinksSelf vnfInstance) {
		this.vnfInstance = vnfInstance;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("vnfInstance")
	@NotNull
	public VnfInstancesInstantiatedVnfInfoLinksSelf getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(VnfInstancesInstantiatedVnfInfoLinksSelf vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	/**
	 **/
	public InlineResponse2001Links grant(VnfInstancesInstantiatedVnfInfoLinksSelf grant) {
		this.grant = grant;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("grant")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getGrant() {
		return grant;
	}

	public void setGrant(VnfInstancesInstantiatedVnfInfoLinksSelf grant) {
		this.grant = grant;
	}

	/**
	 **/
	public InlineResponse2001Links cancel(VnfInstancesInstantiatedVnfInfoLinksSelf cancel) {
		this.cancel = cancel;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("cancel")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getCancel() {
		return cancel;
	}

	public void setCancel(VnfInstancesInstantiatedVnfInfoLinksSelf cancel) {
		this.cancel = cancel;
	}

	/**
	 **/
	public InlineResponse2001Links retry(VnfInstancesInstantiatedVnfInfoLinksSelf retry) {
		this.retry = retry;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("retry")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getRetry() {
		return retry;
	}

	public void setRetry(VnfInstancesInstantiatedVnfInfoLinksSelf retry) {
		this.retry = retry;
	}

	/**
	 **/
	public InlineResponse2001Links rollback(VnfInstancesInstantiatedVnfInfoLinksSelf rollback) {
		this.rollback = rollback;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("rollback")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getRollback() {
		return rollback;
	}

	public void setRollback(VnfInstancesInstantiatedVnfInfoLinksSelf rollback) {
		this.rollback = rollback;
	}

	/**
	 **/
	public InlineResponse2001Links fail(VnfInstancesInstantiatedVnfInfoLinksSelf fail) {
		this.fail = fail;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("fail")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getFail() {
		return fail;
	}

	public void setFail(VnfInstancesInstantiatedVnfInfoLinksSelf fail) {
		this.fail = fail;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2001Links inlineResponse2001Links = (InlineResponse2001Links) o;
		return Objects.equals(self, inlineResponse2001Links.self) &&
				Objects.equals(vnfInstance, inlineResponse2001Links.vnfInstance) &&
				Objects.equals(grant, inlineResponse2001Links.grant) &&
				Objects.equals(cancel, inlineResponse2001Links.cancel) &&
				Objects.equals(retry, inlineResponse2001Links.retry) &&
				Objects.equals(rollback, inlineResponse2001Links.rollback) &&
				Objects.equals(fail, inlineResponse2001Links.fail);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, vnfInstance, grant, cancel, retry, rollback, fail);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2001Links {\n");

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
