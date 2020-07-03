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
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
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
		addGrantsCompute(grants, lcmOpOccs.getResourceChanges().getAffectedVnfcs());
		addGrantsVl(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualLinks());
		addGrantsStorage(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualStorages());
		removeGrantsCompute(grants, lcmOpOccs.getResourceChanges().getAffectedVnfcs());
		removeGrantsVl(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualLinks());
		removeGrantsStorage(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualStorages());
		removeGrantsLinkPorts(grants, lcmOpOccs.getResourceChanges().getAffectedExtCp());
		// addGrantsLinkPorts(grants,
		// lcmOpOccs.getResourceChanges().getAffectedExtCp());
		grants.setVimConnections(vnfInstance.getVimConnectionInfo());
		return grantRequestJpa.save(grants);
	}

	public GrantsRequest createTerminateGrantRequest(final VnfPackage vnfPkg, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		final GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, NsdChangeType.TERMINATE);
		removeGrantsCompute(grants, lcmOpOccs.getResourceChanges().getAffectedVnfcs());
		removeGrantsVl(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualLinks());
		removeGrantsStorage(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualStorages());
		removeGrantsLinkPorts(grants, lcmOpOccs.getResourceChanges().getAffectedExtCp());
		return grantRequestJpa.save(grants);
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

	private static void addGrantsStorage(final GrantsRequest grants, final Set<VnfInstantiatedStorage> vnfInstantiatedStorages) {
		final Set<GrantInformation> res = map(vnfInstantiatedStorages, ChangeType.ADDED, ResourceTypeEnum.STORAGE);
		grants.getAddResources().addAll(res);
	}

	private static void addGrantsVl(final GrantsRequest grants, final Set<VnfInstantiatedVirtualLink> vnfInstantiatedVirtualLinks) {
		final Set<GrantInformation> res = map(vnfInstantiatedVirtualLinks, ChangeType.ADDED, ResourceTypeEnum.VL);
		grants.getAddResources().addAll(res);
	}

	private static void addGrantsCompute(final GrantsRequest grants, final Set<VnfInstantiatedCompute> vnfInstantiatedComputes) {
		final Set<GrantInformation> res = map(vnfInstantiatedComputes, ChangeType.ADDED, ResourceTypeEnum.COMPUTE);
		grants.getAddResources().addAll(res);
	}

	private static void removeGrantsLinkPorts(final GrantsRequest grants, final Set<VnfInstantiatedExtCp> vnfInstantiatedExtCps) {
		final Set<GrantInformation> res = map(vnfInstantiatedExtCps, ChangeType.REMOVED, ResourceTypeEnum.LINKPORT);
		grants.getRemoveResources().addAll(res);
	}

	private static void removeGrantsStorage(final GrantsRequest grants, final Set<VnfInstantiatedStorage> vnfInstantiatedStorages) {
		final Set<GrantInformation> res = map(vnfInstantiatedStorages, ChangeType.REMOVED, ResourceTypeEnum.STORAGE);
		grants.getRemoveResources().addAll(res);
	}

	private static void removeGrantsVl(final GrantsRequest grants, final Set<VnfInstantiatedVirtualLink> vnfInstantiatedVirtualLinks) {
		final Set<GrantInformation> res = map(vnfInstantiatedVirtualLinks, ChangeType.REMOVED, ResourceTypeEnum.VL);
		grants.getRemoveResources().addAll(res);
	}

	private static void removeGrantsCompute(final GrantsRequest grants, final Set<VnfInstantiatedCompute> vnfInstantiatedComputes) {
		final Set<GrantInformation> res = map(vnfInstantiatedComputes, ChangeType.REMOVED, ResourceTypeEnum.COMPUTE);
		grants.getRemoveResources().addAll(res);
	}

	private static Set<GrantInformation> map(final Set<? extends VnfInstantiatedBase> vnfInstantiatedComputes, final ChangeType changeType, final ResourceTypeEnum resourceType) {
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
