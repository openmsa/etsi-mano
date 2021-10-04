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
package com.ubiqube.etsi.mano.dao.mano.v2;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.VnfVl;

@Entity
public class NetworkTask extends VnfTask {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private VnfVl vnfVl;

	private String vimZoneId;

	private String qosPolicy;

	public VnfVl getVnfVl() {
		return vnfVl;
	}

	public void setVnfVl(final VnfVl vnfVl) {
		this.vnfVl = vnfVl;
	}

	public String getVimZoneId() {
		return vimZoneId;
	}

	public void setVimZoneId(final String vimZoneId) {
		this.vimZoneId = vimZoneId;
	}

	public String getQosPolicy() {
		return qosPolicy;
	}

	public void setQosPolicy(final String qosPolicy) {
		this.qosPolicy = qosPolicy;
	}

}
