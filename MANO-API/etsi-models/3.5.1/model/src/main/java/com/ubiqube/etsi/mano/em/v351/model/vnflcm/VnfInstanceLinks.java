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
package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.Link;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Links to resources related to this resource.
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated

public class VnfInstanceLinks {
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

	public VnfInstanceLinks self(final Link self) {
		this.self = self;
		return this;
	}

	/**
	 * Get self
	 *
	 * @return self
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public Link getSelf() {
		return self;
	}

	public void setSelf(final Link self) {
		this.self = self;
	}

	public VnfInstanceLinks indicators(final Link indicators) {
		this.indicators = indicators;
		return this;
	}

	/**
	 * Get indicators
	 *
	 * @return indicators
	 **/
	@Schema(description = "")

	@Valid
	public Link getIndicators() {
		return indicators;
	}

	public void setIndicators(final Link indicators) {
		this.indicators = indicators;
	}

	public VnfInstanceLinks instantiate(final Link instantiate) {
		this.instantiate = instantiate;
		return this;
	}

	/**
	 * Get instantiate
	 *
	 * @return instantiate
	 **/
	@Schema(description = "")

	@Valid
	public Link getInstantiate() {
		return instantiate;
	}

	public void setInstantiate(final Link instantiate) {
		this.instantiate = instantiate;
	}

	public VnfInstanceLinks terminate(final Link terminate) {
		this.terminate = terminate;
		return this;
	}

	/**
	 * Get terminate
	 *
	 * @return terminate
	 **/
	@Schema(description = "")

	@Valid
	public Link getTerminate() {
		return terminate;
	}

	public void setTerminate(final Link terminate) {
		this.terminate = terminate;
	}

	public VnfInstanceLinks scale(final Link scale) {
		this.scale = scale;
		return this;
	}

	/**
	 * Get scale
	 *
	 * @return scale
	 **/
	@Schema(description = "")

	@Valid
	public Link getScale() {
		return scale;
	}

	public void setScale(final Link scale) {
		this.scale = scale;
	}

	public VnfInstanceLinks scaleToLevel(final Link scaleToLevel) {
		this.scaleToLevel = scaleToLevel;
		return this;
	}

	/**
	 * Get scaleToLevel
	 *
	 * @return scaleToLevel
	 **/
	@Schema(description = "")

	@Valid
	public Link getScaleToLevel() {
		return scaleToLevel;
	}

	public void setScaleToLevel(final Link scaleToLevel) {
		this.scaleToLevel = scaleToLevel;
	}

	public VnfInstanceLinks changeFlavour(final Link changeFlavour) {
		this.changeFlavour = changeFlavour;
		return this;
	}

	/**
	 * Get changeFlavour
	 *
	 * @return changeFlavour
	 **/
	@Schema(description = "")

	@Valid
	public Link getChangeFlavour() {
		return changeFlavour;
	}

	public void setChangeFlavour(final Link changeFlavour) {
		this.changeFlavour = changeFlavour;
	}

	public VnfInstanceLinks heal(final Link heal) {
		this.heal = heal;
		return this;
	}

	/**
	 * Get heal
	 *
	 * @return heal
	 **/
	@Schema(description = "")

	@Valid
	public Link getHeal() {
		return heal;
	}

	public void setHeal(final Link heal) {
		this.heal = heal;
	}

	public VnfInstanceLinks operate(final Link operate) {
		this.operate = operate;
		return this;
	}

	/**
	 * Get operate
	 *
	 * @return operate
	 **/
	@Schema(description = "")

	@Valid
	public Link getOperate() {
		return operate;
	}

	public void setOperate(final Link operate) {
		this.operate = operate;
	}

	public VnfInstanceLinks changeExtConn(final Link changeExtConn) {
		this.changeExtConn = changeExtConn;
		return this;
	}

	/**
	 * Get changeExtConn
	 *
	 * @return changeExtConn
	 **/
	@Schema(description = "")

	@Valid
	public Link getChangeExtConn() {
		return changeExtConn;
	}

	public void setChangeExtConn(final Link changeExtConn) {
		this.changeExtConn = changeExtConn;
	}

	public VnfInstanceLinks createSnapshot(final Link createSnapshot) {
		this.createSnapshot = createSnapshot;
		return this;
	}

	/**
	 * Get createSnapshot
	 *
	 * @return createSnapshot
	 **/
	@Schema(description = "")

	@Valid
	public Link getCreateSnapshot() {
		return createSnapshot;
	}

	public void setCreateSnapshot(final Link createSnapshot) {
		this.createSnapshot = createSnapshot;
	}

	public VnfInstanceLinks revertToSnapshot(final Link revertToSnapshot) {
		this.revertToSnapshot = revertToSnapshot;
		return this;
	}

	/**
	 * Get revertToSnapshot
	 *
	 * @return revertToSnapshot
	 **/
	@Schema(description = "")

	@Valid
	public Link getRevertToSnapshot() {
		return revertToSnapshot;
	}

	public void setRevertToSnapshot(final Link revertToSnapshot) {
		this.revertToSnapshot = revertToSnapshot;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfInstanceLinks vnfInstanceLinks = (VnfInstanceLinks) o;
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
		final StringBuilder sb = new StringBuilder();
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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
