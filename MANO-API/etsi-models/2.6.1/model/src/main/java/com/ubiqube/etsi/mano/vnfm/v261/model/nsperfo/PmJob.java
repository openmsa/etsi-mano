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

package com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo;

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
 * This type represents a PM job.
 */
@ApiModel(description = "This type represents a PM job. ")
@Validated

public class PmJob {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("objectInstanceIds")
	@Valid
	private List<String> objectInstanceIds = new ArrayList<>();

	@JsonProperty("criteria")
	private PmJobCriteria criteria = null;

	@JsonProperty("reports")
	private List<PmJobReports> reports = null;

	@JsonProperty("_links")
	private PmJobLinks links = null;

	public PmJob id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this PM job.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this PM job. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public PmJob objectInstanceIds(final List<String> objectInstanceIds) {
		this.objectInstanceIds = objectInstanceIds;
		return this;
	}

	public PmJob addObjectInstanceIdsItem(final String objectInstanceIdsItem) {
		this.objectInstanceIds.add(objectInstanceIdsItem);
		return this;
	}

	/**
	 * Identifiers of the VNF instances for which performance information is collected.
	 *
	 * @return objectInstanceIds
	 **/
	@ApiModelProperty(required = true, value = "Identifiers of the VNF instances for which performance information is collected. ")
	@NotNull

	public List<String> getObjectInstanceIds() {
		return objectInstanceIds;
	}

	public void setObjectInstanceIds(final List<String> objectInstanceIds) {
		this.objectInstanceIds = objectInstanceIds;
	}

	public PmJob criteria(final PmJobCriteria criteria) {
		this.criteria = criteria;
		return this;
	}

	/**
	 * Criteria of the collection of performance information.
	 *
	 * @return criteria
	 **/
	@ApiModelProperty(required = true, value = "Criteria of the collection of performance information. ")
	@NotNull

	@Valid

	public PmJobCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(final PmJobCriteria criteria) {
		this.criteria = criteria;
	}

	public PmJob reports(final List<PmJobReports> reports) {
		this.reports = reports;
		return this;
	}

	/**
	 * Get reports
	 *
	 * @return reports
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<PmJobReports> getReports() {
		return reports;
	}

	public void setReports(final List<PmJobReports> reports) {
		this.reports = reports;
	}

	public PmJob links(final PmJobLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public PmJobLinks getLinks() {
		return links;
	}

	public void setLinks(final PmJobLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PmJob pmJob = (PmJob) o;
		return Objects.equals(this.id, pmJob.id) &&
				Objects.equals(this.objectInstanceIds, pmJob.objectInstanceIds) &&
				Objects.equals(this.criteria, pmJob.criteria) &&
				Objects.equals(this.reports, pmJob.reports) &&
				Objects.equals(this.links, pmJob.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, objectInstanceIds, criteria, reports, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PmJob {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
		sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
		sb.append("    reports: ").append(toIndentedString(reports)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
