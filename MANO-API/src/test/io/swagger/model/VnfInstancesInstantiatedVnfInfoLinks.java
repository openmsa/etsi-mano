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

public class VnfInstancesInstantiatedVnfInfoLinks {

	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf self = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf indicators = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf instantiate = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf terminate = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf scale = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf scaleToLevel = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf changeFlavour = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf heal = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf operate = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinksSelf changeExtConn = null;

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks self(VnfInstancesInstantiatedVnfInfoLinksSelf self) {
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
	public VnfInstancesInstantiatedVnfInfoLinks indicators(VnfInstancesInstantiatedVnfInfoLinksSelf indicators) {
		this.indicators = indicators;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("indicators")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getIndicators() {
		return indicators;
	}

	public void setIndicators(VnfInstancesInstantiatedVnfInfoLinksSelf indicators) {
		this.indicators = indicators;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks instantiate(VnfInstancesInstantiatedVnfInfoLinksSelf instantiate) {
		this.instantiate = instantiate;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("instantiate")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getInstantiate() {
		return instantiate;
	}

	public void setInstantiate(VnfInstancesInstantiatedVnfInfoLinksSelf instantiate) {
		this.instantiate = instantiate;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks terminate(VnfInstancesInstantiatedVnfInfoLinksSelf terminate) {
		this.terminate = terminate;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("terminate")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getTerminate() {
		return terminate;
	}

	public void setTerminate(VnfInstancesInstantiatedVnfInfoLinksSelf terminate) {
		this.terminate = terminate;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks scale(VnfInstancesInstantiatedVnfInfoLinksSelf scale) {
		this.scale = scale;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("scale")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getScale() {
		return scale;
	}

	public void setScale(VnfInstancesInstantiatedVnfInfoLinksSelf scale) {
		this.scale = scale;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks scaleToLevel(VnfInstancesInstantiatedVnfInfoLinksSelf scaleToLevel) {
		this.scaleToLevel = scaleToLevel;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("scaleToLevel")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getScaleToLevel() {
		return scaleToLevel;
	}

	public void setScaleToLevel(VnfInstancesInstantiatedVnfInfoLinksSelf scaleToLevel) {
		this.scaleToLevel = scaleToLevel;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks changeFlavour(VnfInstancesInstantiatedVnfInfoLinksSelf changeFlavour) {
		this.changeFlavour = changeFlavour;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("changeFlavour")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getChangeFlavour() {
		return changeFlavour;
	}

	public void setChangeFlavour(VnfInstancesInstantiatedVnfInfoLinksSelf changeFlavour) {
		this.changeFlavour = changeFlavour;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks heal(VnfInstancesInstantiatedVnfInfoLinksSelf heal) {
		this.heal = heal;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("heal")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getHeal() {
		return heal;
	}

	public void setHeal(VnfInstancesInstantiatedVnfInfoLinksSelf heal) {
		this.heal = heal;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks operate(VnfInstancesInstantiatedVnfInfoLinksSelf operate) {
		this.operate = operate;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("operate")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getOperate() {
		return operate;
	}

	public void setOperate(VnfInstancesInstantiatedVnfInfoLinksSelf operate) {
		this.operate = operate;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoLinks changeExtConn(VnfInstancesInstantiatedVnfInfoLinksSelf changeExtConn) {
		this.changeExtConn = changeExtConn;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("changeExtConn")
	public VnfInstancesInstantiatedVnfInfoLinksSelf getChangeExtConn() {
		return changeExtConn;
	}

	public void setChangeExtConn(VnfInstancesInstantiatedVnfInfoLinksSelf changeExtConn) {
		this.changeExtConn = changeExtConn;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoLinks vnfInstancesInstantiatedVnfInfoLinks = (VnfInstancesInstantiatedVnfInfoLinks) o;
		return Objects.equals(self, vnfInstancesInstantiatedVnfInfoLinks.self) &&
				Objects.equals(indicators, vnfInstancesInstantiatedVnfInfoLinks.indicators) &&
				Objects.equals(instantiate, vnfInstancesInstantiatedVnfInfoLinks.instantiate) &&
				Objects.equals(terminate, vnfInstancesInstantiatedVnfInfoLinks.terminate) &&
				Objects.equals(scale, vnfInstancesInstantiatedVnfInfoLinks.scale) &&
				Objects.equals(scaleToLevel, vnfInstancesInstantiatedVnfInfoLinks.scaleToLevel) &&
				Objects.equals(changeFlavour, vnfInstancesInstantiatedVnfInfoLinks.changeFlavour) &&
				Objects.equals(heal, vnfInstancesInstantiatedVnfInfoLinks.heal) &&
				Objects.equals(operate, vnfInstancesInstantiatedVnfInfoLinks.operate) &&
				Objects.equals(changeExtConn, vnfInstancesInstantiatedVnfInfoLinks.changeExtConn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, indicators, instantiate, terminate, scale, scaleToLevel, changeFlavour, heal, operate, changeExtConn);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoLinks {\n");

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
