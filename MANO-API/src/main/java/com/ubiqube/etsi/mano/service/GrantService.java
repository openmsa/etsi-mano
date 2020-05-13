package com.ubiqube.etsi.mano.service;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.GrantRequestJpa;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantedLcmOperationType;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition.TypeEnum;

import ma.glasnost.orika.MapperFacade;

@Service
public class GrantService {

    private static final Logger LOG = LoggerFactory.getLogger(GrantService.class);

    private final GrantRequestJpa grantRequestJpa;
    private final MapperFacade mapper;
    private final GrantManagement grantManagement;

    public GrantService(GrantRequestJpa _grantRequestJpa, MapperFacade _mapper, GrantManagement _grantManagement) {
        grantRequestJpa = _grantRequestJpa;
        mapper = _mapper;
        grantManagement = _grantManagement;
    }

    public GrantRequest createInstantiateGrantRequest(VnfPackage vnfPkg, VnfInstance vnfInstance, VnfLcmOpOccs lcmOpOccs) {
        GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, GrantedLcmOperationType.INSTANTIATE);
        addGrantsCompute(grants, vnfPkg.getVnfCompute());
        addGrantsVl(grants, vnfPkg.getVnfVl());
        addGrantsStorage(grants, vnfPkg.getVnfStorage());
        addGrantsLinkPorts(grants, vnfPkg.getVnfLinkPort());

        grants = grantRequestJpa.save(grants);
        return mapper.map(grants, GrantRequest.class);
    }

    public GrantRequest createTerminateGrantRequest(VnfPackage vnfPkg, VnfInstance vnfInstance, VnfLcmOpOccs lcmOpOccs) {
        GrantsRequest grants = createGrant(vnfInstance, lcmOpOccs, vnfPkg, GrantedLcmOperationType.TERMINATE);
        removeGrantsCompute(grants, vnfPkg.getVnfCompute());
        removeGrantsVl(grants, vnfPkg.getVnfVl());
        removeGrantsStorage(grants, vnfPkg.getVnfStorage());
        removeGrantsLinkPorts(grants, vnfPkg.getVnfLinkPort());
        grants = grantRequestJpa.save(grants);
        return mapper.map(grants, GrantRequest.class);
    }

    private static GrantsRequest createGrant(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPackage, final GrantedLcmOperationType state) {
        final GrantsRequest grants = new GrantsRequest();
        grants.setVnfInstance(vnfInstance);
        grants.setVnfLcmOpOccs(lcmOpOccs);
        grants.setVnfdId(vnfInstance.getVnfdId());
        grants.setFlavourId(vnfPackage.getFlavorId());
        grants.setAutomaticInvocation(false);
        grants.setOperation(state.toString());
        /// XXX: Have a closer look on lcm_operations_configuration or vnf_profile.
        grants.setInstantiationLevelId("0");
        grants.setVnfInstance(vnfInstance);
        grants.setVnfLcmOpOccs(lcmOpOccs);
        // grants.setInstantiationLevelId(vnfInstance.getI);
        return grants;
    }

    public GrantResponse sendAndWaitGrantRequest(final GrantRequest grantRequest) {
        final GrantResponse grants = grantManagement.post(grantRequest);
        return pollGrants(grants);
    }

    private static void addGrantsLinkPorts(final GrantsRequest grants, final Set<VnfLinkPort> vnfLinkPort) {
        final Set<GrantInformation> res = vnfLinkPort.stream().map(mapEntityGrantInformation(TypeEnum.LINKPORT)).collect(Collectors.toSet());
        grants.getAddResources().addAll(res);
    }

    private static void addGrantsStorage(final GrantsRequest grants, final Set<VnfStorage> vnfStorage) {
        final Set<GrantInformation> res = vnfStorage.stream().map(mapEntityGrantInformation(TypeEnum.STORAGE)).collect(Collectors.toSet());
        grants.getAddResources().addAll(res);
    }

    private static void addGrantsVl(final GrantsRequest grants, final Set<VnfVl> vnfVl) {
        final Set<GrantInformation> res = vnfVl.stream().map(mapEntityGrantInformation(TypeEnum.VL)).collect(Collectors.toSet());
        grants.getAddResources().addAll(res);
    }

    private static void addGrantsCompute(final GrantsRequest grants, final Set<VnfCompute> vnfCompute) {
        final Set<GrantInformation> res = vnfCompute.stream().map(mapEntityGrantInformation(TypeEnum.COMPUTE)).collect(Collectors.toSet());
        grants.getAddResources().addAll(res);
    }

    private static void removeGrantsLinkPorts(final GrantsRequest grants, final Set<VnfLinkPort> vnfLinkPort) {
        final Set<GrantInformation> res = vnfLinkPort.stream().map(mapEntityGrantInformation(TypeEnum.LINKPORT)).collect(Collectors.toSet());
        grants.getRemoveResources().addAll(res);
    }

    private static void removeGrantsStorage(final GrantsRequest grants, final Set<VnfStorage> vnfStorage) {
        final Set<GrantInformation> res = vnfStorage.stream().map(mapEntityGrantInformation(TypeEnum.STORAGE)).collect(Collectors.toSet());
        grants.getRemoveResources().addAll(res);
    }

    private static void removeGrantsVl(final GrantsRequest grants, final Set<VnfVl> vnfVl) {
        final Set<GrantInformation> res = vnfVl.stream().map(mapEntityGrantInformation(TypeEnum.VL)).collect(Collectors.toSet());
        grants.getRemoveResources().addAll(res);
    }

    private static void removeGrantsCompute(final GrantsRequest grants, final Set<VnfCompute> vnfCompute) {
        final Set<GrantInformation> res = vnfCompute.stream().map(mapEntityGrantInformation(TypeEnum.COMPUTE)).collect(Collectors.toSet());
        grants.getRemoveResources().addAll(res);
    }

    private static Function<BaseEntity, GrantInformation> mapEntityGrantInformation(final TypeEnum type) {
        return entity -> {
            final GrantInformation gi = new GrantInformation();
            gi.setVduId(entity.getId());
            gi.setType(type);
            return gi;
        };
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

}
