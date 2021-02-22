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
package com.ubiqube.etsi.mano.vnfm.v261.controller.faultmngt;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.alarm.AckState;
import com.ubiqube.etsi.mano.dao.mano.alarm.Alarms;
import com.ubiqube.etsi.mano.dao.mano.alarm.PerceivedSeverityType;
import com.ubiqube.etsi.mano.service.AlarmVnfmController;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.Alarm;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.AlarmLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.AlarmModifications;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.PerceivedSeverityRequest;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Controller
public class Alarms261Sol002ApiController implements Alarms261Sol002Api {
	private static final String ALARM_SEARCH_DEFAULT_EXCLUDE_FIELDS = null;

	private static final Set<String> ALARM_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "managedObjectId", "rootCauseFaultyResource", "alarmRaisedTime", "ackState",
			"perceivedSeverity", "eventTime", "eventType", "probableCause", "isRootCause", "_links.self.href"));

	private final MapperFacade mapper;

	private final AlarmVnfmController alarmVnfmController;

	public Alarms261Sol002ApiController(final MapperFacade mapper, final AlarmVnfmController alarmVnfmController) {
		super();
		this.mapper = mapper;
		this.alarmVnfmController = alarmVnfmController;
	}

	@Override
	public ResponseEntity<Void> alarmsAlarmIdEscalatePost(final String alarmId, @Valid final PerceivedSeverityRequest perceivedSeverityRequest) {
		alarmVnfmController.escalate(UUID.fromString(alarmId), PerceivedSeverityType.valueOf(perceivedSeverityRequest.getProposedPerceivedSeverity().toString()));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Alarm> alarmsAlarmIdGet(final String alarmId) {
		final Alarms alarm = alarmVnfmController.findById(UUID.fromString(alarmId));
		final Alarm ret = mapper.map(alarm, Alarm.class);
		makeLinks(ret);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<AlarmModifications> alarmsAlarmIdPatch(final String alarmId, final AlarmModifications alarmModifications) {
		final Alarms alarm = alarmVnfmController.modify(UUID.fromString(alarmId), AckState.valueOf(alarmModifications.getAckState().toString()));
		return ResponseEntity.ok(mapper.map(alarm, AlarmModifications.class));
	}

	@Override
	public ResponseEntity<String> alarmsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		final Consumer<Alarm> setLink = Alarms261Sol002ApiController::makeLinks;
		return alarmVnfmController.search(requestParams, Alarm.class, ALARM_SEARCH_DEFAULT_EXCLUDE_FIELDS, ALARM_SEARCH_MANDATORY_FIELDS, setLink);
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
