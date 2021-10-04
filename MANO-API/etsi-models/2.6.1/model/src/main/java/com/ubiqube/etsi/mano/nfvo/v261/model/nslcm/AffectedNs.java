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

package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type provides information about added, deleted and modified nested NSs.
 * It shall comply with the provisions in Table 6.5.3.6-1.
 */
@Schema(description = "This type provides information about added, deleted and modified nested NSs. It shall comply with the provisions in Table 6.5.3.6-1. ")
@Validated


public class AffectedNs {
	@JsonProperty("nsInstanceId")
	private String nsInstanceId = null;

	@JsonProperty("nsdId")
	private String nsdId = null;

	/**
	 * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE -
	 * INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE
	 */
	public enum ChangeTypeEnum {
		ADD("ADD"),

		REMOVE("REMOVE"),

		INSTANTIATE("INSTANTIATE"),

		SCALE("SCALE"),

		UPDATE("UPDATE"),

		HEAL("HEAL"),

		TERMINATE("TERMINATE");

		private final String value;

		ChangeTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ChangeTypeEnum fromValue(final String text) {
			for (final ChangeTypeEnum b : ChangeTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("changeType")
	private ChangeTypeEnum changeType = null;

	/**
	 * Signals the result of change identified by the \"changeType\" attribute.
	 * Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED
	 */
	public enum ChangeResultEnum {
		COMPLETED("COMPLETED"),

		ROLLED_BACK("ROLLED_BACK"),

		FAILED("FAILED"),

		PARTIALLY_COMPLETED("PARTIALLY_COMPLETED");

		private final String value;

		ChangeResultEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ChangeResultEnum fromValue(final String text) {
			for (final ChangeResultEnum b : ChangeResultEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("changeResult")
	private ChangeResultEnum changeResult = null;

	public AffectedNs nsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
		return this;
	}

	/**
	 * Identifier of the nested NS instance.
	 * 
	 * @return nsInstanceId
	 **/
	@Schema(required = true, description = "Identifier of the nested NS instance. ")
	@NotNull

	public String getNsInstanceId() {
		return nsInstanceId;
	}

	public void setNsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
	}

	public AffectedNs nsdId(final String nsdId) {
		this.nsdId = nsdId;
		return this;
	}

	/**
	 * Identifier of the NSD of the nested NS instance.
	 * 
	 * @return nsdId
	 **/
	@Schema(required = true, description = "Identifier of the NSD of the nested NS instance. ")
	@NotNull

	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public AffectedNs changeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
		return this;
	}

	/**
	 * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE -
	 * INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE
	 * 
	 * @return changeType
	 **/
	@Schema(required = true, description = "Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE ")
	@NotNull

	public ChangeTypeEnum getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
	}

	public AffectedNs changeResult(final ChangeResultEnum changeResult) {
		this.changeResult = changeResult;
		return this;
	}

	/**
	 * Signals the result of change identified by the \"changeType\" attribute.
	 * Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED
	 * 
	 * @return changeResult
	 **/
	@Schema(required = true, description = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED ")
	@NotNull

	public ChangeResultEnum getChangeResult() {
		return changeResult;
	}

	public void setChangeResult(final ChangeResultEnum changeResult) {
		this.changeResult = changeResult;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AffectedNs affectedNs = (AffectedNs) o;
		return Objects.equals(this.nsInstanceId, affectedNs.nsInstanceId) &&
				Objects.equals(this.nsdId, affectedNs.nsdId) &&
				Objects.equals(this.changeType, affectedNs.changeType) &&
				Objects.equals(this.changeResult, affectedNs.changeResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsInstanceId, nsdId, changeType, changeResult);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AffectedNs {\n");

		sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
		sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
		sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
		sb.append("    changeResult: ").append(toIndentedString(changeResult)).append("\n");
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
