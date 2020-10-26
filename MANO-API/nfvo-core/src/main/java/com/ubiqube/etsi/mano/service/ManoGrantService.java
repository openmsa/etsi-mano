package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;

import ma.glasnost.orika.MapperFacade;

@Service
public class ManoGrantService implements VimResourceService {

	private final MapperFacade mapper;
	private final Nfvo nfvo;

	public ManoGrantService(final MapperFacade _mapper, final Nfvo _nfvo) {
		mapper = _mapper;
		nfvo = _nfvo;
	}

	@Override
	public void allocate(final Blueprint plan) {
		final GrantsRequest grantRequest = mapper.map(plan, GrantsRequest.class);
		plan.getTasks().forEach(x -> {
			if (x.getChangeType() == ChangeType.ADDED) {
				grantRequest.getAddResources().add(mapper.map(x, GrantInformation.class));
			} else if (x.getChangeType() == ChangeType.REMOVED) {
				grantRequest.getRemoveResources().add(mapper.map(x, GrantInformation.class));
			}
		});
		final GrantResponse grantsResp = nfvo.sendSyncGrantRequest(grantRequest);
		// Merge resources.
		grantsResp.getAddResources().forEach(x -> {
			// Get VNFM Grant Resource information ID.
			final UUID grantUuid = UUID.fromString(x.getResourceDefinitionId());
			final Task task = findTask(plan, grantUuid);
			task.setVimReservationId(x.getReservationId());
			task.setResourceGroupId(x.getResourceGroupId());
			task.setZoneId(x.getZoneId());
		});
		plan.setVimConnections(grantsResp.getVimConnections());
		plan.setZoneGroups(grantsResp.getZoneGroups());
		plan.setZones(grantsResp.getZones());
		plan.getParameters().setExtManagedVirtualLinks(grantsResp.getExtManagedVirtualLinks());
	}

	private static Task findTask(final Blueprint plan, final UUID grantUuid) {
		return plan.getTasks().stream().filter(x -> x.getId() == grantUuid).findFirst().orElseThrow();
	}

}
