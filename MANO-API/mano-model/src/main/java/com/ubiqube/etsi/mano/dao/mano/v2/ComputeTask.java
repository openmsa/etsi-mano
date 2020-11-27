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

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;

@Entity
@EntityListeners(AuditListener.class)
public class ComputeTask extends VnfTask {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private VnfCompute vnfCompute;

	private String flavorId;

	private String imageId;

	public VnfCompute getVnfCompute() {
		return vnfCompute;
	}

	public void setVnfCompute(final VnfCompute vnfCompute) {
		this.vnfCompute = vnfCompute;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(final String imageId) {
		this.imageId = imageId;
	}

	@Override
	public void setChangeType(final ChangeType removed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(final PlanStatusType notStarted) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setType(final ResourceTypeEnum compute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setToscaName(final String toscaName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAlias(final String alias) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVimResourceId(final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRemovedVnfLiveInstance(final UUID id) {
		// TODO Auto-generated method stub

	}

}
