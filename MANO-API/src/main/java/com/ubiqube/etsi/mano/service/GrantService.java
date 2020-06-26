package com.ubiqube.etsi.mano.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
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
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.GrantInformationJpa;
import com.ubiqube.etsi.mano.jpa.GrantRequestJpa;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;

import ma.glasnost.orika.MapperFacade;

@Service
public class GrantService {

	private static final Logger LOG = LoggerFactory.getLogger(GrantService.class);

	private final GrantRequestJpa grantRequestJpa;
	private final MapperFacade mapper;
	private final GrantManagement grantManagement;
	private final GrantInformationJpa grantInformationJpa;

	public GrantService(final GrantRequestJpa _grantRequestJpa, final MapperFacade _mapper, final GrantManagement _grantManagement, final GrantInformationJpa _grantInformationJpa) {
		grantRequestJpa = _grantRequestJpa;
		mapper = _mapper;
		grantManagement = _grantManagement;
		grantInformationJpa = _grantInformationJpa;
	}

	public GrantRequest createInstantiateGrantRequest(final VnfPackage vnfPkg, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, NsdChangeType.INSTANTIATE);
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
		grants = grantRequestJpa.save(grants);
		return mapper.map(grants, GrantRequest.class);
	}

	public GrantRequest createTerminateGrantRequest(final VnfPackage vnfPkg, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, NsdChangeType.TERMINATE);
		removeGrantsCompute(grants, lcmOpOccs.getResourceChanges().getAffectedVnfcs());
		removeGrantsVl(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualLinks());
		removeGrantsStorage(grants, lcmOpOccs.getResourceChanges().getAffectedVirtualStorages());
		removeGrantsLinkPorts(grants, lcmOpOccs.getResourceChanges().getAffectedExtCp());
		grants = grantRequestJpa.save(grants);
		return mapper.map(grants, GrantRequest.class);
	}

	private static GrantsRequest createGrant(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPackage, final NsdChangeType state) {
		final GrantsRequest grants = new GrantsRequest();
		grants.setVnfLcmOpOccs(lcmOpOccs);
		grants.setVnfdId(vnfInstance.getVnfdId());
		grants.setFlavourId(vnfPackage.getFlavorId());
		grants.setAutomaticInvocation(false);
		grants.setOperation(state);
		grants.setVnfLcmOpOccs(lcmOpOccs);
		grants.setInstantiationLevelId(lcmOpOccs.getVnfInstantiatedInfo().getInstantiationLevelId());
		return grants;
	}

	public GrantResponse sendAndWaitGrantRequest(final GrantRequest grantRequest) {
		final GrantResponse grants = grantManagement.post(grantRequest);
		return pollGrants(grants);
	}

	private static void addGrantsStorage(final GrantsRequest grants, final Set<VnfInstantiatedStorage> vnfInstantiatedStorages) {
		final Set<GrantInformation> res = vnfInstantiatedStorages.stream()
				.filter(x -> x.getChangeType() == ChangeType.ADDED)
				.map(x -> {
					final GrantInformation grantInformation = new GrantInformation();
					grantInformation.setResourceDefinitionId(x.getId().toString());
					grantInformation.setType(ResourceTypeEnum.STORAGE);
					grantInformation.setVduId(x.getVnfVirtualStorage().getId());
					grantInformation.setResourceTemplateId(x.getToscaName());
					return grantInformation;
				}).collect(Collectors.toSet());
		grants.getAddResources().addAll(res);
	}

	private static void addGrantsVl(final GrantsRequest grants, final Set<VnfInstantiatedVirtualLink> vnfInstantiatedVirtualLinks) {
		final Set<GrantInformation> res = vnfInstantiatedVirtualLinks.stream()
				.filter(x -> x.getChangeType() == ChangeType.ADDED)
				.map(x -> {
					final GrantInformation grantInformation = new GrantInformation();
					grantInformation.setResourceDefinitionId(x.getId().toString());
					grantInformation.setType(ResourceTypeEnum.VL);
					grantInformation.setVduId(x.getVnfVirtualLink().getId());
					grantInformation.setResourceTemplateId(x.getToscaName());
					return grantInformation;
				}).collect(Collectors.toSet());
		grants.getAddResources().addAll(res);
	}

	private static void addGrantsCompute(final GrantsRequest grants, final Set<VnfInstantiatedCompute> vnfInstantiatedComputes) {
		final Set<GrantInformation> res = vnfInstantiatedComputes.stream()
				.filter(x -> x.getChangeType() == ChangeType.ADDED)
				.map(x -> createGrantInformation(x, ResourceTypeEnum.COMPUTE, x.getVnfCompute().getId())).collect(Collectors.toSet());
		grants.getAddResources().addAll(res);
	}

	private static void removeGrantsLinkPorts(final GrantsRequest grants, final Set<VnfInstantiatedExtCp> vnfInstantiatedExtCps) {
		final Set<GrantInformation> res = vnfInstantiatedExtCps.stream()
				.filter(x -> x.getChangeType() == ChangeType.REMOVED)
				.map(x -> createGrantInformation(x, ResourceTypeEnum.LINKPORT, x.getVnfExtCp().getId())).collect(Collectors.toSet());
		grants.getRemoveResources().addAll(res);
	}

	private static void removeGrantsStorage(final GrantsRequest grants, final Set<VnfInstantiatedStorage> vnfInstantiatedStorages) {
		final Set<GrantInformation> res = vnfInstantiatedStorages.stream()
				.filter(x -> x.getChangeType() == ChangeType.REMOVED)
				.map(x -> createGrantInformation(x, ResourceTypeEnum.STORAGE, x.getVnfVirtualStorage().getId())).collect(Collectors.toSet());
		grants.getRemoveResources().addAll(res);
	}

	private static void removeGrantsVl(final GrantsRequest grants, final Set<VnfInstantiatedVirtualLink> vnfInstantiatedVirtualLinks) {
		final Set<GrantInformation> res = vnfInstantiatedVirtualLinks.stream()
				.filter(x -> x.getChangeType() == ChangeType.REMOVED)
				.map(x -> createGrantInformation(x, ResourceTypeEnum.VL, x.getVnfVirtualLink().getId())).collect(Collectors.toSet());
		grants.getRemoveResources().addAll(res);
	}

	private static void removeGrantsCompute(final GrantsRequest grants, final Set<VnfInstantiatedCompute> vnfInstantiatedComputes) {
		final Set<GrantInformation> res = vnfInstantiatedComputes.stream()
				.filter(x -> x.getChangeType() == ChangeType.REMOVED)
				.map(x -> createGrantInformation(x, ResourceTypeEnum.COMPUTE, x.getVnfCompute().getId())).collect(Collectors.toSet());
		grants.getRemoveResources().addAll(res);
	}

	private static GrantInformation createGrantInformation(final VnfInstantiatedBase x, final ResourceTypeEnum type, final UUID vduId) {
		final GrantInformation grantInformation = new GrantInformation();
		grantInformation.setResourceDefinitionId(x.getId().toString());
		grantInformation.setType(type);
		grantInformation.setVduId(vduId);
		grantInformation.setResourceTemplateId(x.getToscaName());
		return grantInformation;
	}

	private GrantResponse pollGrants(final GrantResponse grants) {
		int counter = 50;
		while (counter > 0) {
			final GrantResponse grantOpt = grantManagement.get(grants.getId());
			if (Boolean.TRUE.equals(grantOpt.getAvailable())) {
				return grantOpt;
			}
			LOG.debug("Grant ID {} not ready.", grants.getId());
			counter--;
			try {
				Thread.sleep(5 * 1000L);
			} catch (final InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new GenericException(e);
			}
		}
		throw new GenericException("Unable to get grant ID " + grants.getId());
	}

	public Optional<GrantInformation> getGrantInformation(final UUID grantUuid) {
		return grantInformationJpa.findById(grantUuid);
	}

	public void deleteByLcmOpOccs(final VnfLcmOpOccs lcmOpOccs) {
		grantRequestJpa.deleteByVnfLcmOpOccs(lcmOpOccs);

	}

}
