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
package com.ubiqube.etsi.mano.model;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;

public class VnfOperateRequest {

	private OperationalStateType changeStateTo = null;

	private Integer gracefulStopTimeout = null;

	private Map<String, String> additionalParams = null;

	public OperationalStateType getChangeStateTo() {
		return changeStateTo;
	}

	public void setChangeStateTo(final OperationalStateType changeStateTo) {
		this.changeStateTo = changeStateTo;
	}

	public Integer getGracefulStopTimeout() {
		return gracefulStopTimeout;
	}

	public void setGracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
	}

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

}
