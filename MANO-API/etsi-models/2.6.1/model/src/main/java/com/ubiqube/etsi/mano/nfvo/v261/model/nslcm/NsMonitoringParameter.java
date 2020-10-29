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
package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a monitoring parameter that is tracked by the NFVO, for
 * example, for auto-scaling purposes. It shall comply with the provisions
 * defined in Table 6.5.3.68-1.
 */
@ApiModel(description = "This type represents a monitoring parameter that is tracked by the NFVO, for example,  for auto-scaling purposes. It shall comply with the provisions defined in Table 6.5.3.68-1. ")
@Validated


public class NsMonitoringParameter {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("performanceMetric")
	private String performanceMetric = null;

	public NsMonitoringParameter id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the monitoring parameter defined in the NSD.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the monitoring parameter defined in the NSD. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsMonitoringParameter name(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Human readable name of the monitoring parameter, as defined in the NSD.
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "Human readable name of the monitoring parameter, as defined in the NSD. ")

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public NsMonitoringParameter performanceMetric(final String performanceMetric) {
		this.performanceMetric = performanceMetric;
		return this;
	}

	/**
	 * Performance metric that is monitored. This attribute shall contain the
	 * related \"Measurement Name\" value as defined in clause 7.2 of ETSI GS
	 * NFV-IFA 027.
	 * 
	 * @return performanceMetric
	 **/
	@ApiModelProperty(required = true, value = "Performance metric that is monitored. This attribute shall contain the related  \"Measurement Name\" value as defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
	@NotNull

	public String getPerformanceMetric() {
		return performanceMetric;
	}

	public void setPerformanceMetric(final String performanceMetric) {
		this.performanceMetric = performanceMetric;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsMonitoringParameter nsMonitoringParameter = (NsMonitoringParameter) o;
		return Objects.equals(this.id, nsMonitoringParameter.id) &&
				Objects.equals(this.name, nsMonitoringParameter.name) &&
				Objects.equals(this.performanceMetric, nsMonitoringParameter.performanceMetric);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, performanceMetric);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsMonitoringParameter {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
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
