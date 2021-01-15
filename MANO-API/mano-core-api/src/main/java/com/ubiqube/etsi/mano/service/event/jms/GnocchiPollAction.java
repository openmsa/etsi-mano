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
package com.ubiqube.etsi.mano.service.event.jms;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class GnocchiPollAction {

	private VimConnectionInformation vimConnectionInformation;
	private String vnfInstanceId;
	private String metricId;

	public GnocchiPollAction(final VimConnectionInformation _vimConnectionInformation, final String _vnfInstanceId, final String _metricId) {
		vimConnectionInformation = _vimConnectionInformation;
		vnfInstanceId = _vnfInstanceId;
		metricId = _metricId;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public String getMetricId() {
		return metricId;
	}

	public void setMetricId(final String metricId) {
		this.metricId = metricId;
	}

}
