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
package tosca.datatypes.nfv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.scalar.Time;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import tosca.datatypes.Root;

/**
 * represents information that affect the invocation of the OperateVnf operation
 */
public class VnfOperateOperationConfiguration extends Root {
	/**
	 * Maximum recommended timeout value that can be needed to gracefully stop a VNF
	 * instance of a particular type under certain conditions, such as maximum load
	 * condition. This is provided by VNF provider as information for the operator
	 * facilitating the selection of optimal timeout value. This value is not used
	 * as constraint
	 */
	@Valid
	@JsonProperty("max_recommended_graceful_stop_timeout")
	private Time maxRecommendedGracefulStopTimeout;

	/**
	 * Minimum timeout value for graceful stop of a VNF instance
	 */
	@Valid
	@NotNull
	@JsonProperty("min_graceful_stop_timeout")
	private Time minGracefulStopTimeout;

	public Time getMaxRecommendedGracefulStopTimeout() {
		return this.maxRecommendedGracefulStopTimeout;
	}

	public void setMaxRecommendedGracefulStopTimeout(final Time maxRecommendedGracefulStopTimeout) {
		this.maxRecommendedGracefulStopTimeout = maxRecommendedGracefulStopTimeout;
	}

	@NotNull
	public Time getMinGracefulStopTimeout() {
		return this.minGracefulStopTimeout;
	}

	public void setMinGracefulStopTimeout(@NotNull final Time minGracefulStopTimeout) {
		this.minGracefulStopTimeout = minGracefulStopTimeout;
	}
}
