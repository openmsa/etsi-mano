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
package com.ubiqube.etsi.mano.common.v261.model.lcmgrant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type provides information regarding a resource zone group. A resource
 * zone group is a group of one or more related resource zones which can be used
 * in resource placement constraints. To fulfil such constraint, the NFVO may
 * decide to place a resource into any zone that belongs to a particular group.
 * NOTE: A resource zone group can be used to support overflow from one resource
 * zone into another, in case a particular deployment supports only non-elastic
 * resource zones.
 */
@ApiModel(description = "This type provides information regarding a resource zone group. A resource zone group is a group of one or more related resource zones which can be used in resource placement constraints. To fulfil such constraint, the NFVO may decide to place a resource into any zone that belongs to a particular group. NOTE: A resource zone group can be used to support overflow from one resource zone into another, in case a particular deployment supports only non-elastic resource zones. ")
@Validated
public class ZoneGroupInfo {
	@JsonProperty("zoneId")
	@Valid
	private List<String> zoneId = new ArrayList<>();

	public ZoneGroupInfo zoneId(final List<String> zoneId) {
		this.zoneId = zoneId;
		return this;
	}

	public ZoneGroupInfo addZoneIdItem(final String zoneIdItem) {
		this.zoneId.add(zoneIdItem);
		return this;
	}

	/**
	 * References of identifiers of \"ZoneInfo\" structures, each of which provides
	 * information about a resource zone that belongs to this group.
	 *
	 * @return zoneId
	 **/
	@ApiModelProperty(required = true, value = "References of identifiers of \"ZoneInfo\" structures, each of which provides information about a resource zone that belongs to this group. ")
	@NotNull

	public List<String> getZoneId() {
		return zoneId;
	}

	public void setZoneId(final List<String> zoneId) {
		this.zoneId = zoneId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ZoneGroupInfo zoneGroupInfo = (ZoneGroupInfo) o;
		return Objects.equals(this.zoneId, zoneGroupInfo.zoneId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(zoneId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ZoneGroupInfo {\n");

		sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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
