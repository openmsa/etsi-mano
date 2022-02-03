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
package com.ubiqube.mano.mapping;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.nfvo.config.NfvoOrikaConfiguration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class TaskToGrantTest {

	private final DefaultMapperFactory mapperFactory;
	private final MapperFacade mapper;

	public TaskToGrantTest() {
		final NfvoOrikaConfiguration orikaConfiguration = new NfvoOrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
		mapper = mapperFactory.getMapperFacade();
	}

	@Test
	void testName() throws Exception {
		final ComputeTask task = new ComputeTask();
		setBase(task);
		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setId(UUID.fromString("aa65150e-1e02-42e0-9d74-ce43da1ef82f"));
		task.setVnfCompute(vnfCompute);
		final GrantInformationExt grant = mapper.map(task, GrantInformationExt.class);
		System.out.println("");
		assertNotNull(grant);
	}

	@Test
	void testNetwork() throws Exception {
		final NetworkTask task = new NetworkTask();
		setBase(task);
		final VnfVl vnfVl = new VnfVl();
		vnfVl.setId(UUID.fromString("aa65150e-1e02-42e0-9d74-ce43da1ef82f"));
		task.setVnfVl(vnfVl);
		final GrantInformationExt grant = mapper.map(task, GrantInformationExt.class);
		System.out.println("");
		assertNotNull(grant);
	}

	private static void setBase(final VnfTask task) {
		task.setAlias("alias");
		final Audit audit = new Audit();
		audit.setCreatedOn(OffsetDateTime.now());
		audit.setUpdatedOn(OffsetDateTime.now());
		task.setAudit(audit);
		// task.setBlueprint(_blueprint);
		task.setChangeType(ChangeType.ADDED);
		task.setEndDate(LocalDateTime.now());
		task.setId(UUID.fromString("8ccd579e-f99d-4022-a55c-57e890f05225"));
		task.setResourceGroupId("resourceGroupId");
		task.setStartDate(LocalDateTime.now());
		task.setStatus(PlanStatusType.SUCCESS);
		task.setToscaName("toscaName");
		task.setType(ResourceTypeEnum.COMPUTE);
		task.setVimReservationId("vimReservationId");
		task.setVimResourceId("vimResourceId");
		task.setZoneId("zoneId");
	}
}
