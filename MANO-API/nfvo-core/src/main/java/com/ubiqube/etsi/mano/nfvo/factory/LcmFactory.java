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
package com.ubiqube.etsi.mano.nfvo.factory;

import java.util.Date;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;

public final class LcmFactory {
	private LcmFactory() {
		// Nothing.
	}

	@Nonnull
	public static NsBlueprint createNsLcmOpOcc(final NsdInstance nsInstance, final PlanOperationType lcmOperationType) {
		final NsBlueprint nsLcmOpOccsNsLcmOpOcc = new NsBlueprint();
		nsLcmOpOccsNsLcmOpOcc.setOperation(lcmOperationType);
		nsLcmOpOccsNsLcmOpOcc.setNsInstance(nsInstance);
		nsLcmOpOccsNsLcmOpOcc.setOperation(lcmOperationType);
		nsLcmOpOccsNsLcmOpOcc.setOperationStatus(OperationStatusType.PROCESSING);
		nsLcmOpOccsNsLcmOpOcc.setStartTime(new Date());
		nsLcmOpOccsNsLcmOpOcc.setStateEnteredTime(new Date());
		return nsLcmOpOccsNsLcmOpOcc;
	}

}
