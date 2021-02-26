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

package com.ubiqube.etsi.mano.vnfm.v261.controller.vnffm.sol003;

import static com.ubiqube.etsi.mano.Constants.ALARM_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.ALARM_SEARCH_MANDATORY_FIELDS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.alarm.AckState;
import com.ubiqube.etsi.mano.dao.mano.alarm.Alarms;
import com.ubiqube.etsi.mano.service.AlarmVnfmController;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnffm.sol002.Alarms261Sol002Api;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.Alarm;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.AlarmLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.AlarmModifications;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class FaultAlarmsSol003Api implements FaultAlarmsSol003 {
	private final MapperFacade mapper;

	private final AlarmVnfmController alarmVnfmController;

	public FaultAlarmsSol003Api(final MapperFacade mapper, final AlarmVnfmController alarmVnfmController) {
		super();
		this.mapper = mapper;
		this.alarmVnfmController = alarmVnfmController;
	}

	@Override
	public ResponseEntity<Alarm> alarmsAlarmIdGet(final String alarmId) {
		final Alarms alarm = alarmVnfmController.findById(UUID.fromString(alarmId));
		final Alarm ret = mapper.map(alarm, Alarm.class);
		makeLinks(ret);
		return ResponseEntity.ok().eTag("" + alarm.getVersion()).body(ret);
	}

	@Override
	public ResponseEntity<AlarmModifications> alarmsAlarmIdPatch(final String alarmId, final AlarmModifications alarmModifications, final String ifMatch) {
		final Alarms alarm = alarmVnfmController.modify(UUID.fromString(alarmId), AckState.valueOf(alarmModifications.getAckState().toString()), ifMatch);
		return ResponseEntity.ok(mapper.map(alarm, AlarmModifications.class));
	}

	@Override
	public ResponseEntity<String> alarmsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		return alarmVnfmController.search(requestParams, Alarm.class, ALARM_SEARCH_DEFAULT_EXCLUDE_FIELDS, ALARM_SEARCH_MANDATORY_FIELDS, FaultAlarmsSol003Api::makeLinks);
	}

	private static void makeLinks(final Alarm alarm) {
		final AlarmLinks links = new AlarmLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(Alarms261Sol002Api.class).alarmsAlarmIdGet(alarm.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref(linkTo(methodOn(Alarms261Sol002Api.class).alarmsAlarmIdGet(alarm.getId())).withSelfRel().getHref());
		links.setObjectInstance(link);

		alarm.setLinks(links);
	}

}
