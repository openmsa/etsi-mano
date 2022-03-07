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
package com.ubiqube.etsi.mano.em.v281.controller.vnflcm;

import com.ubiqube.etsi.mano.em.v281.model.vnflcm.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.ChangeVnfFlavourRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.HealVnfRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.TerminateVnfRequest;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.VnfInfoModificationRequest;
import com.ubiqube.etsi.mano.vnfm.fc.vnflcm.VnfLcmClassMaping;

/**
 *
 * @author ncuser
 *
 */
public class VnfLcmClassMaping281 implements VnfLcmClassMaping {

	@Override
	public Class<?> getInstantiateVnfRequest() {
		return InstantiateVnfRequest.class;
	}

	@Override
	public Class<?> getScaleVnfRequest() {
		return ScaleVnfRequest.class;
	}

	@Override
	public Class<?> getScaleVnfToLevelRequest() {
		return ScaleVnfToLevelRequest.class;
	}

	@Override
	public Class<?> getChangeVnfFlavourRequest() {
		return ChangeVnfFlavourRequest.class;
	}

	@Override
	public Class<?> getOperateVnfRequest() {
		return OperateVnfRequest.class;
	}

	@Override
	public Class<?> getHealVnfRequest() {
		return HealVnfRequest.class;
	}

	@Override
	public Class<?> getChangeExtVnfConnectivityRequest() {
		return ChangeExtVnfConnectivityRequest.class;
	}

	@Override
	public Class<?> getTerminateVnfRequest() {
		return TerminateVnfRequest.class;
	}

	@Override
	public Class<?> getVnfInfoModificationRequest() {
		return VnfInfoModificationRequest.class;
	}

	@Override
	public Class<?> getCreateVnfSnapshotRequest() {
		return null;
	}

	@Override
	public Class<?> getRevertToVnfSnapshotRequest() {
		return null;
	}

	@Override
	public Class<?> getChangeCurrentVnfPkgRequest() {
		return null;
	}

}
