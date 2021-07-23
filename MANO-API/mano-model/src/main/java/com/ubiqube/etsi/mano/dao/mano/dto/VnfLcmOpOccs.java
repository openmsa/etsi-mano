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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.OperateChanges;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
public class VnfLcmOpOccs implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private InstantiationStatusType operationState = null;

	private OffsetDateTime stateEnteredTime = null;

	private OffsetDateTime startTime = null;

	private VnfInstance vnfInstance = null;

	private String grantId = null;

	private NsdChangeType operation = null;

	private Boolean isAutomaticInvocation = null;

	private Map<String, String> operationParams;

	private Boolean isCancelPending = null;

	private CancelModeTypeEnum cancelMode = null;

	private FailureDetails error = new FailureDetails();

	private String externalProcessId;

	private VnfInstantiatedInfo vnfInstantiatedInfo = new VnfInstantiatedInfo();

	private VnfLcmResourceChanges resourceChanges = new VnfLcmResourceChanges();

	private OperateChanges operateChanges = new OperateChanges();

	private Set<VnfInstantiatedBase> resourceHandleEntity;

	private VnfScaleInfo vnfScaleInfo = new VnfScaleInfo();

	private Set<ExtVirtualLinkInfoEntity> changedExtConnectivity;

	private VnfInfoModificationsDto changedInfo;

	// 3.3.1
	private ModificationsTriggeredByVnfPkgChangeEntity modificationsTriggeredByVnfPkgChange;
	// 3.3.1
	private String vnfSnapshotInfoId;

	private Audit audit;

}
