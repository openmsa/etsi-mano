package com.ubiqube.etsi.mano.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.jpa.GrantInformationJpa;
import com.ubiqube.etsi.mano.jpa.GrantRequestJpa;

@Service
public class GrantService {
	private final GrantRequestJpa grantRequestJpa;

	private final GrantInformationJpa grantInformationJpa;

	public GrantService(final GrantRequestJpa _grantRequestJpa, final GrantInformationJpa _grantInformationJpa) {
		grantRequestJpa = _grantRequestJpa;
		grantInformationJpa = _grantInformationJpa;
	}

	public GrantsRequest createInstantiateGrantRequest(final VnfPackage vnfPkg, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		final GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, NsdChangeType.INSTANTIATE);
		addGrantsObjects(grants, lcmOpOccs.getResourceChanges().getAffectedVnfcs(), ResourceTypeEnum.COMPUTE);
		addGrantsObjects(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualLinks(), ResourceTypeEnum.VL);
		addGrantsObjects(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualStorages(), ResourceTypeEnum.STORAGE);
		removeGrantResource(grants, lcmOpOccs.getResourceChanges());
		grants.setVimConnections(vnfInstance.getVimConnectionInfo());
		return grantRequestJpa.save(grants);
	}

	public GrantsRequest createTerminateGrantRequest(final VnfPackage vnfPkg, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		final GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, NsdChangeType.TERMINATE);
		removeGrantResource(grants, lcmOpOccs.getResourceChanges());
		return grantRequestJpa.save(grants);
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

	private static void addGrantsObjects(final GrantsRequest grants, final Set<? extends VnfInstantiatedBase> vnfInstantiated, final ResourceTypeEnum resourceType) {
		final Set<GrantInformation> res = mapFilterChangeType(vnfInstantiated, ChangeType.ADDED, resourceType);
		grants.getAddResources().addAll(res);
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
		grantInformation.setResourceDefinitionId(x.getId().toString());
		grantInformation.setType(type);
		grantInformation.setVduId(vduId);
		grantInformation.setResourceTemplateId(x.getToscaName());
		return grantInformation;
	}

	public Optional<GrantInformation> getGrantInformation(final UUID grantUuid) {
		return grantInformationJpa.findById(grantUuid);
	}

	public void deleteByLcmOpOccs(final VnfLcmOpOccs lcmOpOccs) {
		grantRequestJpa.deleteByVnfLcmOpOccs(lcmOpOccs);

	}

}
