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
package com.ubiqube.etsi.mano.service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantsRequest;

@Service
public class GrantService {

	public GrantsRequest createTerminateGrantRequest(final VnfPackage vnfPkg, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		final GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, NsdChangeType.TERMINATE);
		removeGrantResource(grants, lcmOpOccs.getResourceChanges());
		return grants;
	}

	private static void removeGrantResource(final GrantsRequest grants, final VnfLcmResourceChanges resourceChange) {
		removeGrantsObjects(grants, resourceChange.getAffectedVnfcs(), ResourceTypeEnum.COMPUTE);
		removeGrantsObjects(grants, resourceChange.getAffectedVirtualLinks(), ResourceTypeEnum.VL);
		removeGrantsObjects(grants, resourceChange.getAffectedVirtualStorages(), ResourceTypeEnum.STORAGE);
		removeGrantsObjects(grants, resourceChange.getAffectedExtCp(), ResourceTypeEnum.LINKPORT);
	}

	private static GrantsRequest createGrant(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPackage, final NsdChangeType state) {
		final GrantsRequest grants = new GrantsRequest();
		grants.setVnfLcmOpOccs(lcmOpOccs);
		grants.setVnfdId(vnfInstance.getVnfdId());
		grants.setVnfInstance(vnfInstance);
		grants.setFlavourId(vnfPackage.getFlavorId());
		grants.setAutomaticInvocation(false);
		grants.setOperation(state);
		grants.setVnfLcmOpOccs(lcmOpOccs);
		grants.setInstantiationLevelId(lcmOpOccs.getVnfInstantiatedInfo().getInstantiationLevelId());
		return grants;
	}

	private static void removeGrantsObjects(final GrantsRequest grants, final Set<? extends VnfInstantiatedBase> vnfInstantiated, final ResourceTypeEnum resourceType) {
		final Set<GrantInformation> res = mapFilterChangeType(vnfInstantiated, ChangeType.REMOVED, resourceType);
		grants.getRemoveResources().addAll(res);
	}

	private static Set<GrantInformation> mapFilterChangeType(final Set<? extends VnfInstantiatedBase> vnfInstantiatedComputes, final ChangeType changeType, final ResourceTypeEnum resourceType) {
		return vnfInstantiatedComputes.stream()
				.filter(x -> x.getChangeType() == changeType)
				.map(x -> createGrantInformation(x, resourceType, x.getManoResourceId()))
				.collect(Collectors.toSet());
	}

	private static GrantInformation createGrantInformation(final VnfInstantiatedBase x, final ResourceTypeEnum type, final UUID vduId) {
		final GrantInformation grantInformation = new GrantInformation();
		grantInformation.setType(type);
		grantInformation.setVduId(vduId);
		grantInformation.setResourceTemplateId(x.getToscaName());
		return grantInformation;
	}

}
