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
package com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a threshold.
 */
@ApiModel(description = "This type represents a threshold. ")
@Validated


public class Threshold {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("objectInstanceId")
	private String objectInstanceId = null;

	@JsonProperty("criteria")
	private ThresholdCriteria criteria = null;

	@JsonProperty("_links")
	private ThresholdLinks links = null;

	public Threshold id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this threshold resource.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this threshold resource. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Threshold objectInstanceId(final String objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
		return this;
	}

	/**
	 * Identifier of the VNF instance associated with the threshold.
	 * 
	 * @return objectInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF instance associated with the threshold. ")
	@NotNull

	public String getObjectInstanceId() {
		return objectInstanceId;
	}

	public void setObjectInstanceId(final String objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
	}

	public Threshold criteria(final ThresholdCriteria criteria) {
		this.criteria = criteria;
		return this;
	}

	/**
	 * Criteria that define this threshold.
	 * 
	 * @return criteria
	 **/
	@ApiModelProperty(required = true, value = "Criteria that define this threshold. ")
	@NotNull

	@Valid

	public ThresholdCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(final ThresholdCriteria criteria) {
		this.criteria = criteria;
	}

	public Threshold links(final ThresholdLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 * 
	 * @return links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public ThresholdLinks getLinks() {
		return links;
	}

	public void setLinks(final ThresholdLinks links) {
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
		final Threshold threshold = (Threshold) o;
		return Objects.equals(this.id, threshold.id) &&
				Objects.equals(this.objectInstanceId, threshold.objectInstanceId) &&
				Objects.equals(this.criteria, threshold.criteria) &&
				Objects.equals(this.links, threshold.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, objectInstanceId, criteria, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Threshold {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
		sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
