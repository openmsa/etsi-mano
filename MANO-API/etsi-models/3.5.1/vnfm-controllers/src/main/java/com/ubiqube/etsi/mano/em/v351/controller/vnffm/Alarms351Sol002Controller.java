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
package com.ubiqube.etsi.mano.em.v351.controller.vnffm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.dao.mano.alarm.AckState;
import com.ubiqube.etsi.mano.dao.mano.alarm.PerceivedSeverityType;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.Link;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.Alarm;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.AlarmLinks;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.AlarmModifications;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.PerceivedSeverityRequest;
import com.ubiqube.etsi.mano.vnfm.fc.vnffm.AlarmFrontController;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@ConditionalOnMissingClass("com.ubiqube.etsi.mano.em.v331.controller.vnffm.Alarms331Sol002Api")
@RestController
public class Alarms351Sol002Controller implements Alarms351Sol002Api {

	private final AlarmFrontController alarmFrontController;

	public Alarms351Sol002Controller(final AlarmFrontController alarmFrontController) {
		super();
		this.alarmFrontController = alarmFrontController;
	}

	@Override
	public ResponseEntity<Void> alarmsAlarmIdEscalatePost(final String alarmId, @Valid final PerceivedSeverityRequest perceivedSeverityRequest) {
		return alarmFrontController.escalate(alarmId, PerceivedSeverityType.valueOf(perceivedSeverityRequest.getProposedPerceivedSeverity().toString()));
	}

	@Override
	public ResponseEntity<Alarm> alarmsAlarmIdGet(final String alarmId) {
		return alarmFrontController.findById(alarmId, Alarm.class, Alarms351Sol002Controller::makeLinks);
	}

	@Override
	public ResponseEntity<AlarmModifications> alarmsAlarmIdPatch(final String alarmId, final AlarmModifications alarmModifications, final String ifMatch) {
		return alarmFrontController.patch(alarmId, AckState.valueOf(alarmModifications.getAckState().toString()), ifMatch, AlarmModifications.class);
	}

	@Override
	public ResponseEntity<String> alarmsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return alarmFrontController.search(requestParams, Alarm.class, Alarms351Sol002Controller::makeLinks);
	}

	private static void makeLinks(final Alarm alarm) {
		final AlarmLinks links = new AlarmLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(Alarms351Sol002Api.class).alarmsAlarmIdGet(alarm.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref(linkTo(methodOn(Alarms351Sol002Api.class).alarmsAlarmIdGet(alarm.getId())).withSelfRel().getHref());
		links.setObjectInstance(link);

		alarm.setLinks(links);
	}

}
