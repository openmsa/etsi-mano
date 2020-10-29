/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents attribute modifications for an \&quot;Individual
 * alarm\&quot; resource, i.e. modifications to a resource representation based
 * on the \&quot;Alarm\&quot; data type. The attributes of \&quot;Alarm\&quot;
 * that can be modified are included in the \&quot;AlarmModifications\&quot;
 * data type.
 */
@ApiModel(description = "This type represents attribute modifications for an \"Individual alarm\" resource, i.e. modifications to a resource representation based on the \"Alarm\" data type. The attributes of \"Alarm\" that can be modified are included in the \"AlarmModifications\" data type. ")
@Validated


public class AlarmModifications {
	/**
	 * New value of the \"ackState\" attribute in \"Alarm\". Permitted values: *
	 * ACKNOWLEDGED
	 */
	public enum AckStateEnum {
		ACKNOWLEDGED("ACKNOWLEDGED");

		private final String value;

		AckStateEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AckStateEnum fromValue(final String text) {
			for (final AckStateEnum b : AckStateEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("ackState")
	private AckStateEnum ackState = null;

	public AlarmModifications ackState(final AckStateEnum ackState) {
		this.ackState = ackState;
		return this;
	}

	/**
	 * New value of the \"ackState\" attribute in \"Alarm\". Permitted values: *
	 * ACKNOWLEDGED
	 *
	 * @return ackState
	 **/
	@ApiModelProperty(required = true, value = "New value of the \"ackState\" attribute in \"Alarm\". Permitted values: * ACKNOWLEDGED ")
	@NotNull

	public AckStateEnum getAckState() {
		return ackState;
	}

	public void setAckState(final AckStateEnum ackState) {
		this.ackState = ackState;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AlarmModifications alarmModifications = (AlarmModifications) o;
		return Objects.equals(this.ackState, alarmModifications.ackState);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ackState);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AlarmModifications {\n");

		sb.append("    ackState: ").append(toIndentedString(ackState)).append("\n");
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
